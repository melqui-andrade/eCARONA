package com.br.uepb.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	private static final Log LOG = LogFactory.getLog(HomeController.class);

	@RequestMapping(value = "/home/home.html", method = RequestMethod.GET)
	public ModelAndView homeGet(HttpServletRequest request) {
		
		LOG.debug("Iniciada a execucao do metodo: homeGet");

		ModelAndView modelAndView = new ModelAndView();

		LOG.debug("Finalizada a execucao do metodo: homeGet");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/home/login.html", method = RequestMethod.GET)
	public ModelAndView loginGet(HttpServletRequest request) {
		
		LOG.debug("Iniciada a execucao do metodo: loginGet");

		ModelAndView modelAndView = new ModelAndView();

		LOG.debug("Finalizada a execucao do metodo: loginGet");
		
		return modelAndView;
	}
	
	
}
