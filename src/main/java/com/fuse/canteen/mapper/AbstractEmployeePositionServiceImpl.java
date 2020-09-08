package com.fuse.canteen.mapper;

import com.fuse.canteen.dto.EmployeePositionDto;
import com.fuse.canteen.entity.EmployeePosition;
import com.fuse.canteen.service.EmployeePositionService;

public abstract class AbstractEmployeePositionServiceImpl implements EmployeePositionService {

    @Override
    public EmployeePosition getEmployeePosition(EmployeePositionDto employeePositionDto) {
        return EmployeePosition.builder()
                .positionLevel(employeePositionDto.getPositionLevel())
                .positionName(employeePositionDto.getPositionName())
                .build();
    }

    public EmployeePositionDto getEmployeePositionDto(EmployeePosition employeePosition){
        return EmployeePositionDto.builder()
                .positionName(employeePosition.getPositionName())
                .positionLevel(employeePosition.getPositionLevel())
                .build();
    }
}
