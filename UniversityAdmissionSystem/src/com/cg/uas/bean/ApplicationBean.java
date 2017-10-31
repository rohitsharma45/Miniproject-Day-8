package com.cg.uas.bean;

import java.sql.Date;

public class ApplicationBean {

	@Override
	public String toString() {
		return "ApplicationBean [applicationId=" + applicationId
				+ ", fullName=" + fullName + ", dateOfBirth=" + dateOfBirth
				+ ", qualification=" + qualification + ", marks=" + marks
				+ ", goals=" + goals + ", email=" + email + ", programId="
				+ programId + ", status=" + status + ", interviewDate="
				+ interviewDate + ", university=" + university + "]";
	}

	private int applicationId;
	private	String fullName;
	private Date dateOfBirth;
	private String qualification;
	private int marks;
	private String goals;
	private String email;
	private String programId;
	private String status;
	private Date interviewDate;
	private String university;
	
	public ApplicationBean(){
		
	}
	
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public String getGoals() {
		return goals;
	}
	public void setGoals(String goals) {
		this.goals = goals;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getInterviewDate() {
		return interviewDate;
	}
	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	

	public ApplicationBean(int applicationId) {
		super();
		this.applicationId = applicationId;
	}
	public ApplicationBean(int applicationId, String fullName, String status,Date interviewDate) {
		super();
		this.applicationId = applicationId;
		this.fullName = fullName;
		this.status = status;
		this.interviewDate=interviewDate;
	}



	public ApplicationBean(int applicationId, String fullName,
			Date dateOfBirth, String qualification, int marks, String goals,
			String email, String programId, String status, Date interviewDate) {
		super();
		this.applicationId = applicationId;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.qualification = qualification;
		this.marks = marks;
		this.goals = goals;
		this.email = email;
		this.programId = programId;
		this.status = status;
		this.interviewDate = interviewDate;
	}



	public ApplicationBean(int applicationId, String fullName, String status) {
		super();
		this.applicationId = applicationId;
		this.fullName = fullName;
		this.status = status;
	}

	public ApplicationBean(int applicationId, String status) {
		super();
		this.applicationId = applicationId;
		this.status = status;
	}

	public ApplicationBean(String emailId, int id, String pgmId,
			String university) {
		this.email=emailId;
		this.applicationId=id;
		this.programId=pgmId;
		this.university=university;
	}

	public ApplicationBean(int applicationId, String fullName,
			Date dateOfBirth, String qualification, int marks, String goals,
			String email, String programId, String status, Date interviewDate,
			String university) {
		super();
		this.applicationId = applicationId;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.qualification = qualification;
		this.marks = marks;
		this.goals = goals;
		this.email = email;
		this.programId = programId;
		this.status = status;
		this.interviewDate = interviewDate;
		this.university = university;
	}



	
}
