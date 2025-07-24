package service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


import com.ghost.model.GhostNet;
import com.ghost.model.ReportingPerson;
import com.ghost.model.Status;


public class ReportService {

  
    EntityManager em;

   
    public void registerGhostNet(GhostNet ghostnet, ReportingPerson person) {
       em=Persistence.createEntityManagerFactory("ghostPU").createEntityManager();
       em.getTransaction().begin();
        ReportingPerson existingPerson = null;

        if (!person.isAnonymous()) {
            try {
                TypedQuery<ReportingPerson> query = em.createQuery(
                        "SELECT p FROM ReportingPerson p WHERE p.phoneNumber = :phone", 
                        ReportingPerson.class);
                query.setParameter("phone", person.getPhoneNumber());
                existingPerson = query.getSingleResult();
            } catch (NoResultException e) {
            }
        }

        if (existingPerson == null) {
        	
            em.persist(person);
            existingPerson = person;
        }

        ghostnet.setReportedBy(existingPerson);
        ghostnet.setStatus(Status.REPORTED); 

        em.persist(ghostnet);
        em.getTransaction().commit();
        em.close();
    }
}
