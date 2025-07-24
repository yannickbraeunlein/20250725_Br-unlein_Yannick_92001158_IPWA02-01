package com.ghost.bean;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import java.io.Serializable;
import java.util.*;
import com.ghost.model.*;

import service.RecoveryService;

@Named("reportRecovered")
@ViewScoped
public class ReportRecoveredBean implements Serializable {
	
	
	private static final long serialVersionUID = -5184239281923943154L;
	String phone;
	String msg="";
	List<GhostNet> unRecoveredList=new ArrayList();
	
	public void loadUnRecoveredGhostNets()
	{
	
		RecoveryService service=new RecoveryService();
		unRecoveredList=service.getUnRecoveredNets(phone);
		if(unRecoveredList.isEmpty())
			msg="You have not registerd for recovery";
		else
			msg="";
	}

	
	
	
	public void markRecovered(GhostNet net)
	{
		RecoveryService service=new RecoveryService();
		service.markRecovered(net);
		 loadUnRecoveredGhostNets();
		
	}
	
	public void markLost(GhostNet net)
	{
		RecoveryService service=new RecoveryService();
		service.markLost(net);
		 loadUnRecoveredGhostNets();
	}
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}




	public List<GhostNet> getUnRecoveredList() {
		return unRecoveredList;
	}




	public void setUnRecoveredList(List<GhostNet> unRecoveredList) {
		this.unRecoveredList = unRecoveredList;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	public String getMsg() {
		return msg;
	}




	public void setMsg(String msg) {
		this.msg = msg;
	}

	

	
	
}
