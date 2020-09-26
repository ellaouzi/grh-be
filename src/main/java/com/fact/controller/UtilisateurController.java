package com.fact.controller;


import com.fact.dto.CongeItemDto;
import com.fact.dto.UtilisateurDto;
import com.fact.dto.UtilisateurMapDto;
import com.fact.model.*;
import com.fact.repository.*;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = {"*"}, maxAge = 6000)

@RestController
@RequestMapping("/api")
public class UtilisateurController {
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    EntiteRepository entiteRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    FonctionRepository fonctionRepository;

    @GetMapping("/utilisateurs")
    public ResponseEntity<List<UtilisateurDto>> getAllUtilisateurOptions() {
        return new ResponseEntity<>(utilisateurRepository.allUtilisateurs(), HttpStatus.OK);
    }

    @GetMapping("/utilisateurList")
    public ResponseEntity<List<UtilisateurMapDto>> getAllUtilisateursMap() {
        return new ResponseEntity<>(utilisateurRepository.getUtilisateursList(), HttpStatus.OK);
    }
    @GetMapping("/teamByManagerId/{id}")
     public ResponseEntity<List<UtilisateurDto>> getTeamByManagerId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(utilisateurRepository.teamByManagerId(id), HttpStatus.OK);
    }

    @PostMapping("/utilisateurs/create")
    public Utilisateur createUtilisateur(@RequestBody Employee utilisateur) {



        Logger logger = LoggerFactory.getLogger(UtilisateurController.class);
        logger.info("utilisateur: {}", utilisateur);

        try {
            if (utilisateurRepository.findById(utilisateur.getManager_id_()).isPresent()) {
                Manager manager = new Manager();
                manager.setId(utilisateur.getManager_id_());
                utilisateur.setManager(manager);
            }
        }catch (Exception e){
            logger.info("Now manager specified");
        }

        try {
            if (utilisateurRepository.findById(utilisateur.getInterim_id_()).isPresent()) {
                Employee interim = new Employee();
                interim.setId(utilisateur.getInterim_id_());
                utilisateur.setInterim(interim);
            }
        }catch (Exception e){
            logger.info("Now interim");
        }


        if(entiteRepository.findById(utilisateur.getEntite_id_()).isPresent()) {
            utilisateur.setEntite(entiteRepository.findById(utilisateur.getEntite_id_()).get());
            System.out.println(entiteRepository.findById(utilisateur.getEntite_id_()).get().getNom() +".......");
        }
        if (fonctionRepository.findById(utilisateur.getFonction_id_()).isPresent()) {
            utilisateur.setFonction(fonctionRepository.findById(utilisateur.getFonction_id_()).get());
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getClearPassword()));

        utilisateurRepository.saveAndFlush(utilisateur);
        Authority authority = new Authority();
        authority.setUtilisateur(utilisateur);
        authority.setRole("ROLE_USER");
        authority.setUsername(utilisateur.getLogin());
        authorityRepository.save(authority);
        return utilisateur;

    }


    @GetMapping("/utilisateurs/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable("id") Long id) {
         if( utilisateurRepository.findById(id).isPresent()) {
            return new ResponseEntity<>( utilisateurRepository.findById(id).get(), HttpStatus.OK);
        } else {
             System.out.println(id + "<<<<<<<< Not found");
             System.out.println(utilisateurRepository.findById(id).get());
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/utilisateursByLogin")
    public ResponseEntity<UtilisateurDto> getUtilisateurById( @RequestParam("username") String username) {
         if (utilisateurRepository.findByLogin(username) != null) {
            return new ResponseEntity<>(utilisateurRepository.findByLogin(username), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/utilisateurs/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable("id") Long id,
                                                         @RequestBody Employee utilisateur) {
        Logger logger = LoggerFactory.getLogger(UtilisateurController.class);
        logger.info("utilisateur: {}", utilisateur);
        Entite entite = new Entite();
        Fonction fonction = new Fonction();
        entite.setId(utilisateur.getEntite_id_());
        fonction.setId(utilisateur.getFonction_id_());
        utilisateur.setEntite(entite);
        utilisateur.setFonction(fonction);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getClearPassword()));
        return new ResponseEntity<>(utilisateurRepository.save(utilisateur), HttpStatus.OK);
    }


    @DeleteMapping("/utilisateurs/{id}")
    public @ResponseBody
    ResponseEntity<String> deleteUtilisateur(@PathVariable("id") Long id) {

        System.out.println("Delete Utilisateur with Id: " + id + " Called....");
        utilisateurRepository.deleteById(id);
        return new ResponseEntity<String>("Response from DELETE method", HttpStatus.OK);
    }


/*    @GetMapping("/test1")

  public String affectUser() {
Utilisateur utilisateur = new Utilisateur();
utilisateur.setNom("user1");
utilisateur.setEnabled(true);
Entite entite1 = new Entite();
entite1.setNom("entite1");
entite1.setCode("code1");
Entite entite2 = new Entite();
entite2.setNom("entite2");
entite2.setCode("code2");
 entiteRepository.saveAndFlush(entite1);
utilisateur.setEntite(entite1);
TypeContrat typeContrat1 = new TypeContrat();
typeContrat1.setCode("Code 1");
typeContrat1.setNom("type1 1");
TypeContrat typeContrat2 = new TypeContrat();
typeContrat2.setCode("Code 2");
typeContrat2.setNom("type1 2");
        utilisateurRepository.saveAndFlush(utilisateur);
        typeContratRepository.saveAndFlush(typeContrat1);
        typeContratRepository.saveAndFlush(typeContrat2);
        Set<TypeContrat> typeContrats =  new HashSet<>();
        System.out.println(typeContrat1.getNom() + " ------ " + typeContrat1.getId());
         typeContrats.add(typeContrat1);
         typeContrats.add(typeContrat2);

 utilisateur.setTypeContrats(typeContrats);

        utilisateurRepository.save(utilisateur);

    return " utilisateurTypeContrat was updated.... and utilisateur has : " + utilisateur.getTypeContrats().size()
           ;// + " Entite 1 hase " + typeContrat1.getUtilisateurTypeContratSet().size() ;
    }*/
}