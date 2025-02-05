package org.dit.wim.ase.footprint.service;

import org.dit.wim.ase.footprint.DTO.UserLoginDTO;
import org.dit.wim.ase.footprint.entity.UserProperty;
import org.dit.wim.ase.footprint.model.UserPropertyResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    List<UserPropertyResponse> getAllUsers();
    UserPropertyResponse getUserById(Integer User_Id);
    UserProperty addUser(UserProperty user);
    Optional<Map<String, String>> authenticateUser(UserLoginDTO user);
}
