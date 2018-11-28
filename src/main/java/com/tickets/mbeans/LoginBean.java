package com.tickets.mbeans;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tickets.data.StudentDAO;
import com.tickets.data.exception.DataAccessException;
import com.tickets.domains.Student;

@Component
@RequestScoped
public class LoginBean {
	@Autowired
	StudentDAO student;
	
	private List<Student> students;
	
	public List<Student> getStudents() {
		return students;
	}

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String password;

	public String login() {
		Student s;
		//String url = "index?faces-redirect=true";
		try {
			s = student.retrieveUserByEmail(getEmail());
			if (BCrypt.checkpw(getPassword(), s.getPassword())) {
				FacesContext context = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
				session.setAttribute("studentSession", s);
				return "dashboard?faces-redirect=true";
			}
			else{
				String errorMessage = "Invalid Login";
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
				FacesContext.getCurrentInstance().addMessage("form:msgId", message);
			}
		} catch (DataAccessException e) {
			String errorMessage = "Invalid Email";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
			FacesContext.getCurrentInstance().addMessage("form:email", message);
		}

		return null;
	}

	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		if (session.getAttribute("userSession") != null) {
			session.invalidate();
		}
		return "index?faces-redirect=true";
	}
	public String register() throws IOException
	{
//		FacesContext.getCurrentInstance().getExternalContext().redirect("register?faces-redirect=true");
		return "register?faces-redirect=true";
	}
	public List<Student> retrieve() {
		students=student.retrieveAllSeedLevels();
		return students;
	}
}
