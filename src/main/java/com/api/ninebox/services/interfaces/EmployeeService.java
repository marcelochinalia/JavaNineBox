package com.api.ninebox.services.interfaces;

import com.api.ninebox.dto.EmployeeRequestDto;
import com.api.ninebox.dto.EmployeeResponseDto;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    EmployeeResponseDto save(EmployeeRequestDto dto);
    boolean update(EmployeeRequestDto requestDto, Integer id);
    boolean delete(Integer id);
    List<EmployeeResponseDto> saveAll(List<EmployeeRequestDto> employees);
    Optional<EmployeeResponseDto> findById(Integer employeeId);
    List<EmployeeResponseDto> getAllByName(Integer companyId, String partName);
    List<EmployeeResponseDto> findAllByCompanyId(Integer companyId);
}
