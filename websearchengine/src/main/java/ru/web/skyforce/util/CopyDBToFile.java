package ru.web.skyforce.util;

import org.postgresql.copy.CopyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Date 23.04.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/
@Component
public class CopyDBToFile {

    @Value("${storage.path}")
    private String path;

    @Autowired
    private CopyManager copyManager;

    public String copy(String keyword){
        try {
            String fileName = path + "/" + keyword + ".txt";
            FileWriter fileWriter = new FileWriter(fileName);
            String sql = "COPY (SELECT data.id,data.address,email,data.name, data.phone,data.website,categories.title,cities.name FROM data" +
                    " JOIN (SELECT id,name FROM city) as cities ON data.city_id = cities.id "+
                    " JOIN (SELECT category.id, category_name_to_lower, category.title FROM category) as categories ON data.category_id=categories.id "+
                    " WHERE categories.category_name_to_lower='"+keyword+"') to stdout (DELIMITER '\t')";
            copyManager.copyOut(sql, fileWriter);
            fileWriter.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return path;
    }


}
