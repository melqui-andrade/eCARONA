package com.br.uepb.business;
import easyaccept.*;

public class FacadeBusiness {
	
	private UsuarioBusiness gerenciadorDeUsuario = new UsuarioBusiness();
	private CaronaBusiness gerenciadorDeCarona = new CaronaBusiness();
	private SessaoBusiness gerenciadorDeSessao;
	
	
	public void criarUsuario(String login, String senha, String nome, String endereco, String email) throws Exception{	
		gerenciadorDeUsuario.criarUsuario(login, senha, nome, endereco, email);
	}
	
	public String abrirSessao(String login, String senha) throws Exception{
		String sessaoId = gerenciadorDeUsuario.abrirSessao(login, senha);
		gerenciadorDeSessao = new SessaoBusiness(gerenciadorDeUsuario.getSessaoPorId(sessaoId));
		return sessaoId;
	}
	
	public String getAtributoUsuario(String login, String atributo) throws Exception{
		return gerenciadorDeUsuario.getAtributoUsuario(login, atributo);
	}
	
	public String cadastrarCarona(String origem, String destino, String data,
			String hora, int vagas){
		return gerenciadorDeCarona.cadastrarCarona(origem, destino, data, hora, vagas);
	}
	
	public String localizarCarona(String origem, String destino){
		return gerenciadorDeSessao.localizarCarona(origem, destino);
	}
		
		
	public static void main(String[] args) {
		args = new String[] {"com.br.uepb.business.FacadeBusiness","./src/test/resources/easyAcceptFiles/US02.txt"};
		EasyAccept.main(args);
		
	}

}
