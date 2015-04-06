package com.br.uepb.business;

import java.security.IdentityScope;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.br.uepb.constants.MensagensDeErro;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.domain.UsuarioDomain;
import com.sun.xml.bind.v2.schemagen.xmlschema.List;

public class UsuarioBusiness {

	private Map<String, UsuarioDomain> usuarioBD;
	private Map<String, SessaoDomain> sessaoBD;
	private Map<String, CaronaDomain> caronaBD;
	private int sufixoIdCarona;

	public UsuarioBusiness() {
		usuarioBD = new HashMap<String, UsuarioDomain>();
		sessaoBD = new HashMap<String, SessaoDomain>();
		caronaBD = new HashMap<String, CaronaDomain>();
		sufixoIdCarona = 0;
	}

	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws Exception {

		UsuarioDomain usuario = new UsuarioDomain();

		usuario.setLogin(login);

		if (usuarioBD.containsKey(login)) {
			throw new Exception(MensagensDeErro.EXISTE_USUARIO_C_LOGIN);
		}

		usuario.setSenha(senha);

		usuario.setNome(nome);

		usuario.setEndereco(endereco);

		usuario.setEmail(email);

		for (UsuarioDomain u : usuarioBD.values()) {
			if (u.getEmail().equals(email)) {
				throw new Exception(MensagensDeErro.EXISTE_USUARIO_C_EMAIL);
			}
		}

		usuarioBD.put(login, usuario);

	}

	public String abrirSessao(String login, String senha) throws Exception {

		UsuarioDomain usuario = usuarioBD.get(login);

		if (login == null || senha == null)
			throw new Exception(MensagensDeErro.LOGIN_INVALIDO);

		if (login.equals("") || senha.equals(""))
			throw new Exception(MensagensDeErro.LOGIN_INVALIDO);

		if (usuario == null)
			throw new Exception(MensagensDeErro.USUARIO_INEXISTENTE);

		if (!usuario.getSenha().equals(senha))
			throw new Exception(MensagensDeErro.LOGIN_INVALIDO);

		Date time = new Date();
		String idSessao = Long.toString(time.getTime());

		SessaoDomain sessao = new SessaoDomain(idSessao, usuario);

		sessaoBD.put(idSessao, sessao);

		return idSessao;
	}

	public String getAtributoUsuario(String login, String atributo)
			throws Exception {

		UsuarioDomain usuario;

		if (login == null || login.equals(""))
			throw new Exception(MensagensDeErro.LOGIN_INVALIDO);

		if (usuarioBD.containsKey(login)) {
			usuario = usuarioBD.get(login);

			if (atributo == "" || atributo == null)
				throw new Exception(MensagensDeErro.ATRIBUTO_INVALIDO);

			switch (atributo) {

			case "":
				throw new Exception(MensagensDeErro.ATRIBUTO_INVALIDO);

			case "nome":
				return usuario.getNome();

			case "endereco":
				return usuario.getEndereco();

			case "email":
				return usuario.getEmail();

			default:
				throw new Exception(MensagensDeErro.ATRIBUTO_INEXISTENTE);

			}
		} else {

			throw new Exception(MensagensDeErro.USUARIO_INEXISTENTE);
		}

	}

	public boolean verificaLoginExistente(String login) {
		// verifica se existe no BD o login informado
		UsuarioDomain usuario = usuarioBD.get(login);
		if (usuario != null) {
			return true;
		} else {
			return false;

		}

	}

	public SessaoDomain getSessaoPorId(String idSessao) {

		return sessaoBD.get(idSessao);
	}

	public String cadastrarCarona(String idSessao, String origem,
			String destino, String data, String hora, Integer vagas)
			throws Exception {

		if (idSessao == null || idSessao.equals(""))
			throw new Exception(MensagensDeErro.SESSAO_INVALIDA);

		if (sessaoBD.containsKey(idSessao)) {
			sufixoIdCarona++;
			CaronaDomain carona = new CaronaDomain();

			carona.setOrigem(origem);

			carona.setDestino(destino);

			carona.setData(data);

			carona.setVagas(vagas);

			carona.setHora(hora);

			String identificadorCarona = "carona"
					+ String.valueOf(sufixoIdCarona) + "ID";
			// System.out.println(identificadorCarona);
			carona.setId(identificadorCarona);

			caronaBD.put(identificadorCarona, carona);

			return carona.getId();
		} else {
			throw new Exception(MensagensDeErro.SESSAO_INEXISTENTE);
		}

	}

	public String localizarCarona(String idSessao, String origem, String destino) {

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
				}
			}
		}

		return idCarona + "}";
	}

	public String getAtributoCarona(String idCarona, String atributoCarona)
			throws Exception {
		CaronaDomain carona;

		if (idCarona == null || idCarona == "") {
			throw new Exception(MensagensDeErro.IDENTIFICADOR_CARONA_INVALIDO);
		}

		if (caronaBD.containsKey(idCarona)) {
			carona = caronaBD.get(idCarona);

			if (atributoCarona == "" || atributoCarona == null)
				throw new Exception(MensagensDeErro.ATRIBUTO_INVALIDO);

			switch (atributoCarona) {
			case "":
				throw new Exception(MensagensDeErro.ATRIBUTO_INVALIDO);

			case "origem":
				return carona.getOrigem();

			case "destino":
				return carona.getDestino();

			case "data":
				return carona.getData();

			case "vagas":
				return Integer.toString(carona.getVagas());

			default:
				throw new Exception(MensagensDeErro.ATRIBUTO_INEXISTENTE);
			}

		}

		else
			throw new Exception(MensagensDeErro.ITEM_INEXISTENTE);

	}

	public String getTrajetoCarona(String idCarona) {
		CaronaDomain carona = caronaBD.get(idCarona);
		return carona.getOrigem() + " - " + carona.getDestino();
	}

	public String getCaronaInfo(String idCarona) {
		CaronaDomain carona = caronaBD.get(idCarona);
		return carona.toString();

	}

}
