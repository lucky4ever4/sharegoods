package com.sharegoods.inth3rship.services;

import com.sharegoods.inth3rship.models.Image;
import com.sharegoods.inth3rship.models.Item;
import com.sharegoods.inth3rship.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ItemService itemService;

    public void setImageRepository(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<Image> getImagesByItemId(Long itemId) {
        Item item = itemService.getItemById(itemId);
        return imageRepository.findByItem(item);
    }

    public List<Image> createImages(Item item, List<MultipartFile> imageFiles) {
        List<Image> images = new ArrayList();
        byte[] arrayImg;
        if (imageFiles != null && !imageFiles.isEmpty()) {
            for (MultipartFile imageFile : imageFiles) {
                arrayImg = new byte[(int) imageFile.getSize()];
                try {
                    imageFile.getInputStream().read(arrayImg);
                    images.add(new Image(item, imageFile.getOriginalFilename(), arrayImg));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return imageRepository.saveAll(images);
    }

    public void deleteImagesByItem(Item item) {
        imageRepository.deleteByItem(item);
    }

    public void updateItemImages(Item item, List<MultipartFile> imageFiles) {
       deleteImagesByItem(item);
       createImages(item, imageFiles);
    }

}
