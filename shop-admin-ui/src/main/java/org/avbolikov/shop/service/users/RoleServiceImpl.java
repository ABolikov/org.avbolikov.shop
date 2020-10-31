package org.avbolikov.shop.service.users;

import org.avbolikov.shop.entity.users.Role;
import org.avbolikov.shop.repositories.RoleRepository;
import org.avbolikov.shop.representation.users.RoleRepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleRepr> findAll() {
        return roleRepository
                .findAll()
                .stream()
                .map(RoleRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RoleRepr> findById(Integer id) {
        return roleRepository
                .findById(id)
                .map(RoleRepr::new);
    }

    @Override
    public void save(RoleRepr roleRepr) {
        Role role = new Role();
        role.setId(roleRepr.getId());
        role.setName(roleRepr.getName());
        roleRepository.save(role);
    }

    @Override
    public void delete(Integer id) {
        roleRepository.deleteById(id);
    }
}
