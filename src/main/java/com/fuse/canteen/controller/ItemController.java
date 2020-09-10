package com.fuse.canteen.controller;

import com.fuse.canteen.dto.ItemDto;
import com.fuse.canteen.dto.UserDto;
import com.fuse.canteen.service.ItemService;
import com.fuse.canteen.service.UserService;
import com.fuse.canteen.utils.BaseController;
import com.fuse.canteen.utils.CustomMessageSource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/item")
@RequiredArgsConstructor
public class ItemController extends BaseController {

    private final ItemService itemService;
    private final CustomMessageSource customMessageSource;
    private final String ModuleName = "ITEM";


    @PutMapping("edit")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> edit(@RequestBody @Valid ItemDto itemDto) throws Exception {
        itemService.edit(itemDto);
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_UPDATE,ModuleName)));
    }

    @PostMapping("create")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> create(@RequestBody @Valid ItemDto itemDto) throws Exception {
        itemService.create(itemDto);
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_SAVE,ModuleName)));
    }

    @GetMapping("get/{itemId}")
    @PreAuthorize("hasAnyAuthority('Admin,User')")
    public ResponseEntity<?> getById(@PathVariable("itemId") Long itemId) throws Exception {
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_GET,ModuleName),itemService.getById(itemId)));
    }

    @GetMapping("fetch")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> fetchAll(){
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_GET_ALL,ModuleName),itemService.fetchAll()));
    }

    @DeleteMapping("delete/{itemId}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> delete(@PathVariable("itemId") Long itemId) throws Exception {
        itemService.delete(itemId);
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_DELETE,ModuleName)));
    }

    @GetMapping("todays")
    @PreAuthorize("hasAnyAuthority('Admin,User')")
    public ResponseEntity<?> fetchAllTodaysItem(){
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_GET_ALL,ModuleName),itemService.fetchAllTodaysItem()));
    }
}
