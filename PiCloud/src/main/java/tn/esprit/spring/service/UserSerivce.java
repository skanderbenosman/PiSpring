package tn.esprit.spring.service;


import java.util.List;

import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entity.User;

public interface UserSerivce {
	List<User> retrieveAllUsers();
	User addUser(User U);
	User updateUser(User U);
	void deleteUser(int id);
	User retrieveUser(String id);
	public User authenticate(String login, String password) ;
	public int addOrUpdateEmploye(User employe);
	public  String rechercheEmail(@Param("id") Long id);
	

}
