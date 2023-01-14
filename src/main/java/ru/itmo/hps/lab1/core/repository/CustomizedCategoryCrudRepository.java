package ru.itmo.hps.lab1.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.hps.lab1.core.entity.Category;

import java.util.Optional;


@Repository
public interface CustomizedCategoryCrudRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
    boolean existsByName(String name);
}
