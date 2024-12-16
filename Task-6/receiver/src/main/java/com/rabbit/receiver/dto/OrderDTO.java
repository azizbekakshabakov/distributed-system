package com.rabbit.receiver.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO /*implements Serializable*/ {
    private String restaurant;
    private String courier;
    private List<String> foods;
    private String status;
}
