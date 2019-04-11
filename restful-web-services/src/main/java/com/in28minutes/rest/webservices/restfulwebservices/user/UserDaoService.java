package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> users=new ArrayList<>();
	private static int usercount=3;
	private Iterator<User> iterator;
	
	static {
		users.add(new User(1,"Rajesh",new Date()));
		users.add(new User(2,"Venkata",new Date()));
		users.add(new User(3,"Abhimanyu",new Date()));
	}
	
	public List<User> findAll(){
		
		return users;
	}
	
	public User save(User user) {
		if(user.getId()==null) {
			user.setId(++usercount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		for(User user:users)
			if(user.getId()==id) {
				return user;
			}
		return null;
	}

	
	public User deleteByID(int id) {
		
		Iterator<User> iterator=	users.iterator();
		while(iterator.hasNext()) {
			User user=iterator.next();
				if(user.getId()==id) {
					iterator.remove();
					return user;
				}
		}
		return null;
	}
	
}
