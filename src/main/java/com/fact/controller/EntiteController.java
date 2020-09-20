package com.fact.controller;


import com.fact.model.Entite;
import com.fact.repository.EntiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"*"}, maxAge = 6000)

@RestController
@RequestMapping("/api")
public class EntiteController {
    @Autowired
    EntiteRepository entiteRepository;

    @GetMapping("/entites")
    public ResponseEntity<List<Entite>> getAllEntiteOptions() {
        List<Entite> entites = new ArrayList<>();
         entites = (List<Entite>) entiteRepository.findAll();
        return new ResponseEntity<>(entites, HttpStatus.OK);
    }

    @PostMapping("/entites/create")
    public Entite createEntite(@RequestBody Entite entite) {
        return entiteRepository.save(entite);

    }


    @GetMapping("/entites/{id}")
    public ResponseEntity<Entite> getEntiteById(@PathVariable("id") Long id) {
        Entite entite = entiteRepository.findById(id).get();
        if (entite != null) {
            return new ResponseEntity<>(entite, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/entites/{id}")
    public ResponseEntity<Entite> updateEntite(@PathVariable("id") Long id, @RequestBody Entite entite) {

        return new ResponseEntity<>(entiteRepository.save(entite), HttpStatus.OK);

    }



    @DeleteMapping("/entites/{id}")
    public @ResponseBody
    ResponseEntity<String> deleteEntite(@PathVariable("id") Long id) {

        System.out.println("Delete Entite with Id: " + id + " Called....");
        entiteRepository.deleteById(id);
        return new ResponseEntity<String>("Response from DELETE method", HttpStatus.OK);
    }

}