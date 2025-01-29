package org.dit.wim.ase.footprint.service;

import org.dit.wim.ase.footprint.entity.Answermodel;
import org.dit.wim.ase.footprint.repo.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Answerservice {
    @Autowired
    AnswerRepository answerrepo;

    public List<Answermodel> getanswers() {
        return answerrepo.findAll();
    }


    public void createanswer(Answermodel answer) {
        answerrepo.save(answer);
    }


    public void deletanswer(Integer id) {
        answerrepo.deleteById(id);
    }
}
