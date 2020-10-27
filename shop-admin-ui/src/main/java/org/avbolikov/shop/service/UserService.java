package org.avbolikov.shop.service;

import org.avbolikov.shop.representation.users.UserRepr;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserRepr> findAll();

    Optional<UserRepr> findById(Integer id);

    Optional<UserRepr> findByName(String name);

    void save(UserRepr userRepr);

    void delete(Integer id);
}
