package org.dit.wim.ase.footprint.service;

import org.dit.wim.ase.footprint.entity.Transportmodel;
import org.dit.wim.ase.footprint.repo.MethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Transportservice {
    @Autowired
    MethodRepository Repo;

    public List<Transportmodel> getTransportmethod() {
        return Repo.findAll();
    }
 //shayad beshe az in idea baraye safheye asli va vared kardan method transport estefade kard
    public  Transportmodel getTransportmethodById(Integer  id ) {
       return Repo.findById(id).get();
    }

    public void createTransport(Transportmodel Transpo) {
        Repo.save(Transpo);
    }

    public void deleteTransport(Integer id) {

        Repo.deleteById(id);

    }
}
