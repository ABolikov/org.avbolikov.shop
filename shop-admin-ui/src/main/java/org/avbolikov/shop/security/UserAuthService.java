package org.avbolikov.shop.security;

import org.avbolikov.shop.exception.NotFoundException;
import org.avbolikov.shop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

public class UserAuthService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByName(login)
                .map(user -> new User(
                        user.getName(),
                        user.getPassword(),
//                      user.getRoles()))
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))))
                .orElseThrow(() -> new NotFoundException("User not found", "User"));
    }
}
