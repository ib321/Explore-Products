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

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(HttpServletRequest request, Exception ex) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", ex.getLocalizedMessage());
		ex.printStackTrace();
		mv.addObject("url", request.getRequestURL());
		mv.setViewName("error");
		return mv;
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ModelAndView handleMaxSizeException(MaxUploadSizeExceededException exc, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.getModel().put("dangermsg", "Image size exceeded! Try again with a image smaller than 1 MB.");
		return modelAndView;
	}
}