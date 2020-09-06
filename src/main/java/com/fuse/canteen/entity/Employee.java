package com.fuse.canteen.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Employees")
public class Employee extends BaseEntity{

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_SEQ_GEN")
    @SequenceGenerator(name = "EMPLOYEE_SEQ_GEN", sequenceName = "EMPLOYEE_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;
    @Column(name = "employeeCode", nullable = false)
    private String employeeCode;

    @OneToMany(mappedBy = "employee")
    private Collection<EmployeePosition> employeePosition;
}
