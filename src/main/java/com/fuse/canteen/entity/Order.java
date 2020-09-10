package com.fuse.canteen.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Orders")
public class Order extends BaseEntity {

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQ_GEN")
    @SequenceGenerator(name = "ORDER_SEQ_GEN", sequenceName = "ORDER_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;

    private Date scheduleAt;
    private BigDecimal price;
    @ManyToMany
    @JoinTable(name = "orders_items",
            joinColumns = {@JoinColumn(name = "orderId")},
            inverseJoinColumns = {@JoinColumn(name = "itemId")}
    )
    private Collection<Item> items;
}
