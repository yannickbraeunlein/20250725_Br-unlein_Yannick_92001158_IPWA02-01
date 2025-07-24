package com.ghost.bean;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import java.io.IOException;
import java.util.*;
import com.ghost.model.*;

import service.RescuersDisplayService;


@Named("seeRescuers")
@RequestScoped
public class SeeRescuersBean {

	List<GhostNet> netsAndRescuersList=new ArrayList();
	public SeeRescuersBean() {
		
		netsAndRescuersList=new RescuersDisplayService().getNetsRescuersService();
	}
	
	
	
	public void seeOnMap(GhostNet net)
	{
		String gpsLoc=net.getGpsCoordinates();
		String token[]=gpsLoc.split(",");
		String lat=token[0];
		String lng=token[1];
		try {
	        FacesContext.getCurrentInstance().getExternalContext()
	            .redirect("map.xhtml?lat=" + lat + "&lng=" + lng);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	public List<GhostNet> getNetsAndRescuersList() {
		return netsAndRescuersList;
	}
	public void setNetsAndRescuersList(List<GhostNet> netsAndRescuersList) {
		this.netsAndRescuersList = netsAndRescuersList;
	}
	
	
	 

	
	
}
