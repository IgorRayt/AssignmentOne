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

import assignment1.DatabaseHelper;;


@WebServlet("/AreaClassificationServlet")
public class AreaClassificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession tempSession = request.getSession();
		
		DatabaseHelper db = new DatabaseHelper();
		Connection connection;
		ArrayList<String> areaDetails = new ArrayList<String>();
		
		connection = (Connection)tempSession.getAttribute("dbConnection");
		
		String areaId = request.getParameter("areaId");
		
		ArrayList<String> dataList;
		
		dataList = db.getAreaClassificationList(Integer.parseInt(areaId), connection);
		
		tempSession.setAttribute("levelData", dataList);
		response.sendRedirect("./AreaClassificationDisplay.jsp");
		
	}
}
