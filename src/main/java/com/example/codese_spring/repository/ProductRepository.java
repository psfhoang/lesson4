package com.example.codese_spring.repository;

import com.example.codese_spring.dto.ProductDTO;
import com.example.codese_spring.dto.ProductGetAll;
import com.example.codese_spring.helper.JdbcMapper.ProductCRUDMapper;
import com.example.codese_spring.helper.JdbcMapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {

  @Autowired
  JdbcTemplate jdbcTemplate;


  public Integer addReceiptDetails() {
    return null;
  }


  //get all
  public List<ProductGetAll> getAllProducts() {
    String sql = "select * from Product";
    List<ProductGetAll> product = jdbcTemplate.query(sql, new ProductMapper());
    return product;
  }

  public List<ProductDTO> getAllProducts1() {
    String sql = "select * from Product";
    List<ProductDTO> product = jdbcTemplate.query(sql, new ProductCRUDMapper());
    return product;
  }

  // get by id
  public ProductDTO getProductById(String idInput) {
    String sql = "select * from Product where `productID` = ?";
    Object[] params = {idInput};
    ProductDTO product = (ProductDTO) jdbcTemplate
        .queryForObject(sql, new ProductCRUDMapper(), params);
    return product;
  }

  // check existed by id
  public boolean checkProductExistedById(String idInput) {
    String sql = "select exists (select * from Product where `productID` = ? and deleted = 0)";
    Object[] params = {idInput};
    return jdbcTemplate.queryForObject(sql, Boolean.class, params);
  }

  // check existed by name
  public boolean checkProductExistedByName(String display) {
    String sql = "select exists (select * from Product where display = ? and deleted = 0)";
    Object[] params = {display};
    return jdbcTemplate.queryForObject(sql, Boolean.class, params);
  }

  // add
  public Integer addProduct(ProductDTO productDTO) {
    String sql = "insert into Product (productID, display, priceIn, priceOut, priceSale" +
        ", amount, shipday, description, images) values (?,?,?,?,?,?,?,?,?);";
    String uuid = UUID.randomUUID().toString();
    Object[] params = {uuid, productDTO.getDisplay(), productDTO.getPriceIn()
        , productDTO.getPriceIn(), productDTO.getPriceOut(), productDTO.getAmount()
        , productDTO.getShipday(), productDTO.getDescription(), productDTO.getImages()};
    return jdbcTemplate.update(sql, params);
  }

  // get by price out asc
  public List<ProductDTO> getAllPriceOutAsc(Integer sortType) {
    String sql = "select * from Product order by priceOut ASC and deleted = 0;";
    List<ProductDTO> product = jdbcTemplate.query(sql, new ProductCRUDMapper());
    return product;
  }

  // get by price out desc
  public List<ProductDTO> getAllPriceOutDesc(Integer sortType) {
    String sql = "select * from Product order by priceOut DESC and deleted = 0";
    List<ProductDTO> product = jdbcTemplate.query(sql, new ProductCRUDMapper());
    return product;
  }

  // get by display asc
  public List<ProductDTO> getAllDisplayAsc() {
    String sql = "select * from Product order by display ASC and deleted = 0";
    List<ProductDTO> product = jdbcTemplate.query(sql, new ProductCRUDMapper());
    return product;
  }

  public List<ProductDTO> getAllDisplayDesc() {
    String sql = "select * from Product order by display DESC and deleted = 0";
    List<ProductDTO> product = jdbcTemplate.query(sql, new ProductCRUDMapper());
    return product;
  }

  public List<ProductDTO> getProductByDisplay(String display) {
    String sql = "select * from Product where lower(display) = lower(?) and deleted = 0";
    Object[] params = {display};
    return jdbcTemplate.query(sql, new ProductCRUDMapper(), params);
  }

  public Integer updateProduct(ProductDTO productDTO) {
    String sql = "update Product set display = ?, priceIn = ?, priceOut = ?, priceSale = ?, amount = ?, shipday = ?, description = ?, images = ? where productID = ?";
    return jdbcTemplate
        .update(sql, productDTO.getDisplay(), productDTO.getPriceIn(), productDTO.getPriceOut(),
            productDTO.getPriceSale(), productDTO.getAmount(), productDTO.getShipday(),
            productDTO.getDescription(), productDTO.getImages(), productDTO.getProductID());
  }

  //update product set display=....,price=...., where id = abc
  //sql = update product set id =id '
  //conditionSql = 'where id = abc'
}
