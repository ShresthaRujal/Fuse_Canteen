package com.fuse.canteen.dto;

import com.fuse.canteen.constants.FieldErrorConstants;
import com.fuse.canteen.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;

    @NotNull(message = FieldErrorConstants.NOT_NULL)
    @NotBlank(message = FieldErrorConstants.NOT_BLANK)
    private String firstName;
    private String middleName;
    @NotNull(message = FieldErrorConstants.NOT_NULL)
    @NotBlank(message = FieldErrorConstants.NOT_BLANK)
    private String lastName;

    @NotNull(message = FieldErrorConstants.NOT_NULL)
    @NotBlank(message = FieldErrorConstants.NOT_BLANK)
    private String userName;
    @NotNull(message = FieldErrorConstants.NOT_NULL)
    @NotBlank(message = FieldErrorConstants.NOT_BLANK)
    private String email;
    @NotNull(message = FieldErrorConstants.NOT_NULL)
    @NotBlank(message = FieldErrorConstants.NOT_BLANK)
    private String password;

    @NotNull(message = FieldErrorConstants.NOT_NULL)
    @NotBlank(message = FieldErrorConstants.NOT_BLANK)
    @Pattern(regexp = "\\d+",message = FieldErrorConstants.PATTERN)
    private String phoneNumber;

    @NotNull(message = FieldErrorConstants.NOT_NULL)
    private Integer role;

    @Valid
    private EmployeeDto employee;

}
