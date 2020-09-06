package com.fuse.canteen.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;

    private String firstName;
    private String middleName;
    private String lastName;

    private String userName;
    private String email;
    private String password;

    private String employee

}
