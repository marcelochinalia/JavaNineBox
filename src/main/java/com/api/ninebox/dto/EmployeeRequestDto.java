package com.api.ninebox.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class EmployeeRequestDto implements Serializable {
    private Integer id;
    private Integer companyId;
    private String externalCode;
    private String fullName;
    private String companyEmail;
    private LocalDate hireDate;
    private String areaName;
    private String actualPosition;
    private Boolean activated;
    private Integer bossId;


    public EmployeeRequestDto(Integer companyId, Integer employeeId){
        this.companyId = companyId;
        this.id = employeeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() { return companyId; }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getExternalCode() {
        return externalCode;
    }

    public void setExternalCode(String externalCode) {
        this.externalCode = externalCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getActualPosition() {
        return actualPosition;
    }

    public void setActualPosition(String actualPosition) {
        this.actualPosition = actualPosition;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public Integer getBossId() { return bossId; }

    public void setBossId(Integer bossId) { this.bossId = bossId; }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", companyId=" + companyId + '\'' +
                ", externalCode='" + externalCode + '\'' +
                ", fullName='" + fullName + '\'' +
                ", companyEmail='" + companyEmail + '\'' +
                ", hireDate=" + hireDate +
                ", areaName='" + areaName + '\'' +
                ", actualPosition='" + actualPosition + '\'' +
                ", activated=" + activated + '\'' +
                ", bossId=" + bossId +
                '}';
    }
}
