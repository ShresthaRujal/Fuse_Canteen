package com.fuse.canteen.service;

import com.fuse.canteen.dto.FoodDto;
import com.fuse.canteen.entity.Food;

public interface FoodService {

    void create(FoodDto foodDto);

    void edit(FoodDto foodDto) throws Exception;

    Object getById(Long foodId) throws Exception;

    Object fetchAll();

    void delete(Long foodId) throws Exception;

    Food getFood(FoodDto foodDto);

    FoodDto getFoodDto(Food food);

    Object fetchPopular();
}
