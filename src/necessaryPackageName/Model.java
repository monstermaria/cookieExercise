package necessaryPackageName;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Model {
	
	private static boolean initialized = false;
	private static ArrayList<User> users = new ArrayList<User>();
	
	public static void loadDatabase() {
		
		if (isInitialized()) {
			
			return;
		}
		
		addUser(Integer.toString(users.size()), "maria", "secretpassword", "");
		addUser(Integer.toString(users.size()), "fredrik", "hoppsan", "");
		addUser(Integer.toString(users.size()), "test", "123", "");
		
		setInitialized(true);
	}
	
	public static void addUser(String userId, String userName, String password, String content) {
		
		User user = new User();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setPassword(password);
		user.setContent(content);
		users.add(user);
	}
	
	public static User getUserByName(String userName) {
		
		User user = null;
		
		for (int i = 0; i < users.size(); i++) {
			if (userName.equals(users.get(i).getUserName())) {
				user = users.get(i);
			}
		}
		
		return user;
	}
	
	public static User getUserById(String id) {
		
		User user = null;
		int intId;
		
		try {
			System.out.println("Get user by ID: " + id);
			intId = Integer.parseInt(id);
			user = users.get(intId);
		} catch (Exception e) {
			System.out.println("Somethin went wrong getting user by ID");
			System.out.println("Number of users:" + users.size());
		}
		
		return user;
	}
	
	public static User getUserFromCookie(Cookie cookies[]) {
		
		User user = null;
		
		if (cookies != null) {
						
			for (int i = 0; i < cookies.length; i++) {
				
				if (cookies[i].getName().equals("userid")) {
					
					String userId = cookies[i].getValue();
					user = Model.getUserById(userId);
//					System.out.println("Found user ID cookie, ID = " + userId);
					break;
				}
			}
		}
				
		return user;
	}
	
	public static User getUserFromRequest(HttpServletRequest request) {
		
		User user = (User) request.getAttribute("user");

		if (user == null) {

			user = Model.getUserFromCookie(request.getCookies());
		}

		return user;
	}

	public static boolean isInitialized() {
		return initialized;
	}

	public static void setInitialized(boolean initialized) {
		Model.initialized = initialized;
	}
}
