package com.in28minutes.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class UserJPAResource {

	
	@Autowired
	private UserDaoService userDaoService;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/jpa/users")
	public List<User> retriveAllUsers(){
		
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public Resource<User> retriveUser(@PathVariable int id){
		System.out.println(id);
		Optional<User> user =userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException(" id-"+id);
		}
		
		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder linkto= linkTo(methodOn(this.getClass()).retriveAllUsers());
		
		resource.add(linkto.withRel("all-users"));
		return resource;
	}
	
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
		
		System.out.println("am at create user - JPA");
		
		User saveUser=userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(saveUser.getId())
		.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/users/{id}") 
	public void  deleteUser(@PathVariable int id){
		System.out.println(id);
		userRepository.deleteById(id);
	}
}
