package servicesBackup;

import domainBackup.ChicoTripa;

public interface UserDAO {

	public ChicoTripa getUser(String cpf);
	
}
