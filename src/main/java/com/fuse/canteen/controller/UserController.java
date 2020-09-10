package com.fuse.canteen.controller;

import com.fuse.canteen.dto.UserDto;
import com.fuse.canteen.service.UserService;
import com.fuse.canteen.utils.BaseController;
import com.fuse.canteen.utils.CustomMessageSource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Class Responsible for handling all User Requests
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController extends BaseController {

    private final UserService userService;
    private final CustomMessageSource customMessageSource;
    private final String ModuleName = "USER";


    @PutMapping("edit")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> edit(@RequestBody @Valid UserDto userDto) throws Exception {
        userService.edit(userDto);
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_UPDATE,ModuleName)));
    }

    @PostMapping("create")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> create(@RequestBody @Valid UserDto userDto) throws Exception {
        userService.create(userDto);
        return ResponseEntity.ok(successResponse(customMessageSource.get("success.save",ModuleName)));
    }

    @GetMapping("get/{user_id}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> getById(@PathVariable("user_id") Long userId) throws Exception {
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_GET,ModuleName),userService.getById(userId)));
    }

    @GetMapping("fetch")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> fetchAll(){
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_GET_ALL,ModuleName),userService.fetchAll()));
    }

    @DeleteMapping("delete/{user_id}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> delete(@PathVariable("user_id") Long userId) throws Exception {
        userService.delete(userId);
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_DELETE,ModuleName)));
    }

}
