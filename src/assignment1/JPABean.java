package assignment1;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import assignment1.EMFListener;

//This class is where all the database queries will get the information for the lists
public class JPABean implements Serializable {
	
	
	static EntityManagerFactory entityMF = null;
	static EntityManager entityManager = null;
	final String PERSISTANCE_UNIT_NAME = "Assignment2";
	boolean connection;
	
	public boolean getConnection() {
		return connection;
	}
	
	public boolean getConnection(String username, String password) {
		try {
			Map<String, String> properties = new HashMap<String,String>();
			properties.put("hibernate.connection.username", username);
			properties.put("hibernate.connection.password", password);
			
			entityMF = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME, properties);
			entityManager = entityMF.createEntityManager();
			connection = true;
			return true;
					
		}
		catch(Exception e) {
			connection = false;
			if(entityManager != null) {
				entityManager.getTransaction().rollback();
			}
			e.printStackTrace();
			return false;
		}
	}
	
	
	//task 1 a)
	public List<GeographicArea> getAllGeographicAreas() throws SQLException{

		String tempSelectJPQLQuery = "SELECT ga FROM GeographicArea ga "
				+ "ORDER by ga.level, ga.name ASC";
		Query tempQuery = entityManager.createQuery(tempSelectJPQLQuery);

		entityManager.getTransaction().begin();
		
		List<GeographicArea> tmpGA = tempQuery.getResultList();
		return tmpGA;
	
	}
	
	
	//task 1 b)
	public List<Object[]> getIndividualGeoAreaDetail(int id) throws SQLException{
		
		if(id >= 2) {
			final int CENSUSYEARID_2016 = 1;
			final int CENSUSYEARID_2011 = 2;

	        CensusYear tempCensusYear2011 = entityManager.find(CensusYear.class, CENSUSYEARID_2011);
	        CensusYear tempCensusYear2016 = entityManager.find(CensusYear.class, CENSUSYEARID_2016);
	        
			String tempSelectJPQLQuery ="";
			tempSelectJPQLQuery = "SELECT ga, a FROM GeographicArea ga, Age a "
					+ "WHERE a.censusYear = :censusYear";
			Query tempQuery = entityManager.createQuery(tempSelectJPQLQuery);
			tempQuery.setParameter("censusYear", tempCensusYear2011.getCensusYearID());

			entityManager.getTransaction().begin();
	        List<Object[]> tmpList = tempQuery.getResultList();
	        
	        tempQuery.setParameter("censusYear", tempCensusYear2016.getCensusYearID());
	        tmpList.addAll(tempQuery.getResultList());
	        
			
			return tmpList;
		}
		else {
			final int CENSUSYEARID_2016 = 1;
			final int HOUSEHOLD_EARNERS = 3;
			final int HOUSEHOLD_SIZE = 3;
			final int HOUSEHOLD_TYPE = 4;
			final int TOTAL_MEDIAN_INCOME = 15;
			
			CensusYear tempCensusYear = entityManager.find(CensusYear.class, CENSUSYEARID_2016);
	        HouseHoldSize tempHouseHoldSize = entityManager.find(HouseHoldSize.class, HOUSEHOLD_SIZE);
	        HouseHoldEarners tempHouseHoldEarners = entityManager.find(HouseHoldEarners.class, HOUSEHOLD_EARNERS);
	        HouseHoldType tempHouseHoldType = entityManager.find(HouseHoldType.class, HOUSEHOLD_TYPE);
	        TotalIncome tempTotalIncome = entityManager.find(TotalIncome.class, TOTAL_MEDIAN_INCOME);
	        
	        String tempSelectJPQLQuery = "SELECT ga, a FROM GeographicArea ga, Age a, Household hh "
	        		+ "WHERE a.censusYear = :censusYear "
	        		+ "AND hh.censusYear = :censusYear"
	        		+ "AND a.geographicArea = :geographicArea"
	        		+ "AND hh.geographicArea = :geographicArea"
	        		+ "AND hh.householdType = :htID "
	        		+ "AND hh.householdSize = :hsID "
	        		+ "AND hh.householdEarners = :heID "
	        		+ "AND hh.totalIncome = :tiID";

	        Query tempQuery = entityManager.createQuery(tempSelectJPQLQuery);
	        tempQuery
	    		.setParameter("censusYear", tempCensusYear.getCensusYearID())
	        	.setParameter("geographicArea", id)
	    		.setParameter("htID", tempHouseHoldType.getId())
	    		.setParameter("hsID", tempHouseHoldSize.getId())
	    		.setParameter("heID", tempHouseHoldEarners.getId())
	    		.setParameter("tiID", tempTotalIncome.getId());

			entityManager.getTransaction().begin();
	        List<Object[]> tmpList = tempQuery.getResultList();
			entityManager.close();
			return tmpList;

		}
		
		
	}
	//task 1 d)
	public List<Object[]> getMedianTotalHousehold() throws SQLException{
		
		final int PROVINCE_AND_TERRITORIES_LVL = 1;
		final int CENSUSYEARID_2016 = 1;
		final int HOUSEHOLD_SIZE = 3;
		final int HOUSEHOLD_EARNERS = 3;
		final int HOUSEHOLD_AGE = 9;
		final int HOUSEHOLD_TYPE = 4;
		final int TOTAL_MEDIAN_INCOME = 22;
		
		
        CensusYear tempCensusYear = entityManager.find(CensusYear.class, CENSUSYEARID_2016);
        GeographicArea tempGeographicArea = entityManager.find(GeographicArea.class, PROVINCE_AND_TERRITORIES_LVL);
        HouseHoldSize tempHouseHoldSize = entityManager.find(HouseHoldSize.class, HOUSEHOLD_SIZE);
        HouseHoldEarners tempHouseHoldEarners = entityManager.find(HouseHoldEarners.class, HOUSEHOLD_EARNERS);
        HouseHoldsByAgeRange tempHouseHoldsByAgeRange = entityManager.find(HouseHoldsByAgeRange.class, HOUSEHOLD_AGE);
        HouseHoldType tempHouseHoldType = entityManager.find(HouseHoldType.class, HOUSEHOLD_TYPE);
        TotalIncome tempTotalIncome = entityManager.find(TotalIncome.class, TOTAL_MEDIAN_INCOME);
        String tempSelectJPQLQuery = "SELECT ga, hh FROM GeographicArea ga, TotalIncome tc, HouseHold hh "
        		+ "WHERE hh.censusYear = :censusYear "
        		+ "AND hh.geographicArea = :geographicArea "
        		+ "AND hh.houseHoldType = :htID "
        		+ "AND hh.houseHoldSize = :hsID "
        		+ "AND hh.houseHoldsByAgeRange = :hrID "
        		+ "AND hh.houseHoldEarners = :heID "
        		+ "AND hh.totalIncome = :tiID "
        		+ "ORDER by hh.numberReported DESC";

        Query tempQuery = entityManager.createQuery(tempSelectJPQLQuery);
        tempQuery
    		.setParameter("censusYear", tempCensusYear.getCensusYearID())
        	.setParameter("geographicArea", tempGeographicArea.getGeographicAreaID())
    		.setParameter("htID", tempHouseHoldType.getId())
    		.setParameter("hsID", tempHouseHoldSize.getId())
    		.setParameter("hrID", tempHouseHoldsByAgeRange.getId())
    		.setParameter("heID", tempHouseHoldEarners.getId())
    		.setParameter("tiID", tempTotalIncome.getId());
        
        entityManager.getTransaction().begin();
		
		List<Object[]> tmpIncome = tempQuery.getResultList();
		return tmpIncome;
	}

	//task 1 c)
	public List<Object[]> getAllAgeGroups() throws SQLException{
		
		String tempSelectJPQLQuery = "SELECT a.ageGroup.description, sum(a.male),sum(a.female) FROM Age a "
				+ "WHERE a.ageGroup.ageGroupID in (3,9,15,22,28,34,40,46,52,58,64,70,76,83,89,95,101,108,114,120,126) "
				+ "GROUP BY a.ageGroup.description";
		Query tempQuery = entityManager.createQuery(tempSelectJPQLQuery);
		entityManager.getTransaction().begin();
		
		List<Object[]> tmpGA = tempQuery.getResultList();
		return tmpGA;
	}
	
	public List<Object[]> getDBRecords (Query tempQuery){
		entityManager.getTransaction().begin();
		return tempQuery.getResultList();
	}
	
	
}
