package com.example.codese_spring.service;

import com.example.codese_spring.exception.ResourceAlreadyExistException;
import com.example.codese_spring.repository.UserRepository;
import com.example.codese_spring.security.dto.UserDTO;
import com.example.codese_spring.security.dto.UserRegister;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;
  @Autowired
  PasswordEncoder passwordEncoder;

  public Boolean addUser(UserRegister userRegister) {
    if (userRepository.isExist(userRegister.getEmail())) {
      throw new ResourceAlreadyExistException(userRegister.getEmail());
    }
    UserDTO userDTO = new UserDTO();
    userDTO.setID(UUID.randomUUID().toString());
    userDTO.setEmail(userRegister.getEmail());
    userDTO.setPassword(userRegister.getPassword());
    userDTO.setDisplay(userRegister.getDisplay());
    userDTO.setRoles("USER");
    return userRepository.addUser(userDTO) != 0;
  }

  public boolean isExist(String username, String password) {
    return userRepository.isExist(username, password);
  }
}
