package org.avbolikov.shop.service;

import org.avbolikov.shop.entity.products.Product;
import org.avbolikov.shop.repositories.ProductRepository;
import org.avbolikov.shop.representation.ProductRepr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductRepr> findAll(Specification<Product> specification) {
        logger.info("Product findAll call");
        return productRepository.findAll(specification).stream()
                .map(ProductRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductRepr> findById(Integer id) {
        return productRepository.findById(id)
                .map(ProductRepr::new);
    }
}
