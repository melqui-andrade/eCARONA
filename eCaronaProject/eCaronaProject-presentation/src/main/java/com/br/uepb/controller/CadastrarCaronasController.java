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

import com.br.uepb.business.CaronaBusiness;
import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.business.SolicitacaoBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.UsuarioDomain;
import com.br.uepb.model.CadastrarCaronaModel;
import com.br.uepb.model.CadastrarModel;
import com.br.uepb.model.CaronaModel;
import com.br.uepb.model.LoginModel;
import com.br.uepb.model.UsuarioModel;

@Controller
public class CadastrarCaronasController {

	private static final Log LOG = LogFactory.getLog(CadastrarCaronasController.class);

	@Autowired
	private UsuarioBusiness gerenciadorDeUsuario;

	private SessaoBusiness gerenciadorDeSessao;

	private CaronaBusiness gerenciadorDeCaronas;

	private SolicitacaoBusiness gerenciadorDeSolicitacoes;

	@RequestMapping(value = "/home/cadastrarCarona.html", method = RequestMethod.GET)
	public ModelAndView cadastrarCaronaGet(HttpServletRequest request) {

		LOG.debug("Iniciada a execucao do metodo: cadastrarGet");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/home/cadastrarCarona");
		modelAndView.addObject("modelCadastrarCarona",
				new CadastrarCaronaModel());
		modelAndView.addObject("mensagem", "");

		LOG.debug("Finalizada a execucao do metodo: cadastrarGet");

		return modelAndView;
	}

	@RequestMapping(value = "/home/cadastrarCarona.html", method = RequestMethod.POST)
	public ModelAndView cadastrarCaronaPost(
			@ModelAttribute("modelCadastrarCarona") @Valid CadastrarCaronaModel model,
			BindingResult bindingResult, HttpServletRequest request) {

		LOG.debug("Iniciada a execucao do metodo: cadastrarGet");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/home/cadastrarCarona");

		if (bindingResult.hasErrors()) {
			modelAndView.addObject("modelCadastrarCarona", model);
			return modelAndView;
		}
		String idSessao = (String) request.getSession()
				.getAttribute("idSessao");
		gerenciadorDeCaronas = new CaronaBusiness();
		try {
			if (model.getCarona().equals("intermunicipal")) {
				gerenciadorDeCaronas.cadastrarCarona(idSessao,
						model.getOrigem(), model.getDestino(), model.getData(),
						model.getHora(), model.getVagas());
				modelAndView.addObject("mensagem",
						"Carona " + model.getCarona()
								+ " cadastrada com sucesso!");
				modelAndView.addObject("status", "positivo");
			}
			if (model.getCarona().equals("municipal")) {
				gerenciadorDeCaronas.cadastrarCaronaMunicipal(idSessao,
						model.getOrigem(), model.getDestino(),
						model.getCidade(), model.getData(), model.getHora(),
						model.getVagas());
				modelAndView.addObject("mensagem",
						"Carona " + model.getCarona()
								+ " cadastrada com sucesso!");
				modelAndView.addObject("status", "positivo");
			}
			if (model.getCarona().equals("relampago")) {

			}

		} catch (Exception e) {
			modelAndView.addObject("modelCadastrarCarona", model);
			modelAndView.addObject("mensagem", e.getMessage());
			modelAndView.addObject("status", "negativo");
			return modelAndView;
		}

		LOG.debug("Finalizada a execucao do metodo: cadastrarGet");
		return modelAndView;
	}

}
