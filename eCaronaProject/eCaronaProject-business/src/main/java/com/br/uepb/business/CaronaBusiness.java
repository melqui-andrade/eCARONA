package com.br.uepb.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanComparator;

import com.br.uepb.constants.ECaronaException;
import com.br.uepb.constants.MensagensDeErro;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.InteresseDomain;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.domain.UsuarioDomain;
import com.br.uepb.persistencia.Persistencia;
import com.br.uepb.utilities.VerificadorString;

/**
 * Implementação das regras de negócio de CaronaDomain Todas as ações
 * relacionadas ao gerenciamento de caronas se encontram nessa classe
 * 
 * @see CaronaDomain
 */
public class CaronaBusiness {

	private Persistencia persistenciaBD;
	private int sufixoIdCarona;

	/**
	 * Construtor da classe, inicializa a entidade de persistência
	 * 
	 * @param persistencia
	 *            Entidade responsavel em persistir os dados do sistema
	 */
	public CaronaBusiness() {
		this.persistenciaBD = new Persistencia();
		sufixoIdCarona = 0;

	}

	/**
	 * Cadastra uma nova carona de determinado usuário
	 * 
	 * @param idSessao
	 *            Sessão do usuário que está cadastrando a carona
	 * @param origem
	 *            Local de onde o usuário vai sair
	 * @param destino
	 *            Local para onde o usuário pretende ir
	 * @param data
	 *            Dia, mês, ano no formato: "dd/mm/aaaa" que o trajeto será
	 *            realizado
	 * @param hora
	 *            Hora e minutos no formato "hh:mm" que o usuário vai partir
	 * @param vagas
	 *            Quantidade de acentos disponíveis para carona
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

		SessaoDomain sessao = persistenciaBD.getSessaoBD().getSessao(idSessao);
		if (sessao != null) {
			sufixoIdCarona++;

			CaronaDomain carona = new CaronaDomain();

			carona.setOrigem(origem);

			carona.setDestino(destino);

			carona.setCidade(null);

			carona.setData(data);

			carona.setIdSessao(idSessao);

			carona.setVagas(Integer.valueOf(vagas));

			carona.setHora(hora);

			carona.setUsuarioLogin(sessao.getIdUsuario());

			carona.foiConcluida(false);

			carona.ehMunicipal(false);
			
			carona.setTranquila("");
			
			carona.setPreferencial(false);

			String identificadorCarona = "carona"
					+ String.valueOf(sufixoIdCarona);
			// System.out.println(identificadorCarona);
			carona.setId(identificadorCarona);

			UsuarioDomain usuario = persistenciaBD.getUsuarioBD().getUsuario(
					sessao.getIdUsuario());
			usuario.adicionarCarona(carona);
			persistenciaBD.getUsuarioBD().update(usuario);

			notificarCarona(data, carona);

			return carona.getId();
		} else {
			throw new ECaronaException(MensagensDeErro.SESSAO_INEXISTENTE);
		}

	}

	private void notificarCarona(String data, CaronaDomain carona) {
		for (InteresseDomain interesse : persistenciaBD.getInteresseBD().list()) {
			if (interesse.getOrigem().equals(carona.getOrigem())
					&& interesse.getDestino().equals(carona.getDestino())) {
				if (interesse.getData().isEmpty()) {
					if (interesse.getHorarioInicio().isEmpty()
							&& interesse.getHorarioFim().isEmpty()) {
						interesse.adcionaNotificacao(carona.getId());
					} else if (interesse.getHorarioFim().isEmpty()) {
						String[] horaInicio = interesse.getHorarioInicio()
								.split(":");
						String[] horaCarona = carona.getHora().split(":");
						if (Integer.parseInt(horaInicio[0]) <= Integer
								.parseInt(horaCarona[0])
								&& Integer.parseInt(horaInicio[1]) <= Integer
										.parseInt(horaCarona[1])) {
							interesse.adcionaNotificacao(carona.getId());
						}
					} else if (interesse.getHorarioInicio().isEmpty()) {
						String[] horaFim = interesse.getHorarioFim().split(":");
						String[] horaCarona = carona.getHora().split(":");
						if (Integer.parseInt(horaFim[0]) >= Integer
								.parseInt(horaCarona[0])
								&& Integer.parseInt(horaFim[1]) <= Integer
										.parseInt(horaCarona[1])) {
							interesse.adcionaNotificacao(carona.getId());
						}
					} else {
						String[] horaInicio = interesse.getHorarioInicio()
								.split(":");
						String[] horaCarona = carona.getHora().split(":");
						String[] horaFim = interesse.getHorarioFim().split(":");
						if (Integer.parseInt(horaInicio[0]) <= Integer
								.parseInt(horaCarona[0])
								&& Integer.parseInt(horaInicio[1]) <= Integer
										.parseInt(horaCarona[1])
								&& Integer.parseInt(horaFim[0]) >= Integer
										.parseInt(horaCarona[0])
								&& Integer.parseInt(horaFim[1]) <= Integer
										.parseInt(horaCarona[1])) {

							interesse.adcionaNotificacao(carona.getId());
						}
					}
				} else {
					if (interesse.getData().equals(data)) {
						if (interesse.getHorarioInicio().isEmpty()
								&& interesse.getHorarioFim().isEmpty()) {
							interesse.adcionaNotificacao(carona.getId());
						} 
						else if (interesse.getHorarioFim().isEmpty()) {
							String[] horaInicio = interesse.getHorarioInicio()
									.split(":");
							String[] horaCarona = carona.getHora().split(":");
							if (Integer.parseInt(horaInicio[0]) <= Integer
									.parseInt(horaCarona[0])
									&& Integer.parseInt(horaInicio[1]) <= Integer
											.parseInt(horaCarona[1])) {
								interesse.adcionaNotificacao(carona.getId());
							}
						} 
						else if (interesse.getHorarioInicio().isEmpty()) {
							String[] horaFim = interesse.getHorarioFim().split(
									":");
							String[] horaCarona = carona.getHora().split(":");
							if (Integer.parseInt(horaFim[0]) >= Integer
									.parseInt(horaCarona[0])
									&& Integer.parseInt(horaFim[1]) <= Integer
											.parseInt(horaCarona[1])) {
								interesse.adcionaNotificacao(carona.getId());
							}
						} else {
							String[] horaInicio = interesse.getHorarioInicio()
									.split(":");
							String[] horaCarona = carona.getHora().split(":");
							String[] horaFim = interesse.getHorarioFim().split(
									":");
							if (Integer.parseInt(horaInicio[0]) <= Integer
									.parseInt(horaCarona[0])
									&& Integer.parseInt(horaInicio[1]) <= Integer
											.parseInt(horaCarona[1])
									&& Integer.parseInt(horaFim[0]) >= Integer
											.parseInt(horaCarona[0])
									&& Integer.parseInt(horaFim[1]) <= Integer
											.parseInt(horaCarona[1])) {

								interesse.adcionaNotificacao(carona.getId());
							}
						}
					}
				}
				persistenciaBD.getInteresseBD().update(interesse);
			}
		}
	}

	public String cadastrarCaronaMunicipal(String idSessao, String origem,
			String destino, String cidade, String data, String hora,
			String vagas) throws ECaronaException {
		if (vagas == null || vagas.equals(""))
			throw new ECaronaException(MensagensDeErro.VAGA_INVALIDA);
		if (VerificadorString.ContemLetra(vagas))
			throw new ECaronaException(MensagensDeErro.VAGA_INVALIDA);

		if (idSessao == null || idSessao.equals(""))
			throw new ECaronaException(MensagensDeErro.SESSAO_INVALIDA);

		SessaoDomain sessao = persistenciaBD.getSessaoBD().getSessao(idSessao);
		if (sessao != null) {
			sufixoIdCarona++;

			CaronaDomain carona = new CaronaDomain();

			carona.setOrigem(origem);

			carona.setDestino(destino);

			carona.setCidade(cidade);

			carona.setData(data);

			carona.setIdSessao(idSessao);

			carona.setVagas(Integer.valueOf(vagas));

			carona.setHora(hora);

			carona.setUsuarioLogin(sessao.getIdUsuario());

			carona.foiConcluida(false);

			carona.setTranquila("");

			carona.setNaoFuncionou("");

			carona.ehMunicipal(true);
			
			carona.setPreferencial(false);

			String identificadorCarona = "carona"
					+ String.valueOf(sufixoIdCarona);
			// System.out.println(identificadorCarona);
			carona.setId(identificadorCarona);

			UsuarioDomain usuario = persistenciaBD.getUsuarioBD().getUsuario(
					sessao.getIdUsuario());
			usuario.adicionarCarona(carona);
			persistenciaBD.getUsuarioBD().update(usuario);

			// Notificar carona municipal [ainda não testado]
			// notificarCarona(data, carona);

			return carona.getId();
		} else {
			throw new ECaronaException(MensagensDeErro.SESSAO_INEXISTENTE);
		}

	}

	/**
	 * Localizar uma carona do usuário, através do índice da carona no conjunto
	 * de caronas
	 * 
	 * @param idSessao
	 *            ID da sessão do usuário que se deseja obter a carona
	 * @param index
	 *            i-ésima posição do elemento carona no conjunto de caronas
	 * @return ID da carona solicitada
	 */
	public String localizarCaronaUsuarioPorIndex(String idSessao, String index) {

		int valorIndex = Integer.parseInt(index);
		SessaoDomain sessao = persistenciaBD.getSessaoBD().getSessao(idSessao);
		ArrayList<CaronaDomain> caronas = persistenciaBD.getUsuarioBD()
				.getUsuario(sessao.getIdUsuario()).getCaronas();
		// caronas.sort((p1, p2) -> p1.getId().compareTo(p2.getId()));

		return caronas.get(valorIndex - 1).getId();
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

		SessaoDomain sessao = persistenciaBD.getSessaoBD().getSessao(idSessao);

		ArrayList<String> allID = new ArrayList<String>();
		String idCarona = "{";
		// System.out.println(caronaBD.values().);
		if (origem.equals("") && destino.equals("")) {
			for (CaronaDomain carona : persistenciaBD.getUsuarioBD()
					.getUsuario(sessao.getIdUsuario()).getCaronas()) {
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

	public String localizarCaronaMunicipal(String idSessao, String origem,
			String destino, String cidade) throws ECaronaException {

		if (origem.equals("-") || origem.equals("()") || origem.equals("!")
				|| origem.equals("!?"))
			throw new ECaronaException(MensagensDeErro.ORIGEM_INVALIDA);
		if (destino.equals(".") || destino.equals("()") || destino.equals("!")
				|| destino.equals("!?"))
			throw new ECaronaException(MensagensDeErro.DESTINO_INVALIDO);
		if (cidade == null || cidade.isEmpty())
			throw new ECaronaException(MensagensDeErro.CIDADE_INEXISTENTE);

		ArrayList<CaronaDomain> caronas = new ArrayList<CaronaDomain>();
		ArrayList<String> allID = new ArrayList<String>();
		String idCarona = "{";

		for (CaronaDomain carona : persistenciaBD.getCaronaBD().list()) {
			if (carona.getCidade() != null) {
				if (carona.getCidade().equals(cidade)) {
					caronas.add(carona);
				}
			}
		}
		if (caronas.size() > 0) {
			if (origem.equals("") && destino.equals("")) {
				for (CaronaDomain carona : caronas) {
					allID.add(carona.getId());
				}

				allID.sort(null);
				idCarona = allID.toString();

				idCarona = idCarona.substring(1, idCarona.length() - 1);
				idCarona = "{" + idCarona;
				idCarona = idCarona.replace(" ", "");

			} else if (origem.equals("")) {
				for (CaronaDomain carona : caronas) {
					if (carona.getDestino().equals(destino)) {
						idCarona += carona.getId();
						idCarona += ",";
					}
				}
				idCarona = idCarona.substring(0, idCarona.length() - 1);
			} else if (destino.equals("")) {
				for (CaronaDomain carona : caronas) {
					if (carona.getOrigem().equals(origem)) {
						idCarona += carona.getId();
						idCarona += ",";
					}
				}
				idCarona = idCarona.substring(0, idCarona.length() - 1);
			} else {
				for (CaronaDomain carona : caronas) {
					if (carona.getOrigem().equals(origem)
							&& carona.getDestino().equals(destino)) {
						idCarona += carona.getId();
						idCarona += ",";
					}
				}
				if (idCarona.length() > 2)
					idCarona = idCarona.substring(0, idCarona.length() - 1);
			}
		} else {
			throw new ECaronaException(MensagensDeErro.CIDADE_INEXISTENTE);
		}

		return idCarona + "}";
	}

	/**
	 * Pegar um atributo específico de uma carona
	 * 
	 * @param idCarona
	 *            ID da carona
	 * @param atributoCarona
	 *            Atributos válidos: origem | destino | data | vagas
	 * @return O valor do atributo especificado
	 * @throws Exception
	 *             caso a carona não exista na base ou o atributo não seja
	 *             válido
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

			case "foi concluida":
				return Boolean.toString(carona.foiConcluida());

			case "nao funcionou":
				return String.valueOf(carona.getNaoFuncionou().split(",").length);

			case "ehMunicipal":
				return Boolean.toString(carona.ehMunicipal());

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
	 * @throws Exception
	 *             caso os campos sejam nulos ou vazios, caso o id da carona não
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
	
	public CaronaDomain getCarona(String idCarona) throws ECaronaException {
		if (idCarona == null)
			throw new ECaronaException(MensagensDeErro.CARONA_INVALIDA);
		if (idCarona.equals(""))
			throw new ECaronaException(MensagensDeErro.CARONA_INEXISTENTE);
		CaronaDomain carona = persistenciaBD.getCaronaBD().getCarona(idCarona);
		if (carona == null)
			throw new ECaronaException(MensagensDeErro.CARONA_INEXISTENTE);
		return carona;

	}
	

	/**
	 * Listar todas as caronas registradas no sistema
	 * 
	 * @return Lista de CaronaDomain
	 */
	public ArrayList<CaronaDomain> getTodasCaronas() {
		ArrayList<CaronaDomain> caronas = new ArrayList<CaronaDomain>(
				persistenciaBD.getCaronaBD().list());
		return caronas;
	}

	/**
	 * Buscar Caronas no banco de dados, que contenham a palavra chave
	 * 
	 * @param palavraChave
	 *            critério da busca
	 * @param filtro
	 *            onde buscar: origem, destino, intermunicipal, municipal. Caso
	 *            seja vazio, a função procura a palavra chave em todos os
	 *            campos de cada carona
	 * @return Uma lista de carona que satisfaz a pesquisa
	 */
	public List<CaronaDomain> getCaronasPorFiltro(String palavraChave,
			String filtro) {
		List<CaronaDomain> caronas = new ArrayList<CaronaDomain>();

		switch (filtro) {
		case "origem":
			break;
		case "destino":
			break;
		case "intermunicipal":
			break;
		case "municipal":
			break;
		default:
			for (CaronaDomain carona : persistenciaBD.getCaronaBD().list()) {
				if (carona.getOrigem().equals(palavraChave)) {
					caronas.add(carona);
				} else if (carona.getDestino().equals(palavraChave)) {
					caronas.add(carona);
				} else if (carona.getData().equals(palavraChave)) {
					caronas.add(carona);
				}
			}
		}
		return caronas;
	}

	public ArrayList<CaronaDomain> getCaronasSessao(String idSessao) {
		SessaoDomain sessao = persistenciaBD.getSessaoBD().getSessao(idSessao);

		ArrayList<CaronaDomain> allCaronas = new ArrayList<CaronaDomain>();
		for (CaronaDomain carona : persistenciaBD.getUsuarioBD()
				.getUsuario(sessao.getIdUsuario()).getCaronas()) {
			allCaronas.add(carona);
		}

		return allCaronas;
	}
	
	public void adicionarQualificacao(CaronaDomain carona, String idPassageiro, String classificacao){
		switch(classificacao){
		case "positiva":
			if(carona.getTranquila() == null){
				carona.setTranquila(idPassageiro);
			}
			else{
				carona.setTranquila(carona.getTranquila() + ", "  + idPassageiro);
			}
			break;
		case "negativa":
			if(carona.getNaoFuncionou() == null){
				carona.setNaoFuncionou(idPassageiro);
			}
			else{
				carona.setNaoFuncionou(carona.getNaoFuncionou() + ", "  + idPassageiro);
			}
			break;
		}
		persistenciaBD.getCaronaBD().update(carona);
	}
	
	public void definirCaronaPreferencial(String idCarona) {
		CaronaDomain carona = persistenciaBD.getCaronaBD().getCarona(idCarona);
		carona.setPreferencial(true);
		UsuarioDomain usuario = persistenciaBD.getUsuarioBD().getUsuario(carona.getUsuarioLogin());
		removeCarona(carona, usuario);
		usuario.adicionarCarona(carona);
		persistenciaBD.getCaronaBD().update(carona);
		
	}
	
	public boolean isCaronaPreferencial(String idCarona){
		CaronaDomain carona = persistenciaBD.getCaronaBD().getCarona(idCarona);
		return carona.isPreferencial();
	}
	
	public String getUsuariosPreferenciaisCarona(String idCarona){
		String idUsuarios = "[";		
		CaronaDomain carona = persistenciaBD.getCaronaBD().getCarona(idCarona);
		UsuarioDomain usuario = persistenciaBD.getUsuarioBD().getUsuario(carona.getUsuarioLogin());
		
		if(carona.isPreferencial()){
			for(CaronaDomain c : usuario.getCaronas()){
				if(c.foiConcluida() && c.getTranquila().length() > 0){
					if(!idUsuarios.contains(c.getTranquila())){ 
						idUsuarios += c.getTranquila();
					}
				}
			}
		}
		idUsuarios = idUsuarios.replaceAll(" ", "");
		idUsuarios += "]";
		return idUsuarios;
	}
	
	public void zerarBase(){
		persistenciaBD.getCaronaBD().excluirTudo();
	}
	
	private void removeCarona(CaronaDomain carona, UsuarioDomain usuario) {
		for(CaronaDomain c : usuario.getCaronas()){
			if(c.getId().equals(carona.getId())){
				usuario.getCaronas().remove(c);
				break;
			}
		}
	}


}
