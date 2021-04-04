package com.example.codese_spring.entity;

public class ReceiptDetail {

  private String receiptID;
  private String productID;
  private int amount;

  public ReceiptDetail() {
  }

  public ReceiptDetail(String receiptID, String productID, int amount) {
    this.receiptID = receiptID;
    this.productID = productID;
    this.amount = amount;
  }

  public String getReceiptID() {
    return receiptID;
  }

  public void setReceiptID(String receiptID) {
    this.receiptID = receiptID;
  }

  public String getProductID() {
    return productID;
  }

  public void setProductID(String productID) {
    this.productID = productID;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }
}
