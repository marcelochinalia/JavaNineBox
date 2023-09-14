package com.api.ninebox.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="Employee")
public class Employee implements Serializable {
    @Id()
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="employee_id", nullable = false, unique = true)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;
    @Column(name="employee_external_code")
    private String externalCode;
    @Column(name="employee_full_name", nullable = false)
    private String fullName;
    @Column(name="employee_email", nullable = false, unique = true)
    private String companyEmail;
    @Column(name="employee_hire_date")
    private LocalDate hireDate;
    @Column(name="employee_area", nullable = false)
    private String areaName;
    @Column(name="employee_actual_position", nullable = false)
    private String actualPosition;
    @Column(name="employee_activated", nullable = false)
    private Boolean activated;

    @Column(name="employee_boss_id")
    private Integer bossId;

    public Employee(){ }

    public Employee(Integer companyId){
        this.company = new Company(companyId);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Company getCompany() { return company; }

    public void setCompany(Company company) {
        this.company = company;
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
                ", company=" + company.toString() +
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
