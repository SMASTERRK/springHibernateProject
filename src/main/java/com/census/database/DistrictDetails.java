package com.census.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "districtDetail")
public class DistrictDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "districtId")
    private String districtId;

    @Column(name = "districtName", nullable = false)
    private String districtName;
    @ManyToOne
    @JoinColumn(name = "stateID", referencedColumnName = "stateId")
    private StateDetails stateDetails;
    
    public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public StateDetails getStateDetails() {
		return stateDetails;
	}

	public void setStateDetails(StateDetails stateDetails) {
		this.stateDetails = stateDetails;
	}
	
}
