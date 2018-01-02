package com.gasyou.gam.common.model;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This annotation is used to declare the configuration of an Model.
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface WebModel {

	/**
	 * @return name of the Model
	 */
	String name();

	/**
	 * @return array of forwards for this Model
	 */
	WebModelForward[] forwards();
}
