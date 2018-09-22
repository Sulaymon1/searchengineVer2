package ru.web.skyforce.controllers;

import ru.web.skyforce.models.Statistics;
import ru.web.skyforce.services.interfaces.StatisticsService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Date 22.09.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/

@Controller
public class StatisticsController {

    @Value("${logging.file}")
    private String path;


    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/statistics")
    public String getStatisticsPage(@ModelAttribute("model")ModelMap modelMap){
        List<Statistics> statistics = statisticsService.getAllSearchedData();
        modelMap.addAttribute("statistics", statistics);
        return "statistics";
    }

    @GetMapping("/downloadLog")
    public void downloadLog(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",
                String.format("attachment; filename=\"%s\"","application.log"));
        InputStream is = new FileInputStream(new File(path));
        IOUtils.copy(is, response.getOutputStream());
    }

}
