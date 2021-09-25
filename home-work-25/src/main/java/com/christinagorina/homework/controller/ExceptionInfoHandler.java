package com.christinagorina.homework.controller;

import com.christinagorina.homework.util.NotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionInfoHandler {
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFoundException(HttpServletRequest req, NotFoundException e) {
        return createModelAndView(req, e, "Some element NotFound");
    }


    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception e) {
        return createModelAndView(req, e, "Some error");
    }

    private ModelAndView createModelAndView(HttpServletRequest req, Exception e, String message) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exception", e);
        modelAndView.addObject("url", req.getRequestURL());
        modelAndView.addObject("message", message);
        return modelAndView;
    }
}