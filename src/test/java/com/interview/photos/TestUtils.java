package com.interview.photos;

import com.interview.photos.loader.dto.ImageDetailsDto;
import com.interview.photos.loader.dto.ImageDto;
import com.interview.photos.loader.dto.ImagesPageDto;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by Denis G. on 8/25/2020.
 */
public class TestUtils {

   public static ImageDto randImageDto() {
      return new ImageDto(randString(), randString(), randImageDetailsDto());
   }

   public static ImageDetailsDto randImageDetailsDto() {
      return new ImageDetailsDto(randString(), randString(), randString(), randString(), randString());
   }

   public static String randString() {
      return UUID.randomUUID().toString();
   }

   public static ImagesPageDto randImagePageDto() {
      List<ImageDto> list = Arrays.asList(randImageDto(), randImageDto(), randImageDto());
      return new ImagesPageDto(list, 1,2,false);
   }
}
