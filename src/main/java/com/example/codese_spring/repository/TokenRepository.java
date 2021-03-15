package com.example.codese_spring.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class TokenRepository {

  private final JdbcTemplate jdbcTemplate;

  public int addToken(String email, String token) {
    String sql = "insert into token(email,token) values(?,?)";
    Object[] param = {email, token};
    return jdbcTemplate.update(sql, param);
  }

}
