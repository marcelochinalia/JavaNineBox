package com.api.ninebox.dto;

import java.io.Serializable;
import java.util.List;

public class HierarchyRequestDto implements Serializable {
    private Integer bossId;
    private List<Integer> employeesId;

    public HierarchyRequestDto() {

    }

    public Integer getBossId() {
        return bossId;
    }

    public void setBossId(Integer bossId) {
        this.bossId = bossId;
    }

    public List<Integer> getEmployeesId() {
        return employeesId;
    }

    public void setEmployeesId(List<Integer> employeesId) {
        this.employeesId = employeesId;
    }

    @Override
    public String toString() {
        return "HierarchyRequestDto{" +
                "bossId=" + bossId +
                ", employeesId=" + employeesId +
                '}';
    }
}
