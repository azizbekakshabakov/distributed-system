package com.example.keycloak_service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Deprecated
@RestController
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
public class AdminController {

//    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/userlist")
    public List<String> userList() {
        return Arrays.asList("ilyas", "artem", "tanat", "nataliya");
    }
}
