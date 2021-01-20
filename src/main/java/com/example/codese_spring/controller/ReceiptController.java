package com.example.codese_spring.controller;

import com.example.codese_spring.dto.OrderReqDto;
import com.example.codese_spring.dto.ReceiptInfor;
import com.example.codese_spring.helper.ResponseBuilder.ResponseForm;
import com.example.codese_spring.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/receipt")
public class ReceiptController {
    @Autowired
    ReceiptService receiptService;
    @PostMapping("/create")
    public ResponseEntity<ResponseForm<Boolean>> createReceiptDetails(@RequestBody OrderReqDto orderReqDto, HttpServletRequest request){
        return ResponseEntity.ok(ResponseForm.buildCustomResponse(receiptService.createReceipt(orderReqDto),1,"oke"));

    }


    @GetMapping("/all")
    public ResponseEntity<ResponseForm<ReceiptInfor>> showAllReceipt(){
        return ResponseEntity.ok(ResponseForm.buildCustomResponse(receiptService.showAll(),1,"ok"));
    }
}
