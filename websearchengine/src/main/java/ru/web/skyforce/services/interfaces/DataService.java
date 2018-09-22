package ru.web.skyforce.services.interfaces;

import ru.web.skyforce.models.Data;

import java.util.List;


public interface DataService {
    List<Data> getDataListByKeywordAndCity(String keyword, String city, Integer page);

}
