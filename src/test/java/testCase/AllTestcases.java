package testCase;

import static org.junit.Assert.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.example.JerseyCRUD.MyResource;
import com.example.JerseyCRUD.Dao.UserDao;
import com.example.JerseyCRUD.Model.User;

@RunWith(PowerMockRunner.class)  
@PrepareForTest(fullyQualifiedNames = "com.example.JerseyCRUD.*")  
public class AllTestcases {
	
	@InjectMocks
	public MyResource myResource;

	@Test
	public void testAddUser() {
		User user = new User(10, "Ram", "Delhi", 21);
		PowerMockito.mockStatic(UserDao.class);  
	    PowerMockito.when(UserDao.save(user)).thenReturn(true);  
	    Response response = myResource.addUser(user);
	    assertEquals(201, response.getStatus());
	}
	
	@Test
	public void testGetAllUser() {
		PowerMockito.mockStatic(UserDao.class);
		PowerMockito.when(UserDao.getAllUsers()).thenReturn(Stream.of(new User(1,"A","Delhi",29), new User(2,"B","Delhi",31))
				.collect(Collectors.toList()));
		System.out.println(UserDao.getAllUsers());
		Response response = myResource.getAllUsers();
		assertEquals(200, response.getStatus());
	}

}
