package com.example.ecommercespringboot.service.serviceImpl;

import com.example.ecommercespringboot.dto.CartDto;
import com.example.ecommercespringboot.dto.Cart_ProductDto;
import com.example.ecommercespringboot.dto.CustomerDto;
import com.example.ecommercespringboot.dto.ProductDto;
import com.example.ecommercespringboot.exception.EntityNotFoundException;
import com.example.ecommercespringboot.exception.ErrorCodes;
import com.example.ecommercespringboot.exception.InvalidEntityException;
import com.example.ecommercespringboot.jwt.JwtFilter;
import com.example.ecommercespringboot.models.Cart;
import com.example.ecommercespringboot.models.Cart_product;
import com.example.ecommercespringboot.models.Customer;
import com.example.ecommercespringboot.models.Product;
import com.example.ecommercespringboot.repository.CartProductRepository;
import com.example.ecommercespringboot.repository.CartRepository;
import com.example.ecommercespringboot.repository.CustomerRepository;
import com.example.ecommercespringboot.repository.ProductRepository;
import com.example.ecommercespringboot.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
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
    @Autowired
    private CartProductRepository cartProductRepository;
    @Autowired
    private ProductRepository productRepository;

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

        Cart savedCart = cartRepository.save(cart);

        if (cartDto.getCartProducts() != null)
        {

            cartDto.getCartProducts().forEach(product ->{
                Cart_product cartProduct = Cart_ProductDto.toEntity(product);
                cartProduct.setCart(savedCart);

                Optional<Product> product1 = productRepository.findById(cartProduct.getProduct().getId());
                if (!product1.isEmpty())
                {
                    Product productEntity = product1.get();
                    BigDecimal price = productEntity.getPrice();
                    BigDecimal totalPrice = BigDecimal.valueOf(cartProduct.getQuantity().intValue() * price.intValue());
                    cartProduct.setTotal_cost(totalPrice);
                    cartProductRepository.save(cartProduct);
                } else
                    throw new InvalidEntityException("One of the product does not exist", ErrorCodes.PRODUCT_NOT_FOUND);
            });

        }

        return ResponseEntity.ok("Cart updated successfully");

    }

    @Override
    public CartDto getCartByCustomerId(Integer customerId) {
        if(customerId == null) {
            log.error("customer is null");
            return null;
        }
        Optional<Cart> cart = cartRepository.findByCustomerId(customerId);
        return Optional.of(CartDto.fromEntity(cart.get()))
                .orElseThrow(() -> new EntityNotFoundException(
                        "No Customer with this ID"+ customerId + " found in DB"
                        , ErrorCodes.CUSTOMER_CART_NOT_FOUND)
                );
    }
}
