package com.api.ninebox.services.utils;

import com.api.ninebox.models.Company;
import com.api.ninebox.models.Employee;
import com.api.ninebox.dto.EmployeeRequestDto;
import com.api.ninebox.dto.EmployeeResponseDto;
import com.api.ninebox.dto.ValidationsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {
    @Autowired
    private NineboxUtils utils;
    final public static char OPERATION_CREATE = 'C';
    final public static char OPERATION_UPDATE = 'U';
    final public static char OPERATION_DELETE = 'D';

    public EmployeeResponseDto convertModelToDto(Employee e){
        return new EmployeeResponseDto(e.getId(),
                                       e.getCompany().getId(),
                                       e.getFullName(),
                                       e.getActivated()
        );
    }
    public Optional<EmployeeResponseDto> convertModelToDto(Optional<Employee> employee){
        if (employee.isPresent()) {
            Employee e = employee.get();
            EmployeeResponseDto dto = new EmployeeResponseDto(e.getId(), e.getCompany().getId(), e.getFullName(), e.getActivated());
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    public Optional<List<EmployeeResponseDto>> convertListModelToListDto(Optional<List<Employee>> listModel) {
        return listModel.map(listDto ->
                listDto.stream()
                        .map(item -> new EmployeeResponseDto(item.getId(),
                                                             item.getCompany().getId(),
                                                             item.getFullName(),
                                                             item.getActivated()))
                        .collect(Collectors.toList())
        );
    }

    public List<EmployeeResponseDto> convertListModelToListDto(List<Employee> listModel) {
        return listModel.stream()
                        .map(item -> new EmployeeResponseDto(item.getId(),
                                item.getCompany().getId(),
                                item.getFullName(),
                                item.getActivated()))
                        .collect(Collectors.toList());
    }

    public Employee convertDtoToModel(EmployeeRequestDto dto) {
        Employee e = new Employee();

        e.setActivated(dto.getActivated());
        e.setActualPosition(dto.getActualPosition());
        e.setCompany(new Company(dto.getCompanyId()));
        e.setAreaName(dto.getAreaName());
        e.setCompanyEmail(dto.getCompanyEmail());
        e.setFullName(dto.getFullName());
        e.setExternalCode(dto.getExternalCode());
        e.setHireDate(dto.getHireDate());
        e.setBossId(dto.getBossId());

        return e;
    }

    public List<Employee> convertListDtoToListModel(List<EmployeeRequestDto> dtoList){
        return dtoList.stream()
                .map(this::convertDtoToModel)
                .collect(Collectors.toList());
    }

    public List<String> validatedEmployee(EmployeeRequestDto dto, char operation){
        List<String> validations = new ArrayList<>();

        if (operation != OPERATION_CREATE && dto.getId()==0)
            validations.add("Employee Id is required.");

        if (dto.getCompanyId() == 0) {
            validations.add("Company Id is required.");
        }

        if (dto.getFullName().isEmpty() || dto.getFullName().trim().equals("")){
            validations.add("Full name field is required.");
        }
        if (dto.getAreaName().isEmpty() || dto.getAreaName().trim().equals("")){
            validations.add("Area name field is required.");
        }
        if (dto.getActualPosition().isEmpty() || dto.getActualPosition().trim().equals("")){
            validations.add("Area name field is required.");
        }
        if (dto.getCompanyEmail().isEmpty() || dto.getCompanyEmail().trim().equals("")){
            validations.add("E-mail field is required.");
        }
        else if (!utils.validateEmailFormat(dto.getCompanyEmail())) {
            validations.add("Invalid format e-mail.");
        }
        if (dto.getHireDate() != null) {
            if (dto.getHireDate().isAfter(LocalDate.now())) {
                validations.add("Invalid hire date.");
            }

            LocalDate pastDate = LocalDate.of(1950,1,1);
            if (dto.getHireDate().isBefore(pastDate)) {
                validations.add("Invalid hire date.");
            }
        }

        return validations;
    }

    public ValidationsDto validateEmployees(List<EmployeeRequestDto> employees){
        List<String> validations =  employees.stream()
                                                    .map(item -> this.validatedEmployee(item, OPERATION_CREATE))
                                                    .flatMap(List::stream)
                                                    .collect(Collectors.toList());

        return new ValidationsDto(validations);
    }
}
