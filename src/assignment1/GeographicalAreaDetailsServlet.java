package assignment1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.ArrayList;

import assignment1.DatabaseHelper;;

@WebServlet("/GeographicalAreaDetailsServlet")
public class GeographicalAreaDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GeographicalAreaDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession tempSession = request.getSession();
	
		DatabaseHelper db = new DatabaseHelper();
		Connection connection;
		ArrayList<String> areaDetails = new ArrayList<String>();
		ArrayList<String> listOfDependencies = new ArrayList<String>();
		
		connection = (Connection)tempSession.getAttribute("dbConnection");
		
        String areaIdStr = request.getParameter("areaId");
      
        int areaId;
        

        if ("".equals(areaIdStr))
        {

            tempSession.setAttribute("error", "please provide the area id");
            response.sendRedirect("./GeographicalAreaDisplay.jsp");

        }
        
        areaId = Integer.parseInt(areaIdStr);
        
        areaDetails = db.getAreaDetails(areaId, connection);
        listOfDependencies = db.getAreaDetails2(areaId, connection);
        
        tempSession.setAttribute("areaDetails", areaDetails);
        tempSession.setAttribute("listOfDependencies", listOfDependencies);

        response.sendRedirect("./GeographicalAreaDisplay.jsp");
	}

}
