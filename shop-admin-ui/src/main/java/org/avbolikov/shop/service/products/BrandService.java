package org.avbolikov.shop.service.products;

import org.avbolikov.shop.representation.products.BrandRepr;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BrandService {

    List<BrandRepr> findAll();

    Optional<BrandRepr> findById(Integer id);

    void save(BrandRepr brandRepr) throws IOException;

    void delete(Integer id);
}
