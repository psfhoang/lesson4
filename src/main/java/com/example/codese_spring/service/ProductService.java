package com.example.codese_spring.service;

import com.example.codese_spring.dto.ProductCRUD;
import com.example.codese_spring.dto.ProductGetAll;
import com.example.codese_spring.exception.ResourceAlreadyExistException;
import com.example.codese_spring.exception.ResourceNotFoundException;
import com.example.codese_spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        if (!productRepository.checkProductExistedById(productCRUD.getProductID())
                && !productRepository.checkProductExistedByName(productCRUD.getDisplay())) {
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
        try {
            return productRepository.getProductByDisplay(display);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Boolean updateProduct(ProductCRUD productCRUD) {

            if (productRepository.checkProductExistedById(productCRUD.getProductID())) {
                if(productRepository.updateProduct(productCRUD) != 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                throw  new ResourceNotFoundException(productCRUD.getDisplay());
            }
    }
}
