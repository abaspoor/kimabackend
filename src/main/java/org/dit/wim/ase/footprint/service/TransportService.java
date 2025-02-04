package org.dit.wim.ase.footprint.service;

import org.dit.wim.ase.footprint.entity.Transportmodel;
import org.dit.wim.ase.footprint.model.TransportResponse;

import java.util.List;

public interface TransportService {
    List<TransportResponse> getAllTransportmethod();
    TransportResponse getTransportmethodById(Integer  T_Id);
    void createTransport(Transportmodel Transpo);
    void deleteTransport(Integer T_Id);

}
