package org.dit.wim.ase.footprint.service;

import org.dit.wim.ase.footprint.DTO.AnswerDTO;
import org.dit.wim.ase.footprint.entity.Answermodel;
import org.dit.wim.ase.footprint.model.AnswerResponse;

import java.util.List;

public interface AnswerService {
    List<AnswerResponse> getAllAnswers();
    List<AnswerDTO> getAllAnswersExtra();
    void setAnswer(Answermodel answer);
    void deleteAnswer(Integer id);
}
