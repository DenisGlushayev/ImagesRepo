package com.interview.photos.loader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Denis G. on 8/25/2020.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImagesPageDto {

   private List<ImageDto> pictures;

   private Integer page;

   private Integer pageCount;

   private Boolean hasMore;
}
