package com.fuse.canteen.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogDb extends BaseEntity{

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOG_SEQ_GEN")
    @SequenceGenerator(name = "LOG_SEQ_GEN", sequenceName = "LOG_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(columnDefinition = "text")
    private String requestBody;
    @Column(columnDefinition = "text")
    private String responseBody;

    private Date processStartTime;
    private Date processEndTime;

    private String controllerName;
}
