package com.fuse.canteen.dto;

import com.fuse.canteen.constants.FieldErrorConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusPojo {
    @NotNull(message = FieldErrorConstants.NOT_NULL)
    private Long id;

    @NotNull(message = FieldErrorConstants.NOT_NULL)
    private Integer status;
    private String remarks;
}
