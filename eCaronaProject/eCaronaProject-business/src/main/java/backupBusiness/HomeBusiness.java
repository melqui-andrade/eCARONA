package backupBusiness;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import servicesBackup.UserDAO;
import domainBackup.ChicoTripa;

@Component
public class HomeBusiness {
	
	@Autowired
	private UserDAO userDao;
	
	public ChicoTripa retrieveUser(String cpf){
		return userDao.getUser(cpf);
	}
	
}
