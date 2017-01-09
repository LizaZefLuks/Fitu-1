package com.linoge.servicies;

import com.linoge.models.entities.User;
import com.linoge.models.enums.Role;
import com.linoge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Tim on 09.01.2017.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public void createUser(String userName, String password) {
        User user = User.builder()
                .username(userName)
                .password(new BCryptPasswordEncoder().encode(password))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .authorities(Arrays.asList(Role.ROLE_ADMIN,
                        Role.ROLE_USER))
                .enabled(true)
                .build();
        userRepository.saveAndFlush(user);
    }
}
