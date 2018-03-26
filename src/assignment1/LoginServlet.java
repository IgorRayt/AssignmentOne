package assignment1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assignment1.DatabaseHendler;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DatabaseHendler db;
		HttpSession tempSession = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if ("".equals(username) || "".equals(password))
        {
            tempSession.setAttribute("status", "Please Provide Login Credentials");
            response.sendRedirect("./LoginDisplay.jsp");
        }
		
		db = new DatabaseHendler(username, password);
		
		if (db.getConnectionStatus()) {
			tempSession.setAttribute("status", "Login failed");
            response.sendRedirect("./LoginDisplay.jsp");
		}
		
				
		tempSession.setAttribute("dbConnection", db.getConnection());
		tempSession.setAttribute("status", "Loged in");
        response.sendRedirect("./AreaClassificationDisplay.jsp");
		
	}

}
