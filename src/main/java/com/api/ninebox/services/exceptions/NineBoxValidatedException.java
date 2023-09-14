package com.api.ninebox.services.exceptions;

import com.api.ninebox.dto.ValidationsDto;

public class NineBoxValidatedException extends RuntimeException {
    private ValidationsDto validations;
    public NineBoxValidatedException(ValidationsDto validations){
        this.validations = validations;
    }
    public ValidationsDto getValidations() {
        return validations;
    }
}
