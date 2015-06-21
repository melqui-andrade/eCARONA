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
public class HomeController {

	private static final Log LOG = LogFactory.getLog(HomeController.class);

	@Autowired
	private UsuarioBusiness gerenciadorDeUsuario;

	private SessaoBusiness gerenciadorDeSessao;

	private CaronaBusiness gerenciadorDeCaronas;

	private SolicitacaoBusiness gerenciadorDeSolicitacoes;

	@RequestMapping(value = "/home/home.html", method = RequestMethod.GET)
	public ModelAndView homeGet(
			@RequestParam(required = false) String idCarona,
			HttpServletRequest request) {

		LOG.debug("Iniciada a execucao do metodo: homeGet");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/home/home");

		UsuarioModel usuarioModel = new UsuarioModel();
		gerenciadorDeUsuario = new UsuarioBusiness();
		String login = (String) request.getSession().getAttribute("login");
		String idSessao = (String) request.getSession()
				.getAttribute("idSessao");
		
		gerenciadorDeCaronas = new CaronaBusiness();
		modelAndView.addObject("modelIdCarona", new CaronaModel());
		LOG.debug("\n-------------------------------"+idCarona+"--------------------------------------\n");
		if (idCarona != null) {
			gerenciadorDeSolicitacoes = new SolicitacaoBusiness();
			try {
				CaronaDomain carona = gerenciadorDeCaronas.getCarona(idCarona);
				gerenciadorDeSolicitacoes.aceitarSolicitacao(idSessao, gerenciadorDeSolicitacoes.solicitarVaga(idSessao, idCarona));
				//gerenciadorDeCaronas.cadastrarCarona(idSessao, carona.getOrigem(), carona.getDestino(),carona.getData(), carona.getHora(), String.valueOf(carona.getVagas()));

			} catch (Exception e) {
				modelAndView.addObject("mensagemErro", e.getMessage());
				modelAndView.addObject("status", "erro");

			}			
		}

		try {
			usuarioModel.setNome(gerenciadorDeUsuario.getAtributoUsuario(login,
					"nome"));
			usuarioModel.setEndereco(gerenciadorDeUsuario.getAtributoUsuario(
					login, "endereco"));
			usuarioModel.setEmail(gerenciadorDeUsuario.getAtributoUsuario(
					login, "email"));
			String allCaronas = gerenciadorDeCaronas.localizarCarona(idSessao,
					"", "");
			List<CaronaDomain> todasCaronas = gerenciadorDeCaronas
					.getTodasCaronas();
			LOG.debug("Caronas: " + todasCaronas.toString());
			modelAndView.addObject("allCaronas", allCaronas);
			modelAndView.addObject("todasCaronas", todasCaronas);
		} catch (Exception e) {
			LOG.debug("Ocorreu um erro no login:" + e.getMessage());
			modelAndView.addObject("modelUsuario", new UsuarioModel());
			return modelAndView;
		}

		System.out.println(usuarioModel.getNome());
		modelAndView.addObject("modelUsuario", usuarioModel);

		LOG.debug("Finalizada a execucao do metodo: homeGet");

		return modelAndView;
	}

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

	@RequestMapping(value = "/home/cadastrarCarona.html", method = RequestMethod.GET)
	public ModelAndView cadastrarCaronaGet(HttpServletRequest request) {

		LOG.debug("Iniciada a execucao do metodo: cadastrarGet");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/home/cadastrarCarona");
		modelAndView.addObject("model", new CadastrarCaronaModel());
		modelAndView.addObject("mensagem", "");

		LOG.debug("Finalizada a execucao do metodo: cadastrarGet");

		return modelAndView;
	}

	@RequestMapping(value = "/home/cadastrarCarona.html", method = RequestMethod.POST)
	public ModelAndView cadastrarCaronaPost(
			@ModelAttribute("model") @Valid CadastrarCaronaModel model,
			BindingResult bindingResult, HttpServletRequest request) {

		LOG.debug("Iniciada a execucao do metodo: cadastrarGet");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/home/cadastrarCarona");

		if (bindingResult.hasErrors()) {
			modelAndView.addObject("model", model);
			return modelAndView;
		}
		String idSessao = (String) request.getSession()
				.getAttribute("idSessao");
		gerenciadorDeCaronas = new CaronaBusiness();
		try {
			gerenciadorDeCaronas.cadastrarCarona(idSessao, model.getOrigem(),
					model.getDestino(), model.getData(), model.getHora(),
					model.getVagas());
			modelAndView
					.addObject("mensagem", "Carona cadastrada com sucesso!");
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

	@RequestMapping(value = "/home/perfil.html", method = RequestMethod.GET)
	public ModelAndView perfilGet(HttpServletRequest request) {

		LOG.debug("Iniciada a execucao do metodo: homeGet");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/home/perfil");

		UsuarioModel usuarioModel = new UsuarioModel();
		gerenciadorDeUsuario = new UsuarioBusiness();
		String login = (String) request.getSession().getAttribute("login");

		try {
			usuarioModel.setNome(gerenciadorDeUsuario.getAtributoUsuario(login,
					"nome"));
			usuarioModel.setEndereco(gerenciadorDeUsuario.getAtributoUsuario(
					login, "endereco"));
			usuarioModel.setEmail(gerenciadorDeUsuario.getAtributoUsuario(
					login, "email"));

		} catch (Exception e) {
			LOG.debug("Ocorreu um erro no login:" + e.getMessage());
			modelAndView.addObject("modelUsuario", new UsuarioModel());
			return modelAndView;
		}
		System.out.println(usuarioModel.getNome());
		modelAndView.addObject("modelUsuario", usuarioModel);

		LOG.debug("Finalizada a execucao do metodo: homeGet");

		return modelAndView;
	}

	@RequestMapping(value = "/home/visualizarCaronas.html", method = RequestMethod.GET)
	public ModelAndView visualizarCaronasGet(HttpServletRequest request) {
		LOG.debug("Iniciada a execucao do metodo: homeGet");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/home/visualizarCaronas");

		UsuarioModel usuarioModel = new UsuarioModel();
		String idSessao = (String) request.getSession()
				.getAttribute("idSessao");
		gerenciadorDeCaronas = new CaronaBusiness();

		try {
			usuarioModel.setNome(gerenciadorDeUsuario
					.getAtributoUsuario((String) request.getSession()
							.getAttribute("login"), "nome"));
			List<CaronaDomain> todasCaronas = gerenciadorDeCaronas
					.getCaronasSessao(idSessao);
			if (todasCaronas.isEmpty()) {
				modelAndView.addObject("temCarona", "nao");
			} else {
				modelAndView.addObject("temCarona", "sim");
			}
			modelAndView.addObject("todasCaronas", todasCaronas);
		} catch (Exception e) {
			LOG.debug("Ocorreu um erro no login:" + e.getMessage());
			modelAndView.addObject("modelUsuario", new UsuarioModel());
			return modelAndView;
		}

		System.out.println(usuarioModel.getNome());
		modelAndView.addObject("modelUsuario", usuarioModel);

		LOG.debug("Finalizada a execucao do metodo: homeGet");

		return modelAndView;
	}

}
