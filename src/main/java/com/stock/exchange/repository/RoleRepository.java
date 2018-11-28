package com.stock.exchange.repository;

import com.stock.exchange.domain.user.Role;
import org.springframework.data.repository.CrudRepository;


public interface RoleRepository extends CrudRepository<Role, Long> {
}
