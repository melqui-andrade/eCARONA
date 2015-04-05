package com.br.uepb.business;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.html.parser.Parser;

import net.sf.cglib.core.Converter;

import com.br.uepb.constants.MensagensDeErro;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.domain.UsuarioDomain;

public class UsuarioBusiness {

	public UsuarioDomain usuarioBuffer;
	public Map<String, UsuarioDomain> usuarioBD;
	public Map<String, SessaoDomain> sessaoBD;
	public Map<String, CaronaDomain> caronaBD;

	public UsuarioBusiness() {
		usuarioBD = new HashMap<String, UsuarioDomain>();
		sessaoBD = new HashMap<String, SessaoDomain>();
		caronaBD = new HashMap<String, CaronaDomain>();
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

		CaronaDomain carona = new CaronaDomain();
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
	
	public SessaoDomain getSessaoPorId(String idSessao){
		
		return sessaoBD.get(idSessao);
	}

	public String cadastrarCarona(String idSessao, String origem, String destino, String data,
			String hora, int vagas) {

		CaronaDomain carona = new CaronaDomain();

		carona.setOrigem(origem);

		carona.setDestino(destino);

		carona.setData(data);

		carona.setVagas(vagas);

		String identificadorCarona = data + "_" + vagas + "_" + origem + "_"
				+ destino;

		caronaBD.put(identificadorCarona, carona);
		
		return String.valueOf(carona.getId());

	}

	public String localizarCarona(String idSessao, String origem, String destino){
		String idCarona = "{}";
		for(CaronaDomain carona : caronaBD.values()){
			if(carona.getOrigem() == origem && carona.getDestino() == destino){
				idCarona = carona.getId();
			}
		}
		return idCarona;
	}
	
}
