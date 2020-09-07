package com.fuse.canteen.dto;

import com.fuse.canteen.constants.FieldErrorConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePositionDto {
    private Long id;

    @NotNull(message = FieldErrorConstants.NOT_NULL)
    @NotBlank(message = FieldErrorConstants.NOT_BLANK)
    private String positionName;

    @NotNull(message = FieldErrorConstants.NOT_NULL)
    @NotBlank(message = FieldErrorConstants.NOT_BLANK)
    private Integer positionLevel;
}
