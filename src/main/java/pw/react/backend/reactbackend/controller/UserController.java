package pw.react.backend.reactbackend.controller;
import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pw.react.backend.reactbackend.entity.User;
import pw.react.backend.reactbackend.repo.UserRepository;

@RequestMapping("/users")
@RestController
public class    UserController {
	@Autowired
	UserRepository repository;

	@RequestMapping("/save")
	public String process(){
		// save a single User
		repository.save(new User("Jack", "Smith","js",Date.valueOf("1997-11-30") ,true));

		// save a list of Users
		repository.save(new User("Adam", "Johnson","aj", Date.valueOf("1996-10-29"),true));
		repository.save(new User("Kim", "Smith","ks", Date.valueOf("1995-9-28"),true));
		repository.save(new User("David", "Williams","dw", Date.valueOf("1994-8-27"),true));
		repository.save(new User("Peter", "Davis","pd", Date.valueOf("1993-07-26"),true));

		return "Done";
	}


	@GetMapping("")
	public String findAll(){
		String result = "";

		for(User u : repository.findAll()){
			result += u.toString() + "<br>";
		}

		return result;
	}

	@PostMapping("")
	public void addUser(@RequestBody User user)
	{
		repository.save(new User(user.getFirstName(),user.getLastName(),user.getLogin(), user.getDoB(),user.getisActive()));
	}

	/*@GetMapping("/id")
	public ResponseEntity<String> findById(@RequestParam("id") long id){
		String result = repository.findOne(id).toString();
		if(result.length()>0)
			return ResponseEntity.ok().body(result);
		return ResponseEntity.noContent().build();
	}*/
	@GetMapping("/id")
	public User findById(@RequestParam("id") long id){
		User result = repository.findOne(id);
		if(result!=null)
			return result;
		return null;
	}

	/*@GetMapping("/login")
	public ResponseEntity<String> fetchDataByLogin(@RequestParam("login") String login){
		String result = repository.findByLogin(login).toString();
		if(result.length()>0)
			return ResponseEntity.ok().body(result);
		return ResponseEntity.ok().body("User with given ID do not exist");
	}*/
	@GetMapping("/login")
	public User fetchDataByLogin(@RequestParam("login") String login){
		User result = repository.findByLogin(login);
		if(result!=null)
			return result;
		return null;

	}
	@PutMapping("/id")
	public ResponseEntity<String> updateUser(@RequestParam("id") long id,@RequestBody User user)
	{
		User userToBeUpdated=repository.findOne(id);
		if (userToBeUpdated!=null) {
			userToBeUpdated.setFirstName(user.getFirstName());
			userToBeUpdated.setLastName(user.getLastName());
			userToBeUpdated.setLogin(user.getLogin());
			userToBeUpdated.setDoB(user.getDoB());
			userToBeUpdated.setisActive(user.getisActive());
			userToBeUpdated.setFirstName(user.getFirstName());
			repository.save(userToBeUpdated);
			return ResponseEntity.ok().body("User updated");
		}
		return ResponseEntity.ok().body("User with given ID do not exist");
	}

	@DeleteMapping("/id")
	public ResponseEntity<String> deleteUser(@RequestParam("id") long id)
	{
		User userToBeDeleted=repository.findOne(id);
		if (userToBeDeleted!=null) {
			repository.delete(id);
			return ResponseEntity.ok().body("User deleted");
		}
		return ResponseEntity.ok().body("User with given ID do not exist");
	}
}
