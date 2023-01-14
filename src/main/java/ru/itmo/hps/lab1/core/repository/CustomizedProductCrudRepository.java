package ru.itmo.hps.lab1.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.hps.lab1.core.entity.Product;

import java.util.Optional;


@Repository
public interface CustomizedProductCrudRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
}
