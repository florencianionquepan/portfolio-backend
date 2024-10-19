package com.flower.portfolio.configuration;

import com.flower.portfolio.model.Role;
import com.flower.portfolio.model.RoleName;
import com.flower.portfolio.auth.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitialDataConfig {

    private final RoleRepository roleRepository;

    public InitialDataConfig(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init(){
        Role role_user=new Role();
        role_user.setId(1);
        role_user.setName(RoleName.ROLE_USER);
        this.roleRepository.save(role_user);
        Role role_admin=new Role();
        role_admin.setId(2);
        role_admin.setName(RoleName.ROLE_ADMIN);
        this.roleRepository.save(role_admin);
    }
}
