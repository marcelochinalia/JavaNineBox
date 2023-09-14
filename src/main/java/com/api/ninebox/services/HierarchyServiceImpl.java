package com.api.ninebox.services;

import com.api.ninebox.models.Employee;
import com.api.ninebox.dto.HierarchyRequestDto;
import com.api.ninebox.dto.HierarchyResponseDto;
import com.api.ninebox.dto.ValidationsDto;
import com.api.ninebox.models.repositories.EmployeeRepository;
import com.api.ninebox.services.interfaces.HierarchyService;
import com.api.ninebox.services.exceptions.NineBoxValidatedException;
import com.api.ninebox.services.utils.HierarchyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HierarchyServiceImpl implements HierarchyService {
    @Autowired
    private EmployeeRepository repo;
    @Autowired
    private HierarchyMapper mapper;

    @Transactional
    public void updateHierarchy(List<HierarchyRequestDto> requestDto) {
        List<String> validations = new ArrayList<>();
        List<String> validationsItem = new ArrayList<>();

        for (HierarchyRequestDto item: requestDto) {
            validationsItem.clear();

            if (item.getBossId()==0)
                validationsItem.add("Boss Id is required.");

            if (item.getEmployeesId().size()==0)
                validationsItem.add("Employees Id is empty in bossId: " + item.getBossId() + ".");

            for (Integer empl: item.getEmployeesId()){
                if (empl.intValue()==0) {
                    validationsItem.add("Employeed Id is required for bossId: " + item.getBossId() + ".");
                    break;
                }
            }

            if (validationsItem.size() > 0)
                validations.addAll(validationsItem);
            else
                repo.updateHierarchy(item.getBossId(),
                                     item.getEmployeesId());
        }

        if (validations.size() > 0)
            throw new NineBoxValidatedException(new ValidationsDto(validations));
    }

    public List<HierarchyResponseDto> findHierarchyByCompanyId(Integer companyId) {
        List<HierarchyResponseDto> ret = new ArrayList<>();
        List<Integer> leadersId = this.repo.findBossCompanyId(companyId);

        for (Integer leaderId : leadersId) {
            HierarchyResponseDto dto = findSubordinatesByBossId(leaderId);
            if (dto != null)
                ret.add(dto);
        }

        return ret;
    }

    public HierarchyResponseDto findSubordinatesByBossId(Integer bossId) {
        Optional<Employee> model = this.repo.findById(bossId);

        if (model.isPresent()) {
            HierarchyResponseDto bossDto = mapper.convertModelToDto(model.get());
            this.recursiveSearch(bossDto);
            return bossDto;
        }

        return null;
    }

    private void recursiveSearch(HierarchyResponseDto dto) {
        List<Employee> employees = this.repo.findAllByBossId(dto.getId());
        for (Employee employee : employees) {
            HierarchyResponseDto colab = mapper.convertModelToDto(employee);
            dto.getCollaborators().add(colab);
            recursiveSearch(colab);
        }
    }
}
