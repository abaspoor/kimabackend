package org.dit.wim.ase.footprint.service;

import lombok.extern.log4j.Log4j2;
import org.dit.wim.ase.footprint.DTO.AnswerDTO;
import org.dit.wim.ase.footprint.entity.Answermodel;
import org.dit.wim.ase.footprint.model.AnswerResponse;
import org.dit.wim.ase.footprint.repo.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Log4j2
public class AnswerserviceImpl implements AnswerService{

    private final AnswerRepository answerRepository;

    public AnswerserviceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }


    @Override
    public List<AnswerResponse> getAllAnswers() {
        log.info("Fetching All Answers");
        List<Answermodel> answerModelList = answerRepository.findAll();
        List<AnswerResponse> answerResponseList =answerModelList.stream()
                .map(this::convertToAnswerResponse)
                .collect(Collectors.toList());
        log.info("Fetched ALl Answers");
        return answerResponseList;
    }

    @Override
    public List<AnswerDTO> getAllAnswersExtra() {
        log.info("Fetching All Answers with extra");
        List<Answermodel> answerModelList = answerRepository.findAll();
        List<AnswerDTO> answerDtoList =answerModelList.stream()
                .map(this::convertToAnswerDTO)
                .collect(Collectors.toList());
        log.info("Fetched ALl Answers with extra");
        return answerDtoList;
    }

    @Override
    public void setAnswer(Answermodel answer) {
        answerRepository.save(answer);
    }

    @Override
    public void deleteAnswer(Integer id) {
        answerRepository.deleteById(id);
    }

    private AnswerResponse convertToAnswerResponse(Answermodel answerModel){
        return AnswerResponse.builder()
                .Answer_id(answerModel.getAnswer_id())
                .Date(answerModel.getDate())
                .Time(answerModel.getTime())
                .Distance(answerModel.getDistance())
                .Passenger_count(answerModel.getPassenger_count())
                .build();
    }
    private AnswerDTO convertToAnswerDTO(Answermodel answerModel){
        String transportMethodName = (answerModel.getTransportmodel() != null) ? answerModel.getTransportmodel().getTransportname() : "Unknown TransportName";
        String userName = (answerModel.getUserproperty() != null) ? answerModel.getUserproperty().getUsername() : "Unknown Username";
        return AnswerDTO.builder()
                .Answer_id(answerModel.getAnswer_id())
                .Date(answerModel.getDate())
                .Time(answerModel.getTime())
                .Distance(answerModel.getDistance())
                .Passenger_count(answerModel.getPassenger_count())
                .TransportMethodName(transportMethodName)
                .UserName(userName)
                .build();
    }
}
