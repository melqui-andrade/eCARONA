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

import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.model.CadastrarModel;
import com.br.uepb.model.LoginModel;


@Controller
public class CadastrarUsuarioController {

	private static final Log LOG = LogFactory.getLog(CadastrarUsuarioController.class);

	@Autowired
	private UsuarioBusiness gerenciadorDeUsuario;

	@RequestMapping(value = "/home/cadastrar.html", method = RequestMethod.GET)
	public ModelAndView cadastrarGet(HttpServletRequest request) {

		LOG.debug("Iniciada a execucao do metodo: cadastrarGet");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/home/cadastrar");
		modelAndView.addObject("model", new CadastrarModel());
		modelAndView.addObject("modelLogin", new LoginModel());
		modelAndView.addObject("mensagem", "");

		LOG.debug("Finalizada a execucao do metodo: cadastrarGet");

		return modelAndView;
	}

	@RequestMapping(value = "/home/cadastrar.html", method = RequestMethod.POST)
	public ModelAndView cadastrarPost(
			@ModelAttribute("model") @Valid CadastrarModel model,
			BindingResult bindingResult, HttpServletRequest request) {

		LOG.debug("Iniciada a execucao do metodo: cadastrarGet");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/home/cadastrar");

		if (bindingResult.hasErrors()) {
			modelAndView.addObject("model", model);
			return modelAndView;
		}

		gerenciadorDeUsuario = new UsuarioBusiness();
		try {
			gerenciadorDeUsuario.criarUsuario(model.getLogin(),
					model.getSenha(), model.getNome(), model.getEndereco(),
					model.getEmail());
			modelAndView.addObject("mensagem",
					"Cadastro realizado com sucesso!");
			modelAndView.addObject("status", "positivo");
		} catch (Exception e) {
			modelAndView.addObject("model", model);
			modelAndView.addObject("mensagem", e.getMessage());
			modelAndView.addObject("status", "negativo");
			return modelAndView;
		}

		LOG.debug("Finalizada a execucao do metodo: cadastrarGet");
		return modelAndView;
	}


}
