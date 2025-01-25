package org.dit.wim.ase.footprint.service;

import org.dit.wim.ase.footprint.model.UserProperty;
import org.dit.wim.ase.footprint.repo.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    Userrepo userrepository;


    public List<UserProperty> getUsers() {
        return userrepository.findAll();

    }


    public void addUser(UserProperty User) {
        userrepository.save(User);
    }

    public void deletuser(Integer id) {
        userrepository.deleteById(id);
    }


}
