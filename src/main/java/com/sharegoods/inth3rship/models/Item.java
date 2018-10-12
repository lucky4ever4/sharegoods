package com.sharegoods.inth3rship.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true, nullable=false)
    private Long userId;

    @Column(unique=true, nullable=false)

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ElementCollection(targetClass=User.class)
    public User user;

    private Date dateTime;

    @Column(unique=true, nullable=false)
    private String title;

    @Column(unique=true, nullable=false)
    private String description;

    public Item() { // For JPA
    }

    public Item(User user, Date dateTime, String title, String description) {
        this.user = user;
        this.dateTime = dateTime;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
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
