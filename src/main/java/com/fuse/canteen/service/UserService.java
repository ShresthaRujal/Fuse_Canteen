package com.fuse.canteen.service;

import com.fuse.canteen.dto.UserDto;
import com.fuse.canteen.entity.User;
import javassist.NotFoundException;

public interface UserService {
    void create(UserDto userDto) throws Exception;

    void edit(UserDto userDto) throws Exception;

    Object getById(Long userId) throws Exception;

    Object fetchAll();

    void delete(Long userId) throws Exception;

    User getUser(UserDto userDto) throws NotFoundException;

}
