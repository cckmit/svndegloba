package com.degloba.rent.cqrs.readmodel.jpa;

import java.util.List;

import com.degloba.rent.domain.persistence.rdbms.jpa.Photo;
import com.degloba.rent.domain.persistence.rdbms.jpa.Product;
import com.degloba.rent.domain.persistence.rdbms.jpa.Subcategory;



public interface IPhotoFinder {

	Photo findPhotoByIdGcs(String idGcs);

    List<Photo> findPhotosBySubcategory(Subcategory subcategory);
    
    List<Photo> findPhotosByProduct(Product product);
}
