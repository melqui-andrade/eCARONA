package control;

import easyaccept.EasyAccept;

public class ControlFacade {

	private GerenciadorDeUsuario gerenciadorDeUsuario = new GerenciadorDeUsuario();
		
	public void criarUsuario(String login, String senha, String nome, String endereco, String email) throws Exception{	
		gerenciadorDeUsuario.criarUsuario(login, senha, nome, endereco, email);
	}
	
	public int abrirSessao(String login, String senha) throws Exception{
		return gerenciadorDeUsuario.abrirSessao(login, senha);
	}
	
	public String getAtributoUsuario(String login, String atributo) throws Exception{
		return gerenciadorDeUsuario.getAtributoUsuario(login, atributo);
	}
		
		
	public static void main(String[] args) {
		args = new String[] {"control.ControlFacade","EasyAcceptFiles/US01.txt"};
		EasyAccept.main(args);
		
	}
}
