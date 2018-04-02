package assignment1;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

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
	
	//public void setUserCredentials(String username, String password) throws SQLException
	
	//task 1 a)
	public List<Object> getAllGeographicAreas() throws SQLException{
		
		//everything in between these comments need to be changed to method calls to get the entitymanagerfactorylistener methods
		String PERSISTENCE_UNIT_NAME = "Assignment2";
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        //everything in between these comments need to be changed to method calls to get the entitymanagerfactorylistener methods       
        //if you can't get it to work, just leave it like this because it will work.

		String tempSelectJPQLQuery = "SELECT ga FROM GeographicArea";
		Query tempQuery = entityManager.createQuery(tempSelectJPQLQuery);
		List<Object> tempList = tempQuery.getResultList();
		
		return tempList;
	
	}
	
	
	//task 1 b)
	public List<Object[]> getIndividualGeoAreaDetail() throws SQLException{
		
		
		//everything in between these comments need to be changed to method calls to get the entitymanagerfactorylistener methods
		String PERSISTENCE_UNIT_NAME = "Assignment2";
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        //everything in between these comments need to be changed to method calls to get the entitymanagerfactorylistener methods       
        //if you can't get it to work, just leave it like this because it will work.
		
		HouseHold tempHouseHold = new HouseHold();
        
        tempHouseHold.setHouseHoldSize(entityManager.find(HouseHoldSize.class, 3));
        tempHouseHold.setCensusYear(entityManager.find(CensusYear.class, 1));
        tempHouseHold.setHouseHoldsByAgeRange(entityManager.find(HouseHoldsByAgeRange.class, 7));
        tempHouseHold.setHouseHoldType(entityManager.find(HouseHoldType.class, 4));
        tempHouseHold.setTotalIncome(entityManager.find(TotalIncome.class, 15));
        tempHouseHold.setHouseHoldEarners(entityManager.find(HouseHoldEarners.class, 3));
        tempHouseHold.setNumberReported(0);
        entityManager.persist(tempHouseHold);
        //not sure if this will work 100%, but it should be pretty close
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
        
        List<Object[]> tempList = tempQuery.getResultList();
        
		return tempList;
		
	}
	//task 1 d)
	public List<Object[]> getMedianTotalHousehold() throws SQLException{
		
		String tempSelectJPQLQuery = "SELECT a FROM Age";
		
		return null;
	
	}

	//task 1 c)
	public List<Object> getAllAgeGroups() throws SQLException{
		
		//everything in between these comments need to be changed to method calls to get the entitymanagerfactorylistener methods
				String PERSISTENCE_UNIT_NAME = "Assignment2";
				EntityManagerFactory entityManagerFactory = null;
				EntityManager entityManager = null;
				entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
				entityManager = entityManagerFactory.createEntityManager();
		        entityManager.getTransaction().begin();
		        //everything in between these comments need to be changed to method calls to get the entitymanagerfactorylistener methods       
		        //if you can't get it to work, just leave it like this because it will work.
		
		String tempSelectJPQLQuery = "SELECT ag FROM AgeGroup";
		Query tempQuery = entityManager.createQuery(tempSelectJPQLQuery);
		List<Object> tempList = tempQuery.getResultList();
		
		return tempList;
	}
}
