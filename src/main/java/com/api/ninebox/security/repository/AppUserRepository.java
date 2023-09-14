package com.api.ninebox.security.repository;

import com.api.ninebox.security.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByUsername(@Param("username") String username);
}
