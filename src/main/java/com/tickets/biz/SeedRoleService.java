package com.tickets.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tickets.data.SeedRoleDAO;
import com.tickets.domains.SeedRole;

@Service
public class SeedRoleService {

	@Autowired
	SeedRoleDAO roleDAO;

	public List<SeedRole> getAllSeedLevels() {
		List<SeedRole> list = null;
		list = roleDAO.retrieveAllSeedRoles();
		return list;
	}
}
