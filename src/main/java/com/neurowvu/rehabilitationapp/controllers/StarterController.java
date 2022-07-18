package com.neurowvu.rehabilitationapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class StarterController {

    @GetMapping
    public String choice() {
        return "choice";
    }

}
