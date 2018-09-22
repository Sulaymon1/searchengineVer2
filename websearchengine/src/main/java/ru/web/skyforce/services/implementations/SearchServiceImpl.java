package ru.web.skyforce.services.implementations;

import ru.web.skyforce.models.Category;
import ru.web.skyforce.models.City;
import ru.web.skyforce.repositories.jpa.CategoryRepository;
import ru.web.skyforce.repositories.jpa.CityRepository;
import ru.web.skyforce.services.interfaces.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    private CategoryRepository categoryRepository;
    private CityRepository cityRepository;

    @Autowired
    public SearchServiceImpl(CategoryRepository categoryRepository,
                             CityRepository cityRepository){
        this.categoryRepository = categoryRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public List<Category> getCategoriesByInput(String input) {
        PageRequest pageRequest = new PageRequest(1, 4);
        return categoryRepository.findAllByCategoryNameToLowerStartsWith(input.toLowerCase(), pageRequest);
    }

    @Override
    public List<City> getCitiesByInput(String input){
        PageRequest pageRequest = new PageRequest(1,4);
        return cityRepository.findAllByStateToLowerStartsWith(input.toLowerCase(), pageRequest);
    }
}
