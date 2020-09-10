package com.fuse.canteen.service.impl;

import com.fuse.canteen.constants.Status;
import com.fuse.canteen.dto.UserDto;
import com.fuse.canteen.entity.Employee;
import com.fuse.canteen.entity.User;
import com.fuse.canteen.mapper.AbstractUserServiceImpl;
import com.fuse.canteen.repo.UserRepository;
import com.fuse.canteen.service.EmployeeService;
import com.fuse.canteen.utils.CustomMessageSource;
import com.fuse.canteen.utils.NullAwareBeanUtilsBean;
import lombok.RequiredArgsConstructor;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;

import static com.fuse.canteen.constants.StringConstants.MISSING_ID;
import static com.fuse.canteen.constants.StringConstants.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends AbstractUserServiceImpl {

    private final String ModuleName = "USER";
    private final UserRepository userRepository;
    private final EmployeeService employeeService;
    private final CustomMessageSource customMessageSource;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void create(UserDto userDto) throws Exception {
        try {
            User user = getUser(userDto);
            user.setEmployee(employeeService.getEmployee(userDto.getEmployee()));
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setStatus(Status.ACTIVE.getKey());
            userRepository.save(user);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void edit(UserDto userDto) throws Exception {
        try {
            Assert.notNull(userDto.getId(),customMessageSource.get(MISSING_ID));

            User userDb = userRepository.findById(userDto.getId()).orElseThrow(() -> new Exception(customMessageSource.get(NOT_FOUND,ModuleName)));
            BeanUtilsBean beanUtilsBean = new NullAwareBeanUtilsBean();
            beanUtilsBean.copyProperties(userDb,getUser(userDto));

            if (userDto.getEmployee() != null) {
                Employee employeeDb = userDb.getEmployee();
                beanUtilsBean.copyProperties(employeeDb, employeeService.getEmployee(userDto.getEmployee()));
                userDb.setEmployee(employeeDb);
            }
            userRepository.save(userDb);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Object getById(Long userId) throws Exception {
        Assert.notNull(userId,customMessageSource.get(MISSING_ID));
        return userRepository.findById(userId).orElseThrow(() -> new Exception(customMessageSource.get(NOT_FOUND,ModuleName)));
    }

    @Override
    public Object fetchAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(Long userId) throws Exception {
        Assert.notNull(userId,customMessageSource.get(MISSING_ID));
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception(customMessageSource.get(NOT_FOUND,ModuleName)));
        user.setStatus(Status.DEACTIVATED.getKey());
        userRepository.save(user);
    }
}
