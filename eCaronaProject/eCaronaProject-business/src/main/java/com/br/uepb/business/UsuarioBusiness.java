package com.br.uepb.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.br.uepb.constants.ECaronaException;
import com.br.uepb.constants.MensagensDeErro;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.domain.UsuarioDomain;
import com.br.uepb.utilities.VerificadorString;
/**
 * Gerenciador de usuario
 * Classe responsávél por cadastrar e editar objetos do tipo UsuarioDomain,
 * gerenciar sessões do usuário,
 * manipular caronas
 * @author Melqui
 * @see UsuarioDomain
 *
 */
public class UsuarioBusiness {

	

	private Map<String, UsuarioDomain> usuarioBD;
	private Map<String, SessaoDomain> sessaoBD;
	private Map<String, CaronaDomain> caronaBD;
	private int sufixoIdCarona;

	public Map<String, UsuarioDomain> getUsuarioBD() {
		return usuarioBD;
	}

	public void setUsuarioBD(Map<String, UsuarioDomain> usuarioBD) {
		this.usuarioBD = usuarioBD;
	}

	public Map<String, SessaoDomain> getSessaoBD() {
		return sessaoBD;
	}

	public void setSessaoBD(Map<String, SessaoDomain> sessaoBD) {
		this.sessaoBD = sessaoBD;
	}

	public Map<String, CaronaDomain> getCaronaBD() {
		return caronaBD;
	}

	public void setCaronaBD(Map<String, CaronaDomain> caronaBD) {
		this.caronaBD = caronaBD;
	}
	
	public UsuarioBusiness() {
		setUsuarioBD(new HashMap<String, UsuarioDomain>());
		sessaoBD = new HashMap<String, SessaoDomain>();
		caronaBD = new HashMap<String, CaronaDomain>();
		sufixoIdCarona = 0;
	}

	/**
	 * Cria um novo usuário
	 * @param login login do usuário
	 * @param senha senha
	 * @param nome nome
	 * @param endereco rua, número e bairro
	 * @param email endereço de email
	 * @throws Exception caso algum dos atributos sejam inválidos (campos vazios,
	 * formato de email incorreto) ou já exista um usuário com mesmo login ou email
	 */
	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws ECaronaException {

		UsuarioDomain usuario = new UsuarioDomain();

		usuario.setLogin(login);

		if (getUsuarioBD().containsKey(login)) {
			throw new ECaronaException(MensagensDeErro.EXISTE_USUARIO_C_LOGIN);
		}

		usuario.setSenha(senha);

		usuario.setNome(nome);

		usuario.setEndereco(endereco);

		usuario.setEmail(email);

		for (UsuarioDomain u : getUsuarioBD().values()) {
			if (u.getEmail().equals(email)) {
				throw new ECaronaException(MensagensDeErro.EXISTE_USUARIO_C_EMAIL);
			}
		}

		getUsuarioBD().put(login, usuario);

	}

	/**
	 * Iniciar sessão de usuário. O usuário só pode realizar atividades no sistema
	 * após abrir a sessão
	 * @param login
	 * @param senha
	 * @return O id da sessão
	 * @throws Exception caso Algum dos atributos sejam nulos ou vazios, ou a autenticação
	 * do usuário falhe
	 */
	public String abrirSessao(String login, String senha) throws ECaronaException {

		UsuarioDomain usuario = getUsuarioBD().get(login);

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

		sessaoBD.put(idSessao, sessao);

		return idSessao;
	}

	/**
	 * Pegar um atributo específico de um usuário
	 * @param login login do usuário
	 * @param atributo nome | endereço | email
	 * @return O valor do atributo especificado
	 * @throws Exception caso o usuário não exista na base ou o atributo não seja válido
	 */
	public String getAtributoUsuario(String login, String atributo)
			throws ECaronaException {

		UsuarioDomain usuario;

		if (login == null || login.equals(""))
			throw new ECaronaException(MensagensDeErro.LOGIN_INVALIDO);

		if (getUsuarioBD().containsKey(login)) {
			usuario = getUsuarioBD().get(login);

			if (atributo == "" || atributo == null)
				throw new ECaronaException(MensagensDeErro.ATRIBUTO_INVALIDO);

			switch (atributo) {

			case "":
				throw new ECaronaException(MensagensDeErro.ATRIBUTO_INVALIDO);

			case "nome":
				return usuario.getNome();

			case "endereco":
				return usuario.getEndereco();

			case "email":
				return usuario.getEmail();

			default:
				throw new ECaronaException(MensagensDeErro.ATRIBUTO_INEXISTENTE);

			}
		} else {

			throw new ECaronaException(MensagensDeErro.USUARIO_INEXISTENTE);
		}

	}

	/**
	 * Verifica se um dado login existe na base de usuários
	 * @param login
	 * @return true, caso exista
	 */
	public boolean verificaLoginExistente(String login) {
		// verifica se existe no BD o login informado
		UsuarioDomain usuario = getUsuarioBD().get(login);
		if (usuario != null) {
			return true;
		} else {
			return false;

		}

	}

	/**
	 * Pegar sessão por id
	 * @param idSessao
	 * @return Uma SessãoDomain
	 * @see SessaoDomain
	 */
	public SessaoDomain getSessaoPorId(String idSessao) {

		return sessaoBD.get(idSessao);
	}
	
	
	/**
	 * Finaliza sessao de um usuário
	 * @param usuario login do usuário
	 */
	public void encerrarSessao(String usuario){
		for(SessaoDomain sessao : sessaoBD.values()){
			if(sessao.getUsuario().getLogin().equals(usuario)){
				sessaoBD.remove(sessao.getId());
				break;
			}
		}
	}

	/**
	 * Cadastra uma nova carona de determinado usuário
	 * @param idSessao
	 * @param origem
	 * @param destino
	 * @param data
	 * @param hora
	 * @param vagas
	 * @return Id da carona
	 * @throws Exception caso os campos sejam nulos ou vazios, o id da sessão não seja validado,
	 * a sessão não exista na base de sessões ativas
	 */
	public String cadastrarCarona(String idSessao, String origem,
			String destino, String data, String hora, String vagas)
			throws ECaronaException {
		if(vagas == null || vagas.equals("")) throw new ECaronaException(MensagensDeErro.VAGA_INVALIDA);
		if(VerificadorString.ContemLetra(vagas)) throw new ECaronaException(MensagensDeErro.VAGA_INVALIDA);

		if (idSessao == null || idSessao.equals(""))
			throw new ECaronaException(MensagensDeErro.SESSAO_INVALIDA);

		if (sessaoBD.containsKey(idSessao)) {
			sufixoIdCarona++;
			CaronaDomain carona = new CaronaDomain();

			carona.setOrigem(origem);

			carona.setDestino(destino);

			carona.setData(data);

			carona.setVagas(Integer.valueOf(vagas));

			carona.setHora(hora);

			String identificadorCarona = "carona"
					+ String.valueOf(sufixoIdCarona) + "ID";
			// System.out.println(identificadorCarona);
			carona.setId(identificadorCarona);

			caronaBD.put(identificadorCarona, carona);

			return carona.getId();
		} else {
			throw new ECaronaException(MensagensDeErro.SESSAO_INEXISTENTE);
		}

	}

	/**
	 * Localiza as caronas cadastradas de determinado usuário
	 * @param idSessao campo obrigatório, id da sessão do usuário
	 * @param origem [opicional] nome do local de origem
	 * @param destino [opicional] nome do local de destino
	 * @return uma mensagem com o conjuto de caronas que satisfazem os critérios de pesquisa
	 * @throws Exception caso o os campos sejam preenchidos incorretamente
	 */
	public String localizarCarona(String idSessao, String origem, String destino) throws ECaronaException {

		if(origem.equals("-") || origem.equals("()") || origem.equals("!") || origem.equals("!?")) 
			throw new ECaronaException(MensagensDeErro.ORIGEM_INVALIDA);
		if(destino.equals(".") || destino.equals("()") || destino.equals("!") || destino.equals("!?")) 
			throw new ECaronaException(MensagensDeErro.DESTINO_INVALIDO);
		
		
		ArrayList<String> allID = new ArrayList<String>();
		String idCarona = "{";
		// System.out.println(caronaBD.values().);
		if (origem.equals("") && destino.equals("")) {
			for (CaronaDomain carona : caronaBD.values()) {
				allID.add(carona.getId());
			}

			allID.sort(null);
			idCarona = allID.toString();

			idCarona = idCarona.substring(1, idCarona.length() - 1);
			idCarona = "{" + idCarona;
			idCarona = idCarona.replace(" ", "");

		} else if (origem.equals("")) {
			for (CaronaDomain carona : caronaBD.values()) {
				if (carona.getDestino().equals(destino)) {
					idCarona += carona.getId();
					idCarona += ",";
				}
			}
			idCarona = idCarona.substring(0, idCarona.length() - 1);
		} else if (destino.equals("")) {
			for (CaronaDomain carona : caronaBD.values()) {
				if (carona.getOrigem().equals(origem)) {
					idCarona += carona.getId();
					idCarona += ",";
				}
			}
			idCarona = idCarona.substring(0, idCarona.length() - 1);
		} else {
			for (CaronaDomain carona : caronaBD.values()) {

				if (carona.getOrigem().equals(origem)
						&& carona.getDestino().equals(destino)) {
					idCarona += carona.getId();
					idCarona += ",";
				}
			}
			if(idCarona.length() > 2)
			idCarona = idCarona.substring(0, idCarona.length() - 1);
		}

		return idCarona + "}";
	}

	/**
	 * Pegar um atributo específico de uma carona
	 * @param idCarona id da carona
	 * @param atributoCarona origem | destino | data | vagas
	 * @return O valor do atributo especificado
	 * @throws Exception caso a carona não exista na base ou o atributo não seja válido
	 */
	public String getAtributoCarona(String idCarona, String atributoCarona)
			throws ECaronaException {
		CaronaDomain carona;

		if (idCarona == null || idCarona.equals("")) {
			throw new ECaronaException(MensagensDeErro.IDENTIFICADOR_CARONA_INVALIDO);
		}

		if (caronaBD.containsKey(idCarona)) {
			carona = caronaBD.get(idCarona);

			if (atributoCarona == "" || atributoCarona == null)
				throw new ECaronaException(MensagensDeErro.ATRIBUTO_INVALIDO);

			switch (atributoCarona) {
			case "":
				throw new ECaronaException(MensagensDeErro.ATRIBUTO_INVALIDO);

			case "origem":
				return carona.getOrigem();

			case "destino":
				return carona.getDestino();

			case "data":
				return carona.getData();

			case "vagas":
				return Integer.toString(carona.getVagas());

			default:
				throw new ECaronaException(MensagensDeErro.ATRIBUTO_INEXISTENTE);
			}

		}

		else
			throw new ECaronaException(MensagensDeErro.ITEM_INEXISTENTE);

	}

	/**
	 * Pegar o trajeto (origem -- destino) de uma carona 
	 * @param idCarona
	 * @return mensagem no formato: "origem - destino" da carona
	 * @throws Exception caso os campos sejam nulos ou vazios, caso o id da carona
	 * não exista
	 */
	public String getTrajetoCarona(String idCarona) throws ECaronaException {
		if(idCarona == null) throw new ECaronaException(MensagensDeErro.TRAJETO_INVALIDO);
		if(idCarona.equals("")) throw new ECaronaException(MensagensDeErro.TRAJETO_INEXISTENTE);
		
		CaronaDomain carona = caronaBD.get(idCarona);
		if(carona == null) throw new ECaronaException(MensagensDeErro.TRAJETO_INEXISTENTE);
		return carona.getOrigem() + " - " + carona.getDestino();
	}

	/**
	 * Pegar todas as informações de uma carona
	 * (origem, destino, data, horario, vagas)
	 * @param idCarona
	 * @return uma mensagem com as informações da carona
	 * @see CaronaDomain CaronaDomain.toString()
	 * @throws Exception Caso o atributo seja nulo ou inválido, não exista
	 * carona com o id informado na base
	 */
	public String getCaronaInfo(String idCarona) throws ECaronaException {
		if(idCarona == null) throw new ECaronaException(MensagensDeErro.CARONA_INVALIDA);
		if(idCarona.equals("")) throw new ECaronaException(MensagensDeErro.CARONA_INEXISTENTE);
		CaronaDomain carona = caronaBD.get(idCarona);
		if(carona == null) throw new ECaronaException(MensagensDeErro.CARONA_INEXISTENTE);
		return carona.toString();

	}

	/**
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @param pontosSugeridos
	 * @return
	 */
	public String sugerirPontoEncontro(String idSessao, String idCarona,
			String[] pontosSugeridos) {
		return idSessao + idCarona;
	}

}
