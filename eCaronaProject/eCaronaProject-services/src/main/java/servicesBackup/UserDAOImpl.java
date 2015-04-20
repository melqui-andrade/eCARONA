package servicesBackup;

import org.springframework.stereotype.Service;

import domainBackup.ChicoTripa;

@Service
public class UserDAOImpl implements UserDAO {

	@Override
	public ChicoTripa getUser(String cpf) {
		ChicoTripa ud = new ChicoTripa();
		ud.setCpf("0538953958395839");
		ud.setNome("noca");
		return ud;
	}

}
