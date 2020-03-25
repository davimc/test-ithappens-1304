package com.ithappens.inventory.repositories;

import com.ithappens.inventory.domains.Branch;
import com.ithappens.inventory.domains.Product;
import com.ithappens.inventory.domains.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    public Optional<Stock> findByBranchAndProduct(Branch branch, Product product);
}
