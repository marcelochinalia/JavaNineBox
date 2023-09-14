package com.api.ninebox.controllers;

import com.api.ninebox.dto.HierarchyRequestDto;
import com.api.ninebox.dto.HierarchyResponseDto;
import com.api.ninebox.services.interfaces.HierarchyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*", maxAge=600)
@RequestMapping("ninebox/api/hierarchy")
public class HierarchyController {
    @Autowired
    private HierarchyService service;

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody List<HierarchyRequestDto> requestDto) {
        service.updateHierarchy(requestDto);
    }

    @GetMapping("{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public List<HierarchyResponseDto> findAllByCompanyId(@PathVariable("companyId") Integer companyId){
        return this.service.findHierarchyByCompanyId(companyId);
    }

    @GetMapping("/boss/{bossId}")
    @ResponseStatus(HttpStatus.OK)
    public HierarchyResponseDto findEmployeesByBossId(@PathVariable("bossId") Integer bossId){
        return this.service.findSubordinatesByBossId(bossId);
    }
}
