package com.fuse.canteen.service.impl;

import com.fuse.canteen.entity.Role;
import com.fuse.canteen.repo.RoleRepository;
import com.fuse.canteen.service.RoleService;
import com.fuse.canteen.utils.CustomMessageSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static com.fuse.canteen.constants.StringConstants.NOT_FOUND;
import static com.fuse.canteen.constants.StringConstants.MISSING_ID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final String ModuleName = "ROLE";
    private final RoleRepository roleRepository;
    private final CustomMessageSource customMessageSource;


    @Override
    public Role getRoleById(Long roleId) throws Exception {
        Assert.notNull(roleId,customMessageSource.get(MISSING_ID));
        return roleRepository.findById(roleId).orElseThrow(() -> new Exception(customMessageSource.get(NOT_FOUND,ModuleName)));
    }
}
