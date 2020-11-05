package org.avbolikov.shop.service.products;

import org.avbolikov.shop.entity.pictures.Picture;
import org.avbolikov.shop.entity.pictures.PictureData;
import org.avbolikov.shop.entity.products.Brand;
import org.avbolikov.shop.repositories.BrandRepository;
import org.avbolikov.shop.repositories.PictureRepository;
import org.avbolikov.shop.representation.products.BrandRepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final PictureRepository pictureRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, PictureRepository pictureRepository) {
        this.brandRepository = brandRepository;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public List<BrandRepr> findAll() {
        return brandRepository
                .findAll()
                .stream()
                .map(BrandRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BrandRepr> findById(Integer id) {
        return brandRepository
                .findById(id)
                .map(BrandRepr::new);
    }

    @Override
    public void save(BrandRepr brandRepr) throws IOException {
        Brand brand = new Brand();
        brand.setId(brandRepr.getId());
        brand.setName(brandRepr.getName());
        brand.setProducts(brandRepr.getProducts());
        if (brandRepr.getNewPictures() != null) {
            for (MultipartFile newPicture : brandRepr.getNewPictures()) {
                if (!Objects.equals(newPicture.getContentType(), "application/octet-stream")) {
                    if (brandRepr.getId() != null &&
                            brandRepository.findById(brandRepr.getId()).get().getPicture() != null) {
                        int test = brandRepository.findById(brandRepr.getId()).get().getPicture().getId();
                        pictureRepository.deleteById(test);
                    }
                    brand.setPicture(new Picture(
                            newPicture.getOriginalFilename(),
                            newPicture.getContentType(),
                            new PictureData(newPicture.getBytes())));
                } else {
                    if (brandRepr.getId() != null) {
                        brand.setPicture(brandRepository.findById(brandRepr.getId()).get().getPicture());
                    }
                }
            }
        }
        brandRepository.save(brand);
    }

    @Override
    public void delete(Integer id) {
        brandRepository.deleteById(id);
    }
}
