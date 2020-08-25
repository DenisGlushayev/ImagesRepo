package com.interview.photos.loader;

import com.interview.photos.persist.PhotoPersistServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.interview.photos.TestUtils.randImagePageDto;
import static org.mockito.Mockito.*;

/**
 * Created by Denis G. on 8/25/2020.
 */
class PhotoLoaderServiceImplTest {

   @Test
   void updateImages() {
      //Arrange
      PhotoLoaderClient mock = Mockito.mock(PhotoLoaderClientImpl.class);
      PhotoPersistServiceImpl mock2 = Mockito.mock(PhotoPersistServiceImpl.class);

      when(mock.loadImages()).thenReturn(randImagePageDto());

      PhotoLoaderServiceImpl photoLoaderService = new PhotoLoaderServiceImpl(mock, mock2);
      //Test
      photoLoaderService.updateImages();

      //Assert
      verify(mock, times(1)).loadImages();
   }
}