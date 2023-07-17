/*
 * Copyright 2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hcl.explore.products.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author Indian Bittu
 *
 */
@Controller
@ControllerAdvice
public class ErrorController {

	/**
	 * Handles all exceptions that occur in the project by displaying an error page
	 * 
	 * @param request HttpServletRequest request object
	 * @param ex Object of Exception being occurred
	 * @return Error model view page with exception message
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(HttpServletRequest request, Exception ex) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", ex.getLocalizedMessage());
		ex.printStackTrace();
		mv.addObject("url", request.getRequestURL());
		mv.setViewName("error");
		return mv;
	}

	/**
	 * Custom Exception handler for image max upload size exceeded 
	 * 
	 * @param exc Object of Exception being occurred
	 * @param request The HttpServletRequest object representing the current request.
	 * @param response HttpServletResponse object representing the current response.
	 * @return Error model view page with message
	 */
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ModelAndView handleMaxSizeException(MaxUploadSizeExceededException exc, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.getModel().put("dangermsg", "Image size exceeded! Try again with a image smaller than 1 MB.");
		return modelAndView;
	}
}