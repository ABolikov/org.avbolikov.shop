package org.avbolikov.shop.service;

import org.avbolikov.shop.entity.pictures.Picture;
import org.avbolikov.shop.entity.products.Brand;
import org.avbolikov.shop.entity.products.Category;
import org.avbolikov.shop.entity.products.Product;
import org.avbolikov.shop.repositories.ProductRepository;
import org.avbolikov.shop.representation.ProductRepr;
import org.avbolikov.shop.specification.ProductSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private ProductService productService;

    private ProductRepository productRepository;

    @BeforeEach
    public void init() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductServiceImpl(productRepository);
    }

    private Picture createPicture(int id) {
        Picture picture = new Picture();
        picture.setId(id);
        picture.setName("Test " + id);
        return picture;
    }

    private Category createCategory(int id) {
        Category category = new Category();
        category.setId(id);
        category.setName("Category name " + id);
        return category;
    }

    private Brand createBrand(int id) {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setName("Brand name " + id);
        return brand;
    }

    @Test
    public void testFindById() {
        List<Category> categories = new ArrayList<>();
        categories.add(createCategory(1));

        List<Picture> pictures = new ArrayList<>();
        pictures.add(createPicture(1));
        pictures.add(createPicture(2));

        Product product = new Product();
        product.setId(1);
        product.setName("Product name");
        product.setCost(new BigDecimal("223.34"));
        product.setBrand(createBrand(1));
        product.setCategories(categories);
        product.setPictures(pictures);

        when(productRepository.findById(eq(1))).thenReturn(Optional.of(product));

        Optional<ProductRepr> byId = productService.findById(1);
        assertTrue(byId.isPresent());
        assertEquals(product.getId(), byId.get().getId());
        assertEquals(product.getName(), byId.get().getName());
        assertEquals(product.getCost(), byId.get().getCost());
        assertEquals(product.getBrand().getName(), byId.get().getBrandName());
        assertEquals(product.getPictures().stream().map(Picture::getId).collect(Collectors.toList()), byId.get().getPictureIds());
    }

    @Test
    public void testFindAll() {
        List<Category> categories1 = new ArrayList<>();
        categories1.add(createCategory(1));

        List<Picture> pictures1 = new ArrayList<>();
        pictures1.add(createPicture(1));
        pictures1.add(createPicture(2));

        Product product1 = new Product();
        product1.setId(1);
        product1.setName("Product name");
        product1.setCost(new BigDecimal("223.34"));
        product1.setBrand(createBrand(1));
        product1.setCategories(categories1);
        product1.setPictures(pictures1);

        List<Category> categories2 = new ArrayList<>();
        categories2.add(createCategory(1));

        List<Picture> pictures2 = new ArrayList<>();
        pictures2.add(createPicture(1));
        pictures2.add(createPicture(2));

        Product product2 = new Product();
        product2.setId(1);
        product2.setName("Product name");
        product2.setCost(new BigDecimal("223.34"));
        product2.setBrand(createBrand(1));
        product2.setCategories(categories2);
        product2.setPictures(pictures2);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        when(productRepository.findAll(eq(ProductSpecification.trueLiteral()))).thenReturn(products);

        List<ProductRepr> all = productService.findAll(ProductSpecification.trueLiteral());
        assertNotNull(all);
        assertEquals(2, products.size());
        for (int i = 0 ; i<=all.size()-1; i++) {
            assertEquals(products.get(i).getId(), all.get(i).getId());
            assertEquals(products.get(i).getName(), all.get(i).getName());
            assertEquals(products.get(i).getCost(), all.get(i).getCost());
            assertEquals(products.get(i).getBrand().getName(), all.get(i).getBrandName());
            assertEquals(products.get(i).getPictures().stream().map(Picture::getId).collect(Collectors.toList()), all.get(i).getPictureIds());
        }
    }
}
