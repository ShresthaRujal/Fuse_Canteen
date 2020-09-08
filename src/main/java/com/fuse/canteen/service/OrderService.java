package com.fuse.canteen.service;

import com.fuse.canteen.dto.OrderDto;
import com.fuse.canteen.entity.Order;

public interface OrderService {
    void edit(OrderDto orderDto) throws Exception;

    void create(OrderDto orderDto);

    Object getById(Long orderId) throws Exception;

    Object fetchAll();

    void delete(Long orderId) throws Exception;

    Order getOrder(OrderDto orderDto);

    OrderDto getOrderDto(Order order);

    Object fetchPopular();

}
