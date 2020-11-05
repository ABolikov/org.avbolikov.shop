package org.avbolikov.shop.controller;

import org.avbolikov.shop.entity.pictures.Picture;
import org.avbolikov.shop.exception.NotFoundException;
import org.avbolikov.shop.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/admin/picture")
public class PictureController {

    private final PictureRepository pictureRepository;

    @Autowired
    public PictureController(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;

    }

    @GetMapping("/{pictureId}")
    private void downloadPicture(@PathVariable("pictureId") Integer pictureId,
                                 HttpServletResponse response) throws IOException {
        Optional<Picture> picture = pictureRepository.findById(pictureId);
        if (picture.isPresent()) {
            response.setContentType(picture.get().getContentType());
            response.getOutputStream().write(picture.get().getPictureData().getData());
            return;
        }
        throw new NotFoundException("Picture");
    }
}
