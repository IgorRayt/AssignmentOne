package assignment1;
//Don't need this - this is used for assignment 1 not assignment 2
// Login listener will handle login/user logic
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/**
	 * 
	 */

//	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		JPABean jpaBean;
		HttpSession tempSession = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if ("".equals(username) || "".equals(password))
        {
            tempSession.setAttribute("status", "Please Provide Login Credentials");
            response.sendRedirect("./LoginDisplay.jsp");
        }
		jpaBean = new JPABean();
		if(jpaBean.getConnection(username, password)) {
			tempSession.setAttribute("JPABean", jpaBean);
			tempSession.setAttribute("status", "Loged in");
            response.sendRedirect("./GeographicAreaClassificationList.jsp");
		}
		else {
			tempSession.setAttribute("status", "Login failed");
            response.sendRedirect("./LoginDisplay.jsp");
		}
	}

}
