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
import com.example.ecommercespringboot.validator.CustomerValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    CustomerDetailsService customerUserDetailsService;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    JwtFilter jwtFilter;

    private final PasswordEncoder passwordEncoder;

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

                    return new ResponseEntity<String>("{\"token\":\""+jwtUtil.generateToken(customerUserDetailsService.getUserDetail().getEmail(),
                            customerUserDetailsService.getUserDetail().getRole())+"\"}", HttpStatus.OK);
            }

        } catch (Exception ex) {
            log.error("{}", ex);
        }
        return new ResponseEntity<String>("{\"message\":\"" + "Bad Credentials"+"\"}",HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<List<Customer>> getAllCustomer() {
        return null;
    }

    public boolean isEmailExists(String email) {
        return customerRepository.existsByEmail(email);
    }
}
