package com.interview.photos.persist;

import com.interview.photos.loader.dto.ImageDto;

import java.util.Collection;
import java.util.List;

/**
 * Allows to store and retrieve images to/from cache.
 *
 * Created by Denis G. on 8/25/2020.
 */
public interface PhotoPersistService {

   /**
    * Saves images to cache.
    * Old cache considered deprecated.
    *
    * @param imagesDtos to store in cache
    */
   void save(List<ImageDto> imagesDtos);

   /**
    * Search Images in cache by given param.
    *
    * @param term to search by
    * @return Images, which metadata has given term.
    */
   List<ImageDto> search(String term);
}