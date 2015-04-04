package com.br.uepb.dao.impl;

import org.springframework.stereotype.Service;

import com.br.uepb.dao.UserDAO;
import com.br.uepb.domain.ChicoTripa;

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
