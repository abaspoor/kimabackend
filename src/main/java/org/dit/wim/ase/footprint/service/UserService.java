package org.dit.wim.ase.footprint.service;

import org.dit.wim.ase.footprint.model.UserPropertyResponse;

import java.util.List;

public interface UserService {
    List<UserPropertyResponse> getAllUsers();
    UserPropertyResponse getUserById(Integer User_Id);
}
