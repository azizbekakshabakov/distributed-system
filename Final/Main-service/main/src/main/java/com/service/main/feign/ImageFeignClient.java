package com.service.main.feign;

import com.service.main.dto.*;
import feign.Param;
import feign.RequestLine;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "image-feign-client", url = "${feign.image.url}")
public interface ImageFeignClient {

    @GetMapping("/file/download/{theValueToSend}")
    ResponseEntity<ByteArrayResource> getDownload(@PathVariable("theValueToSend") String theValueToSend);

//    @PostMapping(value = "/user/create")
//    UserDto createUser(UserCreateDto userCreateDto);
//
//    @GetMapping(value = "/user/current-user-name")
//    String getCurrentUserName(@RequestHeader("Authorization") String authorization);
//
//    @GetMapping(value = "/user/current-user")
//    UserDto getCurrentUser(@RequestHeader("Authorization") String authorization);
//
//    @PostMapping(value = "/user/change-password")
//    ResponseEntity<String> changePassword(UserChangePasswordDto userChangePasswordDto, @RequestHeader("Authorization") String authorization);
}
