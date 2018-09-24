package ru.web.skyforce.services.implementations;

import ru.web.skyforce.models.Category;
import ru.web.skyforce.models.CategoryStatus;
import ru.web.skyforce.repositories.jpa.CategoryRepository;
import ru.web.skyforce.repositories.jpa.CategoryStatusRepository;
import ru.web.skyforce.services.interfaces.DataParserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.web.skyforce.services.interfaces.LinuxCommandService;
import ru.web.skyforce.util.CopyDBToFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


/**
 * Date 22.09.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/
@Service
@Slf4j
public class DataParserServiceImpl implements DataParserService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryStatusRepository categoryStatusRepository;

    @Autowired
    private LinuxCommandService linuxCommandService;

    @Autowired
    private CopyDBToFile copyDBToFile;

    @Override
    public void addNewDataToParse(String categories) {
        if (categories.length()>0)
            //if you got many categories
            for (String categoryTitle: categories.split("\n")) {
                categoryTitle = categoryTitle.trim();
                Optional<Category> categoryOptional = categoryRepository.findByCategoryNameToLower(categoryTitle.toLowerCase());
                if (!categoryOptional.isPresent()){
                    Category category = Category.builder()
                            .title(categoryTitle)
                            .categoryNameToLower(categoryTitle.toLowerCase())
                            .build();
                    categoryRepository.save(category);
                    categoryStatusRepository.save(CategoryStatus.builder()
                            .category(category)
                            .inProcess(false)
                            .ready(false)
                            .build());
                    if (!linuxCommandService.isRunningParser()){
                        linuxCommandService.startParser();
                    }
                }
            }
    }



    @Override
    public void deleteData(String keyword){

    }

    @Override
    public void downloadData(String keyword, HttpServletResponse response) throws SQLException {
        keyword = keyword.replace("'","").toLowerCase();
        String path = copyDBToFile.copy(keyword);

        try {
            InputStream inputStream = new FileInputStream(path+"/"+keyword+".txt");

            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition","attachment; filename=" + keyword+".txt");
            IOUtils.copy(inputStream, response.getOutputStream());

            response.flushBuffer();
            inputStream.close();
        } catch (IOException e) {
            log.info(e.getMessage(), e);
        }
    }

    @Override
    public List<CategoryStatus> getAllTasks(){
        return categoryStatusRepository.findAll();
    }


}
