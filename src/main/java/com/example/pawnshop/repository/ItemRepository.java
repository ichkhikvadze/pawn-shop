package com.example.pawnshop.repository;

import com.example.pawnshop.entity.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
