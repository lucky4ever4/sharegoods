package com.sharegoods.inth3rship.services;

import com.sharegoods.inth3rship.models.Image;
import com.sharegoods.inth3rship.models.Item;
import com.sharegoods.inth3rship.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ItemService itemService;

    public void setItemService(UserService userService) {
        this.itemService = itemService;
    }

    public void setImageRepository(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<Image> getImagesByItemId(Long itemId) {
        Item item = itemService.getItemById(itemId);
        return imageRepository.findByItem(item);
    }

    public List<Image> createImages(List<Image> images) {
        return imageRepository.saveAll(images);
    }



}
