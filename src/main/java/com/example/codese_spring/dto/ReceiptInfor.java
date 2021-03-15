package com.example.codese_spring.dto;

import java.util.List;

public class ReceiptInfor {

  private String receiptID;
  private String userName;
  private String status;
  private int totalMoney;
  private ProductDTORes productDTORes;

  public String getReceiptID() {
    return receiptID;
  }

  public void setReceiptID(String receiptID) {
    this.receiptID = receiptID;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getTotalMoney() {
    return totalMoney;
  }

  public void setTotalMoney(int totalMoney) {
    this.totalMoney = totalMoney;
  }

  public ProductDTORes getProductDTORes() {
    return productDTORes;
  }

  public void setProductDTORes(ProductDTORes productDTORes) {
    this.productDTORes = productDTORes;
  }
}


class ProductDTORes {

  List<ProductDTO> productDTOS;
  int amount;

}
