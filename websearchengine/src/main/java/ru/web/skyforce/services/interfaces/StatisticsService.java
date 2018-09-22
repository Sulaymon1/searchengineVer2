package ru.web.skyforce.services.interfaces;

import ru.web.skyforce.models.Statistics;

import java.util.List;

/**
 * Date 22.09.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/
public interface StatisticsService {
    List<Statistics> getAllSearchedData();
    void addNewSearchedData(Statistics statistics);
}
