package assignment1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseHelper {
	

	static final int MISSING_ARGUMENT_ERROR_CODE = 1;
    static final int UNHANDLED_EXCEPTION_ERROR_CODE = 2;

	
	public ArrayList<String> getAreaClassificationList(int level, Connection connection) {
		
		String sqlStatement = "SELECT name FROM GEOGRAPHICAREA WHERE level = " + level;
		
		ArrayList<String> areaClassification = new ArrayList<String>();
		
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) 
			{
				areaClassification.add(resultSet.getString("name"));
			}
			return areaClassification;
		}
		
		catch (SQLException e)
        {
            e.printStackTrace();
            System.exit(e.getErrorCode());
            return areaClassification;
        }
		
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(UNHANDLED_EXCEPTION_ERROR_CODE);
            return areaClassification;
        }		
	}
	
	public ArrayList<String> getAreaDetails(int areaId, Connection connection){
		String sqlStatement = "SELECT GA.name,GA.level,GA.code ,Count(A.combined) as combined"
				+" FROM GEOGRAPHICAREA GA "
				+"JOIN AGE A ON A.geographicArea = GA.geographicAreaID JOIN CENSUSYEAR CY ON CY.censusYearID = A.censusYear "
				+"WHERE CY.censusYearID = 1 and GA.alternativeCode = " + String.valueOf(areaId)
				+" GROUP BY GA.name,GA.level,GA.code";
		ArrayList<String> areaDetals = new ArrayList<String>();
		
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) 
			{
				areaDetals.add(resultSet.getString("name"));
				areaDetals.add(String.valueOf(resultSet.getInt("level")));
				areaDetals.add(String.valueOf(resultSet.getInt("code")));
				areaDetals.add(String.valueOf(resultSet.getInt("combined")));
			}
			return areaDetals;
		}
		
		catch (SQLException e)
        {
            e.printStackTrace();
            System.exit(e.getErrorCode());
            return areaDetals;
        }
		
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(UNHANDLED_EXCEPTION_ERROR_CODE);
            return areaDetals;
        }
	}
	
	public ArrayList<String> getAreaDetails2(int groupId, Connection connection){
		String sqlStatement = "SELECT NAME"
				+" FROM GEOGRAPHICAREA GA"
				+" WHERE CAST(ALTERNATIVECODE AS CHAR(16)) LIKE '" +  String.valueOf(groupId) + "%'"
				+" AND ALTERNATIVECODE <> "+  String.valueOf(groupId);
		
		ArrayList<String> areaDetals = new ArrayList<String>();
		
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) 
			{
				areaDetals.add(resultSet.getString("name"));
			}
			return areaDetals;
		}
		
		catch (SQLException e)
        {
            e.printStackTrace();
            System.exit(e.getErrorCode());
            return areaDetals;
        }
		
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(UNHANDLED_EXCEPTION_ERROR_CODE);
            return areaDetals;
        }

	}
	
	public ArrayList<String> getAgeGroupDetails(int groupId, Connection connection){
		String sqlStatement = "SELECT AGEGROUP.description, Count(AGE.male) as male, Count(AGE.female) as female "
				+"FROM AGEGROUP" 
				+" JOIN AGE ON "
				+"AGEGROUP.ageGroupID = AGE.ageGroup "
				+"WHERE AGEGROUP.ageGroupID = " + String.valueOf(groupId)
				+" GROUP BY AGEGROUP.description";
		
		ArrayList<String> ageGroupDetails = new ArrayList<String>();
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) 
			{
				ageGroupDetails.add(resultSet.getString("description"));
				ageGroupDetails.add(String.valueOf(resultSet.getInt("male")));
				ageGroupDetails.add(String.valueOf(resultSet.getInt("female")));
			}
			return ageGroupDetails;
		}
		
		catch (SQLException e)
        {
            e.printStackTrace();
            System.exit(e.getErrorCode());
            return ageGroupDetails;
        }
		
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(UNHANDLED_EXCEPTION_ERROR_CODE);
            return ageGroupDetails;
        }
	}
}
