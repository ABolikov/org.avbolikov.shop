package org.avbolikov.shop.service.users;

import org.avbolikov.shop.entity.users.User;
import org.avbolikov.shop.repositories.UserRepository;
import org.avbolikov.shop.representation.users.UserRepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserRepr> findAll() {
        return userRepository
                .findAll()
                .stream()
                .map(UserRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserRepr> findById(Integer id) {
        return userRepository.
                findById(id).
                map(UserRepr::new);
    }

    @Override
    public Optional<UserRepr> findByName(String name) {
        return userRepository.
                findByName(name).
                map(UserRepr::new);
    }

    @Override
    public void save(UserRepr userRepr) {
        User user = new User();
        user.setId(userRepr.getId());
        user.setName(userRepr.getName());
        user.setPassword(passwordEncoder.encode(userRepr.getPassword()));
        user.setEmail(userRepr.getEmail());
        user.setAge(userRepr.getAge());
        user.setRoles(userRepr.getRoles());
        userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
