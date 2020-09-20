package com.fact.controller;


import com.fact.model.Fonction;
import com.fact.repository.FonctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"*"}, maxAge = 6000)

@RestController
@RequestMapping("/api")
public class FonctionController {
    @Autowired
    FonctionRepository fonctionRepository;

    @GetMapping("/fonctions")
    public ResponseEntity<List<Fonction>> getAllFonctionOptions() {
        List<Fonction> fonctions = new ArrayList<>();
         fonctions = (List<Fonction>) fonctionRepository.findAll();
        return new ResponseEntity<>(fonctions, HttpStatus.OK);
    }

    @PostMapping("/fonctions/create")
    public Fonction createFonction(@RequestBody Fonction fonction) {
        return fonctionRepository.save(fonction);
    }


    @GetMapping("/fonctions/{id}")
    public ResponseEntity<Fonction> getFonctionById(@PathVariable("id") Long id) {
        Fonction fonction = fonctionRepository.findById(id).get();
        if (fonction != null) {
            return new ResponseEntity<>(fonction, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/fonctions/{id}")
    public ResponseEntity<Fonction> updateFonction(@PathVariable("id") Long id, @RequestBody Fonction fonction) {

        return new ResponseEntity<>(fonctionRepository.save(fonction), HttpStatus.OK);

    }



    @DeleteMapping("/fonctions/{id}")
    public @ResponseBody
    ResponseEntity<String> deleteFonction(@PathVariable("id") Long id) {

        System.out.println("Delete Fonction with Id: " + id + " Called....");
        fonctionRepository.deleteById(id);
        return new ResponseEntity<String>("Response from DELETE method", HttpStatus.OK);
    }

}