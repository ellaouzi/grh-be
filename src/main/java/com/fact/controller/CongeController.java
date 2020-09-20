package com.fact.controller;


import com.fact.model.Conge;
import com.fact.repository.CongeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"*"}, maxAge = 6000)

@RestController
@RequestMapping("/api")
public class CongeController {
    @Autowired
    CongeRepository congeRepository;

    @GetMapping("/conges")
    public ResponseEntity<List<Conge>> getAllCongeOptions() {
        List<Conge> conges = new ArrayList<>();
         conges = (List<Conge>) congeRepository.findAll();
        return new ResponseEntity<>(conges, HttpStatus.OK);
    }

    @PostMapping("/conges/create")
    public Conge createConge(@RequestBody Conge conge) {
        return congeRepository.save(conge);

    }


    @GetMapping("/conges/{id}")
    public ResponseEntity<Conge> getCongeById(@PathVariable("id") Long id) {
        Conge conge = congeRepository.findById(id).get();
        if (conge != null) {
            return new ResponseEntity<>(conge, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/conges/{id}")
    public ResponseEntity<Conge> updateConge(@PathVariable("id") Long id, @RequestBody Conge conge) {

        return new ResponseEntity<>(congeRepository.save(conge), HttpStatus.OK);

    }



    @DeleteMapping("/conges/{id}")
    public @ResponseBody
    ResponseEntity<String> deleteConge(@PathVariable("id") Long id) {

        System.out.println("Delete Conge with Id: " + id + " Called....");
        congeRepository.deleteById(id);
        return new ResponseEntity<String>("Response from DELETE method", HttpStatus.OK);
    }

}