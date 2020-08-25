package com.interview.photos.loader;

import com.interview.photos.loader.dto.ImageDetailsDto;
import com.interview.photos.loader.dto.ImageDto;
import com.interview.photos.loader.dto.ImagesPageDto;
import com.interview.photos.persist.PhotoPersistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Denis G. on 8/25/2020.
 */
@Component
@Slf4j
public class PhotoLoaderServiceImpl implements PhotoLoaderService {

   final PhotoLoaderClient client;

   final PhotoPersistService persistService;

   @Autowired
   public PhotoLoaderServiceImpl(PhotoLoaderClient client, PhotoPersistService persistService) {
      this.client = client;
      this.persistService = persistService;
   }

   @PostConstruct
   public void init() {
      log.debug("Initializing PhotoLoaderService, loading images...");

      updateImages();

      log.debug("Initialization of PhotoLoaderService is complete, finished loading images!");
   }

   @Scheduled(cron = "${photo.update.cron}")
   public void updateImages() {
      //With additional details on data update specifics, the update algorithm could be optimized
      //e.g. if photo details could not be changed once photo is uploaded. - we won't read details if we are storing that photo already.
      List<ImageDto> imagesDtos = readImagesList();
      readImagesDetailsList(imagesDtos);
      cacheImages(imagesDtos);
   }

   private void cacheImages(List<ImageDto> imagesDtos) {
      persistService.save(imagesDtos);
   }

   private void readImagesDetailsList(List<ImageDto> imagesDtos) {
      for (ImageDto dto : imagesDtos) {
         ImageDetailsDto imageDetailsDto = client.loadImageDetails(dto.getId());
         dto.setDetails(imageDetailsDto);
      }
   }

   private List<ImageDto> readImagesList() {
      ImagesPageDto imagesPageDto = client.loadImages();

      ArrayList<ImagesPageDto> imagesPageDtoList = new ArrayList<>();
      imagesPageDtoList.add(imagesPageDto);

      IntStream.range(2, imagesPageDto.getPageCount())
            .mapToObj(i -> client.loadImages(i))
            .forEach(imagesPageDtoList::add);

      List<ImageDto> imageList = imagesPageDtoList.stream()
            .flatMap(p -> p.getPictures().stream())
            .collect(Collectors.toList());

      return imageList;
   }
}
