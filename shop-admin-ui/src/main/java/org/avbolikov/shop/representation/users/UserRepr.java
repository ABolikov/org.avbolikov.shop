package org.avbolikov.shop.representation.users;

import org.avbolikov.shop.entity.users.Role;
import org.avbolikov.shop.entity.users.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

public class UserRepr implements Serializable {

    private Integer id;

    @NotBlank(message = "Необходимо указать логин")
    private String name;

    @NotBlank(message = "Необходимо указать пароль")
    private String password;

    @Email(message = "Проверте указанный почтовый адрес")
    private String email;

    private Integer age;

    @NotEmpty(message = "Необходимо выбрать роли для юзера")
    public List<Role> roles;

    public UserRepr() {
    }

    public UserRepr(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.age = user.getAge();
        this.roles = user.getRoles();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return name;
    }
}
