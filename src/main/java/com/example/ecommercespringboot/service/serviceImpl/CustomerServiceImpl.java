package com.example.ecommercespringboot.service.serviceImpl;

import com.example.ecommercespringboot.dto.CustomerDto;
import com.example.ecommercespringboot.exception.ErrorCodes;
import com.example.ecommercespringboot.exception.InvalidEntityException;
import com.example.ecommercespringboot.jwt.CustomerDetailsService;
import com.example.ecommercespringboot.jwt.JwtFilter;
import com.example.ecommercespringboot.jwt.JwtUtil;
import com.example.ecommercespringboot.models.Customer;
import com.example.ecommercespringboot.repository.CustomerRepository;
import com.example.ecommercespringboot.service.CustomerService;
import com.example.ecommercespringboot.token.Token;
import com.example.ecommercespringboot.token.TokenRepository;
import com.example.ecommercespringboot.token.TokenType;
import com.example.ecommercespringboot.validator.CustomerValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private PasswordEncoder passwordEncoder;

    private CustomerRepository customerRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    CustomerDetailsService customerUserDetailsService;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    JwtFilter jwtFilter;
    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public CustomerDto signUp(CustomerDto dto) {
        List<String> errors = CustomerValidator.validate(dto);
        if (!errors.isEmpty())
        {
            log.error("customer is not valid", dto);
            throw new InvalidEntityException("this customer is not valid", ErrorCodes.CUSTOMER_NOT_VALID);
        }

        if(isEmailExists(dto.getEmail()) == true)
        {
            log.error("email already exist", dto);
            throw new InvalidEntityException("email already exist", ErrorCodes.EMAIL_ALREADY_EXIST);
        }

        dto.setRole("user");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(encodedPassword);


        return CustomerDto.fromEntity(customerRepository.save(
                CustomerDto.toEntity(dto)
        ));
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Inside login");
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password"))
            );

            if(auth.isAuthenticated()) {

                String jwttoken = jwtUtil.generateToken(customerUserDetailsService.getUserDetail().getEmail(),
                        customerUserDetailsService.getUserDetail().getRole());

                Customer customer = customerRepository.findByEmailId(requestMap.get("email"));
                revokeAllUserTokens(customer);

                var token = Token.builder()
                        .customer(customer)
                        .token(jwttoken)
                        .tokenType(TokenType.BEARER)
                        .revoked(false)
                        .expired(false)
                        .build();

                tokenRepository.save(token);

                return new ResponseEntity<String>("{\"token\":\""+jwttoken+"\"}", HttpStatus.OK);
            }

        } catch (Exception ex) {
            log.error("{}", ex);
        }
        return new ResponseEntity<String>("{\"message\":\"" + "Bad Credentials"+"\"}",HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<String> logOut() {
        return null;
    }

    @Override
    public ResponseEntity<List<Customer>> getAllCustomer() {
        return null;
    }

    public boolean isEmailExists(String email) {
        return customerRepository.existsByEmail(email);
    }

    private void revokeAllUserTokens(Customer customer)
    {
        //we need only one token valid we gonna make the others expired and revoked
        var validCustomerTokens = tokenRepository.findAllValidTokenByCustomer(customer.getId());
        if (validCustomerTokens.isEmpty())
            return;

        validCustomerTokens.forEach(t -> {
            t.setRevoked(true);
            t.setExpired(true);
        });
        tokenRepository.saveAll(validCustomerTokens);
    }
}
