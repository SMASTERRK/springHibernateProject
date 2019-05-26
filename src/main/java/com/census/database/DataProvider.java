package com.census.database;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;


@Component("regDetails")
@Entity
@Table(name ="user_info")
public class DataProvider {

	@Column(name="firstName")
	private String fname;
	@Column(name="lastName")
	private String lname;
	@Id
	@Column(name="userName")
	private String uname;
	@Column(name="password")
	private String pword;
	@Column(name="email")
	private String email;
	@Column(name="mobile")
	private String monbile;
	@Column(name="role")
	private  String  role;
	@Column(name="status")
	private String status;
	@Transient
	private String rpword;
	@Column(name="regDate")
	private Date regDate;
	
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getRpword() {
		return rpword;
	}
	public void setRpword(String rpword) {
		this.rpword = rpword;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPword() {
		return pword;
	}
	public void setPword(String pword) {
		this.pword = pword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMonbile() {
		return monbile;
	}
	public void setMonbile(String monbile) {
		this.monbile = monbile;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public DataProvider() {
		super();
		
	}
	public DataProvider(String fname, String lname, String uname, String pword,
			String email, String monbile, String role, String rpword) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.uname = uname;
		this.pword = pword;
		this.email = email;
		this.monbile = monbile;
		this.role = role;
		this.rpword = rpword;
	}
	
	
}
