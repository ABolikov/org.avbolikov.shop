package org.avbolikov.shop.exception;

import java.util.function.Supplier;

public class NotFoundException extends RuntimeException implements Supplier<NotFoundException> {
    private String nameEntity;

    public NotFoundException(String nameEntity) {
        this.nameEntity = nameEntity;
    }

    public String getNameEntity() {
        return nameEntity;
    }

    public void setNameEntity(String nameEntity) {
        this.nameEntity = nameEntity;
    }

    @Override
    public NotFoundException get() {
        return this;
    }
}
