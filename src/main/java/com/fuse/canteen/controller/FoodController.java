package com.fuse.canteen.controller;

import com.fuse.canteen.dto.FoodDto;
import com.fuse.canteen.dto.UserDto;
import com.fuse.canteen.service.FoodService;
import com.fuse.canteen.utils.BaseController;
import com.fuse.canteen.utils.CustomMessageSource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/food")
@RequiredArgsConstructor
public class FoodController extends BaseController {

    private final FoodService foodService;
    private final CustomMessageSource customMessageSource;
    private final String ModuleName = "FOOD";


    @PutMapping("edit")
    public ResponseEntity<?> edit(@RequestBody @Valid FoodDto foodDto) throws Exception {
        foodService.edit(foodDto);
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_UPDATE,ModuleName)));
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody @Valid FoodDto foodDto) throws Exception {
        foodService.create(foodDto);
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_SAVE,ModuleName)));
    }

    @GetMapping("get/{food_id}")
    public ResponseEntity<?> getById(@PathVariable("food_id") Long foodId) throws Exception {
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_GET,ModuleName),foodService.getById(foodId)));
    }

    @GetMapping("fetch")
    public ResponseEntity<?> fetchAll(){
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_GET_ALL,ModuleName),foodService.fetchAll()));
    }

    @DeleteMapping("delete/{food_id}")
    public ResponseEntity<?> delete(@PathVariable("food_id") Long foodId) throws Exception {
        foodService.delete(foodId);
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_DELETE,ModuleName)));
    }

    @GetMapping("fetch_popular")
    public ResponseEntity<?> fetchPopular(){
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_GET_ALL,ModuleName),foodService.fetchPopular()));
    }
}

