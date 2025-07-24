package service;

import java.util.List;

import com.ghost.model.GhostNet;
import com.ghost.model.Status;

import javax.persistence.*;
import java.util.*;
public class RescuersDisplayService {

	EntityManager em;
	
	public List<GhostNet> getNetsRescuersService() {
		em=Persistence.createEntityManagerFactory("ghostPU").createEntityManager();
		List<GhostNet> list=em.createQuery("select g from GhostNet g where g.status = :status",GhostNet.class)
				.setParameter("status", Status.RECOVERY_PENDING).getResultList();
		return list;
	}

}
