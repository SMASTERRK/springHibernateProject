package com.census.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "censusDetails")
public class CensusDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "censusid")
    private String censusId;
    @Column(name = "persons")
    private Long persons;
    @Column(name = "numberofhouseholds")
    private Long numberofhouseholds;
    @Column(name = "educated")
    private Long educated;
    @Column(name = "workers")
    private Long workers;
   
    
	public String getCensusId() {
		return censusId;
	}
	public void setCensusId(String censusId) {
		this.censusId = censusId;
	}
	
	public Long getPersons() {
		return persons;
	}
	public void setPersons(Long persons) {
		this.persons = persons;
	}
	public Long getNumberofhouseholds() {
		return numberofhouseholds;
	}
	public void setNumberofhouseholds(Long numberofhouseholds) {
		this.numberofhouseholds = numberofhouseholds;
	}
	public Long getEducated() {
		return educated;
	}
	public void setEducated(Long educated) {
		this.educated = educated;
	}
	public Long getWorkers() {
		return workers;
	}
	public void setWorkers(Long workers) {
		this.workers = workers;
	}
	public String getStatusRecord() {
		return statusRecord;
	}
	public void setStatusRecord(String statusRecord) {
		this.statusRecord = statusRecord;
	}
	public DistrictDetails getDistrictdetails() {
		return districtdetails;
	}
	public void setDistrictdetails(DistrictDetails districtdetails) {
		this.districtdetails = districtdetails;
	}
	@Column(name = "statusRecord")
    private String statusRecord;
	@OneToOne
	@JoinColumn(name = "districtID", referencedColumnName = "districtId")
	private DistrictDetails districtdetails;

}