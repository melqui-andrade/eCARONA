package com.br.uepb.business;

import java.util.ArrayList;

import servicesBackup.PersistenciaDAO;

import com.br.uepb.constants.ECaronaException;
import com.br.uepb.constants.MensagensDeErro;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.domain.UsuarioDomain;
import com.br.uepb.persistencia.Persistencia;
import com.br.uepb.utilities.VerificadorString;

/**
 * Implementação das regras de negócio de CaronaDomain
 * Todas as ações relacionadas ao gerenciamento de caronas se encontram nessa classe
 * @see CaronaDomain
 */
public class CaronaBusiness {

	//Persistencia volatil [antiga]
	private PersistenciaDAO persistencia;
	//persistencia atraves de BD
	private Persistencia persistenciaBD;
	private int sufixoIdCarona;

	/**
	 * Construtor da classe, inicializa a entidade de persistência
	 * @param persistencia Entidade responsavel em persistir os dados do sistema
	 */
	public CaronaBusiness(PersistenciaDAO persistencia) {
		this.persistencia = persistencia;
		this.persistenciaBD = new Persistencia();
		sufixoIdCarona = 0;

	}

	/**
	 * Cadastra uma nova carona de determinado usuário
	 * 
	 * @param idSessao Sessão do usuário que está cadastrando a carona
	 * @param origem Local de onde o usuário vai sair
	 * @param destino Local para onde o usuário pretende ir
	 * @param data Dia, mês, ano no formato: "dd/mm/aaaa" que o trajeto será realizado
	 * @param hora Hora e minutos no formato "hh:mm" que o usuário vai partir
	 * @param vagas Quantidade de acentos disponíveis para carona
	 * @return Id da carona
	 * @throws Exception
	 *             caso os campos sejam nulos ou vazios, o id da sessão não seja
	 *             validado, a sessão não exista na base de sessões ativas
	 */
	public String cadastrarCarona(String idSessao, String origem,
			String destino, String data, String hora, String vagas)
			throws ECaronaException {
		if (vagas == null || vagas.equals(""))
			throw new ECaronaException(MensagensDeErro.VAGA_INVALIDA);
		if (VerificadorString.ContemLetra(vagas))
			throw new ECaronaException(MensagensDeErro.VAGA_INVALIDA);

		if (idSessao == null || idSessao.equals(""))
			throw new ECaronaException(MensagensDeErro.SESSAO_INVALIDA);

		if (persistencia.getSessaoBD().containsKey(idSessao)) {
			sufixoIdCarona++;
			
			SessaoDomain sessao = persistencia.getSessaoBD().get(idSessao);
			
			CaronaDomain carona = new CaronaDomain();

			carona.setOrigem(origem);

			carona.setDestino(destino);

			carona.setData(data);
			
			carona.setIdSessao(idSessao);

			carona.setVagas(Integer.valueOf(vagas));

			carona.setHora(hora);
			
			carona.setUsuarioLogin(sessao.getIdUsuario());
			
			carona.foiConcluida(false);
			
			carona.foiTranquila(false);

			String identificadorCarona = "carona"
					+ String.valueOf(sufixoIdCarona);
			// System.out.println(identificadorCarona);
			carona.setId(identificadorCarona);

			persistencia.getCaronaBD().put(identificadorCarona, carona);
			UsuarioDomain usuario = persistenciaBD.getUsuarioBD().getUsuario(sessao.getIdUsuario());
			usuario.adicionarCarona(carona);
			persistenciaBD.getUsuarioBD().update(usuario);
			return carona.getId();
		} else {
			throw new ECaronaException(MensagensDeErro.SESSAO_INEXISTENTE);
		}

	}
	
	/**
	 * Localizar uma carona do usuário, através do índice da carona no conjunto de caronas  
	 * @param idSessao ID da sessão do usuário que se deseja obter a carona
	 * @param index i-ésima posição do elemento carona no conjunto de caronas
	 * @return ID da carona solicitada
	 */
	public String localizarCaronaUsuarioPorIndex(String idSessao, String index){
		
		int valorIndex = Integer.parseInt(index);
		SessaoDomain sessao = persistencia.getSessaoBD().get(idSessao);
		ArrayList<CaronaDomain> caronas = persistenciaBD.getUsuarioBD().getUsuario(sessao.getIdUsuario()).getCaronas();
		caronas.sort((p1, p2) -> p1.getId().compareTo(p2.getId()));
		return caronas.get(valorIndex-1).getId();
	}

	/**
	 * Localiza as caronas cadastradas de determinado usuário
	 * 
	 * @param idSessao
	 *            campo obrigatório, id da sessão do usuário
	 * @param origem
	 *            [opicional] nome do local de origem
	 * @param destino
	 *            [opicional] nome do local de destino
	 * @return uma mensagem com o conjuto de caronas que satisfazem os critérios
	 *         de pesquisa
	 * @throws Exception
	 *             caso o os campos sejam preenchidos incorretamente
	 */
	public String localizarCarona(String idSessao, String origem, String destino)
			throws ECaronaException {

		if (origem.equals("-") || origem.equals("()") || origem.equals("!")
				|| origem.equals("!?"))
			throw new ECaronaException(MensagensDeErro.ORIGEM_INVALIDA);
		if (destino.equals(".") || destino.equals("()") || destino.equals("!")
				|| destino.equals("!?"))
			throw new ECaronaException(MensagensDeErro.DESTINO_INVALIDO);
		
		SessaoDomain sessao = persistencia.getSessaoBD().get(idSessao);

		ArrayList<String> allID = new ArrayList<String>();
		String idCarona = "{";
		// System.out.println(caronaBD.values().);
		if (origem.equals("") && destino.equals("")) {
			for (CaronaDomain carona : persistenciaBD.getUsuarioBD().getUsuario(sessao.getIdUsuario()).getCaronas()) {
				allID.add(carona.getId());
			}

			allID.sort(null);
			idCarona = allID.toString();

			idCarona = idCarona.substring(1, idCarona.length() - 1);
			idCarona = "{" + idCarona;
			idCarona = idCarona.replace(" ", "");

		} else if (origem.equals("")) {
			for (CaronaDomain carona : persistenciaBD.getCaronaBD().list()) {
				if (carona.getDestino().equals(destino)) {
					idCarona += carona.getId();
					idCarona += ",";
				}
			}
			idCarona = idCarona.substring(0, idCarona.length() - 1);
		} else if (destino.equals("")) {
			for (CaronaDomain carona : persistenciaBD.getCaronaBD().list()) {
				if (carona.getOrigem().equals(origem)) {
					idCarona += carona.getId();
					idCarona += ",";
				}
			}
			idCarona = idCarona.substring(0, idCarona.length() - 1);
		} else {
			for (CaronaDomain carona : persistenciaBD.getCaronaBD().list()) {

				if (carona.getOrigem().equals(origem)
						&& carona.getDestino().equals(destino)) {
					idCarona += carona.getId();
					idCarona += ",";
				}
			}
			if (idCarona.length() > 2)
				idCarona = idCarona.substring(0, idCarona.length() - 1);
		}

		return idCarona + "}";
	}

	/**
	 * Pegar um atributo específico de uma carona
	 * 
	 * @param idCarona ID da carona
	 * @param atributoCarona Atributos válidos: origem | destino | data | vagas
	 * @return O valor do atributo especificado
	 * @throws Exception caso a carona não exista na base ou o atributo não seja válido
	 */
	public String getAtributoCarona(String idCarona, String atributoCarona)
			throws ECaronaException {
		CaronaDomain carona;

		if (idCarona == null || idCarona.equals("")) {
			throw new ECaronaException(
					MensagensDeErro.IDENTIFICADOR_CARONA_INVALIDO);
		}

		carona = persistenciaBD.getCaronaBD().getCarona(idCarona);
		if (carona != null) {

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
	 * 
	 * @param idCarona
	 * @return mensagem no formato: "origem - destino" da carona
	 * @throws Exception caso os campos sejam nulos ou vazios, caso o id da carona não
	 *             exista
	 */
	public String getTrajetoCarona(String idCarona) throws ECaronaException {
		if (idCarona == null)
			throw new ECaronaException(MensagensDeErro.TRAJETO_INVALIDO);
		if (idCarona.equals(""))
			throw new ECaronaException(MensagensDeErro.TRAJETO_INEXISTENTE);

		CaronaDomain carona = persistenciaBD.getCaronaBD().getCarona(idCarona);
		if (carona == null)
			throw new ECaronaException(MensagensDeErro.TRAJETO_INEXISTENTE);
		return carona.getOrigem() + " - " + carona.getDestino();
	}

	/**
	 * Pegar todas as informações de uma carona (origem, destino, data, horario,
	 * vagas)
	 * 
	 * @param idCarona
	 * @return uma mensagem com as informações da carona
	 * @see CaronaDomain CaronaDomain.toString()
	 * @throws Exception
	 *             Caso o atributo seja nulo ou inválido, não exista carona com
	 *             o id informado na base
	 */
	public String getCaronaInfo(String idCarona) throws ECaronaException {
		if (idCarona == null)
			throw new ECaronaException(MensagensDeErro.CARONA_INVALIDA);
		if (idCarona.equals(""))
			throw new ECaronaException(MensagensDeErro.CARONA_INEXISTENTE);
		CaronaDomain carona = persistenciaBD.getCaronaBD().getCarona(idCarona);
		if (carona == null)
			throw new ECaronaException(MensagensDeErro.CARONA_INEXISTENTE);
		return carona.toString();

	}

}
