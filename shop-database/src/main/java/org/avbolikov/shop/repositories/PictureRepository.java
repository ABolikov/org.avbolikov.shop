package org.avbolikov.shop.repositories;

import org.avbolikov.shop.entity.products.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {

}
