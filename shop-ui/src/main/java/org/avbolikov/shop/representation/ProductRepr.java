package org.avbolikov.shop.representation;

import org.avbolikov.shop.entity.pictures.Picture;
import org.avbolikov.shop.entity.products.Product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRepr implements Serializable {

    private Integer id;

    private String name;

    private BigDecimal cost;

    private String brandName;

    private List<Integer> pictureIds;

    public ProductRepr() {
    }

    public ProductRepr(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.cost = product.getCost();
        this.brandName = product.getBrand().getName();
        this.pictureIds = product.getPictures().stream().map(Picture::getId).collect(Collectors.toList());
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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<Integer> getPictureIds() {
        return pictureIds;
    }

    public void setPictureIds(List<Integer> pictureIds) {
        this.pictureIds = pictureIds;
    }
}
