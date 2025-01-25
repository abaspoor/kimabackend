package org.dit.wim.ase.footprint.controller;

import org.dit.wim.ase.footprint.model.UserProperty;
import org.dit.wim.ase.footprint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UserController {
    @Autowired
    private  UserService Service;

    public UserController(UserService Service) {
        this.Service = Service;
    }
    @GetMapping("/users")
    @CrossOrigin(origins = "*")
     public List<UserProperty> getUsers(){
        return Service.getUsers();
    }
@PostMapping("/signup")
    public void  addUser(@RequestBody UserProperty User){
         Service.addUser(User);
}
@DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        Service.deletuser(id);
}

@GetMapping("/")
    public String home(){
        return "Hello World";
}

}
