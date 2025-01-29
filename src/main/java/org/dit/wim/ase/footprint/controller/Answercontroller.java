package org.dit.wim.ase.footprint.controller;

import org.dit.wim.ase.footprint.entity.Answermodel;
import org.dit.wim.ase.footprint.service.Answerservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController


public class Answercontroller {
    @Autowired
    Answerservice answerservice;

    @GetMapping("/answer")
    @CrossOrigin(origins = "*")
    public List<Answermodel> getanswers() {
       return answerservice.getanswers();

    }

    @PostMapping("/answer")
    public void createanswer(@RequestBody Answermodel answer) {
        answerservice.createanswer(answer);
    }

    @DeleteMapping("/answer/{id}")
    public void deleteanswer(@PathVariable int id) {
        answerservice.deletanswer(id);
    }

}
