package org.dit.wim.ase.footprint.controller;

import org.dit.wim.ase.footprint.model.UserPropertyResponse;
import org.dit.wim.ase.footprint.service.AnswerserviceImpl;
import org.dit.wim.ase.footprint.service.UserService;
import org.dit.wim.ase.footprint.service.TransportService;
import org.dit.wim.ase.footprint.entity.UserProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/Users")
public class UserController {
    private final UserService userService;
    private final TransportService transportService;
    private final AnswerserviceImpl answerService;
//    private final ResulService

    public UserController(UserService userservice, TransportService transportService, AnswerserviceImpl answerservice) {
        this.userService = userservice;
        this.transportService = transportService;
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
    @PostMapping("/signup")
        public ResponseEntity<Map<String, Object>> addUser(@RequestBody UserProperty User){
        try {
            UserProperty registeredUser = userService.addUser(User);
            Map<String, Object> response = new HashMap<>();
            response.put("userId", registeredUser.getUser_id());
            response.put("message", "User registered successfully");
            return ResponseEntity.ok().body(response);
        }catch  (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));
    }}
//@DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable Integer id){
//        Service.deletuser(id);
//}

//@GetMapping("/")
//    public String home(){
//        return "Hello World";
//}

}
