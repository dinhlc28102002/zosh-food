package com.zosh.service.impl;

import com.zosh.model.Category;
import com.zosh.model.Restaurant;
import com.zosh.repository.CategoryRepository;
import com.zosh.service.CategoryService;
import com.zosh.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category createCategory(String name, Long userId) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
        Category category = new Category();
        category.setName(name);
        category.setRestaurant(restaurant);

        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoriesByRestaurantId(Long id) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(id);
        return categoryRepository.findByRestaurantId(restaurant.getId());
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new Exception("category not found");
        }
        return optionalCategory.get();
    }
}
