package service;
import java.util.*;
import com.ghost.model.*;

import javax.persistence.*;


public class RecoveryService {

	EntityManager em;
	
	public RecoveryService()
	{
	    em=Persistence.createEntityManagerFactory("ghostPU").createEntityManager();
	}
	
	 public List<GhostNet> getUnRecoveredNets() {
	
	        List<GhostNet> list = new ArrayList<>();

	        try {
	            list = em.createQuery("SELECT g FROM GhostNet g WHERE g.status = :status", GhostNet.class)
	                    .setParameter("status",  Status.REPORTED) 
	                    .getResultList();
	        } catch (Exception e) {
	            e.printStackTrace(); 
	        } 
	        return list;
	    }
	 
	 public List<GhostNet> getUnRecoveredNets(String phone) {
			
		
		 
		 
		 List<SalvagingPerson> spList=em.createQuery("Select s from SalvagingPerson s where phoneNumber = :ph",SalvagingPerson.class)
		 .setParameter("ph", phone).getResultList();
		 
		 if(spList==null || spList.isEmpty())
		 {
			
		 return new ArrayList();
		 }
		 
		SalvagingPerson sp= spList.get(0);
		sp=em.find(SalvagingPerson.class, sp.getId());
		
		
		if(sp==null)
		{
			return new ArrayList();
		}
		 
		 
	        List<GhostNet> list = new ArrayList<>();

	        try {
	            list = em.createQuery("SELECT g FROM GhostNet g WHERE g.status = :status and g.assignedTo = : sp", GhostNet.class)
	                    .setParameter("status",  Status.RECOVERY_PENDING) 
	                    .setParameter("sp", sp)
	                    .getResultList();
	           
	        } catch (Exception e) {
	            e.printStackTrace();  
	        } 
	        return list;
	    }


	public void registerForRecovery(GhostNet ghostNet,SalvagingPerson sPerson) {
		
		
	
		 ghostNet=em.find(GhostNet.class, ghostNet.getId());
		
	    List<SalvagingPerson> list= em.createQuery("Select s from SalvagingPerson s where s.phoneNumber = :ph",SalvagingPerson.class)
		 .setParameter("ph", sPerson.getPhoneNumber()).getResultList();
	   
	    em.getTransaction().begin();
	    if(list.isEmpty())
	    {
	    	em.persist(sPerson);
	    	ghostNet.setAssignedTo(sPerson);
	    	ghostNet.setStatus(Status.RECOVERY_PENDING);
	    	em.merge(ghostNet);
	    
	    }
	    else
	    {
	    	sPerson=list.get(0);
	      	ghostNet.setAssignedTo(sPerson);
	    	ghostNet.setStatus(Status.RECOVERY_PENDING);
	    	em.merge(ghostNet);
	    }
	    
		 
		 em.getTransaction().commit();
		 em.close();
		
	
	}

	public void markRecovered(GhostNet net) {
		net.setStatus(Status.RECOVERED);
		updateGhostNet(net);
	}

	public void markLost(GhostNet net) {
		net.setStatus(Status.LOST);
		updateGhostNet(net);
	}
	
	public void updateGhostNet(GhostNet net)
	{
	
		em.getTransaction().begin();
		em.merge(net);
		em.getTransaction().commit();
	}
}
