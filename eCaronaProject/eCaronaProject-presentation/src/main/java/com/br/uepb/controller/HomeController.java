package com.br.uepb.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.br.uepb.Model.CadastrarModel;
import com.br.uepb.Model.LoginModel;
import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.business.UsuarioBusiness;

@Controller
public class HomeController {

	private static final Log LOG = LogFactory.getLog(HomeController.class);

	@Autowired
	private UsuarioBusiness gerenciadorDeUsuario;
	
	private SessaoBusiness gerenciadorDeSessao;

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
		modelAndView.setViewName("/home/login");
		modelAndView.addObject("model",new LoginModel());

		LOG.debug("Finalizada a execucao do metodo: loginGet");

		return modelAndView;
	}
	
	@RequestMapping(value = "/home/login.html", method = RequestMethod.POST)
	public ModelAndView loginPost(
			@ModelAttribute("model") @Valid LoginModel model,
			BindingResult bindingResult, HttpServletRequest request) {

		LOG.debug("Iniciada a execucao do metodo: loginPost");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/home/login");

		if (bindingResult.hasErrors()) {
			modelAndView.addObject("model", new LoginModel());
			return modelAndView;
		}

		gerenciadorDeSessao = new SessaoBusiness();
		try {
			gerenciadorDeSessao.abrirSessao(model.getLogin(),model.getSenha());
			modelAndView.setViewName("/home/home");
		} catch (Exception e) {
			modelAndView.addObject("model", new LoginModel());
			return modelAndView;
		}

		LOG.debug("Finalizada a execucao do metodo: loginPost");
		return modelAndView;
	}
	

	@RequestMapping(value = "/home/apresentacao.html", method = RequestMethod.GET)
	public ModelAndView apresentacaoGet(HttpServletRequest request) {

		LOG.debug("Iniciada a execucao do metodo: loginGet");

		ModelAndView modelAndView = new ModelAndView();

		LOG.debug("Finalizada a execucao do metodo: loginGet");

		return modelAndView;
	}

	@RequestMapping(value = "/home/cadastrar.html", method = RequestMethod.GET)
	public ModelAndView cadastrarGet(HttpServletRequest request) {

		LOG.debug("Iniciada a execucao do metodo: cadastrarGet");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/home/cadastrar");
		modelAndView.addObject("model", new CadastrarModel());
		modelAndView.addObject("mensagem","");

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
			modelAndView.addObject("model", new CadastrarModel());
			return modelAndView;
		}

		gerenciadorDeUsuario = new UsuarioBusiness();
		try {
			gerenciadorDeUsuario.criarUsuario(model.getLogin(),
					model.getSenha(), model.getNome(), model.getEndereco(),
					model.getEmail());
			modelAndView.addObject("mensagem","Cadastro realizado com sucesso!");
		} catch (Exception e) {
			modelAndView.addObject("model", new CadastrarModel());
			modelAndView.addObject("mensagem",e.getMessage());
			return modelAndView;
		}

		LOG.debug("Finalizada a execucao do metodo: cadastrarGet");
		return modelAndView;
	}

}
