package com.degloba.rent.cqrs.readmodel;

import java.util.List;

import com.degloba.rent.domain.Category;
import com.degloba.rent.domain.Subcategory;

public interface CategoryFinder {

    List<Category> findCategories();

    Category findCategoryBySubcategory(Subcategory subcategory);
}
