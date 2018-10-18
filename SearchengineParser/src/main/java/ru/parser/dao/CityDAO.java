package ru.parser.dao;

import ru.parser.models.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Date 20.09.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/
public class CityDAO {

    private Connection connection;

    public CityDAO(Connection connection){
        this.connection = connection;
    }

    public List<City> findAll(){
        List<City> cities = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM city");
            while (resultSet.next()){
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                City city = new City();
                city.setId(id);
                city.setName(name);
                cities.add(city);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cities;
    }

    public List<City> findAllAfterIndex(Long index){
        List<City> cities = new ArrayList<>();
        try {
            String SQL = "SELECT * FROM city WHERE id > ?";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setLong(1, index);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                City city = new City();
                city.setId(id);
                city.setName(name);
                cities.add(city);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cities;
    }


}
