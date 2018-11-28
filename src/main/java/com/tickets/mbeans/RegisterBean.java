package com.tickets.mbeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tickets.data.StudentDAO;
import com.tickets.domains.Student;

@Component
@RequestScoped
public class RegisterBean {
	
	@Autowired
	StudentDAO student;
	
	private String name;
	
	private String email;
	
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
	
	public String register()
	{
		Student s=new Student();
		s.setName(getName());
		s.setEmailId(getEmail());
		String password=BCrypt.hashpw(getPassword(), BCrypt.gensalt());
		s.setPassword(password);
		String check=student.registerStudent(s);
		if(check!=null)
		{
			FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_INFO, "Student added Successfully", null);
			FacesContext.getCurrentInstance().addMessage("form:msgId", msg);
			return null;
		}
		else
		{
			FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error while registering student",null);
			FacesContext.getCurrentInstance().addMessage("form:msgId", msg);
			return null;
		}
	}
}
