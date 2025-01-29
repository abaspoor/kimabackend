package org.dit.wim.ase.footprint.controller;

import org.dit.wim.ase.footprint.entity.Transportmodel;
import org.dit.wim.ase.footprint.service.Transportservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class Transportcontroller {
    @Autowired
     Transportservice Transport;
    public Transportcontroller(Transportservice Transport) {
        this.Transport = Transport;
    }
    @GetMapping("/method")
    @CrossOrigin(origins = "*")
    public List<Transportmodel> getTransportmethod() {
        return Transport.getTransportmethod();
    }
    @PostMapping("/method/create")
    public void createTransport( @RequestBody Transportmodel Transpo) {
        Transport.createTransport(Transpo);
    }

    @DeleteMapping("/method/{id}")
    public void deleteTransport(@PathVariable Integer id) {

        Transport.deleteTransport(id);
    }

}
