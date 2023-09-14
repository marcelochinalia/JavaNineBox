package com.api.ninebox.models.repositories;

import com.api.ninebox.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findAllByBossId(Integer bossId);

    @Query(value = "select employee_id, company_id, employee_full_name from employee where employee_boss_id is null and company_id = :companyId", nativeQuery = true)
    List<Integer> findBossCompanyId(@Param("companyId") Integer companyId);

    @Query(value = " select e.employee_id, e.employee_activated, e.employee_actual_position," +
                   "        e.employee_area, e.employee_boss_id,e.company_id, e.employee_email," +
                   "        e.employee_external_code,e.employee_full_name,e.employee_hire_date " +
                   "   from employee e join company c" +
                   "     on c.id = e.company_id" +
                   "  where e.employee_activated = true" +
                   "    and e.company_id = :companyId " +
                   "    and lower(e.employee_full_name) like %:partialName%", nativeQuery = true)
    List<Employee> findAllByFullName(@Param("companyId") Integer companyId, @Param("partialName") String partialName);

    @Modifying
    @Query(value = "update employee set employee_boss_id = :bossId where employee_id in (:employeesId) ", nativeQuery = true)
    void updateHierarchy(@Param("bossId") Integer bossId, @Param("employeesId") List<Integer> employeesId);
}
