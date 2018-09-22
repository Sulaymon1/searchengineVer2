package ru.web.skyforce.services.interfaces;

import ru.web.skyforce.models.CategoryStatus;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * Date 22.09.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/
public interface DataParserService {


    void deleteData(String keyword);
    void downloadData(String keyword, HttpServletResponse response) throws SQLException;
    List<CategoryStatus> getAllTasks();

    void addNewDataToParse(String categories);
}
