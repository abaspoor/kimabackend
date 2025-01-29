package org.dit.wim.ase.footprint.service;

import org.dit.wim.ase.footprint.entity.UserProperty;
import org.dit.wim.ase.footprint.model.UserPropertyResponse;
import org.dit.wim.ase.footprint.repo.UserRepository;
import org.springframework.stereotype.Service;
import lombok.extern.log4j.Log4j2;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public List<UserPropertyResponse> getAllUsers(){
        log.info("Fetching All Users");
        //Fetch Users
        List<UserProperty> userPropertyList = userRepository.findAll();
        List<UserPropertyResponse> userPropertyResponseList =userPropertyList.stream()
                .map(this::convertToUserPropertyResponse)
                .collect(Collectors.toList());
        log.info("Fetched ALl Users");
        return userPropertyResponseList;
    }

    @Override
    public UserPropertyResponse getUserById(Integer User_Id) {
        log.info("Fetching User By Id: {}",User_Id);
        UserProperty userProperty=userRepository.findById(User_Id)
                .orElseThrow(()->new RuntimeException("User with given doesnt exists"));
        UserPropertyResponse userPropertyResponse= convertToUserPropertyResponse(userProperty);
        log.info("Fetched User by id: {}",User_Id);
        return userPropertyResponse;
    }

    private UserPropertyResponse convertToUserPropertyResponse(UserProperty userProperty){
        return UserPropertyResponse.builder()
                .User_id(userProperty.getUser_id())
                .Username(userProperty.getUsername())
                .Password(userProperty.getPassword())
                .Email(userProperty.getEmail())
                .Firstname(userProperty.getUsername())
                .Lastname(userProperty.getLastname())
                .build();
    }
}
