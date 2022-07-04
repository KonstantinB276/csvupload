package com.springboot.csvupload.csvupload.controllers;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.springboot.csvupload.csvupload.entity.Person;
import com.springboot.csvupload.csvupload.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ViewController {

    public List<Person> personList = new ArrayList<>();
    @Autowired
    PersonRepository personRepository;

    @GetMapping("/")
    public String chooseCSV() {
        return "chooseCSV";
    }

    @PostMapping("/upload-csv-file")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                CsvToBean<Person> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Person.class)
                        .build();
                personList = csvToBean.stream().filter(person -> person.getIdColor() != 0).collect(Collectors.toList());
                personRepository.saveAll(personList);
//                model.addAttribute("personList", personList);
//                model.addAttribute("status", true);
                model.addAttribute("message", "Success.");
                model.addAttribute("status", false);

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }
        return "file-upload-status";
    }

    @RequestMapping("/addNewPerson")
    public String addNewPerson(Model model){
        Person person = new Person();
        model.addAttribute("person", person);
        return "new-person-form";
    }

    @RequestMapping("/savePerson")
    public String savePerson(@ModelAttribute("person") Person person){
        personRepository.save(person);
        return "redirect:/persons/"+person.getId();
    }
}
