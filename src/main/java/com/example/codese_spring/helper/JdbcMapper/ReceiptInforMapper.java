package com.example.codese_spring.helper.JdbcMapper;

import com.example.codese_spring.dto.ReceiptInfor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReceiptInforMapper implements RowMapper<ReceiptInfor> {

  @Override
  public ReceiptInfor mapRow(ResultSet rs, int rowNum) throws SQLException {
    ReceiptInfor receiptInfor = new ReceiptInfor();
    receiptInfor.setReceiptID(rs.getString(1));
    receiptInfor.setUserName(rs.getString(2));
    receiptInfor.setStatus(rs.getString(3));
    receiptInfor.setTotalMoney(rs.getInt(4));
    return receiptInfor;
  }
}
