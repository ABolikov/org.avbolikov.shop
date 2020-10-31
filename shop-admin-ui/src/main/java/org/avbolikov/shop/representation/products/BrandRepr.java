package org.avbolikov.shop.representation.products;

import org.avbolikov.shop.entity.products.Brand;
import org.avbolikov.shop.entity.products.Product;
import org.avbolikov.shop.representation.PictureRepr;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class BrandRepr implements Serializable {

    private Integer id;

    private String name;

    private List<Product> products;

    private PictureRepr picture;

    private MultipartFile[] newPictures;

    public BrandRepr() {
    }

    public BrandRepr(Brand brand) {
        this.id = brand.getId();
        this.name = brand.getName();
        if (brand.getPicture() != null) {
            this.picture = new PictureRepr(brand.getPicture());
        }
        this.products = brand.getProducts();
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

    public PictureRepr getPicture() {
        return picture;
    }

    public void setPicture(PictureRepr picture) {
        this.picture = picture;
    }

    public MultipartFile[] getNewPictures() {
        return newPictures;
    }

    public void setNewPictures(MultipartFile[] newPictures) {
        this.newPictures = newPictures;
    }
}
