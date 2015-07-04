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
public class CaronasCadastradasController {

	private static final Log LOG = LogFactory.getLog(CaronasCadastradasController.class);

	@Autowired
	private UsuarioBusiness gerenciadorDeUsuario;

	private SessaoBusiness gerenciadorDeSessao;

	private CaronaBusiness gerenciadorDeCaronas;

	private SolicitacaoBusiness gerenciadorDeSolicitacoes;


	@RequestMapping(value = "/home/caronasCadastradas.html", method = RequestMethod.GET)
	public ModelAndView caronasCadastradasGet(@RequestParam(required = false) String idCarona, HttpServletRequest request) {
		LOG.debug("Iniciada a execucao do metodo: homeGet");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/home/caronasCadastradas");

		UsuarioModel usuarioModel = new UsuarioModel();
		String idSessao = (String) request.getSession()
				.getAttribute("idSessao");
		gerenciadorDeCaronas = new CaronaBusiness();
		
		if (idCarona != null) {
			gerenciadorDeSolicitacoes = new SolicitacaoBusiness();
			try {
				//cancelar a carona cadastrada
				

			} catch (Exception e) {
				modelAndView.addObject("mensagemErro", e.getMessage());
				modelAndView.addObject("status", "erro");

			}
		}

		try {
			usuarioModel.setNome(gerenciadorDeUsuario
					.getAtributoUsuario((String) request.getSession()
							.getAttribute("login"), "nome"));
			List<CaronaDomain> todasCaronas = gerenciadorDeCaronas.getCaronasSessao(idSessao);
					
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
