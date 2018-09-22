package ru.web.skyforce.repositories.jpa;

import ru.web.skyforce.models.City;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface CityRepository extends JpaRepository<City,Long>{
    List<City> findAllByStateToLowerStartsWith(String stateToLower, Pageable pageable);
    City findFirstByStateToLower(String stateToLower);
}
