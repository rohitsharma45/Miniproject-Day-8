package com.cg.uas.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cg.uas.bean.ApplicationBean;
import com.cg.uas.bean.LoginBean;
import com.cg.uas.bean.ParticipantBean;
import com.cg.uas.bean.ProgramsOfferedBean;
import com.cg.uas.bean.ProgramsScheduledBean;
import com.cg.uas.exception.UniversityException;

public interface IUasDao {

	int insertData(ApplicationBean bean) throws UniversityException;

	String getProgramId() throws UniversityException;


	ArrayList<LoginBean> allLogin(LoginBean mac) throws UniversityException;

	ArrayList<LoginBean> adminLogin(LoginBean mac) throws UniversityException;

	String getId(String id) throws UniversityException;

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

	int addProgramOffered(ProgramsOfferedBean pgmbean) throws UniversityException;

	int deleteProgramOffered(String programName) throws UniversityException;

	int updateDetail(ProgramsOfferedBean pgmbean) throws UniversityException;

	int addScheduleProgram(ProgramsScheduledBean progmbean) throws UniversityException;

	int deleteProgramSchedule(String programSchId) throws UniversityException;

	ArrayList<ApplicationBean> getApplicantStatusList(String programName) throws UniversityException;

	ArrayList<String> retrievePgmId() throws UniversityException;

	ArrayList<String> retrievePgmName() throws UniversityException;

	ArrayList<ApplicationBean> getApplicantStatList(String programSchId) throws UniversityException;


	int insertParticipant(ParticipantBean participant) throws UniversityException;

	ArrayList<ParticipantBean> retrieveParticipant1() throws UniversityException;

	ArrayList<ApplicationBean> retrieveApplicant() throws UniversityException;

	
}
