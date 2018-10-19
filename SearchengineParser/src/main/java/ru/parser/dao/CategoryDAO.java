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

    public Category next(Boolean isFirstTime){
        Statement statement = null;
        Category category = null;
        String SQL ;
        if (isFirstTime){
            SQL = "SELECT category.*, progress FROM category, (SELECT id, progress FROM categorystatus WHERE in_process=TRUE LIMIT 1) e " +
                    "WHERE category.id = e.id";
        }else {
            SQL = "SELECT category.*, progress FROM category, (SELECT id, progress FROM categorystatus WHERE in_process is NOT TRUE AND ready is NOT TRUE LIMIT 1) e " +
                    "WHERE category.id = e.id";
        }
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()){
                long id = resultSet.getLong("id");
                String categoryNameToLower = resultSet.getString("category_name_to_lower");
                Long progress = resultSet.getLong("progress");
                category = new Category();
                category.setId(id);
                category.setProgress(progress);
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

    public void update(int index, Category category) {
        String SQL="UPDATE categorystatus SET progress=? WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setLong(1,index);
            statement.setLong(2,category.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
