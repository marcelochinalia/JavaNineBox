package com.api.ninebox.controllers;

import com.api.ninebox.dto.CompanyRequestDto;
import com.api.ninebox.dto.CompanyResponseDto;
import com.api.ninebox.services.interfaces.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="*", maxAge=600)
@RequestMapping("ninebox/api/companies")
public class CompanyController {
	@Autowired
	private CompanyService service;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CompanyResponseDto create(@RequestBody CompanyRequestDto requestDto, UriComponentsBuilder uriCBuilder) {
		return this.service.create(requestDto);
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable(name="id") Integer id, @RequestBody CompanyRequestDto requestDto) {
		this.service.update(requestDto, id);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(name="id") Integer id) {
		this.service.delete(id);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<CompanyResponseDto> findAll(){
		return this.service.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<CompanyResponseDto>findById(@PathVariable("id")Integer id){
		return this.service.findById(id);
	}

	@GetMapping("/name/{name}")
	@ResponseStatus(HttpStatus.OK)
	public List<CompanyResponseDto> findByName(@PathVariable("name") String name){
		return this.service.findByName(name);
	}
}
