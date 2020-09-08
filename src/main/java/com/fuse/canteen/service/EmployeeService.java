package com.fuse.canteen.service;

import com.fuse.canteen.dto.EmployeeDto;
import com.fuse.canteen.entity.Employee;

public interface EmployeeService {
    Employee getEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeDto(Employee employee);
}
