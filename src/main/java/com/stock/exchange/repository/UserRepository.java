package com.stock.exchange.repository;

import com.stock.exchange.domain.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
     Optional<User> findByUsername(String username);
}
