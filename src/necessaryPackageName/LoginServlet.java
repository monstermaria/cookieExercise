package necessaryPackageName;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("LoginServlet/doGet enter");
		
		Cookie cookies[] = request.getCookies();
		User user = Model.getUserFromCookie(cookies);
		
		if (user != null) {
			
			request.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("userpage.jsp");
			rd.forward(request, response);
			
		} else {
			
			RequestDispatcher rd = request.getRequestDispatcher("loginpage.jsp");
			rd.forward(request, response);
		}

		System.out.println("LoginServlet/doGet exit");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("LoginServlet/doPost enter");
		
		if (!Model.isInitialized()) {
			Model.loadDatabase();
		}

		User user = Model.getUserByName(request.getParameter("username"));
		
		if (user == null) {
			
			String info = "Couldn't find any user with that name";
			request.setAttribute("loginInfo", info);
			
			RequestDispatcher rd = request.getRequestDispatcher("loginpage.jsp");
			rd.forward(request, response);

		} else {
						
			if (request.getParameter("password").equals(user.getPassword())) {
				
				Cookie ck = new Cookie("userid", user.getUserId());
				ck.setMaxAge(600);

				response.addCookie(ck);

				request.setAttribute("user", user);
				
				RequestDispatcher rd = request.getRequestDispatcher("/userpage.jsp");
				rd.forward(request, response);

			} else {
				
				String info = "Wrong password";
				request.setAttribute("loginInfo", info);
				
				RequestDispatcher rd = request.getRequestDispatcher("loginpage.jsp");
				rd.forward(request, response);
			}
		}

		System.out.println("LoginServlet/doPost exit");

	}
}
