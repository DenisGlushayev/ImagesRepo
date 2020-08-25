package com.interview.photos.search;

import com.interview.photos.search.dto.SearchResultDto;

/**
 * Created by Denis G. on 8/25/2020.
 */
public interface SearchService {

   SearchResultDto search(String term);
}
