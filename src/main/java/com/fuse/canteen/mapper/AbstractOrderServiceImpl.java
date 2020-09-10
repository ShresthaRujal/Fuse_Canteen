package com.fuse.canteen.mapper;

import com.fuse.canteen.dto.OrderDto;
import com.fuse.canteen.entity.Item;
import com.fuse.canteen.entity.Order;
import com.fuse.canteen.service.ItemService;
import com.fuse.canteen.service.OrderService;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class AbstractOrderServiceImpl implements OrderService {

    private final ItemService itemService;

    @Override
    public Order getOrder(OrderDto orderDto) throws Exception {
        List<Item> itemList = itemService.getItemInIds(orderDto.getItemIds());
        Order order = Order.builder()
                .items(itemList)
                .build();
        BigDecimal price = BigDecimal.ZERO;
        for (Item itm : itemList){
            price = price.add(itm.getPrice());
        }
        order.setPrice(price);
        order.setScheduleAt(orderDto.getScheduledAt());
        if (orderDto.getId() != null){
            return order;
        }
        order.getItems().forEach(item -> {
            item.setHitCount(item.getHitCount() != null ? item.getHitCount() + 1 : 1);
        });
        return order;
    }

    public OrderDto getOrderDto(Order order){
        return OrderDto.builder()
                .items(order.getItems().stream().map(item -> itemService.getItemDto(item)).collect(Collectors.toList()))
                .totalPrice(order.getPrice())
                .scheduledAt(order.getScheduleAt())
                .build();
    }
}
