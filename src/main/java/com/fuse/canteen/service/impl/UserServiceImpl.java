package com.fuse.canteen.service.impl;

import com.fuse.canteen.constants.StringConstants;
import com.fuse.canteen.dto.UserDto;
import com.fuse.canteen.entity.Employee;
import com.fuse.canteen.entity.Role;
import com.fuse.canteen.entity.User;
import com.fuse.canteen.mapper.AbstractUserServiceImpl;
import com.fuse.canteen.repo.UserRepository;
import com.fuse.canteen.service.EmployeeService;
import com.fuse.canteen.service.RoleService;
import com.fuse.canteen.utils.CustomMessageSource;
import com.fuse.canteen.utils.NullAwareBeanUtilsBean;
import lombok.RequiredArgsConstructor;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import static com.fuse.canteen.constants.StringConstants.MISSING_ID;
import static com.fuse.canteen.constants.StringConstants.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends AbstractUserServiceImpl {

    private final String ModuleName = "USER";
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final EmployeeService employeeService;
    private final CustomMessageSource customMessageSource;

    @Override
    public void create(UserDto userDto) throws Exception {
        try {
            User user = getUser(userDto);
            user.setRoles(Collections.singleton(roleService.getRoleById(userDto.getRole())));
            user.setEmployee(employeeService.getEmployee(userDto.getEmployee()));
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

            if (userDto.getRole() != null) {
                Collection<Role> rolesDb = userDb.getRoles();
                userDb.setRoles(rolesDb.stream().map(role -> {
                    try {
                        beanUtilsBean.copyProperties(role, roleService.getRoleById(userDto.getRole()));
                        return role;
                    } catch (Exception e) {
                        throw new RuntimeException(e.getMessage());
                    }
                }).collect(Collectors.toList()));
            }
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
        userRepository.delete(user);
    }
}
