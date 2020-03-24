package com.ithappens.inventory.repositories;

import com.ithappens.inventory.domains.Branch;
import com.ithappens.inventory.domains.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    public Optional<Branch> findByLocation(String location);

}
