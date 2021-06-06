package tn.esprit.spring.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.UserRepository;
@Service
public class UserServiceImpl implements UserSerivce{
	@Autowired
	UserRepository userrepository;
	private static final Logger l = LogManager.getLogger(UserServiceImpl.class);

	@Override
	public List<User> retrieveAllUsers() {

		List<User> users= (List<User>) userrepository.findAll();
		for(User user :users)
		{
			l.info("user+++:"+user);
		} return users;
	}
	@Override
	public User addUser(User u){
		User usersaved= userrepository.save(u);
		return usersaved;
	}
	@Override
	public User updateUser(User u){
		l.info("in updateuser:"+u);
		User useradded= userrepository.save(u);
		l.info("out of updating user:");
		return useradded;

	}

	@Override
	public User retrieveUser(String id) {

		l.info("in retrieveUser id :" + id);
		User u= userrepository.findById(Long.parseLong(id)).get();
		l.info("userretrieved:"+u);

		return u;
	}

	@Override
	public User authenticate(String login, String password) {
		return userrepository.getUserByEmailAndPassword(login, password);
	}
	@Override
	public int addOrUpdateEmploye(User employe) {
		userrepository.save(employe);
		return employe.getId().intValue();
	}
	@Override
	public void deleteUser(int id) {


		userrepository.deleteById(Long.valueOf(id));



	}
	@Override
	public String rechercheEmail(Long id) {
		// TODO Auto-generated method stub
		return userrepository.findEmailByUser(id);
	}
	



}



