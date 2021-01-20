package com.example.codese_spring.controller;

import com.example.codese_spring.dto.ProductDTO;
import com.example.codese_spring.dto.ProductGetAll;
import com.example.codese_spring.helper.ResponseBuilder.ResponseForm;
import com.example.codese_spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

  @Autowired
  ProductService productService;


  @GetMapping("/all")
  public ResponseEntity<ResponseForm<List<ProductGetAll>>> getAllProducts() {
    List<ProductGetAll> productGetAll = productService.getAllProducts();
    return ResponseEntity.ok(ResponseForm.buildCustomResponse(productGetAll, 1, "ok"));
  }


  @GetMapping("/find-by-id")
  public ResponseEntity<ResponseForm<ProductDTO>> getProductById(
      @RequestParam(name = "id") String idInput) {
    return ResponseEntity
        .ok(ResponseForm.buildCustomResponse(productService.getProductById(idInput), 1, "ok"));
  }


  @GetMapping("/get-price-out-order")
  public ResponseEntity<ResponseForm<List<ProductDTO>>> getProductByPriceWithOrder(
      @RequestParam Integer sortType) {
    return ResponseEntity.ok(ResponseForm
        .buildCustomResponse(productService.getProductByPriceWithOrder(sortType), 1, "ok"));
  }

  @GetMapping("/get-display")
  public ResponseEntity<ResponseForm<List<ProductDTO>>> getProductByDisplayWithOrder(
      @RequestParam Integer sortType) {
    return ResponseEntity.ok(ResponseForm
        .buildCustomResponse(productService.getProductByDisplayWithOrder(sortType), 1, "ok"));
  }

  @GetMapping("/find-by-name")
  public @ResponseBody
  ResponseEntity<ResponseForm<List<ProductDTO>>> getProductByDisplay(@RequestParam String display) {
    return ResponseEntity
        .ok(ResponseForm.buildCustomResponse(productService.getProductByDisplay(display), 1, "ok"));
  }


  @PostMapping("/add-product")
  public ResponseEntity<ResponseForm<Boolean>> addProduct(@RequestBody ProductDTO productDTO) {
    return ResponseEntity
        .ok(ResponseForm.buildCustomResponse(productService.addProduct(productDTO), 1, "ok"));
  }

  @PutMapping("/update")
  public ResponseEntity<ResponseForm<Boolean>> updateProduct(@RequestBody ProductDTO productDTO) {
    return ResponseEntity
        .ok(ResponseForm.buildCustomResponse(productService.updateProduct(productDTO), 1, "ok"));
  }


}
