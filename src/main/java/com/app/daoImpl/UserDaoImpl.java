package com.app.daoImpl;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.model.User;

@Repository
@Transactional
public class UserDaoImpl {
	
	@Autowired
	SessionFactory sessionFactory;

	private Session getSession() {
	    return sessionFactory.getCurrentSession();
	  }
	
	
	public List<User> getAllUsers() {
		List<User> resultList = getSession().createQuery("from User",User.class).getResultList();
	    return resultList;
	  }
	
	 public User saveUser(User user) {
		 sessionFactory.getCurrentSession().persist(user);
		    return Objects.nonNull(user) &&
		    		Objects.nonNull(user.getId())?user : null;
	}
	 
	 public User getById(Integer id) {
		    return (User) getSession().load(User.class, id);
	}
}
