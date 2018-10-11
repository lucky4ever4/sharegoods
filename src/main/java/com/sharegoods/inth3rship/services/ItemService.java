package com.sharegoods.inth3rship.services;

import com.sharegoods.inth3rship.models.Item;
import com.sharegoods.inth3rship.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getItemsByUserId(Long id) {
        return itemRepository.findByUserId(id);
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }
}