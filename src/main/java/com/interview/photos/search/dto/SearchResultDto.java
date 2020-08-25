package com.interview.photos.search.dto;

import com.interview.photos.loader.dto.ImageDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Denis G. on 8/25/2020.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchResultDto {

   private List<ImageDto> searchResult;

   private String searchRequest;
}
