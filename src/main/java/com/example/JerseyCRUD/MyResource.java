package com.example.JerseyCRUD;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.JerseyCRUD.Model.User;
import com.example.JerseyCRUD.Service.UserService;


//basepath
@Path("rest")
public class MyResource {
	
	//public UserService userService = new UserService();
	
//	  @GET
//	  @Produces(MediaType.TEXT_PLAIN) 
//	  public String getIt() { 
//		  return "Hello World";
//	  }
	 
	@POST
    @Path("/addUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(User user){
    	return UserService.getInstance().addUser(user);
    }
	
    @GET
    @Path("/allUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers(){
    	return UserService.getInstance().getAllUsers();
    	
    }
    
    @GET
    @Path("/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("userId") int userId){
    	return UserService.getInstance().getUserById(userId);
    }
    
    @DELETE
	@Path("/delete/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("userId") int userId) {
		return UserService.getInstance().deleteUser(userId);
	}
    
    @PUT
	@Path("/update/{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(User user, @PathParam("userId") int UserId) {
		return UserService.getInstance().updateUser(user, UserId);
	}
    
}
