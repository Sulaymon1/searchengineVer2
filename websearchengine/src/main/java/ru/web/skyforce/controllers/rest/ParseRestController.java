package ru.web.skyforce.controllers.rest;

import ru.web.skyforce.services.interfaces.DataParserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@RestController
public class ParseRestController {

    @Autowired
    private DataParserService dataParserService;


    // TODO: 22.09.2018 add new param city, download category for concreate city
    @PostMapping("/addCategory")
    @SneakyThrows
    public void addNewTask(@RequestParam("categories") String categories, HttpServletResponse response){
        dataParserService.addNewDataToParse(categories);
        response.sendRedirect("/dataParser");
    }


    @PostMapping("/delete/{keyword}")
    public ResponseEntity deleteData(@PathVariable("keyword") String keyword){
        dataParserService.deleteData(keyword);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/download/{keyword}")
    public void downloadData(@PathVariable("keyword") String keyword, HttpServletResponse response) throws SQLException {
        dataParserService.downloadData(keyword, response);
    }
}
