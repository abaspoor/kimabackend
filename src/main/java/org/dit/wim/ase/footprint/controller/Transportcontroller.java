package org.dit.wim.ase.footprint.controller;

import org.dit.wim.ase.footprint.entity.Transportmodel;
import org.dit.wim.ase.footprint.model.TransportResponse;
import org.dit.wim.ase.footprint.service.AnswerserviceImpl;
import org.dit.wim.ase.footprint.service.TransportService;
import org.dit.wim.ase.footprint.service.TransportServiceImpl;
import org.dit.wim.ase.footprint.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transportmethods")
public class Transportcontroller {
    private final UserService userService;
    private final TransportService transportService;
    private final AnswerserviceImpl answerService;
    //    private final ResulService
    TransportServiceImpl Transport;
    public Transportcontroller(UserService userService, TransportService transportService, AnswerserviceImpl answerService, TransportServiceImpl Transport) {
        this.userService = userService;
        this.transportService = transportService;
        this.answerService = answerService;
        this.Transport = Transport;
    }
    @GetMapping()
//    @CrossOrigin(origins = "*")
    public ResponseEntity<?> getAllTransportmethod() {
        List<TransportResponse> transportResponseList=transportService.getAllTransportmethod();
        if(transportResponseList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Map.of("message", "No transport Method found"));
        }else {
            return new ResponseEntity<>(transportResponseList,HttpStatus.OK);
        }
    }
    @PostMapping("/create")
    public void createTransport( @RequestBody Transportmodel Transpo) {
        Transport.createTransport(Transpo);
    }

    @DeleteMapping("/delete")
    public void deleteTransport(@PathVariable("id") Integer id) {
        Transport.deleteTransport(id);
    }

}
