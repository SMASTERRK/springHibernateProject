package com.census.bean;

public class Census {

	private transient String statename;
	private transient String districtName;
	private transient Long persons;
	private transient Long numberofhouseholds;
	private transient Long educated;
	private transient Long workers;
	private transient String male;
	private transient String female;
	private transient String ratio;

	public String getStatename() {
		return statename;
	}

	public void setStatename(String statename) {
		this.statename = statename;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
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

	public String getMale() {
		return male;
	}

	public void setMale(String male) {
		this.male = male;
	}

	public String getFemale() {
		return female;
	}

	public void setFemale(String female) {
		this.female = female;
	}

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public String getPersonsLiterate() {
		return personsLiterate;
	}

	public void setPersonsLiterate(String personsLiterate) {
		this.personsLiterate = personsLiterate;
	}

	public String getMalesliterate() {
		return malesliterate;
	}

	public void setMalesliterate(String malesliterate) {
		this.malesliterate = malesliterate;
	}

	public String getFemalsesliterate() {
		return femalsesliterate;
	}

	public void setFemalsesliterate(String femalsesliterate) {
		this.femalsesliterate = femalsesliterate;
	}

	public String getPersonsliteracyRate() {
		return personsliteracyRate;
	}

	public void setPersonsliteracyRate(String personsliteracyRate) {
		this.personsliteracyRate = personsliteracyRate;
	}

	public String getMalesLiteracyRate() {
		return malesLiteracyRate;
	}

	public void setMalesLiteracyRate(String malesLiteracyRate) {
		this.malesLiteracyRate = malesLiteracyRate;
	}

	public String getFemalesLiteracyRate() {
		return femalesLiteracyRate;
	}

	public void setFemalesLiteracyRate(String femalesLiteracyRate) {
		this.femalesLiteracyRate = femalesLiteracyRate;
	}

	public String getMainworkers() {
		return mainworkers;
	}

	public void setMainworkers(String mainworkers) {
		this.mainworkers = mainworkers;
	}

	public String getMarginalworkers() {
		return marginalworkers;
	}

	public void setMarginalworkers(String marginalworkers) {
		this.marginalworkers = marginalworkers;
	}

	public String getNonworkers() {
		return nonworkers;
	}

	public void setNonworkers(String nonworkers) {
		this.nonworkers = nonworkers;
	}

	private transient String personsLiterate;
	private transient String malesliterate;
	private transient String femalsesliterate;
	private transient String personsliteracyRate;
	private transient String malesLiteracyRate;
	private transient String femalesLiteracyRate;
	private transient String mainworkers;
	private transient String marginalworkers;
	private transient String nonworkers;

}
