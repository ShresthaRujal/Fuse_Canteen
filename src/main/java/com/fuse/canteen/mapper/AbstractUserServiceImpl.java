package com.fuse.canteen.mapper;

import com.fuse.canteen.dto.UserDto;
import com.fuse.canteen.entity.User;
import com.fuse.canteen.service.UserService;

public abstract class AbstractUserServiceImpl implements UserService {

    public User getUser(UserDto userDto){
        return User.builder()
                .firstName(userDto.getFirstName())
                .middleName(userDto.getMiddleName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .userName(userDto.getUserName())
                .phoneNumber(userDto.getPhoneNumber())
                .build();
    }

    public UserDto getUserDto(User user){
        return UserDto.builder()
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .userName(user.getUserName())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
