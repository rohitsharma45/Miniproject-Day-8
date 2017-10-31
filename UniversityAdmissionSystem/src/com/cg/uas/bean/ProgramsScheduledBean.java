package com.cg.uas.bean;

import java.sql.Date;

public class ProgramsScheduledBean {

	private String schedulePgmId,programName,location,university;
	private Date startDate,endDate;
	private int sessionsPerWeek;
	
	
	public ProgramsScheduledBean() {
	
	}
	
	public String getSchedulePgmId() {
		return schedulePgmId;
	}
	public void setSchedulePgmId(String schedulePgmId) {
		this.schedulePgmId = schedulePgmId;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getSessionsPerWeek() {
		return sessionsPerWeek;
	}
	public void setSessionsPerWeek(int sessionsPerWeek) {
		this.sessionsPerWeek = sessionsPerWeek;
	}
	
	public ProgramsScheduledBean(String schedulePgmId, String programName,
			String location, String university, Date startDate, Date endDate,
			int sessionsPerWeek) {
		super();
		this.schedulePgmId = schedulePgmId;
		this.programName = programName;
		this.location = location;
		this.university = university;
		this.startDate = startDate;
		this.endDate = endDate;
		this.sessionsPerWeek = sessionsPerWeek;
	}

	public ProgramsScheduledBean(String programID, String description,
			String location, Date startDate, Date endDate, int sessions,
			String university) {
		super();
		this.schedulePgmId = programID;
		this.programName = description;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
		this.sessionsPerWeek = sessions;
		this.university = university;
		
	}




	
}
