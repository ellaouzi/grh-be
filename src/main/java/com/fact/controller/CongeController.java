package com.fact.controller;


import com.fact.dto.CongeDto;
import com.fact.dto.CongeInrfaceDto;
import com.fact.dto.CongeItemDto;
import com.fact.mapper.CongeMapper;
import com.fact.model.Conge;
import com.fact.model.Employee;
import com.fact.repository.CongeRepository;
import com.fact.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.fact.enums.CongeState.APPROVED;
import static com.fact.enums.CongeState.NEW;
import static com.fact.enums.CongeState.REJECTED;

@CrossOrigin(origins = {"*"}, maxAge = 6000)

@RestController
@RequestMapping("/api")
public class CongeController {
    @Autowired
    CongeRepository congeRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/conges")
    public ResponseEntity<List<CongeInrfaceDto>> getAllCongeOptions() {
         return new ResponseEntity<>(congeRepository.allConges(), HttpStatus.OK);
    }

    @PostMapping("/conges/create")
    public Conge createConge(@RequestBody Conge conge) {

        Employee employee = new Employee();
        employee.setId(conge.getEmployee_id());
        conge.setEmployee(employee);
        conge.setCreated(new Date());
        conge.setState(NEW);
        return congeRepository.save(conge);

    }


    @GetMapping("/conges/{id}")
    public ResponseEntity<CongeItemDto> getCongeById(@PathVariable("id") Long id) {
        CongeItemDto conge = congeRepository.congerById(id);

         if (conge != null) {
            return new ResponseEntity<>(congeRepository.congerById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/conges/{id}")
    public ResponseEntity<Conge> updateConge(@PathVariable("id") Long id, @RequestBody CongeDto congeDto) {

           Logger logger = LoggerFactory.getLogger(UtilisateurController.class);
        logger.info("congeDto: {}", congeDto);
        System.out.println("Conge ID: " + (id));
        Conge conge= congeRepository.findById(id).get();

        conge.setUpdated(new Date());
        if(congeDto.getStatut().indexOf("acceptée")!=-1)
        conge.setState(APPROVED);
        else     if(congeDto.getStatut().indexOf("refusée")!=-1)
            conge.setState(REJECTED);
        else
            conge.setState(NEW);
        conge.setStatut(congeDto.getStatut());
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