package com.example.codese_spring.dto;

public class ProductDTO {

  private String productID;
  private String display;
  private Integer priceIn;
  private Integer priceOut;
  private Integer priceSale;
  private Integer amount;
  private Integer shipday;
  private String description;
  private String images;

  public ProductDTO() {

  }

  public ProductDTO(String productID, String display, Integer priceIn, Integer priceOut,
      Integer priceSale, Integer amount, Integer shipday, String description, String images) {
    this.productID = productID;
    this.display = display;
    this.priceIn = priceIn;
    this.priceOut = priceOut;
    this.priceSale = priceSale;
    this.amount = amount;
    this.shipday = shipday;
    this.description = description;
    this.images = images;
  }

  public String getProductID() {
    return productID;
  }

  public void setProductID(String productID) {
    this.productID = productID;
  }

  public String getDisplay() {
    return display;
  }

  public void setDisplay(String display) {
    this.display = display;
  }

  public Integer getPriceIn() {
    return priceIn;
  }

  public void setPriceIn(Integer priceIn) {
    this.priceIn = priceIn;
  }

  public Integer getPriceOut() {
    return priceOut;
  }

  public void setPriceOut(Integer priceOut) {
    this.priceOut = priceOut;
  }

  public Integer getPriceSale() {
    return priceSale;
  }

  public void setPriceSale(Integer priceSale) {
    this.priceSale = priceSale;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public Integer getShipday() {
    return shipday;
  }

  public void setShipday(Integer shipday) {
    this.shipday = shipday;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImages() {
    return images;
  }

  public void setImages(String images) {
    this.images = images;
  }

  @Override
  public String toString() {
    return "ProductCRUD{" +
        "productID='" + productID + '\'' +
        ", display='" + display + '\'' +
        ", priceIn=" + priceIn +
        ", priceOut=" + priceOut +
        ", priceSale=" + priceSale +
        ", amount=" + amount +
        ", shipday=" + shipday +
        ", description='" + description + '\'' +
        ", images='" + images + '\'' +
        '}';
  }
}
