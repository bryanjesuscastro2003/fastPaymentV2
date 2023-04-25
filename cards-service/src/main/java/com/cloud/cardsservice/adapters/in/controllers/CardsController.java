package com.cloud.cardsservice.adapters.in.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cards")
public class CardsController {

    @GetMapping("/myCards")
    public String myCards(){
        return "my cards";
    }

    @GetMapping("/test")
    public String test(){
        return " test";
    }
}
