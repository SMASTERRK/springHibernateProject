package com.census.bean;

import com.census.database.DistrictDetails;


public class ModifyTO {
	private String choice;
	private String persons;
	private String numberofhouseholds;
	private String educated;
	private String workers;
	private String rangePersons;
	private String rangeNumberofhouseholds;
	private String rangeEducated;
	private String rangeWorkers;
	public String getRangePersons() {
		return rangePersons;
	}
	public void setRangePersons(String rangePersons) {
		this.rangePersons = rangePersons;
	}
	public String getRangeNumberofhouseholds() {
		return rangeNumberofhouseholds;
	}
	public void setRangeNumberofhouseholds(String rangeNumberofhouseholds) {
		this.rangeNumberofhouseholds = rangeNumberofhouseholds;
	}
	public String getRangeEducated() {
		return rangeEducated;
	}
	public void setRangeEducated(String rangeEducated) {
		this.rangeEducated = rangeEducated;
	}
	public String getRangeWorkers() {
		return rangeWorkers;
	}
	public void setRangeWorkers(String rangeWorkers) {
		this.rangeWorkers = rangeWorkers;
	}
	private DistrictDetails districtdetails;
	public DistrictDetails getDistrictdetails() {
		return districtdetails;
	}
	public void setDistrictdetails(DistrictDetails districtdetails) {
		this.districtdetails = districtdetails;
	}
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}
	public String getPersons() {
		return persons;
	}
	public void setPersons(String persons) {
		this.persons = persons;
	}
	public String getNumberofhouseholds() {
		return numberofhouseholds;
	}
	public void setNumberofhouseholds(String numberofhouseholds) {
		this.numberofhouseholds = numberofhouseholds;
	}
	public String getEducated() {
		return educated;
	}
	public void setEducated(String educated) {
		this.educated = educated;
	}
	public String getWorkers() {
		return workers;
	}
	public void setWorkers(String workers) {
		this.workers = workers;
	}
}
