package com.api.ninebox.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidationsDto implements Serializable {
    private List<String> validations;

    public ValidationsDto() { this.validations = new ArrayList<>(); }

    public ValidationsDto(List<String> validations) { this.validations = validations; }

    public void add(String validation){ this.validations.add(validation); }

    public int size(){ return validations.size(); }

    public List<String> getValidations() { return this.validations; }

}
