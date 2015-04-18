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
			String hora, String vagas) throws Exception{
		return gerenciadorDeUsuario.cadastrarCarona(sessao, origem, destino, data, hora, vagas);
	}
	
	public String localizarCarona(String sessao, String origem, String destino) throws Exception{
		return gerenciadorDeUsuario.localizarCarona(sessao, origem, destino);
	}
	
	public String getAtributoCarona(String idCarona, String atributoCarona) throws Exception{
		
		return gerenciadorDeUsuario.getAtributoCarona(idCarona, atributoCarona);		
	}
	
	public String getTrajeto(String idCarona) throws Exception{
		return gerenciadorDeUsuario.getTrajetoCarona(idCarona);
	}
	
	public String getCarona(String idCarona) throws Exception{
		return gerenciadorDeUsuario.getCaronaInfo(idCarona);
	}
	
	public void encerrarSessao(String loginUsuario){
		gerenciadorDeUsuario.encerrarSessao(loginUsuario);
	}
	
	public String sugerirPontoEncontro(String idSessao, String idCarona, String pontos){
		String[] pontosSugeridos = pontos.split(";");
		return gerenciadorDeUsuario.sugerirPontoEncontro(idSessao, idCarona, pontosSugeridos);
	}
	
	public String solicitarVagaPontoEncontro(String idSessao, String idCarona, String ponto){
		return null;
	}
		
	public void responderSugestaoPontoEncontro(String idSessao, String idCarona, String idSegestao, String pontos){
		
	}
	
	public String getAtributoSolicitacao(String idSocilitacao, String atributo){
		return "";
	}
	
	public void aceitarSolicitacaoPontoEncontro(String idSessao, String idSolicitacao){
		
	}
	
	public void zerarSistema(){
		if(gerenciadorDeUsuario != null){
			gerenciadorDeUsuario = new UsuarioBusiness();
		}
	}
	
	
	public void encerrarSistema(){
		
	}	
	
	

}
