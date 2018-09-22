package ru.web.skyforce.repositories.jpa;

import ru.web.skyforce.models.Category;
import ru.web.skyforce.models.City;
import ru.web.skyforce.models.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DataRepository extends JpaRepository<Data, Long> {
    List<Data> findAllByCategoryAndCityAndEmailIsNotNull(Category category, City city, Pageable pageable);
}
