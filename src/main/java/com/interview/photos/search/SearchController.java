package com.interview.photos.search;

import com.interview.photos.search.dto.SearchResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Denis G. on 8/25/2020.
 */
@RestController
@Slf4j
public class SearchController {

   @Autowired
   SearchService searchService;


   @GetMapping(path = "/search/{term}")
   public SearchResultDto endpoint(@PathVariable(name = "term") String term) {
      log.debug("Received search request: {}", term);

      return searchService.search(term);
   }

}
