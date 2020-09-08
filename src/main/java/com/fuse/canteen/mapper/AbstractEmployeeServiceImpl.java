package com.fuse.canteen.mapper;

import com.fuse.canteen.dto.EmployeeDto;
import com.fuse.canteen.entity.Employee;
import com.fuse.canteen.repo.EmployeeRepo;
import com.fuse.canteen.service.EmployeePositionService;
import com.fuse.canteen.service.EmployeeService;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class AbstractEmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final EmployeePositionService employeePositionService;

    @Override
    public Employee getEmployee(EmployeeDto employeeDto) {
        return Employee.builder()
                .employeeCode(employeeDto.getEmployeeCode())
                .employeePosition(employeeRepo.findEmployeeIn(employeeDto.getEmployeePositionIds()))
                .build();
    }

    public EmployeeDto getEmployeeDto(Employee employee){
        return EmployeeDto.builder()
                .employeeCode(employee.getEmployeeCode())
                .employeePositions(employee.getEmployeePosition().stream().map(employeePosition -> employeePositionService.getEmployeePositionDto(employeePosition)).collect(Collectors.toList()))
                .build();
    }
}
