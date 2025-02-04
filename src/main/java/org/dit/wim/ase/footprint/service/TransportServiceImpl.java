package org.dit.wim.ase.footprint.service;

import lombok.extern.log4j.Log4j2;
import org.dit.wim.ase.footprint.entity.Transportmodel;
import org.dit.wim.ase.footprint.model.TransportResponse;
import org.dit.wim.ase.footprint.repo.TransportRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Log4j2
public class TransportServiceImpl implements TransportService{
    private final TransportRepository transportRepository;

    public TransportServiceImpl(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    @Override
    public List<TransportResponse> getAllTransportmethod() {
        log.info("Fetching All TransferMethods");
        List<Transportmodel> transportModelList = transportRepository.findAll();
        List<TransportResponse> transportResponseList =transportModelList.stream()
                .map(this::convertToTransportResponse)
                .collect(Collectors.toList());
        log.info("Fetched ALl TransportMethod");
        return transportResponseList;
    }

    @Override
    public TransportResponse getTransportmethodById(Integer T_Id) {

        log.info("Fetching TransferMethod By Id: {}",T_Id);
        Transportmodel transportModel=transportRepository.findById(T_Id)
                .orElseThrow(()->new RuntimeException("User with given doesnt exists"));
        TransportResponse transportResponse= convertToTransportResponse(transportModel);
        log.info("Fetched TransferMethod by id: {}",T_Id);
        return transportResponse;
    }

    @Override
    public void createTransport(Transportmodel Transpo) {
        transportRepository.save(Transpo);
    }

    @Override
    public void deleteTransport(Integer T_Id) {
        transportRepository.deleteById(T_Id);
    }

    private TransportResponse convertToTransportResponse(Transportmodel transportModel){
        return TransportResponse.builder()
                .T_id(transportModel.getT_id())
                .Transportname(transportModel.getTransportname())
                .Fuel_factor(transportModel.getFuel_factor())
                .Emission_factor(transportModel.getEmission_factor())
                .build();
    }
}
