package com.interview.photos.loader;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;

/**
 * Utility methods for photo loading process.
 *
 * Created by Denis G. on 8/25/2020.
 */
public class PhotoLoaderUtils {

   private PhotoLoaderUtils(){}

   static HttpEntity prepareHttpEntityWithToken(String token) {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
      headers.set("Authorization", "Bearer " + token);
      return new HttpEntity(headers);
   }
}
