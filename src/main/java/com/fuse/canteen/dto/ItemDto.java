package com.fuse.canteen.dto;

import com.fuse.canteen.constants.FieldErrorConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {
    private Long id;
    @NotNull(message = FieldErrorConstants.NOT_NULL)
    @NotBlank(message = FieldErrorConstants.NOT_BLANK)
    private String name;

    private String description;


    @NotNull(message = FieldErrorConstants.NOT_NULL)
    private BigDecimal price;
}
