package com.sharegoods.inth3rship.services;

import com.sharegoods.inth3rship.models.Item;
import com.sharegoods.inth3rship.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Item createNewItem(Item newItem) {
        return itemRepository.save(newItem);

    }

    public Item getItemById(Long id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        return optionalItem.get();
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    //Update item we will fix on stage #5
//    public Item updateItem(Long userId, Item itemToUpdate) {
//        Item itemFromDataBase = itemRepository.findByUserIdAndId(userId, itemToUpdate.getId());
//        itemFromDataBase.setTitle(itemToUpdate.getTitle());
//        itemFromDataBase.setDescription(itemToUpdate.getDescription());
//        itemFromDataBase.setDateTime(itemToUpdate.getDateTime());
//        return itemRepository.save(itemToUpdate);
//    }
}