package com.hcl.explore.products.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller
@ControllerAdvice
public class ErrorController {

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(HttpServletRequest request, Exception ex) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", ex.getLocalizedMessage());
		System.out.println(ex.getLocalizedMessage());
		mv.addObject("url", request.getRequestURL());
		mv.setViewName("error");
		return mv;
	}
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ModelAndView handleMaxSizeException(MaxUploadSizeExceededException exc, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.getModel().put("dangermsg", "Image file too large! Try again with a image smaller than 1 Mb.");
		return modelAndView;
	}
}
