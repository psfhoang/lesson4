package com.example.codese_spring.repository;

import com.example.codese_spring.dto.ReceiptInfor;
import com.example.codese_spring.helper.JdbcMapper.ReceiptInforMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public class ReceiptRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<ReceiptInfor> showInforAll(){
        String sql = "SELECT receiptID, display,`status`,total_money from receipt " +
                "inner join `account` where receipt.accountID=`account`.accountID";
        return jdbcTemplate.query(sql,new ReceiptInforMapper());
    }

    public int addReceipt(String uuid, int totalMoney,String status,String accountID){
        String sql = "insert into Receipt(receiptID,accountID,total_money,status) values(?,?,?,?)";
        Object[] params = {uuid,accountID,totalMoney,status};
        return jdbcTemplate.update(sql,params);
    }
    public int addReceiptDetails(String receiptId,String productId,int amount){
        String sql = "insert into ReceiptDetail(receiptID,productID,amount) values(?,?,?)";
        Object[] params = {receiptId,productId,amount};
        return jdbcTemplate.update(sql,params);
    }



}
