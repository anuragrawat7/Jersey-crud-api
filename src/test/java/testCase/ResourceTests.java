/**
 * 
 */
package testCase;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Connection;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.http.HttpStatus;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.example.JerseyCRUD.MyResource;
import com.example.JerseyCRUD.Dao.UserDao;
import com.example.JerseyCRUD.Model.User;
import com.example.JerseyCRUD.Service.UserService;

/**
 * @author ADMIN
 *
 */
@RunWith(PowerMockRunner.class)  
@PrepareForTest(fullyQualifiedNames = "com.example.JerseyCRUD.*")
public class ResourceTests extends JerseyTest {
	
	@Override
	 public Application configure() {
	  return new ResourceConfig(MyResource.class);
	 }
	
	@InjectMocks
	public UserService userService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * Test method for {@link com.example.JerseyCRUD.MyResource#addUser(com.example.JerseyCRUD.Model.User)}.
	 */
	@Test
	public void testAddUser() {
		User user = new User(10, "Ram", "Delhi", 21);
		PowerMockito.mockStatic(UserDao.class);  
	    PowerMockito.when(UserDao.save(user)).thenReturn(true);  
	    Response response = userService.addUser(user);
	    assertEquals(201, response.getStatus());
}
	
	/**
	 * Test method for {@link com.example.JerseyCRUD.MyResource#getAllUsers()}.
	 */
	@Test
	public void testGetAllUsers() {
		
		Response response = target("/rest/allUsers").request().get();
		assertEquals("should return status 200", 200, response.getStatus());
		System.out.println("Get All Users");
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}

	/**
	 * Test method for {@link com.example.JerseyCRUD.MyResource#getUserById(int)}.
	 */
	@Test
	public void testGetUserById() {
		Response respone = target("/rest/user/1").request().get();
		assertEquals("Should return status 200", 200, respone.getStatus());
		System.out.println("Get User By ID");
		System.out.println(respone.getStatus());
		System.out.println(respone.readEntity(String.class));
	}
	
	/**
	 * Test method for {@link com.example.JerseyCRUD.MyResource#updateUser(com.example.JerseyCRUD.Model.User, int)}.
	 */
	@Test
	public void testUpdateUser() {
		User user = new User(2, "AR", "Delhi", 22);
		Response response = target("/rest/update/2").request().put(Entity.entity(user, MediaType.APPLICATION_JSON));
		assertEquals("Should return status 200", 200, response.getStatus());
		System.out.println("Update user");
		System.out.println(response.getStatus());
	}


	/**
	 * Test method for {@link com.example.JerseyCRUD.MyResource#deleteUser(int)}.
	 */
	@Test
	public void testDeleteUser() {
		Response response = target("/rest/delete/3").request().delete();
		assertEquals("Should return status 200", 200, response.getStatus());
		System.out.println("------Deleted----");
	}

}
