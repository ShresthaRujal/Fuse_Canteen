package com.fuse.canteen.service;

import com.fuse.canteen.dto.ItemDto;
import com.fuse.canteen.entity.Item;

public interface ItemService {

    Item getById(Long itemId);

    Item getItem(ItemDto itemDto);

    ItemDto getItemDto(Item item);
}
