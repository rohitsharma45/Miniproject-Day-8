package com.cg.uas.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import com.cg.uas.bean.ApplicationBean;
import com.cg.uas.bean.LoginBean;
import com.cg.uas.bean.ParticipantBean;
import com.cg.uas.bean.ProgramsOfferedBean;
import com.cg.uas.bean.ProgramsScheduledBean;
import com.cg.uas.exception.UniversityException;

public interface IUasService {

	int insertData(ApplicationBean bean) throws UniversityException;

	String getProgramId() throws UniversityException;

	boolean checkLogin(LoginBean mac) throws UniversityException;

	boolean checkAdminLogin(LoginBean mac) throws UniversityException;

	String getId(String string) throws UniversityException;

	ArrayList<String> retrieveDetails(String university) throws UniversityException;

	ArrayList<ApplicationBean> getApplicantList(String programName) throws UniversityException;

	int updateStatus(ApplicationBean bean) throws UniversityException;

	ArrayList<ApplicationBean> retrieveStatus(String pgmName) throws UniversityException;

	int updateCnfStatus(ApplicationBean bean) throws UniversityException;

	ArrayList<ApplicationBean> getCnfApplicantList(String programName) throws UniversityException;

	ArrayList<ProgramsScheduledBean> retrievePgms() throws UniversityException;

	ArrayList<ApplicationBean> getApplicantStatus(int applicationId) throws UniversityException;

	ArrayList<String> retrievePrograms() throws UniversityException;

	ArrayList<ApplicationBean> retrieveAllDetails() throws UniversityException;

	boolean validateForm(String qualification);

	int addProgramOffered(ProgramsOfferedBean pgmbean) throws UniversityException;

	int deleteProgramOffered(String programName1) throws UniversityException;

	int updateDetail(ProgramsOfferedBean pgmbean) throws UniversityException;

	int addScheduleProgram(ProgramsScheduledBean progmbean) throws UniversityException;

	int deleteProgramSchedule(String programSchId) throws UniversityException;

	boolean checkPattern(String firstName);

	boolean validateStatusCheck(ArrayList<ApplicationBean> statusCheck);

	ArrayList<String> retrievePgmId() throws UniversityException;

	ArrayList<String> retrievePgmName() throws UniversityException;

	boolean checkRetrieveStatus(ArrayList<ApplicationBean> list3);

	boolean checkConfirmList(ArrayList<ApplicationBean> cnfList);

	boolean checkAllDetails(ArrayList<ApplicationBean> allDetails);

	boolean checkDetails(ArrayList<ApplicationBean> allDetails);

	boolean checkprogramSchedule(
			ArrayList<ProgramsScheduledBean> programSchedule);

	boolean validateDate(Date d, Date d1, Date d2);

	boolean validateInterviewDate(Date interviewDate, Date d);

	

	int insertParticipant(ParticipantBean participant) throws UniversityException;

	ArrayList<ParticipantBean> retrieveParticipant1() throws UniversityException;

	ArrayList<ApplicationBean> retrieveApplicant() throws UniversityException;

	
}
