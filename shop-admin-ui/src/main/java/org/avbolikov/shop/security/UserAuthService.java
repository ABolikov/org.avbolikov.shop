package org.avbolikov.shop.security;

import org.avbolikov.shop.exception.NotFoundException;
import org.avbolikov.shop.representation.users.RoleRepr;
import org.avbolikov.shop.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;

public class UserAuthService implements UserDetailsService {

    private final UserServiceImpl userService;

    @Autowired
    public UserAuthService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userService.findByName(login)
                .map(user -> new User(
                        user.getName(),
                        user.getPassword(),
                        user.getRoles().stream().map(RoleRepr::new).collect(Collectors.toList())))
                .orElseThrow(() -> new NotFoundException("User not found", "User"));
    }
}
