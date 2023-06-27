package com.example.ecommercespringboot.service.serviceImpl;

import com.example.ecommercespringboot.dto.*;
import com.example.ecommercespringboot.exception.EntityNotFoundException;
import com.example.ecommercespringboot.exception.ErrorCodes;
import com.example.ecommercespringboot.exception.InvalidEntityException;
import com.example.ecommercespringboot.jwt.JwtFilter;
import com.example.ecommercespringboot.models.*;
import com.example.ecommercespringboot.repository.*;
import com.example.ecommercespringboot.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    private JwtFilter jwtFilter;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartProductRepository cartProductRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;

    @Override
    public ResponseEntity<?> placeOrder(OrderDto orderDto) {

        String userName = jwtFilter.getCurrentUser();
        log.info(userName);
        Customer customer = customerRepository.findByEmailId(userName);
        Cart cart = customer.getCart();
        Order order = new Order();
        order.setCode(UUID.randomUUID().toString());
        order.setOrderDate(LocalDateTime.now());
        order.setCart(cart);
        order.setCustomer(customer);
        order.setAddress(customer.getAddress());
        order.setOrderStatus(orderDto.getOrderStatus());

        // Save the Order entity to the repository
       Order savedOrder = orderRepository.save(order);

        List<Cart_product> cartProducts = cart.getCartProducts();
        for (Cart_product cartProduct : cartProducts) {
            // Create a new OrderProduct entity
            Order_product orderProduct = new Order_product();
            orderProduct.setOrder(order);
            orderProduct.setProduct(cartProduct.getProduct());
            orderProduct.setQuantity(cartProduct.getQuantity());
            orderProduct.setTotal_cost(cartProduct.getTotal_cost());

            // Save the OrderProduct entity to the repository
            orderProductRepository.save(orderProduct);
            // Delete the Cart_product entity
            cartProductRepository.delete(cartProduct);
        }

        // Clear the cartProducts list in the cart
        cartProducts.clear();
        // Update the cart in the database
        cartRepository.save(cart);
        return ResponseEntity.ok("the order placed successfully");
    }

    @Override
    public OrderDto getOrderByCustomerId(Integer customerId) {
        if(customerId == null) {
            log.error("customer is null");
            return null;
        }
        Optional<Order> order = orderRepository.findByCustomerId(customerId);
        return Optional.of(OrderDto.fromEntity(order.get()))
                .orElseThrow(() -> new EntityNotFoundException(
                        "No Customer with this ID"+ customerId + " found in DB"
                        , ErrorCodes.CUSTOMER_CART_NOT_FOUND)
                );
    }
}
