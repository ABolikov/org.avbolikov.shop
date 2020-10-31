package org.avbolikov.shop.representation.users;

import org.avbolikov.shop.entity.users.Role;
import org.avbolikov.shop.entity.users.User;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;

public class RoleRepr implements GrantedAuthority, Serializable {

    private Integer id;

    private String name;

    public List<User> users;

    public RoleRepr() {
    }

    public RoleRepr(Role role) {
        this.id = role.getId();
        this.name = role.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
