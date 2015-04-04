package com.br.uepb.business;
import easyaccept.*;

public class FacadeBusiness {
	
	private UsuarioBusiness gerenciadorDeUsuario = new UsuarioBusiness();
	
	public void criarUsuario(String login, String senha, String nome, String endereco, String email) throws Exception{	
		gerenciadorDeUsuario.criarUsuario(login, senha, nome, endereco, email);
	}
	
	public String abrirSessao(String login, String senha) throws Exception{
		return gerenciadorDeUsuario.abrirSessao(login, senha);
	}
	
	public String getAtributoUsuario(String login, String atributo) throws Exception{
		return gerenciadorDeUsuario.getAtributoUsuario(login, atributo);
	}
		
		
	public static void main(String[] args) {
		args = new String[] {"com.br.uepb.business.FacadeBusiness","./src/test/resources/easyAcceptFiles/US02.txt"};
		EasyAccept.main(args);
		
	}

}