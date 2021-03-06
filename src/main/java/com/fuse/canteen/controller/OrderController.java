package com.fuse.canteen.controller;

import com.fuse.canteen.dto.FoodDto;
import com.fuse.canteen.dto.OrderDto;
import com.fuse.canteen.dto.OrderStatusPojo;
import com.fuse.canteen.service.OrderService;
import com.fuse.canteen.utils.BaseController;
import com.fuse.canteen.utils.CustomMessageSource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<?> edit(@RequestBody @Valid OrderDto orderDto) throws Exception {
        OrderDto orderDtoReturn = orderService.edit(orderDto);
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_UPDATE,ModuleName),orderDtoReturn));
    }

    @PostMapping("create")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<?> create(@RequestBody @Valid OrderDto orderDto) throws Exception {
        OrderDto orderDtoReturn = orderService.create(orderDto);
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_SAVE,ModuleName),orderDtoReturn));
    }

    @PostMapping("update_status")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> create(@RequestBody @Valid OrderStatusPojo orderStatusPojo) throws Exception {
        orderService.updateStatus(orderStatusPojo);
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_UPDATE,ModuleName)));
    }

    @GetMapping("get/{orderId}")
    @PreAuthorize("hasAnyAuthority('Admin,User')")
    public ResponseEntity<?> getById(@PathVariable("orderId") Long orderId) throws Exception {
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_GET,ModuleName),orderService.getById(orderId)));
    }

    @GetMapping("fetch")
    @PreAuthorize("hasAnyAuthority('Admin,User')")
    public ResponseEntity<?> fetchAll(){
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_GET_ALL,ModuleName),orderService.fetchAll()));
    }

    @GetMapping("fetch_popular")
    @PreAuthorize("hasAnyAuthority('Admin,User')")
    public ResponseEntity<?> fetchPopular(){
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_GET_ALL,ModuleName),orderService.fetchPopular()));
    }

    @DeleteMapping("delete/{orderId}")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<?> delete(@PathVariable("orderId") Long orderId) throws Exception {
        orderService.delete(orderId);
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_DELETE,ModuleName)));
    }

    @GetMapping("history")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<?> fetchAllHistoryByUser(){
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_GET_ALL,ModuleName),orderService.fetchAllHistoryByUser()));
    }

    @GetMapping("fetch_ordersby_time")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> fetchAllOrdersByTime(){
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_GET_ALL,ModuleName),orderService.fetchAllOrdersByTime()));
    }
}
