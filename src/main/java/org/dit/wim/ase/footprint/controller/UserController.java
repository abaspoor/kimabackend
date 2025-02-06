package org.dit.wim.ase.footprint.controller;

import lombok.extern.log4j.Log4j2;
import org.dit.wim.ase.footprint.DTO.UserListDTO;
import org.dit.wim.ase.footprint.DTO.UserLoginDTO;
import org.dit.wim.ase.footprint.model.UserPropertyResponse;
import org.dit.wim.ase.footprint.repo.UserRepository;
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
import java.util.Optional;
@Log4j2
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final TransportService transportService;
    private final AnswerserviceImpl answerService;
    private final UserRepository userRepository;
//    private final ResulService

    public UserController(UserService userservice, TransportService transportService, AnswerserviceImpl answerservice, UserRepository userRepository) {
        this.userService = userservice;
        this.transportService = transportService;
        this.answerService = answerservice;
        this.userRepository = userRepository;
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
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> addUser(@RequestBody UserProperty User){
        try {
            UserProperty registeredUser = userService.addUser(User);
            Map<String, Object> response = new HashMap<>();
            response.put("userId", registeredUser.getUser_id());
            response.put("message", "User registered successfully");
            return ResponseEntity.ok().body(response); //
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> signUser(@RequestBody UserLoginDTO userLoginDTO){
        Map<String, String> jwtToken  = userService.authenticateUser(userLoginDTO);
        if(jwtToken != null){
            return ResponseEntity.ok(jwtToken);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid username or password"));
        }
    }

    @GetMapping("/admins")
    public ResponseEntity<?> getAdmins(){
        List<UserPropertyResponse> userPropertyResponse = userService.getAdmins();
        if (userPropertyResponse.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Map.of("message", "No admin users found."));
        }
        return ResponseEntity.ok(userPropertyResponse);
    }

    @GetMapping("/admins/getusers")
    public ResponseEntity<?> getUsersforAdmin(){
        List<UserListDTO> userListDTOS = userService.getUsersforAdmin();
        if (userListDTOS.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Map.of("message", "No Users Found"));
        }
        return ResponseEntity.ok(userListDTOS);
    }


//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
//        try {
//            userService.deleteUser(id);
//            return ResponseEntity.ok(Map.of("message", "User deleted successfully."));
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "User not found."));
//        }
//    }


//@GetMapping("/")
//    public String home(){
//        return "Hello World";
//}

}
