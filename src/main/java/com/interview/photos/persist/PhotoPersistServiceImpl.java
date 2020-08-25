package com.interview.photos.persist;

import com.interview.photos.loader.dto.ImageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Denis G. on 8/25/2020.
 */
@Component
@Slf4j
public class PhotoPersistServiceImpl implements PhotoPersistService {

   //Task 8. You are free to choose the way you maintain local cache (any implementation of the cache, DB, etc).
   //The search algorithm, however, should be implemented by you.
   //Sound like SQL approach is not the one that is wanted here.
   HashMap<String, ImageDto> cache = new HashMap();

   private static final Object MUTEX = new Object();

   @Override
   public void save(List<ImageDto> imagesDtos) {
      log.debug("Updating cache, with new entries: {}", imagesDtos.size());
      synchronized (MUTEX) {
         cache.clear();
         imagesDtos.forEach(d -> cache.put(d.getId(), d));
      }
   }

   @Override
   public List<ImageDto> search(String term) {
      synchronized (MUTEX) {
         return new ArrayList<>(cache.values());
      }
   }


}
