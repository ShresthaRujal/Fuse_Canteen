package com.fuse.canteen.service;

import com.fuse.canteen.dto.EmployeeDto;
import com.fuse.canteen.dto.EmployeePositionDto;
import com.fuse.canteen.entity.Employee;
import com.fuse.canteen.entity.EmployeePosition;

public interface EmployeePositionService {

    void create(EmployeePositionDto employeePositionDto);

    void edit(EmployeePositionDto employeePositionDto) throws Exception;

    Object getById(Long employeePosition_id) throws Exception;

    Object fetchAll();

    void delete(Long employeePosition_id) throws Exception;

    EmployeePosition getEmployeePosition(EmployeePositionDto employeePositionDto);
}
