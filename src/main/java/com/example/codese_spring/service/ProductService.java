package com.example.codese_spring.service;

import com.example.codese_spring.dto.ProductCRUD;
import com.example.codese_spring.dto.ProductGetAll;
import com.example.codese_spring.exception.ResourceAlreadyExistException;
import com.example.codese_spring.exception.ResourceNotFoundException;
import com.example.codese_spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<ProductGetAll> getAllProducts() {
        try {
            return productRepository.getAllProducts();
        } catch (Exception e) {
            System.out.println("Fail at repo");
        } finally {
            return productRepository.getAllProducts();
        }
    }

    public ProductCRUD getProductById(String idInput) {

            if (productRepository.checkProductExistedById(idInput)) {
                return productRepository.getProductById(idInput);
            } else {
                throw new ResourceNotFoundException(idInput);
            }

    }

    public Boolean addProduct(ProductCRUD productCRUD) {

        if (!productRepository.checkProductExistedByName(productCRUD.getDisplay())) {
            if (productRepository.addProduct(productCRUD) != 0) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new ResourceAlreadyExistException(productCRUD.getProductID());
        }
    }

    public List<ProductCRUD> getProductByPriceWithOrder(Integer sortType) {

            switch (sortType) {
                case 0:
                    return productRepository.getAllPriceOutAsc(sortType);
                case 1:
                    return productRepository.getAllPriceOutDesc(sortType);
                default:
                    return null;
            }

    }

    public List<ProductCRUD> getProductByDisplayWithOrder(Integer sortType) {

            switch (sortType) {
                case 0:
                    return productRepository.getAllDisplayAsc();
                case 1:
                    return productRepository.getAllDisplayDesc();
                default:
                    return null;
            }
    }

    public List<ProductCRUD> getProductByDisplay(String display) {
            if(!display.equals("")) {
                return productRepository.getProductByDisplay(display);
            }else {
                return productRepository.getAllDisplayAsc();
            }


    }

    public Boolean updateProduct(ProductCRUD productCRUD) {

            if (productRepository.checkProductExistedById(productCRUD.getProductID())) {
                ProductCRUD productCRUD1 = productRepository.getProductById(productCRUD.getProductID());
                if(productCRUD.getDisplay()!=null){
                    productCRUD1.setDisplay(productCRUD.getDisplay());
                }
                if(productCRUD.getPriceIn()!=null){
                    productCRUD1.setPriceIn(productCRUD.getPriceIn());
                }
                if(productCRUD.getPriceOut()!=null){
                    productCRUD1.setPriceIn(productCRUD.getPriceIn());
                }
                if(productCRUD.getImages()!=null){
                    productCRUD1.setImages(productCRUD.getImages());
                }
                if(productCRUD.getShipday()!=null){
                    productCRUD1.setShipday(productCRUD.getShipday());
                }
                if(productCRUD.getDescription()!=null){
                    productCRUD1.setDescription(productCRUD.getDescription());
                }
                if(productCRUD.getAmount()!=null){
                    productCRUD1.setAmount(productCRUD.getAmount());
                }
                if(productCRUD.getPriceSale()!=null){
                    productCRUD1.setPriceSale(productCRUD.getPriceSale());
                }
                if(productRepository.updateProduct(productCRUD1) != 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                throw  new ResourceNotFoundException(productCRUD.getDisplay());
            }
    }
}
