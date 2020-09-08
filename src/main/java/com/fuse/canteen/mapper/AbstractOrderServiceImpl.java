package com.fuse.canteen.mapper;

import com.fuse.canteen.dto.OrderDto;
import com.fuse.canteen.entity.Order;
import com.fuse.canteen.service.ItemService;
import com.fuse.canteen.service.OrderService;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class AbstractOrderServiceImpl implements OrderService {

    private final ItemService itemService;

    @Override
    public Order getOrder(OrderDto orderDto) {
        return Order.builder()
                .items(orderDto.getItemIds().stream().map(dto -> itemService.(dto)).collect(Collectors.toList()))
                .build();
    }

    public OrderDto getOrderDto(Order order){
        return OrderDto.builder()
                .items(order.getItems().stream().map(item -> itemService.getItemDto(item)).collect(Collectors.toList()))
                .build();
    }
}
