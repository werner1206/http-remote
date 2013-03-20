package no.flomark.http.server;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import no.flomark.user.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author flomark
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:HttpUserServiceTest-context.xml" })
public class HttpUserServiceTest extends ServerRunner {

	@Autowired
	private IHttpUserService remoteUserService;

	private User createOneUser() {
		// New User is created...
		User user = new User(1, "Bruce", "Willies");
		return user;
	}

	@Test
	public void testAddUser() {

		// The user is added to the remote cache...
		assertTrue(remoteUserService.addUser(createOneUser()));
		assertTrue(!remoteUserService.getUserList().isEmpty());

	}

	@Test
	public void testDeleteUser() {

		// The user is added to the remote cache...
		assertTrue(remoteUserService.deleteUser(createOneUser()));
		assertTrue(remoteUserService.getUserList().isEmpty());
	}

	@Test
	public void testAddManyUsers() {

		int x = 0;
		while (x < 20) {
			remoteUserService
					.addUser(new User(x, "Truls_" + x, "Flomark_" + x));
			x++;
		}

		assertTrue(!remoteUserService.getUserList().isEmpty());
		assertTrue(remoteUserService.getUserList().size() == 20);

	}

	@Test
	public void testURLConnection() throws IOException {
		
		int c; 
		URL hp = new URL("http://localhost:8080/remoting/HttpUserService");
		URLConnection hpCon = hp.openConnection(); 

		System.out.println("Date: " + new Date(hpCon.getDate())); 

		System.out.println("Content-Type: " + 
 hpCon.getContentType());
		System.out.println("Expires: " + hpCon.getExpiration()); 

		System.out.println("Last-Modified: " + 
 new Date(hpCon.getLastModified()));
		int len = hpCon.getContentLength(); 
		System.out.println("Content-Length: " + len); 

		if (len > 0) { 
			System.out.println("=== Content ===");
			InputStream input = hpCon.getInputStream();
			int i = len;
			while (((c = input.read()) != -1) && (--i > 0)) {
				System.out.print((char) c);
			}

		input.close(); 

		} else { 
			System.out.println("No Content Available");
		} 
	}

}
