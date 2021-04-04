package com.example.codese_spring.entity;

import java.sql.Timestamp;

public class Receipt {

  private String receiptID;
  private String accountID;
  private Integer totalMoney;
  private String status;
  private byte deleted;
  private String createAt;
  private String updateAt;

  public Receipt() {
  }

  public Receipt(String receiptID, String accountID, Integer totalMoney, String status,
      byte deleted, String createAt, String updateAt) {
    this.receiptID = receiptID;
    this.accountID = accountID;
    this.totalMoney = totalMoney;
    this.status = status;
    this.deleted = deleted;
    this.createAt = createAt;
    this.updateAt = updateAt;
  }

  public String getReceiptID() {
    return receiptID;
  }

  public void setReceiptID(String receiptID) {
    this.receiptID = receiptID;
  }

  public String getAccountID() {
    return accountID;
  }

  public void setAccountID(String accountID) {
    this.accountID = accountID;
  }

  public Integer getTotalMoney() {
    return totalMoney;
  }

  public void setTotalMoney(Integer totalMoney) {
    this.totalMoney = totalMoney;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public byte getDeleted() {
    return deleted;
  }

  public void setDeleted(byte deleted) {
    this.deleted = deleted;
  }

  public String getCreateAt() {
    return createAt;
  }

  public void setCreateAt(String createAt) {
    this.createAt = createAt;
  }

  public String getUpdateAt() {
    return updateAt;
  }

  public void setUpdateAt(String updateAt) {
    this.updateAt = updateAt;
  }
}
