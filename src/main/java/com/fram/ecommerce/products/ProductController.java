package com.fram.ecommerce.products;


import com.fram.ecommerce.categories.Category;
import com.fram.ecommerce.categories.CategoryService;
import com.fram.ecommerce.dto.product.ProductDto;
import com.fram.ecommerce.utils.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/add-product")
    public ResponseEntity<ResponseHelper> addProduct(@RequestBody ProductDto productDto){
        Optional<Category> optionalCategory =  categoryService.getCategoryById(productDto.getCategoryId());
            if(optionalCategory.isPresent()){
                return new ResponseEntity<ResponseHelper>(new ResponseHelper(false, "category is invalid"), HttpStatus.CONFLICT);
            }
        Category category = optionalCategory.get();
        productService.createNewProduct(productDto, category);
        return new ResponseEntity<>(new ResponseHelper(true, "Product has been added"), HttpStatus.CREATED);


    }
}

