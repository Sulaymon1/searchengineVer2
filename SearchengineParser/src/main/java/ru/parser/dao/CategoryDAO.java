package ru.parser.dao;

import ru.parser.models.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Date 20.09.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/
public class CategoryDAO {

    private Connection connection;

    public CategoryDAO(Connection connection){
        this.connection = connection;
    }

    public Category next(){
        Statement statement = null;
        Category category = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM category WHERE id = " +
                    "(SELECT id FROM categorystatus WHERE in_process is NOT TRUE AND ready is NOT TRUE LIMIT 1)");
            while (resultSet.next()){
                long id = resultSet.getLong("id");
                String categoryNameToLower = resultSet.getString("category_name_to_lower");
                category = new Category();
                category.setId(id);
                category.setName(categoryNameToLower);
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE categorystatus SET in_process=TRUE WHERE id=?");
                preparedStatement.setLong(1,id);
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }

    public void update(Category category){
        String SQL="UPDATE categorystatus SET in_process=FALSE, ready=TRUE WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setLong(1,category.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
