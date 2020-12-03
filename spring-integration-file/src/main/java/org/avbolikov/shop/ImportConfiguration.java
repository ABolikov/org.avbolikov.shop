package org.avbolikov.shop;

import org.avbolikov.shop.entity.products.Brand;
import org.avbolikov.shop.entity.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.*;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.jpa.dsl.Jpa;
import org.springframework.integration.jpa.dsl.JpaUpdatingOutboundEndpointSpec;
import org.springframework.integration.jpa.support.PersistMode;
import org.springframework.integration.json.JsonToObjectTransformer;

import javax.persistence.EntityManagerFactory;
import java.io.File;

@Configuration
public class ImportConfiguration {

    @Value("${source.directory.path}")
    private String sourceDirectoryPath;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public MessageSource<File> sourceDirectory() {
        FileReadingMessageSource fileReadingMessageSource = new FileReadingMessageSource();
        fileReadingMessageSource.setDirectory(new File(sourceDirectoryPath));
        fileReadingMessageSource.setAutoCreateDirectory(true);
        return fileReadingMessageSource;
    }

    @Bean
    public JpaUpdatingOutboundEndpointSpec jpaPersistHandlerBrand() {
        return Jpa.outboundAdapter(this.entityManagerFactory)
                .entityClass(Brand.class)
                .persistMode(PersistMode.PERSIST);
    }

    @Bean
    public JpaUpdatingOutboundEndpointSpec jpaPersistHandlerProduct() {
        return Jpa.outboundAdapter(this.entityManagerFactory)
                .entityClass(Product.class)
                .persistMode(PersistMode.PERSIST);
    }

    @Bean
    public IntegrationFlow fileMoveFlow() {
        return IntegrationFlows.from(sourceDirectory(), conf -> conf.poller(Pollers.fixedDelay(10000))) //указваем точку начала действия, которая выполняет сканированеи и чтение указанного ресурса
                .<File>filter(file -> file.getName().endsWith(".json")) //проверка формата
                .<File, Boolean>route(file -> file.getName().startsWith("brand"), //проверка какой из файлов взят, в обработку, чтобы перенаправить в необходимую обработку
                        mapping -> mapping //Действие по результатам проверки
                                .subFlowMapping(true, //истина
                                        brand -> brand
                                                .transform(new JsonToObjectTransformer(Brand.class)) //приобразуем читаемый файл в объект Brand
                                                .handle(jpaPersistHandlerBrand(), ConsumerEndpointSpec::transactional)) //указваем точку завршения действия, пишет в БД
                                .subFlowMapping(false, // ложь
                                        product -> product
                                                .transform(new JsonToObjectTransformer(Product.class))//приобразуем читаемый файл в объект Product
                                                .handle(jpaPersistHandlerProduct(), ConsumerEndpointSpec::transactional)))//указваем точку завршения действия, пишет в БД
                .get();
    }

}
