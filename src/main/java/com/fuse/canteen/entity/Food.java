package com.fuse.canteen.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Foods")
public class Food extends HitCount_BaseEntity {

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FOOD_SEQ_GEN")
    @SequenceGenerator(name = "FOOD_SEQ_GEN", sequenceName = "FOOD_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "food")
    private Collection<Item> items;

}
