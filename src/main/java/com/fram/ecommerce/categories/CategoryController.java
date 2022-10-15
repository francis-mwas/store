package com.fram.ecommerce.categories;

import com.fram.ecommerce.utils.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @GetMapping("/")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.getCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    public ResponseEntity<ResponseHelper>createNewCategory(@RequestBody Category category){
        if(Objects.nonNull(categoryService.getCategoryByNAme(category.getCategoryName()))){
            return new ResponseEntity<>(new ResponseHelper(false, "This category already exists"), HttpStatus.CONFLICT);
        }
       categoryService.createNewCategory(category);
        return new ResponseEntity<>(new ResponseHelper(true, "New category added successfully"), HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseHelper>updateCategory(@RequestBody Category category, @PathVariable Integer catId){
        if(Objects.nonNull(categoryService.getCategoryById(catId))){
           categoryService.updateCategory(catId, category);
           return new ResponseEntity<>(new ResponseHelper(true, "Category updated successfully"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseHelper(false, "This category does not exist, try again"), HttpStatus.NOT_FOUND);
    }
}
