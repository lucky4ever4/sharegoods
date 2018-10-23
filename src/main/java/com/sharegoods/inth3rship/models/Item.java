package com.sharegoods.inth3rship.models;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "Items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    public User user;

    @NotNull
    private Date dateTime;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @Formula("select avg(r.rating) from Rating r where r.item_id = id")
    private Double rating;

    public Item() { // Default constructor for JPA
    }

    public Item(User user, Date dateTime, String title, String description) {
        this.user = user;
        this.dateTime = dateTime;
        this.title = title;
        this.description = description;
    }

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() { return user; }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateTime() { return dateTime; }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
