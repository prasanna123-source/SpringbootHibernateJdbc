package com.app.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.daoImpl.UserDaoImpl;
import com.app.model.User;
@Service
@Transactional
public class UserServiceImpl {
	@Autowired 
	UserDaoImpl userDaoImpl;
	public List<User> getAllUsers(){
		return userDaoImpl.getAllUsers();
	}
}
