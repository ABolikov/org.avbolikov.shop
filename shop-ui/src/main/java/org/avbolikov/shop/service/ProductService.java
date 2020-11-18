package org.avbolikov.shop.service;

import org.avbolikov.shop.representation.ProductRepr;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductRepr> findAll();

    Optional<ProductRepr> findById(Integer id);

}
