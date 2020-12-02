package org.avbolikov.shop;

import org.avbolikov.shop.entity.products.Brand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.StandardIntegrationFlow;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.transformer.FileToStringTransformer;
import org.springframework.integration.jpa.dsl.Jpa;
import org.springframework.integration.jpa.dsl.JpaUpdatingOutboundEndpointSpec;
import org.springframework.integration.jpa.support.PersistMode;
import org.springframework.integration.json.JsonToObjectTransformer;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.messaging.MessageHandler;

import javax.persistence.EntityManagerFactory;
import java.io.File;

@Configuration
public class ImportConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ImportConfiguration.class);

    @Value("${source.directory.path}")
    private String sourceDirectoryPath;

//    @Value("${dest.directory.path}")
//    private String destDirectoryPath;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public MessageSource<File> sourceDirectory() {
        logger.info("Файл найден");
        FileReadingMessageSource fileReadingMessageSource = new FileReadingMessageSource();
        fileReadingMessageSource.setDirectory(new File(sourceDirectoryPath));
        fileReadingMessageSource.setAutoCreateDirectory(true);
        return fileReadingMessageSource;
    }

//    @Bean
//    public MessageHandler destDirectory() {
//        logger.info("Файл перемещен");
//        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File(destDirectoryPath));
//        handler.setExpectReply(false);
//        handler.setDeleteSourceFiles(true);
//        return handler;
//    }

//        @Bean
//    public IntegrationFlow fileMoveFlow() {
//            return IntegrationFlows.from(sourceDirectory(), conf -> conf.poller(Pollers.fixedDelay(2000)))
//                    .filter(msg -> ((File) msg).getName().endsWith(".txt"))//проверка формата
//                    .transform(new FileToStringTransformer()) //читаем файл
//                    .<String, String>transform(String::toUpperCase)
//                    .handle(destDirectory())
//                    .get();
//    }

    @Bean
    public JpaUpdatingOutboundEndpointSpec jpaPersistHandler() {
        return Jpa.outboundAdapter(this.entityManagerFactory)
                .entityClass(Brand.class)
                .persistMode(PersistMode.PERSIST);
    }

    @Bean
    public IntegrationFlow fileMoveFlow() {
        return IntegrationFlows.from(sourceDirectory(), conf -> conf.poller(Pollers.fixedDelay(2000)))
                .filter(msg -> ((File) msg).getName().endsWith(".json")) //проверка формата
                .transform(new JsonToObjectTransformer(Brand.class)) //приобразуем файл в объект
                .handle(jpaPersistHandler()) //вот тут ошибка..
                .get();
    }




}
