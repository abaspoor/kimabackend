package org.dit.wim.ase.footprint.service;

import lombok.extern.log4j.Log4j2;
import org.dit.wim.ase.footprint.entity.Transportmodel;
import org.dit.wim.ase.footprint.model.TransportResponse;
import org.dit.wim.ase.footprint.repo.TransportRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
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
//    public void deleteTransport(Integer id) {
//        log.info("transfer request delete by id "+id);
//        if (!transportRepository.existsById(id)) {
//            throw new RuntimeException("Transport with ID " + id + " not found.");
//        }
//        transportRepository.deleteById(id);
//    }

    public Map<String, String> deleteTransport(Integer T_Id) {
        log.info("Request to delete transport with ID: " + T_Id);

        if (!transportRepository.existsById(T_Id)) {
            log.warn("Transport method not found.");
            return Map.of("error", "Transport method not found.");
        }

        Transportmodel transport = transportRepository.findById(T_Id).orElseThrow();

        if (!transport.getAnswers().isEmpty()) {
            log.warn("Cannot delete transport with ID " + T_Id + " because it has linked answers.");
            return Map.of("error", "Cannot delete: Transport method is linked to existing answers.");
        }

        transportRepository.deleteById(T_Id);
        log.info("Transport method deleted successfully.");

        return Map.of("message", "Transport method deleted successfully.");
    }

    public Map<String, String> deleteTransportCascade(Integer T_Id) {
        log.info("Request to cascade delete transport with ID: " + T_Id);

        if (!transportRepository.existsById(T_Id)) {
            log.warn("Transport method not found.");
            return Map.of("error", "Transport method not found.");
        }

        transportRepository.deleteById(T_Id);
        log.info("Transport method and linked answers deleted successfully.");

        return Map.of("message", "Transport method and all linked answers deleted successfully.");
    }

    public Map<String, String> deleteTransportAndKeepAnswers(Integer T_Id) {
        log.info("Request to delete transport with ID: " + T_Id + " but keep answers.");

        if (!transportRepository.existsById(T_Id)) {
            log.warn("Transport method not found.");
            return Map.of("error", "Transport method not found.");
        }

        Transportmodel transport = transportRepository.findById(T_Id).orElseThrow();

        // Set transport reference in linked answers to NULL
        transport.getAnswers().forEach(answer -> answer.setTransportmodel(null));

        transportRepository.deleteById(T_Id);

        log.info("Transport method deleted, but linked answers were kept.");
        return Map.of("message", "Transport method deleted, but linked answers were kept.");
    }





    private TransportResponse convertToTransportResponse(Transportmodel transportModel){
        return TransportResponse.builder()
                .id(transportModel.getT_id())
                .transportname(transportModel.getTransportname())
                .fuel_factor(transportModel.getFuel_factor())
                .emission_factor(transportModel.getEmission_factor())
                .build();
    }
}
