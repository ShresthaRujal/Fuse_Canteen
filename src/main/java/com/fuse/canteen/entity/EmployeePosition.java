package com.fuse.canteen.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EmployeePositions")
public class EmployeePosition {

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EmployeePosition_SEQ_GEN")
    @SequenceGenerator(name = "EmployeePosition_SEQ_GEN", sequenceName = "EmployeePosition_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "positionName", nullable = false)
    private String positionName;
    @Column(name = "positionLevel", nullable = false)
    private Integer positionLevel;

    @ManyToOne
    @JoinColumn(name = "employeeId",referencedColumnName="id")
    private Employee employee;
}
