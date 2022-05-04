package com.example.coursebooking.controllers;

import com.example.coursebooking.exception.AppBasicException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GlobalExceptionHandlerController {

    @ExceptionHandler(Throwable.class)
    public String handleException(Throwable e, RedirectAttributes redirectAttributes) {
        if (e instanceof AppBasicException) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/error";
    }
}
