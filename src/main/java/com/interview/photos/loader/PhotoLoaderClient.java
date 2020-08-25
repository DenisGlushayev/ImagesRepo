package com.interview.photos.loader;

import com.interview.photos.loader.dto.ImageDetailsDto;
import com.interview.photos.loader.dto.ImagesPageDto;

/**
 * This is a client which is used to load images from a remote provider.
 *
 * Created by Denis G. on 8/25/2020.
 */
public interface PhotoLoaderClient {

   /**
    * Load images from remote resource
    * @return page of images
    */
   ImagesPageDto loadImages();

   /**
    * Load images from remote resource with selected page
    *
    * @param page to load
    * @return chosen page of images
    */
   ImagesPageDto loadImages(int page);

   /**
    * Load details for image from remote resource
    *
    * @param id of the image
    * @return details for image with given id
    */
   ImageDetailsDto loadImageDetails(String id);

   /**
    * Performs relogin on remote resource
    */
   void relogin();
}
