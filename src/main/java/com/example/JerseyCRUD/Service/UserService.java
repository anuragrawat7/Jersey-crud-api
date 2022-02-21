package com.example.JerseyCRUD.Service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.JerseyCRUD.Dao.UserDao;
import com.example.JerseyCRUD.Model.User;

public class UserService {
	
	private static UserService userService;

	public static UserService getInstance() {
		if (userService == null)
			userService = new UserService();
		return userService;
	}

	public Response addUser(User user) {
		System.out.println("111");
		user.setUserId(user.getUserId());
		user.setName(user.getName());
		user.setCity(user.getCity());
		user.setAge(user.getAge());
			
		if (!UserDao.save(user)) {
			System.out.println("222");
			System.out.println("Error in adding a user. ");
			return Response.status(Status.BAD_REQUEST).build();
		}
		System.out.println("333");
		return Response.status(Status.CREATED).entity(user).build();
	}

	public Response getAllUsers() {
		List<User> user = new ArrayList<>();
		for (User user2 : UserDao.getAllUsers())
			user.add(user2);
		return Response.status(Status.OK).entity(user).build();
	}
	
	public Response getUserById(int userId) {
		List<User> user = new ArrayList<>();
		for (User user2 : UserDao.getAllUsers())
			if (user2.getUserId()==userId)
				user.add(user2);
		return Response.status(Status.OK).entity(user).build();
	}

	public Response deleteUser(int userId) {
		for (User user : UserDao.getAllUsers()) {
			
			if (user.getUserId() == userId)
				if (UserDao.remove(user.getUserId())) {
					return Response.status(Status.OK).entity("Deleted successfully").build();
					
				}
				else {
					System.out.println("Error in deleting a user. ");
					return Response.status(Status.EXPECTATION_FAILED).build();
				}
		}
		return Response.status(Status.OK).build();
	}

	public Response updateUser(User user, int userId) {
		User user2 = new User();
		for (User user3 : UserDao.getAllUsers()) {
			if (user3.getUserId() == userId) {
				user2.setUserId(user.getUserId());
				user2.setName(user.getName());
				user2.setCity(user.getCity());
				user2.setAge(user.getAge());
					
				if (!UserDao.update(user2)) {
					System.out.println("Error in updating a user. ");
					return Response.status(Status.BAD_REQUEST).build();
				}
			}
		}
		return Response.status(Status.OK).entity(user2).build();
	}

}
