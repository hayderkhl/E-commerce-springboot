package com.example.ecommercespringboot.service.serviceImpl;

import com.example.ecommercespringboot.dto.CartDto;
import com.example.ecommercespringboot.dto.ProductDto;
import com.example.ecommercespringboot.jwt.JwtFilter;
import com.example.ecommercespringboot.models.Cart;
import com.example.ecommercespringboot.models.Customer;
import com.example.ecommercespringboot.models.Product;
import com.example.ecommercespringboot.repository.CartRepository;
import com.example.ecommercespringboot.repository.CustomerRepository;
import com.example.ecommercespringboot.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ResponseEntity<?> addToCart(CartDto cartDto) {
        String userName = jwtFilter.getCurrentUser();
        log.info(userName);
        Customer customer = customerRepository.findByEmailId(userName);

        Cart cart = customer.getCart();
        if (cart == null)
        {
            cart = new Cart();
            cart.setCustomer(customer);
            cartRepository.save(cart);
        }
        cart.setTotal_cost(cartDto.getTotal_cost());
        cart.setProducts(ProductDto.fromEntitySet(cartDto.getProducts()));

        // Save the updated cart
        cartRepository.save(cart);

        return ResponseEntity.ok("Cart updated successfully");

    }
}
