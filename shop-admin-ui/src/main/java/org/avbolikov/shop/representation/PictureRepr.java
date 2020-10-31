package org.avbolikov.shop.representation;

import org.avbolikov.shop.entity.pictures.Picture;

import java.io.Serializable;

public class PictureRepr implements Serializable {

    private Integer id;

    private String name;

    private String contentType;

    public PictureRepr() {
    }

    public PictureRepr(Picture picture) {
        this.id = picture.getId();
        this.name = picture.getName();
        this.contentType = picture.getContentType();
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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
