package com.example.ecommercespringboot.jwt;

import com.example.ecommercespringboot.models.Customer;
import com.example.ecommercespringboot.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
@Service
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    CustomerRepository customerRepository;
    private Customer userDetail;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("inside loadUserByUsername {}", username);
        userDetail = customerRepository.findByEmailId(username);
        if (!Objects.isNull(username)) {
            return new User(userDetail.getEmail(),userDetail.getPassword(), new ArrayList<>());
        } else
            throw new UsernameNotFoundException("User not found");
    }

    public Customer getUserDetail() {
        return userDetail;
    }

}
