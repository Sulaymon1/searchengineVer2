package ru.web.skyforce.controllers;

import ru.web.skyforce.dto.SearchDTO;
import ru.web.skyforce.validator.SearchFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by Sulaymon on 22.09.2018.
 */
@Controller
public class SearchController {

    @Autowired
    private SearchFormValidator searchFormValidator;

    @InitBinder("searchForm")
    public void initSearchValidator(WebDataBinder dataBinder){
        dataBinder.addValidators(searchFormValidator);
    }


    @GetMapping("/")
    public String mainPage(){
       return  "mainPage";
    }

    @GetMapping("/search")
    public String doSearch(@ModelAttribute("searchForm") @Valid SearchDTO searchDTO,
                               BindingResult result, RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            redirectAttributes.addFlashAttribute("error",result.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute(searchDTO);
        return "redirect:/doSearch";
    }
}
