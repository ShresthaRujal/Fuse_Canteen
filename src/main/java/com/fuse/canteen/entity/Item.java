package com.fuse.canteen.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Items")
public class Item extends HitCount_BaseEntity {

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_SEQ_GEN")
    @SequenceGenerator(name = "ITEM_SEQ_GEN", sequenceName = "ITEM_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private BigDecimal price;

}
