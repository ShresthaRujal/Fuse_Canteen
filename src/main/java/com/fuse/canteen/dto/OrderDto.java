package com.fuse.canteen.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fuse.canteen.constants.FieldErrorConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Long id;

    @NotNull(message = FieldErrorConstants.NOT_NULL)
    private List<Long> itemIds;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date scheduledAt;

    private BigDecimal totalPrice;
    private Collection<ItemDto> items;
}
