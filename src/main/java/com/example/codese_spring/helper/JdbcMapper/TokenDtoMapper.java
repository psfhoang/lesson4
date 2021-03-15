package com.example.codese_spring.helper.JdbcMapper;

import com.example.codese_spring.entity.Token;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class TokenDtoMapper implements RowMapper<Token> {

  @Override
  public Token mapRow(ResultSet rs, int rowNum) throws SQLException {
    Token token = new Token();
    token.setEmail(rs.getString(2));
    token.setToken(rs.getString(3));
    return token;
  }
}
