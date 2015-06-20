package com.br.uepb.business;

import com.br.uepb.constants.ECaronaException;

public class FacadeBusiness {

	private UsuarioBusiness gerenciadorDeUsuario;
	private SessaoBusiness gerenciadorDeSessao;
	private CaronaBusiness gerenciadorDeCarona;
	private PontoDeEncontroBusiness gerenciadorDePontoDeEncontro;
	private SolicitacaoBusiness gerenciadorDeSolicitacao;
	private VisualizadorPerfil controladorPerfil;

	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws Exception {
		gerenciadorDeUsuario.criarUsuario(login, senha, nome, endereco, email);
	}

	public String abrirSessao(String login, String senha) throws Exception {
		String sessaoId = gerenciadorDeSessao.abrirSessao(login, senha);

		return sessaoId;
	}

	public String getAtributoUsuario(String login, String atributo)
			throws Exception {
		return gerenciadorDeUsuario.getAtributoUsuario(login, atributo);
	}

	public String cadastrarCarona(String sessao, String origem, String destino,
			String data, String hora, String vagas) throws Exception {
		return gerenciadorDeCarona.cadastrarCarona(sessao, origem, destino,
				data, hora, vagas);
	}

	public String localizarCarona(String sessao, String origem, String destino)
			throws Exception {
		return gerenciadorDeCarona.localizarCarona(sessao, origem, destino);
	}

	public String getAtributoCarona(String idCarona, String atributoCarona)
			throws Exception {

		return gerenciadorDeCarona.getAtributoCarona(idCarona, atributoCarona);
	}

	public String getTrajeto(String idCarona) throws Exception {
		return gerenciadorDeCarona.getTrajetoCarona(idCarona);
	}

	public String getCarona(String idCarona) throws Exception {
		return gerenciadorDeCarona.getCaronaInfo(idCarona);
	}

	public String getCaronaUsuario(String idSessao, String indexCarona)
			throws ECaronaException {
		return gerenciadorDeCarona.localizarCaronaUsuarioPorIndex(idSessao,
				indexCarona);
	}

	public String getTodasCaronasUsuario(String idSessao)
			throws ECaronaException {
		return gerenciadorDeCarona.localizarCarona(idSessao, "", "");
	}

	public void encerrarSessao(String loginUsuario) {
		gerenciadorDeSessao.encerrarSessao(loginUsuario);
	}

	public String sugerirPontoEncontro(String idSessao, String idCarona,
			String pontos) throws ECaronaException {

		String[] pontosSugeridos = pontos.split(";");
		return gerenciadorDePontoDeEncontro.sugerirPontoEncontro(idSessao, idCarona,
				pontosSugeridos);
	}

	public String solicitarVaga(String idSessao, String idCarona) {
		return gerenciadorDeSolicitacao.solicitarVaga(idSessao, idCarona);
	}

	public String solicitarVagaPontoEncontro(String idSessao, String idCarona,
			String ponto) {
		return gerenciadorDeSolicitacao
				.solicitarVaga(idSessao, idCarona, ponto);
	}

	public void responderSugestaoPontoEncontro(String idSessao,
			String idCarona, String idSegestao, String pontos)
			throws ECaronaException {

		String[] pontosSugeridos = pontos.split(";");
		gerenciadorDePontoDeEncontro.responderSugestaoPontoEncontro(idSessao,
				idCarona, idSegestao, pontosSugeridos);
	}

	public String getAtributoSolicitacao(String idSolicitacao, String atributo)
			throws ECaronaException {
		return gerenciadorDeSolicitacao.getAtributoSolicitacao(idSolicitacao,
				atributo);
	}

	public void aceitarSolicitacao(String idSessao, String idSolicitacao)
			throws ECaronaException {
		gerenciadorDeSolicitacao.aceitarSolicitacao(idSessao, idSolicitacao);
	}

	public void aceitarSolicitacaoPontoEncontro(String idSessao,
			String idSolicitacao) throws ECaronaException {

		gerenciadorDePontoDeEncontro.aceitarPontoDeEncontro(idSessao,
				idSolicitacao);
	}

	public void rejeitarSolicitacao(String idSessao, String idSolicitacao)
			throws ECaronaException {
		gerenciadorDeSolicitacao.rejeitarSolicitacao(idSessao, idSolicitacao);
	}

	public void desistirRequisicao(String idSessao, String idCarona,
			String idSolicitacao) throws ECaronaException {

		gerenciadorDeSolicitacao.desistirRequisicao(idSessao, idCarona,
				idSolicitacao);
	}

	public String getSolicitacoesConfirmadas(String idSessao, String idCarona) {
		return gerenciadorDeSolicitacao.getSolicitacoesConfirmadas(idSessao,
				idCarona);
	}

	public String getSolicitacoesPendentes(String idSessao, String idCarona) {
		return gerenciadorDeSolicitacao.getSolicitacoesPendentes(idSessao,
				idCarona);
	}

	public String getPontosSugeridos(String idSessao, String idCarona)
			throws ECaronaException {
		return gerenciadorDePontoDeEncontro.getPontosSugeridos(idSessao,
				idCarona);
	}
	
	public String getPontosEncontro(String idSessao, String idCarona){
		return gerenciadorDePontoDeEncontro.getPontosConfirmados(idSessao, idCarona);
	}

	public String getAtributoPerfil(String login, String atributo)
			throws ECaronaException {

		return controladorPerfil.getAtributoPerfil(login, atributo);
	}

	public String visualizarPerfil(String idSessao, String login)
			throws ECaronaException {

		return controladorPerfil.visualizarPerfil(idSessao, login);
	}
	
	public void reviewVagaEmCarona(String idSessao, String idCarona, String loginCaroneiro, String review) 
			throws ECaronaException{
		controladorPerfil.reviewVagaEmCarona(idSessao, idCarona, loginCaroneiro, review);
	}
	
	public void reviewCarona(String idSessao, String idCarona, String review)
			throws ECaronaException{
		controladorPerfil.reviewCarona(idSessao, idCarona, review);
	}
	
	public String cadastrarCaronaMunicipal(String idSessao, String origem, String destino,
			String cidade, String data, String hora, String vagas) 
					throws ECaronaException{
		
		return gerenciadorDeCarona.cadastrarCaronaMunicipal(idSessao, origem, destino, cidade, data, hora, vagas);
	}
	
	public String localizarCaronaMunicipal(String idSessao, String cidade) 
			throws ECaronaException{
		return gerenciadorDeCarona.localizarCaronaMunicipal(idSessao, "", "", cidade);
	}
	
	public String localizarCaronaMunicipal(String idSessao, String cidade, String origem,
			String destino) throws ECaronaException{
		
		return gerenciadorDeCarona.localizarCaronaMunicipal(idSessao, origem, destino, cidade);
	}
	
	public String cadastrarInteresse(String idSessao, String origem, String destino,
			String data, String horaInicio, String horaFim){
		
		return "";
	}
	
	public void verificarMensagensPerfil(String idSessao){
		
	}

	public void zerarSistema() {
		gerenciadorDeUsuario = new UsuarioBusiness();
		gerenciadorDeSessao = new SessaoBusiness();
		gerenciadorDeCarona = new CaronaBusiness();
		gerenciadorDePontoDeEncontro = new PontoDeEncontroBusiness();
		gerenciadorDeSolicitacao = new SolicitacaoBusiness();
		controladorPerfil = new VisualizadorPerfil();
		gerenciadorDeUsuario.zerarBase();
		gerenciadorDeSessao.zerarBase();
		gerenciadorDeSolicitacao.zerarBase();
		gerenciadorDePontoDeEncontro.zerarBase();

	}

	public void reiniciarSistema() {
		encerrarSistema();
	}

	public void encerrarSistema() {

		if (gerenciadorDeUsuario != null) {
			gerenciadorDeUsuario = new UsuarioBusiness();
		}

		if (gerenciadorDeSessao != null) {
			gerenciadorDeSessao = new SessaoBusiness();
		}

		if (gerenciadorDeCarona != null) {
			gerenciadorDeCarona = new CaronaBusiness();
		}
		if (gerenciadorDePontoDeEncontro != null) {
			gerenciadorDePontoDeEncontro = new PontoDeEncontroBusiness();
		}
		if (gerenciadorDeSolicitacao != null) {
			gerenciadorDeSolicitacao = new SolicitacaoBusiness();
		}
		if (controladorPerfil != null) {
			controladorPerfil = new VisualizadorPerfil();
		}

	}

}
