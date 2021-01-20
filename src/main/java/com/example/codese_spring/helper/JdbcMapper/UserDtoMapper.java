package com.example.codese_spring.helper.JdbcMapper;

import com.example.codese_spring.security.dto.UserDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UserDtoMapper implements RowMapper<UserDTO> {

  @Override
  public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    UserDTO userDTO = new UserDTO();
    userDTO.setID(rs.getString(1));
    userDTO.setEmail(rs.getString(2));
    userDTO.setDisplay(rs.getString(3));
    userDTO.setPassword(rs.getString(4));
    userDTO.setRoles(rs.getString(5));
    return userDTO;
  }
}
