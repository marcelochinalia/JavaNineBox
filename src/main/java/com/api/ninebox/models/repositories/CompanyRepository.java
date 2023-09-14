package com.api.ninebox.models.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.api.ninebox.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>{
    @Query(value = "select * from company c where upper(c.name) like upper(concat('%', ?1,'%')) order by c.name asc", nativeQuery = true)
    List<Company> findByCompanyName(String CompanyName);
}

