package com.api.ninebox.dto;

import java.io.Serializable;

public class CompanyResponseDto implements Serializable {

    public CompanyResponseDto(){

    }

    public CompanyResponseDto(Integer id, String name, boolean activated){
        this.id = id;
        this.name = name;
        this.activated = activated;
    }

    private Integer id;
    private String name;
    private boolean activated = true;

    public void setId(Integer id){
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}
