package com.ghost.bean;

import java.io.Serializable;
import java.util.*;
import com.ghost.model.*;

import service.RecoveryService;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.enterprise.*;
import javax.inject.Named;

@Named("ghostRecovery")
@ViewScoped
public class RegisterRecoveryGhostNetBean implements Serializable {
	

	private static final long serialVersionUID = 1L;
	List<GhostNet> ghostNetsNotRecovered=new ArrayList();
	GhostNet selectedGhostNet;
	String recoveryName;
	String recoveryPhone;
	
	public RegisterRecoveryGhostNetBean()
	{
		RecoveryService service=new RecoveryService();
		ghostNetsNotRecovered=service.getUnRecoveredNets();
		
	}

	
	public void selectForRecovery(GhostNet net) {
	   
	            this.selectedGhostNet = net;
	    
	}

	
	public void registerForRecovery()
	{
		RecoveryService service=new RecoveryService();;
		
		SalvagingPerson person=new SalvagingPerson();
		person.setName(recoveryName);
		person.setPhoneNumber(recoveryPhone);
		System.out.print(this.selectedGhostNet.getId()+"yessss");
		service.registerForRecovery(this.selectedGhostNet,person);
	    reset();
	}
	
	private void reset()
	{
		recoveryName="";
		recoveryPhone="";
		selectedGhostNet=new GhostNet();
		RecoveryService service=new RecoveryService();
		ghostNetsNotRecovered=service.getUnRecoveredNets();
		
		
	}


	public List<GhostNet> getGhostNetsNotRecovered() {
		return ghostNetsNotRecovered;
	}


	public void setGhostNetsNotRecovered(List<GhostNet> ghostNetsNotRecovered) {
		this.ghostNetsNotRecovered = ghostNetsNotRecovered;
	}


	public GhostNet getSelectedGhostNet() {
		return selectedGhostNet;
	}


	public void setSelectedGhostNet(GhostNet selectedGhostNet) {
		this.selectedGhostNet = selectedGhostNet;
	}


	public String getRecoveryName() {
		return recoveryName;
	}


	public void setRecoveryName(String recoveryName) {
		this.recoveryName = recoveryName;
	}


	public String getRecoveryPhone() {
		return recoveryPhone;
	}


	public void setRecoveryPhone(String recoveryPhone) {
		this.recoveryPhone = recoveryPhone;
	}
	
}
