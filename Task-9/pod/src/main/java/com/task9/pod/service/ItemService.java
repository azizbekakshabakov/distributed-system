package com.task9.pod.service;

import com.task9.pod.dto.ItemDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
    private List<ItemDto> items;
    private Long id;

    public ItemService() {
        items = new ArrayList<>();
        items.add(new ItemDto(1L, "Iphone 15", 40000, 10));
        items.add(new ItemDto(2L, "Iphone 16", 50000, 14));
        items.add(new ItemDto(3L, "Iphone 17", 60000, 13));
        items.add(new ItemDto(4L, "Iphone 18", 70000, 12));
        id = 5L;
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public ItemDto addItem(ItemDto itemDto) {
        itemDto.setId(id);
        items.add(itemDto);
        id++;

        return itemDto;
    }
}
