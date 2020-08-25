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
public class ImageDto {

   private String id;

   @JsonAlias(value = "cropped_picture")
   private String croppedImageUrl;

   private ImageDetailsDto details;
}
