package com.flower.portfolio.auth.repository;

import com.flower.portfolio.model.Role;
import com.flower.portfolio.model.RoleName;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role,Long> {
    Optional<Role> findByName(RoleName name);
}
