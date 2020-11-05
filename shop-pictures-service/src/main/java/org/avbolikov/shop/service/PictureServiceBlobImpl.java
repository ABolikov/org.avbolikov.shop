package org.avbolikov.shop.service;

import org.avbolikov.shop.entity.pictures.Picture;
import org.avbolikov.shop.entity.pictures.PictureData;
import org.avbolikov.shop.repositories.PictureRepository;

import java.util.Optional;

public class PictureServiceBlobImpl implements PictureService {

    private final PictureRepository pictureRepository;

    public PictureServiceBlobImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Optional<String> getPictureContentTypeById(int id) {
        return pictureRepository.findById(id)
                .map(Picture::getContentType);
    }

    @Override
    public Optional<byte[]> getPictureDataById(int id) {
        return pictureRepository.findById(id)
                .filter(pic -> pic.getPictureData().getData() != null)
                .map(pic -> pic.getPictureData().getData());
    }

    @Override
    public PictureData createPictureData(byte[] picture) {
        return new PictureData(picture);
    }

    @Override
    public void delete(Integer id) {
        pictureRepository.deleteById(id);
    }

}
