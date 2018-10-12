package com.sharegoods.inth3rship.services;

import com.sharegoods.inth3rship.models.Item;
import com.sharegoods.inth3rship.models.User;
import com.sharegoods.inth3rship.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<Item> getItemsByUserId(Long id) {
        User user = userService.getUserById(id);
        return itemRepository.findByUser(user);
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public Item createNewItem(Long userId, String title, String description) {
        User user = userService.getUserById(userId);
        java.util.Date dateNow = new java.util.Date();
        Date date = new Date(dateNow.getTime());
        Item newItem = new Item(user, date, title, description);
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