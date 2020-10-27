package org.avbolikov.shop.controller;

import org.avbolikov.shop.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView notFoundExceptionHandler(NotFoundException notFoundException) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("entity", notFoundException.getNameEntity() + " " + HttpStatus.NOT_FOUND.getReasonPhrase());
        modelAndView.addObject("httpStatus", HttpStatus.NOT_FOUND.value());
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView exceptionHandler(Exception exception) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("entity", exception.fillInStackTrace());
        modelAndView.addObject("httpStatus", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return modelAndView;
    }
}
