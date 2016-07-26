package com.degloba.rent.facade.jpa;

import java.util.Date;

import com.degloba.organisation.domain.Post;
import com.degloba.rent.domain.jpa.Photo;
import com.google.appengine.api.datastore.Key;

/**
 * @author degloba
 */
public interface PhotoFacade {

    void createPhoto(Photo photo);

}