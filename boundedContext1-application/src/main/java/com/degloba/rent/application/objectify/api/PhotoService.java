package com.degloba.rent.application.objectify.api;

import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Photo;

public interface PhotoService {
 
    void createPhoto(Photo photo);
  
}
