package ru.web.skyforce.services.interfaces;

import ru.web.skyforce.models.Category;
import ru.web.skyforce.models.City;
import ru.web.skyforce.models.Data;

import java.util.List;

/**
 * Created by Sulaymon on 11.03.2018.
 */
public interface ParseService {
    void parseCategory();
    void parseStatesAndCities();
    List<Data> parseDataByInput(Category keyword, City city, int currentPage);
}
