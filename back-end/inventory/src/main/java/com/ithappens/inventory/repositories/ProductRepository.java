package com.ithappens.inventory.repositories;

import com.ithappens.inventory.domains.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public Optional<Product> findByDescriptionOrBarcodeOrSequential(String description, String barcode, String sequential);
}
