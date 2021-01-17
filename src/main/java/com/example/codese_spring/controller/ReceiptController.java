package com.example.codese_spring.controller;

import com.example.codese_spring.dto.OrderReqDto;
import com.example.codese_spring.helper.ResponseBuilder.ResponseForm;
import com.example.codese_spring.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/receipt")
public class ReceiptController {
    @Autowired
    ReceiptService receiptService;
    @PostMapping("/create")
    public ResponseEntity<ResponseForm<Boolean>> createReceiptDetails(@RequestBody OrderReqDto orderReqDto, HttpServletRequest request){
        String username = request.getHeader("userID");
        return ResponseEntity.ok(ResponseForm.buildCustomResponse(receiptService.createReceipt(orderReqDto,username),1,"oke"));

    }
}