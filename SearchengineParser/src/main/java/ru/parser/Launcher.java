package ru.parser;

import ru.parser.dao.CategoryDAO;
import ru.parser.dao.CityDAO;
import ru.parser.dao.DataDAO;
import ru.parser.dao.PostgresConnection;
import ru.parser.models.Category;
import ru.parser.models.City;
import ru.parser.services.ParseService;

import java.sql.Connection;
import java.util.List;

/**
 * Date 19.09.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/
public class Launcher {
    public static void main(String[] args) {
        Connection connect = PostgresConnection.connect();
        CategoryDAO categoryDAO = new CategoryDAO(connect);
        CityDAO cityDAO = new CityDAO(connect);
        ParseService parseService =new ParseService(new DataDAO(connect));

        Category category = null;
        List<City> cities = cityDAO.findAll();
        while ((category=categoryDAO.next())!=null){
            for (City city: cities){
                parseService.parseByCategoryAndCity(city,category);
            }
            categoryDAO.update(category);
        }
    }
}
