package org.avbolikov.shop.service;

import org.avbolikov.shop.entity.products.Product;
import org.avbolikov.shop.representation.ProductRepr;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductRepr> findAll(Specification<Product> specification);

    Optional<ProductRepr> findById(Integer id);
}
