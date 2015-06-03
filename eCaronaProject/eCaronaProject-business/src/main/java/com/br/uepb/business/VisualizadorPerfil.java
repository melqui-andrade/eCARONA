package com.br.uepb.business;

import java.util.ArrayList;
import java.util.List;

import com.br.uepb.constants.ECaronaException;
import com.br.uepb.constants.MensagensDeErro;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.domain.SolicitacaoDomain;
import com.br.uepb.domain.UsuarioDomain;
import com.br.uepb.persistencia.Persistencia;

/**
 * Classe para visualização de perfil do usuário
 * Um perfil contém dados do usuário, histórico de caronas, qualificação de caronas
 * qualificação do usuário, caronas que faltou  
 *
 */
public class VisualizadorPerfil {
	
	private Persistencia persistenciaBD;
	
	public VisualizadorPerfil(){
		persistenciaBD = new Persistencia();
	}
	
	/**
	 * Pegar um atributo específico de uma carona
	 * @param idUsuario ID do usuário
	 * @param atributo Atributos válidos: nome | endereco | historico de caronas |
	 * 									  vagas | historico de vagas em caronas	 |
	 * 									  caronas seguras e tranquilas 			 |
	 * 									  caronas que não funcionaram 			 |
	 * 									  faltas em vagas de caronas 			 |
	 * 									  presenças em vagas de caronas
	 * @return
	 * @throws ECaronaException
	 */
	public String getAtributoPerfil(String idUsuario, String atributo) throws ECaronaException {
		
		UsuarioDomain usuario = persistenciaBD.getUsuarioBD().getUsuario(idUsuario);
		if(usuario.equals(null)) throw new ECaronaException(MensagensDeErro.USUARIO_INEXISTENTE);
		
		switch(atributo){
		case "nome":
			return usuario.getNome();
		case "endereco":
			return usuario.getEndereco();
		case "email":
			return usuario.getEmail();
		case "historico de caronas":
			return historicoCarona(usuario);
		case "historico de vagas em caronas":
			return historicoDeVagas(usuario);
		case "caronas seguras e tranquilas":
			return getTotalCaronaTranquilas(usuario);
		case "caronas que não funcionaram":
			return "0";
		case "faltas em vagas de caronas":
			return "0";
		case "presenças em vagas de caronas":
			return "0";
		}
		return "";
	}

	/**
	 * Visualizar perfil do usuário (ainda não implementado)
	 * @param idSessao ID da sessão do usuário
	 * @param login ID do usuário
	 * @return o nome do usuário
	 * @throws ECaronaException caso o usuário não exista na base
	 */
	public String visualizarPerfil(String idSessao, String login) throws ECaronaException {
		UsuarioDomain usuario = persistenciaBD.getUsuarioBD().getUsuario(login);
		if(usuario == null) throw new ECaronaException(MensagensDeErro.USUARIO_INEXISTENTE);
		return usuario.getNome();
	}
	
	/**
	 * Visualizar historico de caronas de um usuário
	 * @param usuario UsuarioDomain que se deseja visualizar o perfil
	 * @return Listagem das caronas do usuário
	 */
	private String historicoCarona(UsuarioDomain usuario){
		String historico = "[";
		for(CaronaDomain carona : usuario.getCaronas()){
			
			historico += carona.getIdCarona();
			historico += ",";
		}
		if(historico.length() > 1) {
			historico = historico.substring(0, (historico.length()-1));
		}
		
		historico += "]";
		return historico;
	}
	
	private String historicoDeVagas(UsuarioDomain usuario){
		
		String historico = "[";
		List<CaronaDomain> caronas = persistenciaBD.getCaronaBD().list();
		List<SessaoDomain> sessoes = persistenciaBD.getSessaoBD().list();
		List<SolicitacaoDomain> solicitacoes = persistenciaBD.getSolicitacaoBD().list();
		List<CaronaDomain> caronasDoUsuario = new ArrayList<CaronaDomain>() {
		};
		for(CaronaDomain carona : persistenciaBD.getCaronaBD().list()){
			if(carona.getUsuarioLogin().equals(usuario.getLogin())){
				if (carona.foiConcluida()) {
					historico += carona.getVagas();
					historico += ",";
				}
			}
		}
		if(historico.length() > 1) {
			historico = historico.substring(0, (historico.length()-1));
		}
		
		historico += "]";
		
		return historico;
	}
	
	private String getTotalCaronaTranquilas(UsuarioDomain usuario){
		int total = 0;
		for(CaronaDomain carona : usuario.getCaronas()){
			if(carona.foiConcluida() && carona.foiTranquila()){
				total++;
			}
		}
		return String.valueOf(total);
	}

	public void reviewVagaEmCarona(String idSessao, String idCarona,
			String loginCaroneiro, String review) {
		// TODO Auto-generated method stub
		
	}

}
