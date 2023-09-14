package com.api.ninebox.services;

import com.api.ninebox.models.Company;
import com.api.ninebox.dto.CompanyRequestDto;
import com.api.ninebox.dto.CompanyResponseDto;
import com.api.ninebox.dto.ValidationsDto;
import com.api.ninebox.models.repositories.CompanyRepository;
import com.api.ninebox.services.interfaces.CompanyService;
import com.api.ninebox.services.exceptions.NineBoxNotFoundException;
import com.api.ninebox.services.exceptions.NineBoxValidatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyRepository repo;
	
	@Transactional
	public CompanyResponseDto create(CompanyRequestDto requestDto) {
		ValidationsDto validators = new ValidationsDto();

		if (requestDto.getName().isEmpty() || requestDto.getName().trim().isEmpty() ) {
				validators.add("Name is required.");
		}

		if (validators.size() > 0)
			throw new NineBoxValidatedException(validators);

		return convertModelToResponseDto(
				this.repo.save(
						convertRequestDTOtoModel(requestDto)
				)
		);
	}

	@Transactional
	public void update(CompanyRequestDto requestDto, Integer id) {
		ValidationsDto validators = new ValidationsDto();

		if (id == 0) {
			validators.add("Company Id is required.");
		}

		if (requestDto.getName().isEmpty() || requestDto.getName().trim().isEmpty() ) {
			validators.add("Name is required.");
		}

		if (validators.size() > 0)
			throw new NineBoxValidatedException(validators);

		Optional<Company> model = this.repo.findById(id);
		if (model.isPresent()){
			model.get().setName(requestDto.getName());
			model.get().setActivated(requestDto.isActivated());

			this.repo.save(model.get());
		}
		else {
			validators.add("Company Id not exists.");
			throw new IllegalArgumentException(new NineBoxValidatedException(validators));
		}
	}

	@Transactional
	public void delete(Integer id) {
		ValidationsDto validators = new ValidationsDto();

		if (id == 0) {
			validators.add("Company Id is required.");
		}

		if (validators.size() > 0)
			throw new IllegalArgumentException(new NineBoxValidatedException(validators));

		Optional<Company> model = this.repo.findById(id);
		if (model.isPresent()){
			this.repo.deleteById(id);
		}
		else {
			validators.add("Company Id not exists.");
			throw new IllegalArgumentException(new NineBoxValidatedException(validators));
		}
	}
	
	public List<CompanyResponseDto> findAll() {
		return convertListModelToListResponseDto(
				this.repo.findAll(
						Sort.by(Sort.Direction.ASC, "name")
				)
		);
	}
	
	public Optional<CompanyResponseDto> findById(Integer id) {
		return convertModelToResponseDto(
				Optional.ofNullable(
						this.repo.findById(id)
								.orElseThrow(() -> new NineBoxNotFoundException())
				)
		);
	}

	public List<CompanyResponseDto> findByName(String name) {
		return convertListModelToListResponseDto(
				this.repo.findByCompanyName(name)
		);
	}

	private CompanyResponseDto convertModelToResponseDto(Company model){
		return new CompanyResponseDto(model.getId(), model.getName(), model.getActivated());
	}

	private Optional<CompanyResponseDto> convertModelToResponseDto(Optional<Company> model){
		return model.map(
				company -> new CompanyResponseDto(company.getId(),
												  company.getName(),
						                          company.getActivated())
		);
	}

	private Company convertRequestDTOtoModel(CompanyRequestDto requestDto){
		Company model = new Company();
		model.setName(requestDto.getName());
		model.setActivated(requestDto.isActivated());

		return model;
	}

	private List<CompanyResponseDto> convertListModelToListResponseDto(List<Company> listModel) {
		return listModel.stream()
						.map(item -> new CompanyResponseDto(item.getId(), item.getName(), item.getActivated()))
						.collect(Collectors.toList());
	}
}