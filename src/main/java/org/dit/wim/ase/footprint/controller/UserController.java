package org.dit.wim.ase.footprint.controller;

import com.sun.jdi.connect.spi.TransportService;
import org.dit.wim.ase.footprint.entity.UserProperty;
import org.dit.wim.ase.footprint.model.UserPropertyResponse;
import org.dit.wim.ase.footprint.service.Answerservice;
import org.dit.wim.ase.footprint.service.UserService;
import org.dit.wim.ase.footprint.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PrivateKey;
import java.util.List;

@RestController
@RequestMapping("/api/Users")
public class UserController {
    private final UserService userService;
    private final TransportService transportService;
    private final Answerservice answerService;
//    private final ResulService

    public UserController(UserService userservice, TransportService transport_service, Answerservice answerservice) {
        this.userService = userservice;
        this.transportService = transport_service;
        this.answerService = answerservice;
    }
    @GetMapping()
    public ResponseEntity<List<UserPropertyResponse>> getAllUsers(){
        List<UserPropertyResponse> userPropertyResponse = userService.getAllUsers();
        return new ResponseEntity<>(userPropertyResponse, HttpStatus.OK);
    }
    @GetMapping("/{id}")
     public ResponseEntity<UserPropertyResponse> getUserById(@PathVariable("id") Integer userId){
        UserPropertyResponse userPropertyResponse = userService.getUserById(userId);
        return new ResponseEntity<>(userPropertyResponse, HttpStatus.OK);
    }
//@PostMapping("/signup")
//    public void  addUser(@RequestBody UserProperty User){
//         Service.addUser(User);
//}
//@DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable Integer id){
//        Service.deletuser(id);
//}

//@GetMapping("/")
//    public String home(){
//        return "Hello World";
//}

}
