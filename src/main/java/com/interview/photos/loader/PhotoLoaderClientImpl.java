package com.interview.photos.loader;

import com.interview.photos.loader.dto.ImageDetailsDto;
import com.interview.photos.loader.dto.ImagesPageDto;
import com.interview.photos.loader.dto.LoginDto;
import com.interview.photos.loader.dto.TokenDto;
import com.interview.photos.loader.relogin.Relogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

import static com.interview.photos.loader.PhotoLoaderUtils.prepareHttpEntityWithToken;
import static org.springframework.http.HttpMethod.GET;

/**
 * Created by Denis G. on 8/25/2020.
 */
@Component
@Slf4j
public class PhotoLoaderClientImpl implements PhotoLoaderClient {

   public static final String BASE_URL = "http://interview.agileengine.com";
   public static final String AUTH = "/auth";
   public static final String IMAGES = "/images?page=%s";
   public static final String IMAGES_DETAILS = "/images/%s";
   public static final String API_KEY = "23567b218376f79d9415";

   HttpEntity request;

   RestTemplate restTemplate = new RestTemplate();

   @PostConstruct
   public void init() {
      log.debug("Initializing PhotoLoaderClient, trying to login...");

      login();

      log.debug("Initialization of PhotoLoaderClient finished. succesfull login!");
   }

   @Override
   @Relogin
   public ImagesPageDto loadImages() {
      return loadImagesImpl();
   }

   @Override
   @Relogin
   public ImagesPageDto loadImages(int page) {
      return loadImagesImpl(page);
   }

   @Override
   @Relogin
   public ImageDetailsDto loadImageDetails(String imageId) {
      ResponseEntity<ImageDetailsDto> exchange =
            restTemplate.exchange(BASE_URL + String.format(IMAGES_DETAILS, imageId), GET, request, ImageDetailsDto.class);

      return exchange.getBody();
   }

   @Override
   public void relogin() {
      login();
   }

   private ImagesPageDto loadImagesImpl(int page) {

      ResponseEntity<ImagesPageDto> imagesPage =
            restTemplate.exchange(BASE_URL + String.format(IMAGES, page), GET, request, ImagesPageDto.class);

      ImagesPageDto body = imagesPage.getBody();
      return body;
   }

   private ImagesPageDto loadImagesImpl() {
      return loadImagesImpl(1);
   }

   private void login() {
      ResponseEntity<TokenDto> tokenDtoResponseEntity =
            restTemplate.postForEntity(BASE_URL + AUTH, new LoginDto(API_KEY), TokenDto.class);

      String token = tokenDtoResponseEntity.getBody().getToken();
      request = prepareHttpEntityWithToken(token);
   }

}
