package org.zhadaev.vdcomtest.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.zhadaev.vdcomtest.webapp.model.CSVHelper;
import org.zhadaev.vdcomtest.webapp.model.Person;
import org.zhadaev.vdcomtest.webapp.repository.PersonRepository;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping("/upload")
    public ResponseEntity uploadCsv(@RequestParam("file") MultipartFile file) {
        if (!CSVHelper.hasCSVFormat(file)) return new ResponseEntity<>(file, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        try {
            List<Person> persons = CSVHelper.csvToPersons(file.getInputStream());
            persons.forEach(person -> {
                Long id = person.getId();
                if (id != null && !personRepository.existsById(id)) person.setId(null);
            });
            int savedPersons = personRepository.saveAll(persons).size();
            return ResponseEntity.ok().body(savedPersons);
        } catch (IOException e) {
            e.printStackTrace();;
            return new ResponseEntity<>(file, HttpStatus.BAD_REQUEST);
        }
    }

}
