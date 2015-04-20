package com.br.uepb.business;

import java.util.ArrayList;
import java.util.Date;

import servicesBackup.PersistenciaDAO;

import com.br.uepb.constants.ECaronaException;
import com.br.uepb.constants.MensagensDeErro;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.domain.UsuarioDomain;

/**
 * Gerenciador de sessão As sessões criadas ficam amazenadas temporariamente em
 * um banco de sessões e são encerradas após comando do usuário
 * 
 * @author Sidney
 */
public class SessaoBusiness {

	private PersistenciaDAO persistencia;
	private ArrayList<SessaoDomain> sessoes;

	public SessaoBusiness(PersistenciaDAO persistencia){
		this.persistencia = persistencia;
		sessoes = new ArrayList<SessaoDomain>();
	}

	/**
	 * Cria uma nova sessão no sistema
	 * 
	 * @param id
	 *            : Identificador da sessão
	 * @param usuario
	 *            : O usuário que vai logar a sessão
	 */
	public void adicionaSessao(String id, UsuarioDomain usuario) {
		SessaoDomain sessao = new SessaoDomain(id, usuario);
		sessoes.add(sessao);
	}

	/**
	 * Busca uma sessão pelo seu ID
	 * 
	 * @param idSessao
	 *            ID da sessão
	 * @return Uma sessão
	 */
	public SessaoDomain getSessao(String idSessao) {
		SessaoDomain sessao = null;
		for (SessaoDomain s : sessoes) {
			if (s.getId() == idSessao) {
				sessao = s;
			}
		}
		return sessao;
	}

	/**
	 * Verifica se o ID passado pertence a alguma sessão do banco de sessões
	 * 
	 * @param idSessao
	 *            ID desejado
	 * @return True, caso exista alguma sessão com o tal ID
	 */
	public boolean ehSessaoValida(String idSessao) {

		if (idSessao == null || idSessao == "")
			return false;

		for (SessaoDomain s : sessoes) {
			if (s.getId() == idSessao) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Iniciar sessão de usuário. O usuário só pode realizar atividades no
	 * sistema após abrir a sessão
	 * 
	 * @param login
	 * @param senha
	 * @return O id da sessão
	 * @throws Exception
	 *             caso Algum dos atributos sejam nulos ou vazios, ou a
	 *             autenticação do usuário falhe
	 */

	public String abrirSessao(String login, String senha)
			throws ECaronaException {

		UsuarioDomain usuario = persistencia.getUsuarioBD().get(login);

		if (login == null || senha == null)
			throw new ECaronaException(MensagensDeErro.LOGIN_INVALIDO);

		if (login.equals("") || senha.equals(""))
			throw new ECaronaException(MensagensDeErro.LOGIN_INVALIDO);

		if (usuario == null)
			throw new ECaronaException(MensagensDeErro.USUARIO_INEXISTENTE);

		if (!usuario.getSenha().equals(senha))
			throw new ECaronaException(MensagensDeErro.LOGIN_INVALIDO);

		Date time = new Date();
		String idSessao = Long.toString(time.getTime());

		SessaoDomain sessao = new SessaoDomain(idSessao, usuario);

		persistencia.getSessaoBD().put(idSessao, sessao);

		return idSessao;
	}

	/**
	 * Pegar sessão por id
	 * 
	 * @param idSessao
	 * @return Uma SessãoDomain
	 * @see SessaoDomain
	 */
	public SessaoDomain getSessaoPorId(String idSessao) {

		return persistencia.getSessaoBD().get(idSessao);
	}

	/**
	 * Finaliza sessao de um usuário
	 * 
	 * @param usuario
	 *            login do usuário
	 */
	public void encerrarSessao(String usuario) {
		for (SessaoDomain sessao : persistencia.getSessaoBD().values()) {
			if (sessao.getUsuario().getLogin().equals(usuario)) {
				persistencia.getSessaoBD().remove(sessao.getId());
				break;
			}
		}
	}
	
	
}
