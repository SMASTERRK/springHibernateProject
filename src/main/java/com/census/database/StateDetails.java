package com.census.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stateDetails")
public class StateDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "stateId", nullable = false)
    private String stateid;
    
    @Column(name = "stateName", nullable = false)
    private String statename;
    
	public String getStateid() {
		return stateid;
	}

	public void setStateid(String stateid) {
		this.stateid = stateid;
	}

	public String getStatename() {
		return statename;
	}

	public void setStatename(String statename) {
		this.statename = statename;
	}

}
