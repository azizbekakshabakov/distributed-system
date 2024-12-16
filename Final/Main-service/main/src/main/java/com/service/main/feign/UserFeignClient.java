package com.service.main.feign;

import com.service.main.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-feign-client", url = "${feign.user.url}")
public interface UserFeignClient {
//    @GetMapping(value = "/users")
//    List<User> getUsers();

//    @GetMapping(value = "/users/{id}")
//    User getUserById(@PathVariable(name = "id") Long id);

//    @PostMapping(value = "/users")
//    User addUser(@RequestBody User user);
    //////////////////////////////////////////////

    @PostMapping(value = "/login/token")
    UserTokenDto authenticate(@RequestBody UserLoginDto userLoginDto);

    @PostMapping(value = "/user/create")
    UserDto createUser(UserCreateDto userCreateDto);

    @GetMapping(value = "/user/current-user-name")
    String getCurrentUserName(@RequestHeader("Authorization") String authorization);

    @GetMapping(value = "/user/current-user")
    UserDto getCurrentUser(@RequestHeader("Authorization") String authorization);

    @PostMapping(value = "/user/change-password")
    ResponseEntity<String> changePassword(UserChangePasswordDto userChangePasswordDto, @RequestHeader("Authorization") String authorization);
}
