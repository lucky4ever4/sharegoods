package com.sharegoods.inth3rship.restcontrollers;

import com.sharegoods.inth3rship.dto.ImageDto;
import com.sharegoods.inth3rship.dto.ItemDetailsDto;
import com.sharegoods.inth3rship.dto.ItemDto;
import com.sharegoods.inth3rship.dto.ItemThumbnailsDto;
import com.sharegoods.inth3rship.exceptions.ItemNotFoundException;
import com.sharegoods.inth3rship.exceptions.RatingException;
import com.sharegoods.inth3rship.exceptions.UserNotFoundException;
import com.sharegoods.inth3rship.models.Image;
import com.sharegoods.inth3rship.models.Item;
import com.sharegoods.inth3rship.services.ImageService;
import com.sharegoods.inth3rship.services.ItemService;
import com.sharegoods.inth3rship.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private RatingService ratingService;

    @GetMapping("/users/{userId}/items")
    public ResponseEntity getItemsByUserId(@PathVariable("userId") Long userId) {
        try {
            List<Item> itemList = itemService.getItemsByUserId(userId);

            if (itemList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body("User has not items");
            }

            List<ItemDto> itemDtoList = ItemDto.getItemDtoList(itemList);
            return ResponseEntity.status(HttpStatus.OK).body(itemDtoList);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @GetMapping("/items")
    public ResponseEntity getItems() {
        List<Item> itemList = itemService.getItems();

        if (itemList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body("No items found");
        }
        Map<Item, Image> itemHashMap = itemService.getItemsWithThumbnails(itemList);

        List<ItemThumbnailsDto> itemThumbnailsDtoList = ItemThumbnailsDto.getItemThumbnailsDtoList(itemHashMap);
        return ResponseEntity.status(HttpStatus.OK).body(itemThumbnailsDtoList);
    }

    @PostMapping("/users/{id}/items")
    public ResponseEntity createItem(@PathVariable("id") Long userId,
                                     @RequestParam("title") String title,
                                     @RequestParam("description") String description,
                                     @RequestParam("file") List<MultipartFile> imageFiles) {
        Item item = itemService.createNewItem(userId, title, description, imageFiles);
        ItemDto itemDto = new ItemDto(item);
    return ResponseEntity.status(HttpStatus.OK).body(itemDto);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity getItemById(@PathVariable("id") Long id) {
        try {
            Item item = itemService.getItemById(id);
            List<Image> itemImages = imageService.getImagesByItemId(id);
            ItemDto itemDto = new ItemDto(item);
            List<ImageDto> imageDtoList = ImageDto.getImageDtoList(itemImages);
            ItemDetailsDto itemDetailsDto = new ItemDetailsDto(itemDto, imageDtoList);
            return ResponseEntity.status(HttpStatus.OK).body(itemDetailsDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
        }
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity deleteItem(@PathVariable("id") Long id) {
        try {
            itemService.deleteItem(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
        }
    }

    @PutMapping("/items/{itemId}")
    public ResponseEntity updateItem(@PathVariable("itemId") Long itemId,
                                     @RequestParam("title") String title,
                                     @RequestParam("description") String description,
                                     @RequestParam("file") List<MultipartFile> imageFiles){
        try {
            Item item = itemService.updateItem(itemId, title, description, imageFiles);
            ItemDto itemDto = new ItemDto(item);
            return ResponseEntity.status(HttpStatus.OK).body(itemDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
        }
    }

    /*
    By posting Rating, every time we create (assign) a value to column "rating" in DB.
     */
    @PostMapping("/users/{userId}/items/{itemId}/rating")
    public ResponseEntity updateRating(@PathVariable("userId") Long userId,
                                       @PathVariable("itemId") Long itemId,
                                       @RequestParam("rating") Double rating) {

        try {
            ratingService.createRating(userId, itemId, rating);
            Item item = itemService.getItemById(itemId);
            ItemDto itemDto = new ItemDto(item);
            return ResponseEntity.status(HttpStatus.OK).body(itemDto);

        } catch (UserNotFoundException e) {
            return ResponseEntity.status((HttpStatus.NOT_FOUND)).body("User not found");
        } catch (ItemNotFoundException e) {
            return ResponseEntity.status((HttpStatus.NOT_FOUND)).body("Item not found");
        } catch (RatingException e) {
            return ResponseEntity.status((HttpStatus.NOT_FOUND)).body("Rating is not allowd");
        }
    }

}