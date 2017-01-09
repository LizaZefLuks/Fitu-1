package com.linoge.repositories;

import com.linoge.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by Tim on 08.01.2017.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByUsername(String userName);
}
