package org.dit.wim.ase.footprint.service;

import lombok.extern.log4j.Log4j2;
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
    public void setAnswer(Answermodel answer) {
        answerRepository.save(answer);
    }

    @Override
    public void deleteAnswer(Integer id) {
        answerRepository.deleteById(id);
    }

    private AnswerResponse convertToAnswerResponse(Answermodel answerModel){
        String transportMethodName = (answerModel.getTransportmodel() != null) ? answerModel.getTransportmodel().getTransportname() : "unknown";
        return AnswerResponse.builder()
                .Answer_id(answerModel.getAnswer_id())
                .Date(answerModel.getDate())
                .Time(answerModel.getTime())
                .Distance(answerModel.getDistance())
                .Passenger_count(answerModel.getPassenger_count())
                .TransportMethodName(transportMethodName)
                .build();
    }
}
