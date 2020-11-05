package org.avbolikov.shop.service;

import org.avbolikov.shop.entity.pictures.Picture;
import org.avbolikov.shop.entity.pictures.PictureData;
import org.avbolikov.shop.repositories.PictureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

public class PictureServiceFileImpl implements PictureService {

    private static final Logger logger = LoggerFactory.getLogger(PictureServiceFileImpl.class);

    @Value("${picture.storage.path}")
    private String storagePath;

    private final PictureRepository pictureRepository;

    public PictureServiceFileImpl(PictureRepository pictureRepository) {
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
                .filter(pic -> pic.getPictureData().getFileName() != null)
                .map(pic -> Paths.get(storagePath, pic.getPictureData().getFileName()))
                .filter(Files::exists)
                .map(path -> {
                    try {
                        return Files.readAllBytes(path);
                    } catch (IOException ex) {
                        logger.error("Can't read picture file ", ex);
                        throw new RuntimeException(ex);
                    }
                });
    }

    @Override
    public PictureData createPictureData(byte[] picture) {
        String fileName = UUID.randomUUID().toString();
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(storagePath + "/" + fileName))) {
            outputStream.write(picture);
        } catch (IOException ex) {
            logger.error("Can't create picture file ", ex);
            throw new RuntimeException(ex);
        }

        return new PictureData(fileName);
    }

    @Override
    public void delete(Integer id) {
        pictureRepository.deleteById(id);
    }
}
