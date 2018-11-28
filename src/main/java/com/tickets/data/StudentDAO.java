package com.tickets.data;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tickets.data.exception.DataAccessException;
import com.tickets.domains.Student;

@Repository
public class StudentDAO {
	@Autowired
	SessionFactory sessionFactory;
	
	private static Logger logger = Logger.getLogger(StudentDAO.class);

	public List<Student> retrieveAllSeedLevels() {
		List<Student> levels = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query<Student> query = session.createQuery("from Student", Student.class);
		levels = query.list();
		transaction.commit();
		session.close();
		return levels;
	}

	public Student retrieveUserByEmail(String email) throws DataAccessException {
		Student level = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try{
		Query<Student> query = session.createQuery("from Student where EMAIL=:email", Student.class);
		query.setParameter("email", email);
		level = query.getSingleResult();}
		catch(NoResultException e){
			logger.error(e.getMessage(),e);
			throw new DataAccessException(e.getMessage(),e);
		}
		transaction.commit();
		session.close();
		return level;
	}
	public String registerStudent(Student s)
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try
		{
			session.save(s);
		}
		catch(Exception e){
			return null;
		}
		transaction.commit();
		session.close();
		return "Success";
	}
}
