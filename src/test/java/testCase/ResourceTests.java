/**
 * 
 */
package testCase;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.example.JerseyCRUD.Model.User;

/**
 * @author ADMIN
 *
 */
public class ResourceTests extends JerseyTest {
	
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
	 * Test method for {@link com.example.JerseyCRUD.MyResource#addUser(com.example.JerseyCRUD.Model.User)}.
	 */
	@Test
	public void testAddUser() {
		User user = new User(3, "Ram", "Delhi", 21);
		Response response = target("/rest/addUser").request().post(Entity.entity(user, MediaType.APPLICATION_JSON));
		assertEquals("Should return status 201", 201, response.getStatus());
		System.out.println("Add user");
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
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
