package assignment1;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.ServletContextEvent;

public class EMFListener implements Serializable {

	static String PERSISTENCE_UNIT_NAME = "Assignment2";
	
	static EntityManagerFactory entityManagerFactory = null;
    static EntityManager entityManager = null;
    
    
    
    public void setCloseConnection() {
    	if(entityManagerFactory != null) {
    		entityManagerFactory.close();
    		entityManagerFactory = null;
    	}
    }
    public void contextInitialized(ServletContextEvent tempEvent) {
    	if(!isEntityManagerFactoryReady()) {
    		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    	}
    }
    
    public static boolean isEntityManagerFactoryReady() {
    	return entityManagerFactory != null && entityManagerFactory.isOpen();
    }

    public static EntityManager createEntityManager(Map <String, String> tempEntityManagerProperties) {
    	entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        return entityManager;
    }
    
}
