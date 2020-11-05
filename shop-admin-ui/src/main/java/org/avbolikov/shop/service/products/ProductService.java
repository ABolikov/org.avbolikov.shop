package org.avbolikov.shop.service.products;

import org.avbolikov.shop.representation.products.ProductRepr;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductRepr> findAll();

    Optional<ProductRepr> findById(Integer id);

    void save(ProductRepr productRepr) throws IOException;

    void delete(Integer id);
}
