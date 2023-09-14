package com.api.ninebox.services.interfaces;

import com.api.ninebox.dto.CompanyRequestDto;
import com.api.ninebox.dto.CompanyResponseDto;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    CompanyResponseDto create(CompanyRequestDto requestDto);
    void update(CompanyRequestDto requestDto, Integer id);
    void delete(Integer id);
    List<CompanyResponseDto> findAll();
    Optional<CompanyResponseDto> findById(Integer id);
    List<CompanyResponseDto> findByName(String name);
}
