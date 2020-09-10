package com.fuse.canteen.service;

import com.fuse.canteen.dto.ItemDto;
import com.fuse.canteen.dto.UserDto;
import com.fuse.canteen.entity.Item;

import java.util.List;

public interface ItemService {

    Item getById(Long itemId) throws Exception;

    List<Item> getItemInIds(List<Long> itemIds) throws Exception;

    Item getItem(ItemDto itemDto);

    ItemDto getItemDto(Item item);

    void edit(ItemDto itemDto) throws Exception;

    void create(ItemDto itemDto) throws Exception;

    Object fetchAll();

    void delete(Long userId) throws Exception;

    Object fetchAllTodaysItem();
}
