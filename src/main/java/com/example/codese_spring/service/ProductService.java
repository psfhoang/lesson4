package com.example.codese_spring.service;

import com.example.codese_spring.dto.ProductDTO;
import com.example.codese_spring.dto.ProductGetAll;
import com.example.codese_spring.exception.ProductTransactionException;
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

  public void subAmountProduct(String id, int amount) {
    ProductDTO productDTO = this.getProductById(id);
    int amountProduct = productDTO.getAmount() - amount;
    if (amountProduct < 0) {
      throw new ProductTransactionException("this product have id = " + productDTO.getProductID() +
          " not enough");
    }
    productDTO.setAmount(amountProduct);
    this.updateProduct(productDTO);
  }

  public List<ProductGetAll> getAllProducts() {
    return productRepository.getAllProducts();
  }

  public ProductDTO getProductById(String idInput) {

    if (productRepository.checkProductExistedById(idInput)) {
      return productRepository.getProductById(idInput);
    } else {
      throw new ResourceNotFoundException(idInput);
    }

  }

  public Boolean addProduct(ProductDTO productDTO) {

    if (!productRepository.checkProductExistedByName(productDTO.getDisplay())) {
      if (productRepository.addProduct(productDTO) != 0) {
        return true;
      } else {
        return false;
      }
    } else {
      throw new ResourceAlreadyExistException(productDTO.getProductID());
    }
  }

  public List<ProductDTO> getProductByPriceWithOrder(Integer sortType) {

    switch (sortType) {
      case 0:
        return productRepository.getAllPriceOutAsc(sortType);
      case 1:
        return productRepository.getAllPriceOutDesc(sortType);
      default:
        return null;
    }

  }

  public List<ProductDTO> getProductByDisplayWithOrder(Integer sortType) {

    switch (sortType) {
      case 0:
        return productRepository.getAllDisplayAsc();
      case 1:
        return productRepository.getAllDisplayDesc();
      default:
        return null;
    }
  }

  public List<ProductDTO> getProductByDisplay(String display) {
    if (!display.equals("")) {
      return productRepository.getProductByDisplay(display);
    } else {
      return productRepository.getAllDisplayAsc();
    }


  }

  public Boolean updateProduct(ProductDTO productDTO) {

    if (productRepository.checkProductExistedById(productDTO.getProductID())) {
      ProductDTO productDTO1 = productRepository.getProductById(productDTO.getProductID());
      if (productDTO.getDisplay() != null) {
        productDTO1.setDisplay(productDTO.getDisplay());
      }
      if (productDTO.getPriceIn() != null) {
        productDTO1.setPriceIn(productDTO.getPriceIn());
      }
      if (productDTO.getPriceOut() != null) {
        productDTO1.setPriceIn(productDTO.getPriceIn());
      }
      if (productDTO.getImages() != null) {
        productDTO1.setImages(productDTO.getImages());
      }
      if (productDTO.getShipday() != null) {
        productDTO1.setShipday(productDTO.getShipday());
      }
      if (productDTO.getDescription() != null) {
        productDTO1.setDescription(productDTO.getDescription());
      }
      if (productDTO.getAmount() != null) {
        productDTO1.setAmount(productDTO.getAmount());
      }
      if (productDTO.getPriceSale() != null) {
        productDTO1.setPriceSale(productDTO.getPriceSale());
      }
      if (productRepository.updateProduct(productDTO1) != 0) {
        return true;
      } else {
        return false;
      }
    } else {
      throw new ResourceNotFoundException(productDTO.getDisplay());
    }
  }
}
