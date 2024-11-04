package com.example.keycloak_service.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Deprecated
@RestController
@RequestMapping(value = "/item")
public class ItemController {
    @GetMapping
    public List<String> getItems() {
        List<String> items = new ArrayList<>();
        items.add("Iphone");
        items.add("Samsung");
        items.add("Xiaomi");

        return items;
    }
}
