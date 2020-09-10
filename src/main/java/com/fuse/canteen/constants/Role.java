package com.fuse.canteen.constants;

import javassist.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public enum Role {

    ADMIN(1, "Admin"),
    USER(2, "User");

    private Integer key;
    private String value;


    Role(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static Role getByKey(Integer key){
        Role[] roles = values();
        for (Role status : roles) {
            if (key.equals(status.key)) {
                return status;
            }
        }
        return null;
    }

    public static List<Role> getRoleInKey(List<Integer> keys) throws NotFoundException {
        List<Role> roleList = new ArrayList<>();
        for (Integer key : keys) {
            if (key == null)
                throw new IllegalArgumentException("Role Key cannot be null");
            Role[] roles = values();
            for (Role status : roles) {
                if (key.equals(status.key)) {
                    roleList.add(status);
                }
            }
        }
        if (roleList.size() > 0){
            return roleList;
        }
        throw new NotFoundException("Role not found");
    }

}
