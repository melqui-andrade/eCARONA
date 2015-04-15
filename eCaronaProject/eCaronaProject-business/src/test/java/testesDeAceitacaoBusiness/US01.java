package testesDeAceitacaoBusiness;

import easyaccept.EasyAccept;

public class US01 {

	public static void main(String[] args) {

		args = new String[] { "com.br.uepb.business.FacadeBusiness",
				"./src/test/resources/easyAcceptFiles/US01.txt" };
		EasyAccept.main(args);
	}
	
}
