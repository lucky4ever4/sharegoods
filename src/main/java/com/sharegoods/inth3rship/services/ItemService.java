package com.sharegoods.inth3rship.services;

import com.sharegoods.inth3rship.models.Image;
import com.sharegoods.inth3rship.models.Item;
import com.sharegoods.inth3rship.models.User;
import com.sharegoods.inth3rship.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.ArrayList;
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

    @Autowired
    private ImageService imageService;

    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    public List<Item> getItemsByUserId(Long id) {
        User user = userService.getUserById(id);
        return itemRepository.findByUser(user);
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public Item createNewItem(Long userId, String title, String description, List<MultipartFile> imageFiles) {
        User user = userService.getUserById(userId);
        java.util.Date dateNow = new java.util.Date();
        Date date = new Date(dateNow.getTime());
        Item newItem = new Item(user, date, title, description);
        itemRepository.save(newItem);

        List<Image> images = new ArrayList();
        byte[] arrayImg;
        if (imageFiles != null && !imageFiles.isEmpty()) {
            for (MultipartFile imageFile : imageFiles) {
                arrayImg = new byte[(int) imageFile.getSize()];
                try {
                    imageFile.getInputStream().read(arrayImg);
                    images.add(new Image(newItem, imageFile.getOriginalFilename(), arrayImg));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            imageService.createImages(images);
        }
        return newItem;
    }

    public Item getItemById(Long id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        return optionalItem.get();
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public Item updateItem(Long itemId, String title, String description) {
        Item item = getItemById(itemId);
        item.setTitle(title);
        item.setDescription(description);
        return itemRepository.save(item);

    }
}