package com.tickets.mbeans;

import java.util.List;

import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tickets.data.SeedRoleDAO;
import com.tickets.domains.SeedRole;

@Component
@RequestScoped
public class SeedRoleBean {
	@Autowired
	SeedRoleDAO roleDAO;

	private List<SeedRole> roles;

	public String getAllRoles() {
		roles = roleDAO.retrieveAllSeedRoles();
		return "role";
	}

	public List<SeedRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SeedRole> roles) {
		this.roles = roles;
	}
}
