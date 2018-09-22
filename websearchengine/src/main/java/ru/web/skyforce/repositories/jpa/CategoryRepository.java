package ru.web.skyforce.repositories.jpa;

import ru.web.skyforce.models.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByCategoryNameToLower(String category);
    List<Category> findAllByCategoryNameToLowerStartsWith(String category, Pageable pageable);
}
