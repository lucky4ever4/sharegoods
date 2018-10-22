package com.sharegoods.inth3rship.dto;

import com.sharegoods.inth3rship.models.Image;
import com.sharegoods.inth3rship.models.Item;

import java.util.*;

public class ItemThumbnailsDto {
    private Long itemId;
    private String title;
    private ImageDto thumbnailDto;

    public ItemThumbnailsDto(Long itemId, String title, ImageDto thumbnailDto) {
        this.itemId = itemId;
        this.title = title;
        this.thumbnailDto = thumbnailDto;
    }


    public static List<ItemThumbnailsDto> getItemThumbnailsDtoList (Map<Item, Image> itemHashMap) {
        List<ItemThumbnailsDto> itemThumbnailsDtoList = new ArrayList<>();
        for (Map.Entry<Item, Image> entry : itemHashMap.entrySet()) {
            Item item = entry.getKey();
            Image thumbnail = entry.getValue();
            ImageDto thumbnailDto = new ImageDto(thumbnail);
            ItemThumbnailsDto itemThumbnailsDto = new ItemThumbnailsDto(item.getId(), item.getTitle(), thumbnailDto);
            itemThumbnailsDtoList.add(itemThumbnailsDto);
        }

        return itemThumbnailsDtoList;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ImageDto getThumbnailDto() {
        return thumbnailDto;
    }

    public void setThumbnailDto(ImageDto thumbnailDto) {
        this.thumbnailDto = thumbnailDto;
    }
}
