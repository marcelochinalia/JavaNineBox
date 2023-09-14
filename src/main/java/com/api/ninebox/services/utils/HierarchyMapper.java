package com.api.ninebox.services.utils;

import com.api.ninebox.models.Employee;
import com.api.ninebox.dto.HierarchyResponseDto;
import org.springframework.stereotype.Component;

@Component
public class HierarchyMapper {

    public HierarchyResponseDto convertModelToDto(Employee e){
        return new HierarchyResponseDto(e.getId(),e.getCompany().getId(),e.getFullName(), e.getActivated());
    }
}
