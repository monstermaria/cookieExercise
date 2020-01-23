package necessaryPackageName;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		System.out.println("EditServlet/doGet enter");

		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher rd = request.getRequestDispatcher("editpage.jsp");
		rd.forward(request, response);

		System.out.println("EditServlet/doGet exit");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("EditServlet/doPost enter");
	
		User user = Model.getUserFromCookie(request.getCookies());
		if (user == null) {
			
			RequestDispatcher rd = request.getRequestDispatcher("loginpage.jsp");
			rd.forward(request, response);

		} else {
			
			System.out.println("User: " + user.getUserName());
			System.out.println("Content: " + user.getContent());
			
			String newcontent = request.getParameter("newcontent");
//			System.out.println("newcontent: " + newcontent);
			String username = request.getParameter("username");
//			System.out.println("username:" + username);

			user.setUserName(username);
			user.setContent(newcontent);
						
			System.out.println("User: " + user.getUserName());
			System.out.println("Content: " + user.getContent());
			
			RequestDispatcher rd = request.getRequestDispatcher("userpage.jsp");
			rd.forward(request, response);
			
		}
		
		

		System.out.println("EditServlet/doPost exit");
	}

}
