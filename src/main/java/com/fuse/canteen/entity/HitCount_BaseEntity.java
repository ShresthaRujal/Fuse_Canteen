package com.fuse.canteen.entity;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class HitCount_BaseEntity {

    private Integer hitCount;
}
