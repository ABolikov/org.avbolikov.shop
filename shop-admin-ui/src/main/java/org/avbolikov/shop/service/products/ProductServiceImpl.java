package org.avbolikov.shop.service.products;

import org.avbolikov.shop.entity.pictures.Picture;
import org.avbolikov.shop.entity.pictures.PictureData;
import org.avbolikov.shop.entity.products.Product;
import org.avbolikov.shop.repositories.ProductRepository;
import org.avbolikov.shop.representation.products.ProductRepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductRepr> findAll() {
        return productRepository
                .findAll()
                .stream()
                .map(ProductRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductRepr> findById(Integer id) {
        return productRepository
                .findById(id)
                .map(ProductRepr::new);
    }

    @Override
    public void save(ProductRepr productRepr) {
        Product product = new Product();
        product.setId(productRepr.getId());
        product.setName(productRepr.getName());
        product.setCost(productRepr.getCost());
        product.setBrand(productRepr.getBrand());
        product.setCategories(productRepr.getCategories());
        if (productRepr.getNewPictures() != null) {
            for (MultipartFile newPicture : productRepr.getNewPictures()) {
                if (!Objects.equals(newPicture.getContentType(), "application/octet-stream")) {
                    if (productRepr.getId() == null || productRepository.findById(productRepr.getId()).get().getPictures() == null) {
                        product.setPictures(new ArrayList<>());
                    } else {
                        product.setPictures(productRepository.findById(productRepr.getId()).get().getPictures());
                    }
                    try {
                        product.getPictures().add(new Picture(
                                newPicture.getOriginalFilename(),
                                newPicture.getContentType(),
                                new PictureData(newPicture.getBytes())));
                    } catch (IOException exception) {
                        exception.fillInStackTrace();
                    }
                } else if (productRepr.getId() != null) {
                    product.setPictures(productRepository.findById(productRepr.getId()).get().getPictures());
                }
            }
        }
        productRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }
}
