package com.fuse.canteen.service.impl;

import com.fuse.canteen.dto.EmployeePositionDto;
import com.fuse.canteen.entity.EmployeePosition;
import com.fuse.canteen.mapper.AbstractEmployeePositionServiceImpl;
import com.fuse.canteen.repo.EmployeePositionRepo;
import com.fuse.canteen.utils.CustomMessageSource;
import com.fuse.canteen.utils.NullAwareBeanUtilsBean;
import lombok.AllArgsConstructor;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import static com.fuse.canteen.constants.StringConstants.MISSING_ID;
import static com.fuse.canteen.constants.StringConstants.NOT_FOUND;

@Service
@AllArgsConstructor
public class EmployeePositionServiceImpl extends AbstractEmployeePositionServiceImpl {

    private final EmployeePositionRepo employeePositionRepo;
    private final CustomMessageSource customMessageSource;

    @Override
    public void create(EmployeePositionDto employeePositionDto) {
        employeePositionRepo.save(getEmployeePosition(employeePositionDto));
    }

    @Override
    public void edit(EmployeePositionDto employeePositionDto) throws Exception {
        try {
            Assert.notNull(employeePositionDto.getId(), customMessageSource.get(MISSING_ID));
            EmployeePosition employeePosition = employeePositionRepo.findById(employeePositionDto.getId()).orElseThrow(() -> new Exception(customMessageSource.get(NOT_FOUND)));
            BeanUtilsBean beanUtilsBean = new NullAwareBeanUtilsBean();
            beanUtilsBean.copyProperties(employeePosition, getEmployeePosition(employeePositionDto));
            employeePositionRepo.save(employeePosition);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Object getById(Long employeePosition_id) throws Exception {
        Assert.notNull(employeePosition_id, customMessageSource.get(MISSING_ID));
        return employeePositionRepo.findById(employeePosition_id).orElseThrow(() -> new Exception(customMessageSource.get(NOT_FOUND)));
    }

    @Override
    public Object fetchAll() {
        return employeePositionRepo.findAll();
    }

    @Override
    public void delete(Long employeePosition_id) throws Exception {
        Assert.notNull(employeePosition_id, customMessageSource.get(MISSING_ID));
        EmployeePosition employeePosition = employeePositionRepo.findById(employeePosition_id).orElseThrow(() -> new Exception(customMessageSource.get(NOT_FOUND)));
        employeePositionRepo.delete(employeePosition);
    }
}
