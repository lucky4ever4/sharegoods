package com.sharegoods.inth3rship.restcontrollers;

import com.sharegoods.inth3rship.models.Item;
import com.sharegoods.inth3rship.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    public void setItemService (ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/users/{id}/items")
    public List<Item> getItemsByUserId(@PathVariable("id") Long id) {
        return itemService.getItemsByUserId(id);
    }

}
