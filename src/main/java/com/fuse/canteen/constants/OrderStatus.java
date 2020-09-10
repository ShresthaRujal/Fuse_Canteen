package com.fuse.canteen.constants;

public enum OrderStatus {

    PENDING(1, "Pending"),
    IN_PROCESS(2, "In-Process"),
    READY(3, "Ready")
    ;

    private Integer key;
    private String value;


    OrderStatus(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static OrderStatus getByKey(Integer key){
        OrderStatus[] statusArray = values();
        for (OrderStatus status : statusArray) {
            if (key.equals(status.key)) {
                return status;
            }
        }
        return null;
    }
}
