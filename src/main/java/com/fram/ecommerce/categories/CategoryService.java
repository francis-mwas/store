package com.fram.ecommerce.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public void createNewCategory(Category category){
        categoryRepository.save(category);
    }
    public Category getCategoryByNAme(String categoryName){
     return categoryRepository.findCategoryByCategoryName(categoryName);
    }

    public Optional<Category> getCategoryById(Integer catId){
        return categoryRepository.findById(catId);
    }

    public void updateCategory(Integer catId, Category newCategory){
        Category category = categoryRepository.findById(catId).get();
        category.setCategoryName(newCategory.getCategoryName());
        category.setDescription(newCategory.getDescription());
        category.setImageUrl(newCategory.getImageUrl());
        categoryRepository.save(category);
    }
}
