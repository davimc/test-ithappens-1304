package com.ithappens.inventory.repositories;

import com.ithappens.inventory.domains.Client;
import com.ithappens.inventory.domains.Order;
import com.ithappens.inventory.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    public Optional<Client> findByIdentification(String identification);
}
