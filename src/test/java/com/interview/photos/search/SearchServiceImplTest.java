package com.interview.photos.search;

import com.interview.photos.loader.dto.ImageDto;
import com.interview.photos.persist.PhotoPersistServiceImpl;
import com.interview.photos.search.dto.SearchResultDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static com.interview.photos.TestUtils.randImageDto;
import static com.interview.photos.TestUtils.randString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Denis G. on 8/25/2020.
 */
class SearchServiceImplTest {


   @Test
   void testEmptyCache() {
      //Arrange
      PhotoPersistServiceImpl photoPersistService = new PhotoPersistServiceImpl();
      photoPersistService.save(Collections.EMPTY_LIST);

      SearchServiceImpl searchService = new SearchServiceImpl(photoPersistService);
      //Test
      String expectedTerm = randString();
      SearchResultDto search = searchService.search(expectedTerm);

      //Assert
      assertEquals(expectedTerm, search.getSearchRequest());
      assertTrue(search.getSearchResult().isEmpty());
   }

   @Test
   void testCorrectRequest() {
      //Arrange
      PhotoPersistServiceImpl photoPersistService = new PhotoPersistServiceImpl();
      ImageDto cachedImage = randImageDto();
      photoPersistService.save(Arrays.asList(cachedImage, randImageDto(), randImageDto()));

      SearchServiceImpl searchService = new SearchServiceImpl(photoPersistService);
      //Test
      String expectedTerm = cachedImage.getDetails().getAuthor();
      SearchResultDto search = searchService.search(expectedTerm);

      //Assert
      assertEquals(expectedTerm, search.getSearchRequest());
      assertTrue(!search.getSearchResult().isEmpty());
      assertEquals(cachedImage, search.getSearchResult().get(0));
   }

   @Test
   void testInCorrectRequest() {
      //Arrange
      PhotoPersistServiceImpl photoPersistService = new PhotoPersistServiceImpl();
      ImageDto cachedImage = randImageDto();
      photoPersistService.save(Collections.singletonList(cachedImage));

      SearchServiceImpl searchService = new SearchServiceImpl(photoPersistService);
      //Test
      String expectedTerm = cachedImage.getDetails().getAuthor() + randString();
      SearchResultDto search = searchService.search(expectedTerm);

      //Assert
      assertEquals(expectedTerm, search.getSearchRequest());
      assertTrue(search.getSearchResult().isEmpty());
   }
}