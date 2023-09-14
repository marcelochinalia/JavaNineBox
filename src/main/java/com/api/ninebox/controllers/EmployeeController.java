package com.api.ninebox.controllers;

import com.api.ninebox.dto.EmployeeRequestDto;
import com.api.ninebox.dto.EmployeeResponseDto;
import com.api.ninebox.services.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="*", maxAge=600)
@RequestMapping("ninebox/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponseDto create(@RequestBody EmployeeRequestDto requestDto) {
        return this.service.save(requestDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("employeeId") Integer employeeId,
                       @RequestBody EmployeeRequestDto requestDto) {
       service.update(requestDto, employeeId);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("employeeId") Integer employeeId) {
        service.delete(employeeId);
    }

    @PostMapping("/batch")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeResponseDto> createBatch(@RequestBody @Valid List<EmployeeRequestDto> requestDto) {
        return this.service.saveAll(requestDto);
    }

    @GetMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<EmployeeResponseDto> findById(@PathVariable("employeeId") Integer employeeId){
        return this.service.findById(employeeId);
    }

    @GetMapping("/company/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeResponseDto> findAllByCompanyId(@PathVariable("companyId") Integer companyId){
        return this.service.findAllByCompanyId(companyId);
    }

    @GetMapping("/company/{companyId}/partialName/{employeeName}")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeResponseDto> findByNameByCompanyId(@PathVariable("companyId") Integer companyId,
                                                         @PathVariable("employeeName") String employeeName){
        return this.service.getAllByName(companyId, employeeName);
    }
}
