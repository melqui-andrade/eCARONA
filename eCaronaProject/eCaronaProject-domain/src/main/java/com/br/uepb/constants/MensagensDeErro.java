package com.br.uepb.constants;

/**
 * Classe com todas as mensagens de erro do sistema
 * Não necessita de instanciação
 * @author Sidney
 *
 */
public class MensagensDeErro {
	
	//ERROS GENERICOS
		public static String ATRIBUTO_INVALIDO = "Atributo inválido";
		public static String ATRIBUTO_INEXISTENTE = "Atributo inexistente";
		
	//ERROS DA CLASSE USUARIO
		public static String LOGIN_INVALIDO = "Login inválido";
		public static String NOME_INVALIDO = "Nome inválido";
		public static String EMAIL_INVALIDO = "Email inválido";
		public static String SENHA_INVALIDA = "Senha inválido";
		public static String ENDERECO_INVALIDO = "Endereço inválido";
		public static String EXISTE_USUARIO_C_EMAIL = "Já existe um usuário com este email";
		public static String EXISTE_USUARIO_C_LOGIN = "Já existe um usuário com este login";
		public static String USUARIO_INEXISTENTE = "Usuário inexistente";
		
	//ERROS DA CLASSE CARONA	
		public static String SESSAO_INVALIDA = "Sessão inválida";
		public static String SESSAO_INEXISTENTE = "Sessão inexistente" ;
		public static String ORIGEM_INVALIDA = "Origem inválida";
		public static String DESTINO_INVALIDO = "Destino inválido";
		public static String DATA_INVALIDA = "Data inválida";
		public static String HORA_INVALIDA = "Hora inválida";
		public static String VAGA_INVALIDA = "Vaga inválida";
		public static String IDENTIFICADOR_CARONA_INVALIDO = "Identificador do carona é inválido";
		public static String ITEM_INEXISTENTE = "Item inexistente";
		public static String CARONA_INEXISTENTE = "Carona Inexistente";
		public static String CARONA_INVALIDA = "Carona Inválida";
		public static String TRAJETO_INEXISTENTE = "Trajeto Inexistente";
		public static String TRAJETO_INVALIDO = "Trajeto Inválido";
		public static String CIDADE_INEXISTENTE = "Cidade inexistente";
		public static String MIN_CARONEIROS_INVALIDO = "Minimo Caroneiros inválido";
		
	//ERROS DA CLASSE SOLICITACAO
		public static String SOLICITACAO_INEXISTENTE = "Solicitação inexistente";
		public static String USUARIO_NAO_PREFERENCIAL = "Usuário não está na lista preferencial da carona";
		
	//ERROS DA CLASSE SUGESTAO
		public static String PONTO_INVALIDO = "Ponto Inválido";
		
		
	// ERROS DA CLASSE VIZUALIZADORPERFIL
		public static String OPCAO_INVALIDA = "Opção inválida.";
		public static String USUARIO_CLADESTINO = "Usuário não possui vaga na carona.";

}
