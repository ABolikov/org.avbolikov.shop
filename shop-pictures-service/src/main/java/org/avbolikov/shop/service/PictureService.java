package org.avbolikov.shop.service;

import org.avbolikov.shop.entity.pictures.PictureData;

import java.util.Optional;

public interface PictureService {

    Optional<String> getPictureContentTypeById(int id);

    Optional<byte[]> getPictureDataById(int id);

    PictureData createPictureData(byte[] picture);

    void delete(Integer id);
}
