package com.flower.portfolio.auth.repository;

import com.flower.portfolio.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsernameAndProvider(String username, String provider);
    Optional<User> findByEmailAndProvider(String email,String provider);
}
