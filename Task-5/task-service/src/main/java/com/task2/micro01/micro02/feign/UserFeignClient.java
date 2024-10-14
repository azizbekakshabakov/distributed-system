package com.task2.micro01.micro02.feign;

import com.task2.micro01.micro02.dto.User;
import com.task2.micro01.micro02.dto.UserLoginDto;
import com.task2.micro01.micro02.dto.UserTokenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "user-feign-client", url = "${feign.user.url}")
public interface UserFeignClient {
    @GetMapping(value = "/users")
    List<User> getUsers();

    @GetMapping(value = "/users/{id}")
    User getUserById(@PathVariable(name = "id") Long id);

    @PostMapping(value = "/users")
    User addUser(@RequestBody User user);

    @PostMapping(value = "/login/token")
    UserTokenDto authenticate(@RequestBody UserLoginDto userLoginDto);
}
