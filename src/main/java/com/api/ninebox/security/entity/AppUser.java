package com.api.ninebox.security.entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class AppUser {
    @Id
    @JoinColumn(name = "employee_id")
    private Integer employeeId;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "is_admin", nullable = false, length = 512)
    private Boolean admin;

    public Integer getEmployeeId() {
        return employeeId;
    }
    public void setEmployee(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }
    public String getPassword() { return password; }

    public Boolean getAdmin() {
        return admin;
    }
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String[] getRoles() { return (this.admin.booleanValue() ? new String[] { "USER", "ADMIN" } : new String[] {"USER"}); }

    @Override
    public String toString() {
        return "User{" +
                "employee=" + employeeId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                '}';
    }
}