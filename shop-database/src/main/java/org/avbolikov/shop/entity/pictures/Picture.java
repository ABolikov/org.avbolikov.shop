package org.avbolikov.shop.entity.pictures;

import org.avbolikov.shop.entity.products.Product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "picture")
public class Picture implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "content_type")
    private String contentType;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            optional = false,
            orphanRemoval = true)
    @JoinColumn(name = "picture_data_id")
    private PictureData pictureData;

    @ManyToMany(mappedBy = "pictures")
    private List<Product> products;

    public Picture() {
    }

    public Picture(String name, String contentType, PictureData pictureData) {
        this.name = name;
        this.contentType = contentType;
        this.pictureData = pictureData;
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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public PictureData getPictureData() {
        return pictureData;
    }

    public void setPictureData(PictureData pictureData) {
        this.pictureData = pictureData;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
