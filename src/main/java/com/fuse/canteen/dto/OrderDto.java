package com.fuse.canteen.dto;

import com.fuse.canteen.constants.FieldErrorConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Long id;

    @NotNull(message = FieldErrorConstants.NOT_NULL)
    private Collection<Integer> itemIds;
//    private Collection<ItemDto> items;
}
