package org.avbolikov.shop.controller;

import org.avbolikov.shop.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.avbolikov.shop.service.PictureService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/admin/picture")
public class PictureController {

    private final PictureService pictureService;

    @Autowired
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/{pictureId}")
    private void downloadPicture(@PathVariable("pictureId") Integer pictureId,
                                 HttpServletResponse response) throws IOException {
        Optional<String> optional = pictureService.getPictureContentTypeById(pictureId);
        if (optional.isPresent()) {
            response.setContentType(optional.get());
            response.getOutputStream().write(pictureService.getPictureDataById(pictureId).get());
        } else {
            throw new NotFoundException("Picture");
        }
    }
}
