package com.sharegoods.inth3rship.restcontrollers;

import com.sharegoods.inth3rship.models.Item;
import com.sharegoods.inth3rship.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/items")
    public Item createItem(@RequestBody Item newItem) {
        return itemService.createNewItem(newItem);
    }

    @GetMapping("/items/{id}")
    public Item getItemById(@PathVariable("id") Long id) {
        return itemService.getItemById(id);
    }

    @DeleteMapping("/items/{id}")
    public void deleteItem(@PathVariable("id") Long id) {
        itemService.deleteItem(id);
    }

    //Update item we will fix on #5
//    @PutMapping("/users/{id}/items/{itemId}")
//    public Item updateItem(@PathVariable("id") Long userId, @RequestBody Item itemToUpdate) {
//        return itemService.updateItem(userId, itemToUpdate);
//    }
}