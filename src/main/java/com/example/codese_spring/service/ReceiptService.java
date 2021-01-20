package com.example.codese_spring.service;

import com.example.codese_spring.dto.OrderReqDto;
import com.example.codese_spring.dto.ProductReqDto;
import com.example.codese_spring.dto.ReceiptInfor;
import com.example.codese_spring.entity.Product;
import com.example.codese_spring.exception.ProductTransactionException;
import com.example.codese_spring.repository.ProductRepository;
import com.example.codese_spring.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
@Service
public class ReceiptService {
    @Autowired
    ReceiptRepository receiptRepository;
    @Autowired
    ProductService productService;

    public ReceiptInfor showAll(){
       return receiptRepository.showInforAll();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = ProductTransactionException.class)
    public Boolean createReceipt(OrderReqDto orderReqDto, String userId) {
        List<ProductReqDto> productReqDtos = orderReqDto.getProductReqDtos();
        Integer money = 0;
        for(ProductReqDto productReqDto : productReqDtos){
            money =money+ productService.getProductById(productReqDto.getProductID()).getPriceOut()
                    *productReqDto.getAmount();
        }
        String status = "chua thanh toan";
        String UUID = java.util.UUID.randomUUID().toString();
        if(receiptRepository.addReceipt(UUID, money, status, userId)==0){
            return false;
        };

        for (ProductReqDto productReqDto : productReqDtos) {
            productService.subAmountProduct(productReqDto.getProductID()
                    ,productReqDto.getAmount());
            if(receiptRepository.addReceiptDetails(UUID,productReqDto.getProductID(),productReqDto.getAmount())==0){
                return false;
            };
        }
        return true;
    }


}
