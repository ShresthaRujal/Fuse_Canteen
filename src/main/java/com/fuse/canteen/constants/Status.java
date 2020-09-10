package com.fuse.canteen.constants;

import javassist.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public enum Status {
    ACTIVE(1, "Active"),
    DEACTIVATED(2, "Deactivated")
    ;

    private Integer key;
    private String value;


    Status(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static Status getByKey(Integer key){
        Status[] statusArray = values();
        for (Status status : statusArray) {
            if (key.equals(status.key)) {
                return status;
            }
        }
        return null;
    }
}