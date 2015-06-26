package testesDeAceitacaoBusiness;
import java.util.ArrayList;
import java.util.List;
import com.br.uepb.business.FacadeBusiness;
import easyaccept.*;
/**
 * Classe responsável por rodar todas as users stories do sistema
 * gera relatorio final
 * @author Melqui
 *
 */
public class UsersStoryTests {

	public static void main(String[] args) {
		
		
		List<String> arquivos = new ArrayList<String>();
//		arquivos.add("./src/test/resources/easyAcceptFiles/US01.txt");
//		arquivos.add("./src/test/resources/easyAcceptFiles/US02.txt");
//		arquivos.add("./src/test/resources/easyAcceptFiles/US03.txt");
//		arquivos.add("./src/test/resources/easyAcceptFiles/US04.txt");
//		arquivos.add("./src/test/resources/easyAcceptFiles/US05.txt");
//		arquivos.add("./src/test/resources/easyAcceptFiles/US06.txt");
//		arquivos.add("./src/test/resources/easyAcceptFiles/US07.txt");
//		arquivos.add("./src/test/resources/easyAcceptFiles/US08.txt");
//		arquivos.add("./src/test/resources/easyAcceptFiles/US09.txt");
//		arquivos.add("./src/test/resources/easyAcceptFiles/US10.txt");
//		arquivos.add("./src/test/resources/easyAcceptFiles/US11.txt");
//		arquivos.add("./src/test/resources/easyAcceptFiles/US12.txt");
//		arquivos.add("./src/test/resources/easyAcceptFiles/US13.txt");
		arquivos.add("./src/test/resources/easyAcceptFiles/US14.txt");
		
		FacadeBusiness facadeBusiness = new FacadeBusiness();
		//facadeBusiness.zerarSistema();
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(facadeBusiness, arquivos);
		eaFacade.executeTests();		
		System.out.println(eaFacade.getCompleteResults());
		System.exit(0);
	}
}
