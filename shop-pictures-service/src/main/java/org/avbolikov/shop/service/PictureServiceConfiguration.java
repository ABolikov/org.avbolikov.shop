package org.avbolikov.shop.service;

import org.avbolikov.shop.repositories.PictureRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PictureServiceConfiguration {

    @Bean
    @ConditionalOnProperty(name = "picture.storage.type", havingValue = "database")
    public PictureService pictureServiceBlobImpl(PictureRepository pictureRepository) {
        return new PictureServiceBlobImpl(pictureRepository);
    }

    @Bean
    @ConditionalOnProperty(name = "picture.storage.type", havingValue = "files")
    public PictureService pictureServiceFileImpl(PictureRepository pictureRepository) {
        return new PictureServiceFileImpl(pictureRepository);
    }
}
