package org.avbolikov.shop.representation.products;

import org.avbolikov.shop.entity.products.Brand;
import org.avbolikov.shop.entity.products.Category;
import org.avbolikov.shop.entity.products.Product;
import org.avbolikov.shop.validation.product.NotBlankBigDecimal;
import org.springframework.web.multipart.MultipartFile;
import org.avbolikov.shop.representation.PictureRepr;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRepr implements Serializable {

    private Integer id;

    @NotBlank(message = "Необходимо указать наименование продукта")
    private String name;

    @NotBlankBigDecimal(message = "Значение должно быть > 0")
    private BigDecimal cost;

    private List<Category> categories;

    private Brand brand;

    private List<PictureRepr> pictures;

    private MultipartFile[] newPictures;

    public ProductRepr() {
    }

    public ProductRepr(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.cost = product.getCost();
        this.brand = product.getBrand();
        this.categories = product.getCategories();
        this.pictures = product.getPictures().stream()
                .map(PictureRepr::new)
                .collect(Collectors.toList());
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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<PictureRepr> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureRepr> pictures) {
        this.pictures = pictures;
    }

    public MultipartFile[] getNewPictures() {
        return newPictures;
    }

    public void setNewPictures(MultipartFile[] newPictures) {
        this.newPictures = newPictures;
    }
}
