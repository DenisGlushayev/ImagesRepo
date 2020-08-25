package com.interview.photos.loader.relogin;

import com.interview.photos.loader.PhotoLoaderClient;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;


/**
 * The implementation of Relogin aspect.
 *
 * Allows ot catch Unauthorized exception thrown from client, perform login and retry the action.
 * The Client operations granularity is a key to a better performance of this logic.
 */
@Component
@Slf4j
@Aspect
public class ReloginAspect {

   @Autowired
   private PhotoLoaderClient photoLoaderClient;

   @Pointcut(value = "@annotation(Relogin)")
   public void annotationPointcut(Relogin Relogin) {
   }


   @Around(value = "annotationPointcut(relogin)")
   public Object lockOpcObject(ProceedingJoinPoint joinPoint, Relogin relogin) throws Throwable {

      Object target = joinPoint.getTarget();

      try {
         Object proceed = joinPoint.proceed();

         return proceed;
      } catch (HttpClientErrorException.Unauthorized ex) {
         photoLoaderClient.relogin();

         Object proceed = joinPoint.proceed();
         return proceed;
      }
   }

}
