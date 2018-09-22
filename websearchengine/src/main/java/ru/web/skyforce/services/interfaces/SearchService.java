package ru.web.skyforce.services.interfaces;

import ru.web.skyforce.models.Category;
import ru.web.skyforce.models.City;

import java.util.List;


public interface SearchService {
    List<Category> getCategoriesByInput(String input);
    List<City> getCitiesByInput(String input);
}
