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
import assignment1.LoginListener;

//This class is where all the database queries will get the information for the lists
public class JPABean implements Serializable {
	
	//private static final long serialVersionUID = -2949472089308311785L;
	
	//public EntityManager EMFListener.createEntityManager()
	
	//public Boolean isConnectionEstablish() throws SQLException
	
	//public void setCloseConnection() throws SQLException
	
	//public void setUserCredentials(String username, String password) throws SQLException\
	
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
		
		/*
		HouseHold tempHouseHold = new HouseHold();
        
        tempHouseHold.setHouseHoldSize(entityManager.find(HouseHoldSize.class, 3));
        tempHouseHold.setCensusYear(entityManager.find(CensusYear.class, 1));
        tempHouseHold.setHouseHoldsByAgeRange(entityManager.find(HouseHoldsByAgeRange.class, 7));
        tempHouseHold.setHouseHoldType(entityManager.find(HouseHoldType.class, 4));
        tempHouseHold.setTotalIncome(entityManager.find(TotalIncome.class, 15));
        tempHouseHold.setHouseHoldEarners(entityManager.find(HouseHoldEarners.class, 3));
        tempHouseHold.setNumberReported(0);
        entityManager.persist(tempHouseHold);
        String tempSelectJPQLQuery = "SELECT a, ag, cy, ga, hh, hhe, hhar, hhs, hht, ti FROM Age a, AgeGroup ag, CensusYear cy, GeographicArea ga, HouseHold hh "+
        		", HouseHoldEarners hhe , HouseHoldsByAgeRange hhar , HouseHoldSize hhs , HouseHoldType hht , TotalIncome ti "
        		+ "WHERE hht.description = :hhtDesc AND hhs.description = :hhsDesc AND hhar.description = :hharDesc AND hhe.description = :hheDesc AND ti.description = :tiDesc"
        		+ " AND (ga.level = 1 OR ga.level = 0)";
        
        Query tempQuery = entityManager.createQuery(tempSelectJPQLQuery);
        tempQuery.setParameter("hhtDesc", tempHouseHold.getHouseHoldType().getDescription());
        tempQuery.setParameter("hhsDesc", tempHouseHold.getHouseHoldSize().getDescription());
        tempQuery.setParameter("hharDesc", tempHouseHold.getHouseHoldsByAgeRange().getDescription());
        tempQuery.setParameter("hheDesc", tempHouseHold.getHouseHoldEarners().getDescription());
        tempQuery.setParameter("tiDesc", tempHouseHold.getTotalIncome().getDescription());
        
        return getDBRecords(tempQuery);
        */
		
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
		
		String tempSelectJPQLQuery = "SELECT ag FROM AgeGroup";
		Query tempQuery = entityManager.createQuery(tempSelectJPQLQuery);
		
		return getDBRecords(tempQuery);
	}
	
	public List<Object[]> getDBRecords (Query tempQuery){
		entityManager.getTransaction().begin();
		return tempQuery.getResultList();
	}
	
	
}
