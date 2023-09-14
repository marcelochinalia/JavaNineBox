package com.api.ninebox.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HierarchyResponseDto implements Serializable {
    private Integer id;
    private Integer companyId;
    private String fullName;

    private Boolean activated;

    private List<HierarchyResponseDto> collaborators;

    public HierarchyResponseDto(Integer id, Integer companyId, String fullName, Boolean activated) {
        this.id = id;
        this.companyId = companyId;
        this.fullName= fullName;
        this.activated = activated;
        this.collaborators = new ArrayList<HierarchyResponseDto>();
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

    public Boolean getActivated() { return activated; }

    public void setActivated(Boolean activated) { this.activated = activated; }

    public List<HierarchyResponseDto> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(List<HierarchyResponseDto> collaborators) {
        this.collaborators = collaborators;
    }

    @Override
    public String toString() {
        return "EmployeeResponseDto{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", fullName='" + fullName + '\'' +
                ", activated='" + activated + '\'' +
                ", collaborators=" + collaborators +
                '}';
    }
}
