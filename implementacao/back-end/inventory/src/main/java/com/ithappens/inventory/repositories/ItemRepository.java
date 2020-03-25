package com.ithappens.inventory.repositories;

import com.ithappens.inventory.domains.Item;
import com.ithappens.inventory.domains.Order;
import com.ithappens.inventory.domains.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    public Optional<Item> findByOrderAndStock(Order order, Stock stock);
}
