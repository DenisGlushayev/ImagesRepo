package com.interview.photos.loader.relogin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This aspects goal is to perform relogin action on
 * PhotoLoaderClient if Unauthorized occurred.
 *
 * Created by Denis G. on 8/25/2020.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Relogin {
}
