package com.api.ninebox.dto;

import java.io.Serializable;

public class EmployeeResponseDto implements Serializable {
    private Integer id;
    private Integer companyId;
    private String fullName;

    private Boolean activated;

    public EmployeeResponseDto(){ }
    public EmployeeResponseDto(Integer id, Integer companyId, String fullName, Boolean activated) {
        this.id = id;
        this.companyId = companyId;
        this.fullName= fullName;
        this.activated = activated;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean isActivated() { return activated; }

    public void setActivated(Boolean activated) { this.activated = activated; }

    @Override
    public String toString() {
        return "EmployeeResponseDto{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", fullName='" + fullName + '\'' +
                ", activated='" + activated +
                '}';
    }
}
