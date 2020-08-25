package com.interview.photos.search;

import com.interview.photos.loader.dto.ImageDetailsDto;
import com.interview.photos.loader.dto.ImageDto;
import com.interview.photos.persist.PhotoPersistService;
import com.interview.photos.search.dto.SearchResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Denis G. on 8/25/2020.
 */
@Component
@Slf4j
public class SearchServiceImpl implements SearchService {

   final PhotoPersistService photoPersistService;

   @Autowired
   public SearchServiceImpl(PhotoPersistService photoPersistService) {
      this.photoPersistService = photoPersistService;
   }

   @Override
   public SearchResultDto search(String term) {
      List<ImageDto> search = photoPersistService.search(term);

      List<ImageDto> result;
      if (term.isEmpty()) {
         result = Collections.emptyList();
      } else {
         String searchTerm = term.trim();

         result = search.stream()
               .filter(i -> isSuitable(searchTerm, i))
               .collect(Collectors.toList());
      }

      log.debug("Search finished for: {}, entries: {}", term, result.size());
      return new SearchResultDto(result, term);
   }

   private boolean isSuitable(String searchTerm, ImageDto i) {
      ImageDetailsDto details = i.getDetails();
      return (details.getAuthor() != null && details.getAuthor().contains(searchTerm)) ||
            (details.getCamera() != null && details.getCamera().contains(searchTerm)) ||
            (details.getTags() != null && details.getTags().contains(searchTerm));
   }
}
