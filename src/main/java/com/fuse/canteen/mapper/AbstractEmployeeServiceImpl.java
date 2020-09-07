package com.fuse.canteen.mapper;

import com.fuse.canteen.dto.EmployeeDto;
import com.fuse.canteen.entity.Employee;
import com.fuse.canteen.repo.EmployeeRepo;
import com.fuse.canteen.service.EmployeeService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractEmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;

    @Override
    public Employee getEmployee(EmployeeDto employeeDto) {
        return Employee.builder()
                .employeeCode(employeeDto.getEmployeeCode())
                .employeePosition(employeeRepo.findEmployeeIn(employeeDto.getEmployeePositions()))
                .build();
    }

}
