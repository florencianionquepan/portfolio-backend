package com.flower.portfolio.service.implementations;

import com.flower.portfolio.auth.dto.UserDTO;
import com.flower.portfolio.auth.dto.mapper.IUserMapper;
import com.flower.portfolio.model.User;
import com.flower.portfolio.repository.UserRepository;
import com.flower.portfolio.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final UserRepository repo;
    private final IUserMapper mapper;

    public UserService(UserRepository repo,
                       IUserMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public void createOrUpdateUser(UserDTO dto, String provider) {
        LocalDate lastLogin=LocalDate.now();
        if(provider.equals("github")){
            Optional<User> oUser=this.repo.findByUsernameAndProvider(dto.getUsername(),"github");
            if(oUser.isEmpty()){
                this.repo.save(this.mapper.mapToEntity(dto,"github"));
            }else{
                User user=oUser.get();
                user.setLastLogin(lastLogin);
                this.repo.save(user);
            }
        }else if(provider.equals("google")){
            //....
        }
    }

}
