package ru.parser.dao;

import ru.parser.models.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Date 20.09.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/
public class DataDAO {

    private Connection connection;

    public DataDAO(Connection connection){
        this.connection = connection;
    }


    public void save(List<Data> dataList){
        String SQL = "insert into data (address, email, name, phone, uri, website, category_id, city_id) VALUES(?,?,?,?,?,?,?,?) ";
        try (PreparedStatement statement = connection.prepareStatement(SQL)) {

            int i=0;

            for (Data data: dataList){
                statement.setString(1, data.getAddress());
                statement.setString(2, data.getEmail());
                statement.setString(3, data.getName());
                statement.setString(4, data.getPhone());
                statement.setString(5, data.getUri());
                statement.setString(6, data.getWebsite());
                statement.setLong(7, data.getCategoryId());
                statement.setLong(8, data.getCityId());

                System.out.println(data.toString());

                statement.addBatch();

                if (i % 1000 ==0 || i == dataList.size()){
                    statement.executeBatch(); // Execute every 1000 items
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
