package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.daoImpl.UserDaoImpl;
import com.app.model.User;

@RestController

public class UserController {

	@Autowired
	private UserDaoImpl userService;

	@PostMapping(value = "/save")
	public User createUser(@RequestBody User user) {
		userService.saveUser(user);
		return user;
	}

	@GetMapping(value = "/getByUserId/{id}")
	public User getByUserId(@PathVariable("id") Integer id) {

		User user = userService.getById(id);

		return user;
	}

	@GetMapping(value = "/getAllUsersList")
	public List<User> getAllUsersList() {
		return userService.getAllUsers();

	}
}
