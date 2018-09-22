package ru.web.skyforce.controllers;

import com.google.common.collect.Lists;
import ru.web.skyforce.models.CategoryStatus;
import ru.web.skyforce.services.implementations.ParseServiceImpl;
import ru.web.skyforce.services.interfaces.DataParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

/**
 * Created by Sulaymon on 22.09.2018.
 */
@Controller
public class ParseController {

    @Autowired
    private ParseServiceImpl parseService;


    @Autowired
    private DataParserService dataParserService;

    @GetMapping("/parseCategory")
    public String parseCategory(){
        parseService.parseCategory();
        return "successPage";
    }

    @GetMapping("/parseCity")
    public String parseCity(){
        parseService.parseStatesAndCities();
        return "successPage";
    }

    @GetMapping("/dataParser")
    public String getDataParser(@ModelAttribute("model") ModelMap modelMap){
        List<CategoryStatus> allTasks = Lists.reverse(dataParserService.getAllTasks());
        modelMap.addAttribute("tasks", allTasks);
        return "dataParserPage";
    }


}

