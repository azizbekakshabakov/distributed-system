package com.rabbit.mq.api;

import com.rabbit.mq.dto.OrderDTO;
import com.rabbit.mq.service.MessageSender;
import com.rabbit.mq.service.OrderPublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class RabbitController {
    private final MessageSender messageSender;
    private final OrderPublisherService orderPublisherService;

    @PostMapping("{city}")
    public ResponseEntity<String> createOrder(@RequestBody OrderDTO orderDTO, @PathVariable String city) {
        try {
            orderPublisherService.sendOrderToPrepare(orderDTO, city);
            return new ResponseEntity<>("Order created", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Failed to create order", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{status}")
    public ResponseEntity<String> updateOrderStatus(@RequestBody OrderDTO orderDTO, @PathVariable String status) {
        try {
            orderPublisherService.updateOrderStatus(orderDTO, status);
            return new ResponseEntity<>("Order updated", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update order", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//    @PostMapping("send")
//    public ResponseEntity<String> sendMessage(@RequestBody String message) {
//        try{
//            messageSender.sendMessage(message);
//
//            return new ResponseEntity<>("Message sent successfully", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Failed send message", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @PostMapping("send-to-all")
//    public ResponseEntity<String> sendNotification(@RequestBody OrderDTO orderDTO){
//        try {
//            orderPublisherService.updateOrderStatus(orderDTO);
//            return new ResponseEntity<>("Message sent successfully", HttpStatus.OK);
//        }catch(Exception e) {
//            return new ResponseEntity<>("Failed send notification", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
