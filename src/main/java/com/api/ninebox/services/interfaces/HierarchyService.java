package com.api.ninebox.services.interfaces;

import com.api.ninebox.dto.HierarchyRequestDto;
import com.api.ninebox.dto.HierarchyResponseDto;

import java.util.List;

public interface HierarchyService {
    void updateHierarchy(List<HierarchyRequestDto> requestDto);
    List<HierarchyResponseDto> findHierarchyByCompanyId(Integer companyId);
    HierarchyResponseDto findSubordinatesByBossId(Integer bossId);
}
