package org.avbolikov.shop.service;

import org.avbolikov.shop.representation.users.RoleRepr;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<RoleRepr> findAll();

    Optional<RoleRepr> findById(Integer id);

    void save(RoleRepr roleRepr);

    void delete(Integer id);
}
