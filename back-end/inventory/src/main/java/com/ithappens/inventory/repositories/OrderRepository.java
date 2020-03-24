package com.ithappens.inventory.repositories;

import com.ithappens.inventory.domains.Branch;
import com.ithappens.inventory.domains.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
