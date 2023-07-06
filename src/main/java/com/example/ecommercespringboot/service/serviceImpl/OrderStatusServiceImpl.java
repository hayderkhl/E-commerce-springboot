package com.example.ecommercespringboot.service.serviceImpl;

import com.example.ecommercespringboot.dto.OrderStatusDto;
import com.example.ecommercespringboot.exception.ErrorCodes;
import com.example.ecommercespringboot.exception.InvalidEntityException;
import com.example.ecommercespringboot.exception.UnauthorizedException;
import com.example.ecommercespringboot.jwt.JwtFilter;
import com.example.ecommercespringboot.models.OrderStatus;
import com.example.ecommercespringboot.repository.OrderStatusRepository;
import com.example.ecommercespringboot.service.OrderStatusService;
import com.example.ecommercespringboot.validator.OrderStatusValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class OrderStatusServiceImpl implements OrderStatusService {

    @Autowired
    OrderStatusRepository orderStatusRepository;
    @Autowired
    JwtFilter jwtFilter;

    @Override
    public OrderStatusDto create(OrderStatusDto dto) {

            if (jwtFilter.isAdmin()) {
                List<String> errors = OrderStatusValidator.validate(dto);
                if (!errors.isEmpty()) {
                    log.error("order status is not valid: {}", dto);
                    throw new InvalidEntityException("This order status is not valid", ErrorCodes.ORDER_STATUS_NOT_VALID);
                }

                OrderStatus savedOrderStatus = orderStatusRepository.save(OrderStatusDto.toEntity(dto));
                return OrderStatusDto.fromEntity(savedOrderStatus);
            }

        log.error("you're not an admin", dto);
        throw new UnauthorizedException("you're not authorized", ErrorCodes.NOT_AUTHORIZED);
            }
}
