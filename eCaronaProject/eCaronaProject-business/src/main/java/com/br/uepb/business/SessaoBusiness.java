package com.br.uepb.business;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.br.uepb.constants.ECaronaException;
import com.br.uepb.constants.MensagensDeErro;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.domain.UsuarioDomain;
import com.br.uepb.persistencia.Persistencia;

/**
 * Implementação das regras de negócio de SessaoDomain
 * Todas as ações relacionadas ao gerenciamento de sessões se encontram nessa classe
 * @see SessaoDomain
 * @author Sidney
 */
public class SessaoBusiness {

	
	private Persistencia persistenciaBD;

	/**
	 * Construtor da classe
	 * @param persistencia Entidade responsavel em persistir os dados do sistema
	 */
	public SessaoBusiness(){
		this.persistenciaBD = new Persistencia();
	}

	/**
	 * Cria uma nova sessão no sistema
	 * 
	 * @param id Identificador da sessão
	 * @param usuario O UsuarioDomain que vai logar a sessão
	 */
	public void adicionaSessao(String id, UsuarioDomain usuario) {
		Calendar data = Calendar.getInstance();
		DateFormat formatoData = new SimpleDateFormat("yyyy/MM/dd");
		DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
		Date time = new Date();
		String idSessao = Long.toString(time.getTime());
		
		SessaoDomain sessao = new SessaoDomain();
		sessao.setIdSessao(idSessao);
		sessao.setIdUsuario(id);
		sessao.setData(formatoData.format(data));
		sessao.setHora(formatoHora.format(data));
		sessao.setEstaAtiva(true);
		
		persistenciaBD.getSessaoBD().save(sessao);
	}

	/**
	 * Busca uma sessão pelo seu ID
	 * 
	 * @param idSessao ID da sessão
	 * @return Uma sessão
	 */
	public SessaoDomain getSessao(String idSessao) {
		
		return persistenciaBD.getSessaoBD().getSessao(idSessao);
	}

	/**
	 * Verifica se o ID passado pertence a alguma sessão do banco de sessões
	 * 
	 * @param idSessao ID desejado
	 * @return True, caso exista alguma sessão com o tal ID
	 */
	public boolean ehSessaoValida(String idSessao) {

		if (idSessao == null || idSessao == "")
			return false;

		SessaoDomain sessao = persistenciaBD.getSessaoBD().getSessao(idSessao);
		if(sessao != null){
			return true;
		}
		else return false;
	}

	/**
	 * Iniciar sessão de usuário. O usuário só pode realizar atividades no
	 * sistema após abrir a sessão
	 * 
	 * @param login Identificador do usuário no sistema
	 * @param senha Senha do usuário
	 * @return ID da nova sessão iniciada
	 * @throws Exception caso Algum dos atributos sejam nulos ou vazios, ou a
	 *             autenticação do usuário falhe
	 */

	public String abrirSessao(String login, String senha)
			throws ECaronaException {


		if (login == null || senha == null)
			throw new ECaronaException(MensagensDeErro.LOGIN_INVALIDO);

		if (login.equals("") || senha.equals(""))
			throw new ECaronaException(MensagensDeErro.LOGIN_INVALIDO);

		UsuarioDomain usuario = persistenciaBD.getUsuarioBD().getUsuario(login);
		
		if (usuario == null)
			throw new ECaronaException(MensagensDeErro.USUARIO_INEXISTENTE);

		if (!usuario.getSenha().equals(senha))
			throw new ECaronaException(MensagensDeErro.LOGIN_INVALIDO);

		Date time = new Date();
		String idSessao = Long.toString(time.getTime());
		
		SessaoDomain sessao = new SessaoDomain();
		
		Date data = new Date();
		DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
		sessao.setIdSessao(idSessao);
		sessao.setIdUsuario(login);
		sessao.setData(formatoData.format(data));
		sessao.setHora(formatoHora.format(data));
		sessao.setEstaAtiva(true);

		persistenciaBD.getSessaoBD().save(sessao);

		return idSessao;
	}

	/**
	 * Pegar sessão por id
	 * 
	 * @param login Identificador do usuário no sistema
	 * @return Uma SessãoDomain
	 * @see SessaoDomain
	 */
	public SessaoDomain getSessaoPorId(String idSessao) {

		return persistenciaBD.getSessaoBD().getSessao(idSessao);
	}

	/**
	 * Finaliza sessao de um usuário
	 * 
	 * @param idUsuario Identificador do usuário
	 */
	public void encerrarSessao(String idUsuario) {
		List<SessaoDomain> sessoes = persistenciaBD.getSessaoBD().list(); 
		for(SessaoDomain sessao : sessoes){
			if(sessao.getIdUsuario().equals(idUsuario) && sessao.isEstaAtiva()){
				sessao.setEstaAtiva(false);
				persistenciaBD.getSessaoBD().update(sessao);
			}
		}
		
	}
	/**
	 * Limpar todos os registros de Sessao.
	 */
	public void zerarBase(){
		persistenciaBD.getSessaoBD().excluirTudo();
	}
	
	
}
