package ru.parser;

import ru.parser.dao.CategoryDAO;
import ru.parser.dao.CityDAO;
import ru.parser.dao.DataDAO;
import ru.parser.dao.PostgresConnection;
import ru.parser.models.Category;
import ru.parser.models.City;
import ru.parser.services.HttpRequestService;
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
    private static Connection connect = PostgresConnection.connect();
    private static CategoryDAO categoryDAO = new CategoryDAO(connect);
    private static CityDAO cityDAO = new CityDAO(connect);
    private static ParseService parseService =new ParseService(new DataDAO(connect));
    private static HttpRequestService httpRequestService = new HttpRequestService();

    public static void main(String[] args) {
        httpRequestService.sendMailByHttp("Parser started", " ");

        Category category = categoryDAO.next(true);
        if (category!=null){
            Long progress = category.getProgress();
            List<City> cities = cityDAO.findAllAfterIndex(progress);
            parseCategory(category, cities);
        }
        List<City> cities = cityDAO.findAll();
        while ((category =categoryDAO.next(false))!=null){
            parseCategory(category, cities);
        }
    }

    private static void parseCategory(Category category, List<City> cities){
        System.out.println(category.getName());
        httpRequestService.sendMailByHttp("category started", category.getName());
        int index = 0;
        for (City city: cities){
            System.out.println(city.getName());
            try {
                parseService.parseByCategoryAndCity(city,category);
                categoryDAO.update(index++, category);
            }catch (Throwable e){
                e.printStackTrace();
                httpRequestService.sendMailByHttp("got exception",e.toString());
            }
        }
        categoryDAO.update(category);
        httpRequestService.sendMailByHttp("category downloaded", category.getName());
    }
}
