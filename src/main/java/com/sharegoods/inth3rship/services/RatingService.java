package com.sharegoods.inth3rship.services;

import com.sharegoods.inth3rship.exceptions.ItemNotFoundException;
import com.sharegoods.inth3rship.exceptions.UserNotFoundException;
import com.sharegoods.inth3rship.models.Item;
import com.sharegoods.inth3rship.models.Rating;
import com.sharegoods.inth3rship.models.User;
import com.sharegoods.inth3rship.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    public List<Rating> getRatingByItemId(Long itemId) {
        Item item = itemService.getItemById(itemId);
        return ratingRepository.findByItem(item);
    }

    public boolean validationRating(int rating) {
        ArrayList<Integer> ratingValues = new ArrayList<Integer>();
        ratingValues.addAll(Arrays.asList(1,2,3,4,5));
        return ratingValues.contains(rating);
    }

    public Rating createRating(Long userId, Long itemId, Double rating) throws UserNotFoundException, ItemNotFoundException {


        User user;
        Item item;

        try {
            user = userService.getUserById(userId);
        } catch (NoSuchElementException e) {
            throw new UserNotFoundException("No such user");
        }
        try {
            item = itemService.getItemById(itemId);
        } catch (NoSuchElementException e) {
            throw new ItemNotFoundException("No such item");
        }
        Rating rate = new Rating(rating, user, item);
        return ratingRepository.save(rate);
    }
}



