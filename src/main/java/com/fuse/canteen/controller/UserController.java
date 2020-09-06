package com.fuse.canteen.controller;

import com.fuse.canteen.utils.BaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class Responsible for handling all User Requests
 */

@RestController
@RequestMapping("api/user")
public class UserController extends BaseController {

    public ResponseEntity<?> create(){
        return ResponseEntity.ok(successResponse());
    }

}
