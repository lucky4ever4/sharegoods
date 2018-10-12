package com.sharegoods.inth3rship.restcontrollers;

import com.sharegoods.inth3rship.dto.ItemDto;
import com.sharegoods.inth3rship.models.Item;
import com.sharegoods.inth3rship.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/users/{id}/items")
    public List<Item> getItemsByUserId(@PathVariable("id") Long id) {
        return itemService.getItemsByUserId(id);
    }

    @GetMapping("/items")
    public List<Item> getItems() {
        return itemService.getItems();
    }

    @PostMapping("/users/{id}/items")
    public ResponseEntity<ItemDto> createItem(@PathVariable("id") Long userId,
                                     @RequestParam("title") String title,
                                     @RequestParam("description") String description) {
        Item item = itemService.createNewItem(userId, title, description);
        return new ResponseEntity<>(new ItemDto(item), HttpStatus.OK);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity getItemById(@PathVariable("id") Long id) {
        try
        {
            Item item = itemService.getItemById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ItemDto(item));
        }
        catch (NoSuchElementException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
        }
    }

    @DeleteMapping("/items/{id}")
    public void deleteItem(@PathVariable("id") Long id) {
        itemService.deleteItem(id);
    }

    @PutMapping("/users/{userId}/items/{itemId}")
    public Item updateItem(@PathVariable("itemId") Long itemId,
                           @RequestParam("title") String title,
                           @RequestParam("description") String description) {
        return itemService.updateItem(itemId, title, description);
    }
}