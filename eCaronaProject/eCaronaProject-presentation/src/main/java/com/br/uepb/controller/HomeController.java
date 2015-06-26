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
		LOG.debug("\n-------------------------------" + idCarona
				+ "--------------------------------------\n");
		if (idCarona != null) {
			gerenciadorDeSolicitacoes = new SolicitacaoBusiness();
			try {
				CaronaDomain carona = gerenciadorDeCaronas.getCarona(idCarona);
				gerenciadorDeSolicitacoes.aceitarSolicitacao(idSessao,
						gerenciadorDeSolicitacoes.solicitarVaga(idSessao,
								idCarona));
				// gerenciadorDeCaronas.cadastrarCarona(idSessao,
				// carona.getOrigem(), carona.getDestino(),carona.getData(),
				// carona.getHora(), String.valueOf(carona.getVagas()));

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
