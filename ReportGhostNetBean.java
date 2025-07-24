package com.ghost.bean;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.ghost.model.*;

import service.ReportService;
@Named("reportGhostNetBean")
@RequestScoped
public class ReportGhostNetBean {
	String name;
	String phone;
	String location;
	String size;
	boolean anonymous=false;
	
	public ReportGhostNetBean()
	{
		name="";
		phone="";
		location="";
		anonymous=false;
		size="";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public boolean isAnonymous() {
		return anonymous;
	}
	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public void submitReport()
	{

		ReportService service=new ReportService();
		ReportingPerson person=new ReportingPerson();
		if(!anonymous)
		{
		person.setName(name);
		person.setPhoneNumber(phone);
		person.setAnonymous(anonymous);
		}
		else
		{
			person.setName("no name");
			person.setPhoneNumber("no number");
			person.setAnonymous(anonymous);
		}
		GhostNet net=new GhostNet();
		net.setEstimatedSize(size);
		net.setReportedBy(person);
		net.setGpsCoordinates(location);
		net.setStatus(Status.REPORTED);
		service.registerGhostNet(net, person);
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Ghost Net reported successfully!", null));

        resetForm();
	}
	

private void resetForm() {
	name="";
	phone="";
	location="";
	size="";
	anonymous=false;
}

}
