package tn.esprit.spring.controlJSF;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.User;
import tn.esprit.spring.service.UserSerivce;

@RestController
public class UserRestController {
	@Autowired
	UserSerivce userService;


	// http://localhost:8084/SpringMVC/servlet/retrieve-all-users
	@GetMapping("/retrieve-all-users")
	@ResponseBody
	public List<User> getUsers() {
		List<User> list = userService.retrieveAllUsers();
		return list;
	}
	@GetMapping("/retrieve-user/{user-id}")
	@ResponseBody
	public User retrieveUser(@PathVariable("user-id") String userId) {
		return userService.retrieveUser(userId);
	}
	@PostMapping("/add-user")
	@ResponseBody
	public User addUser(@RequestBody User u) {
		User user = userService.addUser(u);
		return user;
	}
	@DeleteMapping("/remove-user/{user-id}")
	@ResponseBody
	public void removeUser(@PathVariable("user-id") int userId) {
		userService.deleteUser(userId);
	}
	@PutMapping("/modify-user")
	@ResponseBody
	public User modifyUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
}
