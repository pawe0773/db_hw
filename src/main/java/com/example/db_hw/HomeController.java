package com.example.db_hw;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    List<String > persons = new ArrayList();
    MyDB myDB = new MyDB();
    @GetMapping("/")
    public String home(Model model){
        if(persons.isEmpty()){
            persons = myDB.getPersons();
        }
        model.addAttribute("persons", persons);
        return "index";
    }
}
