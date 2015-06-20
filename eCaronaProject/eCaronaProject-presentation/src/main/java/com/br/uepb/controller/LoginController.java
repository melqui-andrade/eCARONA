package com.br.uepb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.br.uepb.Model.LoginModel;
import com.br.uepb.Model.UsuarioModel;
import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.business.UsuarioBusiness;

@Controller
public class LoginController {

	private static final Log LOG = LogFactory.getLog(LoginController.class);

	@Autowired
	private UsuarioBusiness gerenciadorDeUsuario;

	private SessaoBusiness gerenciadorDeSessao;

	@RequestMapping(value = "/home/login.html", method = RequestMethod.GET)
	public ModelAndView loginGet(HttpServletRequest request) {

		LOG.debug("Iniciada a execucao do metodo: loginGet");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/login/login");
		modelAndView.addObject("model", new LoginModel());

		LOG.debug("Finalizada a execucao do metodo: loginGet");

		return modelAndView;
	}

	@RequestMapping(value = "/home/login.html", method = RequestMethod.POST)
	public ModelAndView loginPost(
			@ModelAttribute("model") @Valid LoginModel model,
			BindingResult bindingResult, HttpServletRequest request) {

		LOG.debug("Iniciada a execucao do metodo: loginPost");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/login/login");

		if (bindingResult.hasErrors()) {
			modelAndView.addObject("model", new LoginModel());
			return modelAndView;
		}

		gerenciadorDeSessao = new SessaoBusiness();
		gerenciadorDeUsuario = new UsuarioBusiness();
		try {
			gerenciadorDeSessao.abrirSessao(model.getLogin(), model.getSenha());
			request.getSession().setAttribute("login", model.getLogin());
			//request.setAttribute("login", model.getLogin());
						
		} catch (Exception e) {
			modelAndView.addObject("model", new LoginModel());
			return modelAndView;
		}

		LOG.debug("Finalizada a execucao do metodo: loginPost");
		return new ModelAndView("redirect:/home/home.html");
	}



}
