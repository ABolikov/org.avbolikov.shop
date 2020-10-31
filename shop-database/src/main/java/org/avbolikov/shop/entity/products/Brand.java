package org.avbolikov.shop.entity.products;

import org.avbolikov.shop.entity.pictures.Picture;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "brand")
public class Brand implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Необходимо указать наименование бренда")
    @Column(name = "name")
    private String name;

    @OneToMany(
            mappedBy = "brand",
            fetch=FetchType.LAZY
    )
    private List<Product> products;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            optional = false,
            orphanRemoval = true)
    @JoinColumn(name = "picture_id")
    private Picture picture;

    public Brand() {
    }

    public Brand(Integer id, String name) {
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

    public void setName(String roleName) {
        this.name = roleName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return name;
    }

    @PreRemove
    public void preRemove() {
        products.forEach(product -> product.setBrand(null));
    }
}
