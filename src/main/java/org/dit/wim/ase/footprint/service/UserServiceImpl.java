package org.dit.wim.ase.footprint.service;

import org.dit.wim.ase.footprint.security.JwtUtil;
import org.dit.wim.ase.footprint.DTO.UserLoginDTO;
import org.dit.wim.ase.footprint.entity.UserProperty;
import org.dit.wim.ase.footprint.model.UserPropertyResponse;
import org.dit.wim.ase.footprint.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
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

    @Override
    public UserProperty addUser(UserProperty user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new RuntimeException("User with this username or email already exists.");
        }
        // Encrypt password before saving
        String hashedPassword = passwordEncoder.encode(user.getPassword());

        log.info("request came");
        // Create user object
        UserProperty newUser = UserProperty.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(hashedPassword)
                .Firstname(user.getFirstname())
                .Lastname(user.getLastname())
                .build();
        return userRepository.save(newUser);
    }
    @Override
    public Optional<Map<String, String>> authenticateUser(UserLoginDTO userDTO){
            Optional<UserProperty> userOptional = userRepository.findByUsername(userDTO.getUsername());
            if(userOptional.isPresent()){
                log.info("fetched");
                UserProperty User = userOptional.get();
                if(passwordEncoder.matches(userDTO.getPassword(),User.getPassword())){
                    String token = jwtUtil.generateToken(userDTO.getUsername());
                    String lastname = User.getLastname();
                    return Optional.of(Map.of("token",token,"lastname",lastname));
                }
            }
            return Optional.empty();
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
