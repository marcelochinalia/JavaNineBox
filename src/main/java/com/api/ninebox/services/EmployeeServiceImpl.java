package com.api.ninebox.services;

import com.api.ninebox.dto.EmployeeRequestDto;
import com.api.ninebox.dto.EmployeeResponseDto;
import com.api.ninebox.dto.ValidationsDto;
import com.api.ninebox.models.Employee;
import com.api.ninebox.models.repositories.EmployeeRepository;
import com.api.ninebox.services.interfaces.EmployeeService;
import com.api.ninebox.services.exceptions.NineBoxNotFoundException;
import com.api.ninebox.services.exceptions.NineBoxValidatedException;
import com.api.ninebox.services.utils.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository repo;
    @Autowired
    private EmployeeMapper mapper;

    @Transactional
    public EmployeeResponseDto save(EmployeeRequestDto dto) {
        ValidationsDto validations = new ValidationsDto(
                this.mapper.validatedEmployee(
                        dto,
                        EmployeeMapper.OPERATION_CREATE
                )
        );

        if (validations.size() > 0)
            throw new NineBoxValidatedException(validations);

        return this.mapper.convertModelToDto(
                this.repo.save(
                        this.mapper.convertDtoToModel(dto)
                )
        );
    }

    @Transactional
    public boolean update(EmployeeRequestDto requestDto, Integer id) {
        ValidationsDto validations = new ValidationsDto(
                this.mapper.validatedEmployee(
                        requestDto,
                        EmployeeMapper.OPERATION_UPDATE
                )
        );
        if (validations.size() > 0)
            throw new NineBoxValidatedException(validations);

        if (! hasId(requestDto.getId()))
            throw new NineBoxNotFoundException();

        this.repo.save(
                this.mapper.convertDtoToModel(requestDto)
        );
        return true;
    }

    @Transactional
    public boolean delete(Integer id) {
        if (id == 0){
            ValidationsDto validations = new ValidationsDto();
            validations.add("Employee Id is required");
            throw new NineBoxValidatedException(validations);
        }

        if (! hasId(id))
            throw new NineBoxNotFoundException();

        this.repo.deleteById(id);
        return true;
    }

    @Transactional
    public List<EmployeeResponseDto> saveAll(List<EmployeeRequestDto> employees){
        ValidationsDto validations = mapper.validateEmployees(employees);

        if (validations.size() == 0) {
            return mapper.convertListModelToListDto(
                    this.repo.saveAll(
                            mapper.convertListDtoToListModel(employees)
                    )
            );
        }
        else {
            throw new NineBoxValidatedException(validations);
        }
    }

    private boolean hasId(Integer id){
        return repo.findById(id).isPresent();
    }

    public Optional<EmployeeResponseDto> findById(Integer employeeId){
       return mapper.convertModelToDto(
               Optional.ofNullable(repo.findById(employeeId)
                                            .orElseThrow(() -> new NineBoxNotFoundException())
               )
       );
    }

    public List<EmployeeResponseDto> getAllByName(Integer companyId, String partName){
        return mapper.convertListModelToListDto(
                    repo.findAllByFullName(companyId, partName)
        );
    }

    public List<EmployeeResponseDto> findAllByCompanyId(Integer companyId){
        return mapper.convertListModelToListDto(
                        this.repo.findAll(
                                Example.of(
                                        new Employee(companyId)
                                )
                        )
        );
    }
}