package com.example.codese_spring.repository;

import com.example.codese_spring.helper.JdbcMapper.UserDtoMapper;
import com.example.codese_spring.security.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public class UserRepository {

  @Autowired
  JdbcTemplate jdbcTemplate;

  public UserDTO getUserByName(String name) {
    String sql = "SELECT accountID,email,display,password,role FROM `shopese-spring`.account "
        + "where email = ?";
    Object[] params = {name};
    UserDTO userDTO = jdbcTemplate.queryForObject(sql, new UserDtoMapper(), params);
    return userDTO;
  }

  public int addUser(UserDTO userDTO) {
    String sql = "insert into `account`(accountID,email,display,`password`,role) values(?,?,?,?,?)";
    Object[] params = {userDTO.getID(), userDTO.getEmail(), userDTO.getDisplay(),
        userDTO.getPassword()
        , userDTO.getRoles()};
    return jdbcTemplate.update(sql, params);
  }

  public boolean isExist(String name) {
    String sql = "select exists (select * from account where `email` = ?)";
    Object[] param = {name};
    return jdbcTemplate.queryForObject(sql, Boolean.class, param);
  }

  public boolean isExist(String username, String password) {
    String sql = "select exists (select * from account where `email`= ? and `password` = ?)";
    Object[] param = {username, password};
    return jdbcTemplate.queryForObject(sql, Boolean.class, param);
  }
}
