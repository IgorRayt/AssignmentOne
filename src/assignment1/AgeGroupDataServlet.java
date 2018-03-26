package assignment1;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AgeGroupDataServlet")
public class AgeGroupDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AgeGroupDataServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
HttpSession tempSession = request.getSession();
		
		DatabaseHelper db = new DatabaseHelper();
		Connection connection;
		connection = (Connection)tempSession.getAttribute("dbConnection");
		ArrayList<Integer> ageGroupDetails = new ArrayList<Integer>();
		ArrayList<ArrayList> listOfAgeGroupDetails = new ArrayList<ArrayList>();
		
		ageGroupDetails.add(3);
		ageGroupDetails.add(9);
		ageGroupDetails.add(15);
		ageGroupDetails.add(22);
		ageGroupDetails.add(28);
		ageGroupDetails.add(34);
		ageGroupDetails.add(40);
		ageGroupDetails.add(46);
		ageGroupDetails.add(52);
		ageGroupDetails.add(58);
		ageGroupDetails.add(64);
		ageGroupDetails.add(70);
		ageGroupDetails.add(76);
		ageGroupDetails.add(83);
		ageGroupDetails.add(89);
		ageGroupDetails.add(95);
		ageGroupDetails.add(101);
		ageGroupDetails.add(108);
		ageGroupDetails.add(114);
		ageGroupDetails.add(120);
		
		for (int temp : ageGroupDetails) {
			listOfAgeGroupDetails.add(db.getAgeGroupDetails(temp, connection));
		}
		
		tempSession.setAttribute("ageGroupsData", listOfAgeGroupDetails);
		
		response.sendRedirect("./AgeGroupDataDisplay.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession tempSession = request.getSession();
		
		DatabaseHelper db = new DatabaseHelper();
		Connection connection;
		connection = (Connection)tempSession.getAttribute("dbConnection");
		ArrayList<Integer> ageGroupDetails = new ArrayList<Integer>();
		ArrayList<ArrayList> lostOfAgeGroupDetails = new ArrayList<ArrayList>();
		
		ageGroupDetails.add(3);
		ageGroupDetails.add(9);
		ageGroupDetails.add(15);
		ageGroupDetails.add(22);
		ageGroupDetails.add(28);
		ageGroupDetails.add(34);
		ageGroupDetails.add(40);
		ageGroupDetails.add(46);
		ageGroupDetails.add(52);
		ageGroupDetails.add(58);
		ageGroupDetails.add(64);
		ageGroupDetails.add(70);
		ageGroupDetails.add(76);
		ageGroupDetails.add(83);
		ageGroupDetails.add(89);
		ageGroupDetails.add(95);
		ageGroupDetails.add(101);
		ageGroupDetails.add(108);
		ageGroupDetails.add(114);
		ageGroupDetails.add(120);
		
		for (int temp : ageGroupDetails) {
			lostOfAgeGroupDetails.add(db.getAgeGroupDetails(temp, connection));
		}
		// put the lostOfAgeGroupDetails into the jsp
		
		response.sendRedirect("./AgeGroupData.jsp");
		
		
	}

}
