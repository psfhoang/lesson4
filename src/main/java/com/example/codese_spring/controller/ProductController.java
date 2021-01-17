package com.example.codese_spring.controller;

import com.example.codese_spring.dto.ProductCRUD;
import com.example.codese_spring.dto.ProductGetAll;
import com.example.codese_spring.helper.ResponseBuilder.ResponseForm;
import com.example.codese_spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopee")
public class ProductController {
    @Autowired
    ProductService productService;


    @GetMapping("/all")
    public ResponseEntity<ResponseForm<List<ProductGetAll>>> getAllProducts() {
        List<ProductGetAll> productGetAll = productService.getAllProducts();
        return ResponseEntity.ok(ResponseForm.buildCustomResponse(productGetAll, 1, "ok"));
    }


    @GetMapping("/find-by-id")
    public ResponseEntity<ResponseForm<ProductCRUD>> getProductById(@RequestParam(name = "id") String idInput) {
        return ResponseEntity.ok(ResponseForm.buildCustomResponse(productService.getProductById(idInput), 1, "ok"));
    }


    @GetMapping("/get-price-out-order")
    public ResponseEntity<ResponseForm<List<ProductCRUD>>> getProductByPriceWithOrder(@RequestParam Integer sortType) {
        return ResponseEntity.ok(ResponseForm.buildCustomResponse(productService.getProductByPriceWithOrder(sortType), 1, "ok"));
    }

    @GetMapping("/get-display")
    public ResponseEntity<ResponseForm<List<ProductCRUD>>> getProductByDisplayWithOrder(@RequestParam Integer sortType) {
        return ResponseEntity.ok(ResponseForm.buildCustomResponse(productService.getProductByDisplayWithOrder(sortType), 1, "ok"));
    }

    @GetMapping("/find-by-name")
    public @ResponseBody
    ResponseEntity<ResponseForm<List<ProductCRUD>>> getProductByDisplay(@RequestParam String display) {
        return ResponseEntity.ok(ResponseForm.buildCustomResponse(productService.getProductByDisplay(display), 1, "ok"));
    }


    @PostMapping("/add-product")
    public ResponseEntity<ResponseForm<Boolean>> addProduct(@RequestBody ProductCRUD productCRUD) {
        return ResponseEntity.ok(ResponseForm.buildCustomResponse(productService.addProduct(productCRUD), 1, "ok"));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseForm<Boolean>> updateProduct(@RequestBody ProductCRUD productCRUD) {
        return ResponseEntity.ok(ResponseForm.buildCustomResponse(productService.updateProduct(productCRUD), 1, "ok"));
    }


}
