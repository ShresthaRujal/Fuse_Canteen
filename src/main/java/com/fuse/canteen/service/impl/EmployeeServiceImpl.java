package com.fuse.canteen.service.impl;

import com.fuse.canteen.mapper.AbstractEmployeeServiceImpl;
import com.fuse.canteen.repo.EmployeeRepo;
import com.fuse.canteen.service.EmployeePositionService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends AbstractEmployeeServiceImpl {

    private final EmployeeRepo employeeRepo;
    private final EmployeePositionService employeePositionService;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo, EmployeePositionService employeePositionService) {
        super(employeeRepo,employeePositionService);
        this.employeeRepo = employeeRepo;
        this.employeePositionService = employeePositionService;
    }

}
