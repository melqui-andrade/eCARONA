package com.br.uepb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.runtime.parser.node.GetExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.br.uepb.Model.CadastrarCaronaModel;
import com.br.uepb.Model.CadastrarModel;
import com.br.uepb.Model.CaronaModel;
import com.br.uepb.Model.LoginModel;
import com.br.uepb.Model.UsuarioModel;
import com.br.uepb.business.CaronaBusiness;
import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.business.SolicitacaoBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.UsuarioDomain;

@Controller
public class ApresentacaoController {

	private static final Log LOG = LogFactory.getLog(ApresentacaoController.class);

	@Autowired
	private UsuarioBusiness gerenciadorDeUsuario;

	private SessaoBusiness gerenciadorDeSessao;

	private CaronaBusiness gerenciadorDeCaronas;

	private SolicitacaoBusiness gerenciadorDeSolicitacoes;


	@RequestMapping(value = "/home/apresentacao.html", method = RequestMethod.GET)
	public ModelAndView apresentacaoGet(HttpServletRequest request) {

		LOG.debug("Iniciada a execucao do metodo: loginGet");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("model", new LoginModel());
		request.getSession().removeAttribute("login");
		request.getSession().removeAttribute("idSessao");

		LOG.debug("Finalizada a execucao do metodo: loginGet");

		return modelAndView;
	}

	@RequestMapping(value = "/home/apresentacao.html", method = RequestMethod.POST)
	public ModelAndView apresentacaoPost(
			@ModelAttribute("model") @Valid LoginModel model,
			BindingResult bindingResult, HttpServletRequest request) {

		LOG.debug("Iniciada a execucao do metodo: loginPost");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/home/apresentacao");

		if (bindingResult.hasErrors()) {
			modelAndView.addObject("model", new LoginModel());
			return modelAndView;
		}

		gerenciadorDeSessao = new SessaoBusiness();
		gerenciadorDeUsuario = new UsuarioBusiness();
		gerenciadorDeCaronas = new CaronaBusiness();
		try {
			gerenciadorDeSessao.abrirSessao(model.getLogin(), model.getSenha());
			request.getSession().setAttribute("login", model.getLogin());
			request.getSession().setAttribute(
					"idSessao",
					gerenciadorDeSessao.abrirSessao(model.getLogin(),
							model.getSenha()));
			// request.setAttribute("login", model.getLogin());

		} catch (Exception e) {
			modelAndView.addObject("model", new LoginModel());
			return modelAndView;
		}

		LOG.debug("Finalizada a execucao do metodo: loginPost");
		return new ModelAndView("redirect:/home/home.html");
	}

	
	@RequestMapping(value = "/home/headerLogin.html", method = RequestMethod.POST)
	public ModelAndView headerLoginPost(
			@ModelAttribute("model") @Valid LoginModel model,
			BindingResult bindingResult, HttpServletRequest request) {

		LOG.debug("Iniciada a execucao do metodo: loginPost");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/home/apresentacao");

		if (bindingResult.hasErrors()) {
			modelAndView.addObject("model", new LoginModel());
			return modelAndView;
		}

		gerenciadorDeSessao = new SessaoBusiness();
		gerenciadorDeUsuario = new UsuarioBusiness();
		gerenciadorDeCaronas = new CaronaBusiness();
		try {
			gerenciadorDeSessao.abrirSessao(model.getLogin(), model.getSenha());
			request.getSession().setAttribute("login", model.getLogin());
			request.getSession().setAttribute(
					"idSessao",
					gerenciadorDeSessao.abrirSessao(model.getLogin(),
							model.getSenha()));
			// request.setAttribute("login", model.getLogin());

		} catch (Exception e) {
			modelAndView.addObject("model", new LoginModel());
			return new ModelAndView("redirect:/home/apresentacao.html");
		}

		LOG.debug("Finalizada a execucao do metodo: loginPost");
		return new ModelAndView("redirect:/home/home.html");
	}

	

}
