package com.fuse.canteen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fuse.canteen.constants.Status;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Audited
public class BaseEntity implements Serializable {

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false,name = "EntryDate",nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date entryDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "StatusChangeDate",nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date statusChangeDate;

    @CreatedBy
    @Column(updatable = false,name = "EntryUserId",nullable = false)
    private Long entryUserId;

    @LastModifiedBy
    @Column(name = "StatusChangeUserId",nullable = false)
    private Long statusChangeUserId;

    @Column(name = "Status",nullable = true)
    private Integer status = Status.ACTIVE.getKey();
}
