package com.fuse.canteen.dto;

import com.fuse.canteen.constants.FieldErrorConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {

    @NotNull(message = FieldErrorConstants.NOT_NULL)
    @NotBlank(message = FieldErrorConstants.NOT_BLANK)
    private String employeeCode;
    @NotNull(message = FieldErrorConstants.NOT_NULL)
    private List<Integer> employeePositions;
}
