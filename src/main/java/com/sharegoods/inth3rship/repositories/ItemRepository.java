package com.sharegoods.inth3rship.repositories;

import com.sharegoods.inth3rship.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository extends JpaRepository<Item, Long> {
}
