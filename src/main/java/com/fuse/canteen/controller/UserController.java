package com.fuse.canteen.controller;

import com.fuse.canteen.dto.UserDto;
import com.fuse.canteen.service.UserService;
import com.fuse.canteen.utils.BaseController;
import com.fuse.canteen.utils.CustomMessageSource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Class Responsible for handling all User Requests
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController extends BaseController {

    private final CustomMessageSource customMessageSource;
    private final UserService userService;
    private final String ModuleName = "USER";


    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody @Valid UserDto userDto) throws Exception {
        userService.create(userDto);
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_SAVE,ModuleName)));
    }

    @PutMapping("edit")
    public ResponseEntity<?> edit(@RequestBody @Valid UserDto userDto) throws Exception {
        userService.edit(userDto);
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_UPDATE,ModuleName)));
    }

    @GetMapping("get/{user_id}")
    public ResponseEntity<?> getById(@PathVariable("user_id") Long userId) throws Exception {
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_GET,ModuleName),userService.getById(userId)));
    }

    @GetMapping("fetch")
    public ResponseEntity<?> fetchAll(){
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_GET_ALL,ModuleName),userService.fetchAll()));
    }

    @DeleteMapping("delete/{user_id}")
    public ResponseEntity<?> delete(@PathVariable("user_id") Long userId) throws Exception {
        userService.delete(userId);
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_DELETE,ModuleName)));
    }

}
