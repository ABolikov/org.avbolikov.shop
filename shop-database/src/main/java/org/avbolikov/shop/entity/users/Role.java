package org.avbolikov.shop.entity.users;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Поле \"Наименование роли\" обязательно для заполнения")
    @Column(name = "name")
    private String name;

    @ManyToMany(
            mappedBy = "roles",
            fetch = FetchType.LAZY
    )
    public List<User> users;

    public Role() {
    }

    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @PreRemove
    public void preRemove() {
        users.forEach(user -> user.getRoles().remove(this));
    }

    @Override
    public String toString() {
        return name;
    }
}
