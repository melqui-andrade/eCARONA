package com.br.uepb.business;
import easyaccept.*;

public class FacadeBusiness {
	
	private UsuarioBusiness gerenciadorDeUsuario = new UsuarioBusiness();
	private SessaoBusiness gerenciadorDeSessao = new SessaoBusiness();
	
	
	public void criarUsuario(String login, String senha, String nome, String endereco, String email) throws Exception{	
		gerenciadorDeUsuario.criarUsuario(login, senha, nome, endereco, email);
	}
	
	public String abrirSessao(String login, String senha) throws Exception{
		String sessaoId = gerenciadorDeUsuario.abrirSessao(login, senha);
		
		return sessaoId;
	}
	
	public String getAtributoUsuario(String login, String atributo) throws Exception{
		return gerenciadorDeUsuario.getAtributoUsuario(login, atributo);
	}
	
	public String cadastrarCarona(String sessao, String origem, String destino, String data,
			String hora, int vagas) throws Exception{
		return gerenciadorDeUsuario.cadastrarCarona(sessao, origem, destino, data, hora, vagas);
	}
	
	public String localizarCarona(String sessao, String origem, String destino){
		return gerenciadorDeUsuario.localizarCarona(sessao, origem, destino);
	}
	
	public String getAtributoCarona(String idCarona, String atributoCarona) throws Exception{
		
		return gerenciadorDeUsuario.getAtributoCarona(idCarona, atributoCarona);		
	}
	
	public String getTrajeto(String idCarona){
		return gerenciadorDeUsuario.getTrajetoCarona(idCarona);
	}
	
	public String getCarona(String idCarona){
		return gerenciadorDeUsuario.getCaronaInfo(idCarona);
	}
		
	public void zerarSistema(){
		if(gerenciadorDeUsuario != null){
			gerenciadorDeUsuario = new UsuarioBusiness();
		}
	}
	
	public void encerrarSistema(){
		
	}
		
	public static void main(String[] args) {
		args = new String[] {"com.br.uepb.business.FacadeBusiness","./src/test/resources/easyAcceptFiles/US02.txt"};
		EasyAccept.main(args);
		
	}
	

}
