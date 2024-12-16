package com.service.main.api;

import com.service.main.dto.FileDetails;
import com.service.main.dto.UserLoginDto;
import com.service.main.dto.UserTokenDto;
import com.service.main.feign.ImageFeignClient;
import com.service.main.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/image")
public class ImageController {

    private final ImageFeignClient imageFeignClient;
    private final RestTemplate restTemplate;

    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadImage(@PathVariable String fileName) {
        return imageFeignClient.getDownload(fileName);
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<String> upload(@RequestParam("image") MultipartFile file) {
        try {
            // Process and store the file locally, if necessary
            // For demonstration, just log the file details
            System.out.println("Received file: " + file.getOriginalFilename() + " | Size: " + file.getSize());

            // Prepare the file details to send to the target microservice
            FileDetails fileDetails = new FileDetails(file.getOriginalFilename(), file.getSize(), file.getBytes());

            // Send the file to another microservice
            String name = sendFileToAnotherMicroservice(fileDetails);

            if (name != null) {

                return ResponseEntity.ok(name);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send file");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while sending file");
        }
//        return imageFeignClient.uploadImage(image);
    }

    public String sendFileToAnotherMicroservice(FileDetails fileDetails) {
        try {
            String targetUrl = "http://localhost:8002/file/upload";

            // Prepare HTTP headers for a multipart request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            // Prepare the body for the request with the multipart file
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", new ByteArrayResource(fileDetails.getContent()) {
                @Override
                public String getFilename() {
                    return fileDetails.getFilename();
                }
            });

            HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

            // Send the POST request
            ResponseEntity<String> response = restTemplate.postForEntity(targetUrl, entity, String.class);

            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return "Null";
        }
    }


}
