package com.sharegoods.inth3rship.dto;

import com.sharegoods.inth3rship.models.Item;
import com.sharegoods.inth3rship.models.User;

public class ItemDto {
    Long id;
    Long userId;
    String title;
    String description;

    public ItemDto() {}

    public ItemDto(Item item) {
        this.id = item.getId();
        this.title = item.getTitle();
        this.description = item.getDescription();
        this.userId = item.getUser().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}