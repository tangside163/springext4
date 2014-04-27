
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tangsi.pojo.User;
import com.tangsi.service.UserService;

public class ApplicationContextTest {

	public static void main(String[] args) {

		String[] configLocation = new String[] { "classpath:config/applicationContext.xml" };

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				configLocation);

		SessionFactory sessionFactory = (SessionFactory) applicationContext
				.getBean("sessionFactory", SessionFactory.class);

		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		User user = new User();
		user.setName("tangsi");
		user.setPassword("123");
		user.setEmail("163");
		session.save(user);
		session.getTransaction().commit();
	}

	@Test
	public void testJcbcDriver() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver");

		Connection conn = DriverManager
				.getConnection(
						"jdbc:mysql://localhost:3306/ext?useUnicode=true&characterEncoding=utf8",
						"root", "root");
		

	}
	
	@Test
	public void test2() {
		
		String[] configLocation = new String[] { "classpath:config/applicationContext.xml","classpath:config/springmvc.xml" };

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				configLocation);
		
		
		UserService userService = (UserService) applicationContext.getBean("userService");
		
		User user = new User();
		user.setName("wo");
		user.setPassword("wo");
		userService.saveUser(user);
		
	}

}
