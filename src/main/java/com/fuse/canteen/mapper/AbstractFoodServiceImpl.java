package com.fuse.canteen.mapper;

import com.fuse.canteen.dto.FoodDto;
import com.fuse.canteen.entity.Food;
import com.fuse.canteen.service.FoodService;
import com.fuse.canteen.service.ItemService;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class AbstractFoodServiceImpl implements FoodService {

    private final ItemService itemService;

    public Food getFood(FoodDto foodDto){
        return Food.builder()
                .name(foodDto.getName())
                .items(foodDto.getItems().stream().map(dto -> itemService.getItem(dto)).collect(Collectors.toList()))
                .build();
    }

    public FoodDto getFoodDto(Food food){
        return FoodDto.builder()
                .name(food.getName())
                .items(food.getItems().stream().map(item -> itemService.getItemDto(item)).collect(Collectors.toList()))
                .build();
    }
}
