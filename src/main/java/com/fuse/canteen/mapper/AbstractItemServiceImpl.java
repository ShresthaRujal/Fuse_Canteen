package com.fuse.canteen.mapper;

import com.fuse.canteen.dto.ItemDto;
import com.fuse.canteen.entity.Item;
import com.fuse.canteen.service.ItemService;

public abstract class AbstractItemServiceImpl implements ItemService {

    @Override
    public Item getItem(ItemDto itemDto) {
        return Item.builder()
                .name(itemDto.getName())
                .description(itemDto.getDescription())
                .price(itemDto.getPrice())
                .build();
    }

    @Override
    public ItemDto getItemDto(Item item) {
        return ItemDto.builder()
                .name(item.getName())
                .description(item.getDescription())
                .price(item.getPrice())
                .build();
    }
}
