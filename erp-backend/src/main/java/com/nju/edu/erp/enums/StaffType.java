package com.nju.edu.erp.enums;

public enum StaffType implements BaseEnum<StaffType, String>{
    INVENTORY_MANAGER("库存管理人员"),
    SALE_STAFF("进货销售人员"),
    SALE_MANAGER("销售经理"),
    FINANCE_STAFF("财务人员"),
    HR("人力资源人员"),
    GM("总经理");


    private final String value;

    StaffType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
