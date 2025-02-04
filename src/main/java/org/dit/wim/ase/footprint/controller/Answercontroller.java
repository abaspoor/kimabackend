package org.dit.wim.ase.footprint.controller;

import org.dit.wim.ase.footprint.entity.Answermodel;
import org.dit.wim.ase.footprint.model.AnswerResponse;
import org.dit.wim.ase.footprint.service.AnswerService;
import org.dit.wim.ase.footprint.service.AnswerserviceImpl;
import org.dit.wim.ase.footprint.service.TransportService;
import org.dit.wim.ase.footprint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Answers")
public class Answercontroller {
    private final UserService userService;
    private final TransportService transportService;
    private final AnswerserviceImpl answerService;

    public Answercontroller(UserService userService, TransportService transportService, AnswerserviceImpl answerService) {
        this.userService = userService;
        this.transportService = transportService;
        this.answerService = answerService;
    }

    @GetMapping()
    public ResponseEntity<List<AnswerResponse>> getAllAnswers(){
        List<AnswerResponse> answerResponseList= answerService.getAllAnswers();
        return new ResponseEntity<>(answerResponseList, HttpStatus.OK);
    }

    @PostMapping("/set")
    public void createanswer(@RequestBody Answermodel answer) {
        answerService.setAnswer(answer);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteanswer(@PathVariable int id) {
        answerService.deleteAnswer(id);
    }

}
