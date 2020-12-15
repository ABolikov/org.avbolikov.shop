package org.avbolikov.shop.controller;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import liquibase.pro.packaged.eq;
import org.avbolikov.shop.entity.pictures.Picture;
import org.avbolikov.shop.entity.products.Brand;
import org.avbolikov.shop.entity.products.Category;
import org.avbolikov.shop.entity.products.Product;
import org.avbolikov.shop.repositories.BrandRepository;
import org.avbolikov.shop.repositories.CategoryRepository;
import org.avbolikov.shop.repositories.PictureRepository;
import org.avbolikov.shop.repositories.ProductRepository;
import org.avbolikov.shop.service.PictureService;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;


@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class SpringShopControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private PictureService pictureService;

    @MockBean
    private EurekaClient eurekaClient;

    @Value("${picture.storage.path}")
    private String storagePath;

    @BeforeEach
    public void init() {
        InstanceInfo instanceInfo = mock(InstanceInfo.class);
        when(instanceInfo.getHomePageUrl()).thenReturn("mock-homepage");
        when(eurekaClient.getNextServerFromEureka(anyString(),anyBoolean()))
                .thenReturn(instanceInfo);
    }

    @Test
    public void testProductDetails() throws Exception {
        Brand brand;
        File file = new File(String.valueOf(Paths.get(storagePath + "/" + "0ba5d8f9-ccdd-42c2-aced-0a61f75f41c6")));
        try (InputStream in = new BufferedInputStream(new FileInputStream(file))) {
            brand = new Brand();
            brand.setName("brand");
            brand.setPicture(new Picture("picture", "image/png",
                    pictureService.createPictureData(CharStreams.toString(new InputStreamReader(in, Charsets.UTF_8)).getBytes())));
            brand = brandRepository.save(brand);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        Category category = new Category();
        category.setName("category");
        category = categoryRepository.save(category);
        List<Category> categories = new ArrayList<>();
        categories.add(category);

        Product product = new Product();
        product.setName("Product");
        product.setCost(new BigDecimal("222.33"));
        product.setBrand(brand);
        product.setCategories(categories);
        product = productRepository.save(product);

        mvc.perform(get("/product/" + product.getId()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("product"))
                .andExpect(model().attributeExists("product", "lineItems"));
    }
}
