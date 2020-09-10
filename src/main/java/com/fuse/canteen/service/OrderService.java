package com.fuse.canteen.service;

import com.fuse.canteen.dto.OrderDto;
import com.fuse.canteen.dto.OrderStatusPojo;
import com.fuse.canteen.entity.Order;

public interface OrderService {
    OrderDto edit(OrderDto orderDto) throws Exception;

    OrderDto create(OrderDto orderDto) throws Exception;

    Object getById(Long orderId) throws Exception;

    Object fetchAll();

    void delete(Long orderId) throws Exception;

    Order getOrder(OrderDto orderDto) throws Exception;

    OrderDto getOrderDto(Order order);

    Object fetchPopular();

    Object fetchAllHistoryByUser();

    void updateStatus(OrderStatusPojo orderStatusPojo) throws Exception;

    Object fetchAllOrdersByTime();
}
