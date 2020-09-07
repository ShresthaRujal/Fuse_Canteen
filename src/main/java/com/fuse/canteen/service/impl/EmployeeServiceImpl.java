package com.fuse.canteen.service.impl;

import com.fuse.canteen.mapper.AbstractEmployeeServiceImpl;
import com.fuse.canteen.repo.EmployeeRepo;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends AbstractEmployeeServiceImpl {


    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        super(employeeRepo);
    }

}
