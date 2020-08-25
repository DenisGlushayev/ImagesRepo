package com.interview.photos.loader.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Denis G. on 8/25/2020.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDetailsDto {

   public String author;

   public String camera;

   public String tags;

   @JsonAlias(value = "full_picture")
   public String fullPictureUrl;

   @JsonAlias(value = "cropped_picture")
   private String croppedImageUrl;

}
