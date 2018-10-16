package com.sharegoods.inth3rship.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private byte[] imageData;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @NotNull
    public Item item;

    public Image() { // Default constructor for JPA
    }

    public Image(Item item, String name, byte[] imageData) {
        this.item = item;
        this.name = name;
        this.imageData = imageData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
