package com.flower.portfolio.auth.service;

import com.flower.portfolio.auth.dto.UserDTO;
import com.flower.portfolio.auth.dto.mapper.IUserMapper;
import com.flower.portfolio.auth.repository.RoleRepository;
import com.flower.portfolio.model.Role;
import com.flower.portfolio.model.RoleName;
import com.flower.portfolio.model.User;
import com.flower.portfolio.auth.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final UserRepository repo;
    private final IUserMapper mapper;
    private final RoleRepository rolRepo;

    public UserService(UserRepository repo,
                       IUserMapper mapper,
                       RoleRepository rolRepo) {
        this.repo = repo;
        this.mapper = mapper;
        this.rolRepo = rolRepo;
    }

    @Override
    @Transactional
    public User createOrUpdateUser(UserDTO dto, String provider) {
        User user;
        Optional<User> oUser = findUserByProvider(dto, provider);
        if (oUser.isEmpty()) {
            User userToCreate = this.mapper.mapToEntity(dto, provider);
            User userWithRole = this.addRoleToUser(userToCreate);
            user=this.repo.save(userWithRole);
        } else {
            user=updateLastLogin(oUser);
        }
        return user;
    }

    private Optional<User> findUserByProvider(UserDTO dto, String provider) {
        if (provider.equals("github")) {
            return this.repo.findByUsernameAndProvider(dto.getUsername(), "github");
        } else if (provider.equals("google")) {
            return this.repo.findByEmailAndProvider(dto.getUsername(), "google");
        }
        return Optional.empty();
    }

    private User updateLastLogin(Optional<User> oUser){
        LocalDate lastLogin=LocalDate.now();
        User user=oUser.get();
        user.setLastLogin(lastLogin);
        return this.repo.save(user);
    }

    private User addRoleToUser(User user){
        List<Role> roles= new ArrayList<Role>();
        Optional<Role> oRole=this.rolRepo.findByName(RoleName.ROLE_USER);
        if(oRole.isEmpty()){
            throw new RuntimeException("Luego agregame excepcion personalizada");
        }
        Role role=oRole.get();
        roles.add(role);
        user.setRoles(roles);
        return user;
    }

}
