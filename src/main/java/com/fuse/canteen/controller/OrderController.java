package com.fuse.canteen.controller;

import com.fuse.canteen.dto.FoodDto;
import com.fuse.canteen.dto.OrderDto;
import com.fuse.canteen.service.OrderService;
import com.fuse.canteen.utils.BaseController;
import com.fuse.canteen.utils.CustomMessageSource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/order")
public class OrderController extends BaseController {

    private final OrderService orderService;
    private final CustomMessageSource customMessageSource;
    private final String ModuleName = "ORDER";


    @PutMapping("edit")
    public ResponseEntity<?> edit(@RequestBody @Valid OrderDto orderDto) throws Exception {
        orderService.edit(orderDto);
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_UPDATE,ModuleName)));
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody @Valid OrderDto orderDto) throws Exception {
        orderService.create(orderDto);
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_SAVE,ModuleName)));
    }

    @GetMapping("get/{orderId}")
    public ResponseEntity<?> getById(@PathVariable("orderId") Long orderId) throws Exception {
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_GET,ModuleName),orderService.getById(orderId)));
    }

    @GetMapping("fetch")
    public ResponseEntity<?> fetchAll(){
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_GET_ALL,ModuleName),orderService.fetchAll()));
    }

    @GetMapping("fetch_popular")
    public ResponseEntity<?> fetchPopular(){
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_GET_ALL,ModuleName),orderService.fetchPopular()));
    }

    @DeleteMapping("delete/{orderId}")
    public ResponseEntity<?> delete(@PathVariable("orderId") Long orderId) throws Exception {
        orderService.delete(orderId);
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_DELETE,ModuleName)));
    }

}
