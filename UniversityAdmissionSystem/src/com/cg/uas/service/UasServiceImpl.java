package com.cg.uas.service;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

import com.cg.uas.bean.ApplicationBean;
import com.cg.uas.bean.LoginBean;
import com.cg.uas.bean.ParticipantBean;
import com.cg.uas.bean.ProgramsOfferedBean;
import com.cg.uas.bean.ProgramsScheduledBean;
import com.cg.uas.dao.IUasDao;
import com.cg.uas.dao.UasDaoImpl;
import com.cg.uas.exception.UniversityException;

public class UasServiceImpl implements IUasService {

	IUasDao dao=null;
	public int insertData(ApplicationBean bean) throws UniversityException {
		 dao=new UasDaoImpl();
		return dao.insertData(bean);
	}
	public String getProgramId() throws UniversityException  {
		dao=new UasDaoImpl();
			return dao.getProgramId();
	}
	@Override
	public boolean checkLogin(LoginBean mac) throws UniversityException{
		dao=new UasDaoImpl();
		ArrayList<LoginBean> list = null;
		list = dao.allLogin(mac);
		
		int flag = 0;
		for(LoginBean b:list)
		{
		
			if((b.getLoginId().equals(mac.getLoginId())) && (b.getPassword().equals(mac.getPassword()))){
				flag = 1;
			
				break;
			}
				
		}
		
		if(flag == 1){
			return true;
		}
		else return false;
		
	}
	@Override
	public boolean checkAdminLogin(LoginBean mac) throws UniversityException{
		dao=new UasDaoImpl();
		ArrayList<LoginBean> list = null;
		list = dao.adminLogin(mac);
		
		int flag = 0;
		for(LoginBean b:list)
		{
			
			if((b.getLoginId().equals(mac.getLoginId())) && (b.getPassword().equals(mac.getPassword()))){
				flag = 1;
				break;
			}
				
		}
		
		if(flag == 1){
			return true;
		}
		else return false;
		
	
	}
	@Override
	public String getId(String id) throws UniversityException  {
		
		dao=new UasDaoImpl();
		return dao.getId(id);
	}
	
	public ArrayList<String> retrieveDetails(String university) throws UniversityException  {
		dao=new UasDaoImpl();
		return dao.retrieveDetails(university);
	}
	@Override
	public ArrayList<ApplicationBean> getApplicantList(String programName) throws UniversityException  {
		
		dao=new  UasDaoImpl();
		return dao.getApplicantList(programName);
	}
	@Override
	public int updateStatus(ApplicationBean bean) throws UniversityException {
		dao=new  UasDaoImpl();
		return dao.updateStatus(bean);
	}
	@Override
	public ArrayList<ApplicationBean> retrieveStatus(String pgmName) throws UniversityException  {
		dao=new  UasDaoImpl();
		return dao.retrieveStatus(pgmName);
	}
	@Override
	public int updateCnfStatus(ApplicationBean bean) throws UniversityException  {
		dao=new  UasDaoImpl();
		return dao.updateCnfStatus(bean);
	}
	@Override
	public ArrayList<ApplicationBean> getCnfApplicantList(String programName) throws UniversityException  {
		dao=new  UasDaoImpl();
		return dao.getCnfApplicantList(programName);
	}

	@Override
	public ArrayList<ApplicationBean> getApplicantStatus(int applicationId) throws UniversityException  {
		dao=new  UasDaoImpl();
		return dao.getApplicantStatus(applicationId);
	}
	@Override
	public ArrayList<String> retrievePrograms() throws UniversityException  {
		dao=new  UasDaoImpl();
		return dao.retrievePrograms();
	}
	@Override
	public ArrayList<ApplicationBean> retrieveAllDetails() throws UniversityException  {
		dao=new  UasDaoImpl();
		return dao.retrieveAllDetails();
	}
	@Override
	public ArrayList<ProgramsScheduledBean> retrievePgms() throws UniversityException  {
		dao=new  UasDaoImpl();;
		return dao.retrievePgms();
	}
	@Override
	public boolean validateForm(String qualification){
		if(qualification.equalsIgnoreCase("HSC")){
			return true;
		}
		else{
			System.out.println("Your qualification should be HSC");
			System.exit(0);
			return false;
		}
		
	}
	@Override
	public int addProgramOffered(
			ProgramsOfferedBean pgmbean)  throws UniversityException {
		dao=new  UasDaoImpl();
		return dao.addProgramOffered(pgmbean);
	}
	@Override
	public int deleteProgramOffered(String programName) throws UniversityException  {
		dao=new UasDaoImpl();
		int flag=0;
		ArrayList<ApplicationBean> list1=dao.getApplicantStatusList(programName);
		if(list1.isEmpty()){
			flag=1;
			return dao.deleteProgramOffered(programName);
			
		}
		for(ApplicationBean l:list1)
		{
			if(l.getStatus().equalsIgnoreCase("ACCEPTED")||l.getStatus().equalsIgnoreCase("CONFIRMED")||l.getStatus().equalsIgnoreCase("APPLIED"))
			{ 
				
				flag=0;
				break;
			}
			else if(l.getApplicationId()!=0 && l.getStatus().equalsIgnoreCase("REJECTED"))
			{
				
				flag=1;
			
				return dao.deleteProgramOffered(programName);
			}
		}
		return flag;
		
		
	}
	@Override
	public int updateDetail(ProgramsOfferedBean pgmbean) throws UniversityException  {
		dao=new UasDaoImpl();
		return dao.updateDetail(pgmbean);
	}
	
	@Override
	public int deleteProgramSchedule(String programSchId) throws UniversityException  {
		dao=new UasDaoImpl();
		int flag=0;
		ArrayList<ApplicationBean> list1=dao.getApplicantStatList(programSchId);
		if(list1.isEmpty()){
			flag=1;
			return dao.deleteProgramSchedule(programSchId);
			
		}
		for(ApplicationBean l:list1)
		{
			if(l.getStatus().equalsIgnoreCase("ACCEPTED")||l.getStatus().equalsIgnoreCase("CONFIRMED")||l.getStatus().equalsIgnoreCase("APPLIED"))
			{ 
				
				flag=0;
				break;
			}
			else if(l.getApplicationId()==0 || (l.getStatus().equalsIgnoreCase("REJECTED")))
			{

				flag=1;
				
				return dao.deleteProgramSchedule(programSchId);
			}
		}
		return flag;
		
	}



	@Override
	public boolean validateStatusCheck(ArrayList<ApplicationBean> statusCheck) {
		if(statusCheck.isEmpty()){
			System.out.println("Application Id not found");
			return false;}
		else{
		return true;
	}
	}
	@Override
	public ArrayList<String> retrievePgmId() throws UniversityException  {
		dao=new UasDaoImpl();
		return dao.retrievePgmId();
	}
	@Override
	public ArrayList<String> retrievePgmName() throws UniversityException  {
		dao=new UasDaoImpl();
		return dao.retrievePgmName();
	}
	@Override
	public boolean checkRetrieveStatus(ArrayList<ApplicationBean> list3) {
		if(list3.isEmpty()){
			System.out.println("No application is  available for confirmation");
			System.exit(0);
			return false;
			}
		else{
		return true;}
	}
	@Override
	public int addScheduleProgram(ProgramsScheduledBean progmbean) throws UniversityException  {
	
		return dao.addScheduleProgram(progmbean);
	}
	@Override
	public boolean checkPattern(String firstName) {
		String ptn="[A-Z]{1}[A-Za-z]{2,20}";
		if(Pattern.matches(ptn,firstName))
		{
			return true;
		}
		else
		{
			System.out.println("Please enter valid name with first letter capital and minimum of 3 characters");
			System.exit(0);
			return false;
		}
	}
	@Override
	public boolean checkConfirmList(ArrayList<ApplicationBean> cnfList) {
		if(cnfList.isEmpty()){
			System.out.println("No application is  available ");
			System.exit(0);
			return false;
			}
		else{
		return true;}
	}
	@Override
	public boolean checkAllDetails(ArrayList<ApplicationBean> allDetails) {
		boolean res=true;
		if(allDetails.isEmpty()){
			System.out.println("No application is  available ");
			res=false;
						
	}
		else{
			
		res=true;
		}
		return res;
	}
	@Override
	public boolean checkDetails(ArrayList<ApplicationBean> allDetails) {
		if(allDetails.isEmpty()){
			System.out.println("No application is  available ");
			System.exit(0);
			return false;
			}
		else{
		return true;}
	}
	@Override
	public boolean checkprogramSchedule(
			ArrayList<ProgramsScheduledBean> programSchedule) {
		if(programSchedule.isEmpty()){
			System.out.println("No program schedule is  available ");
			System.exit(0);
			return false;
			}
		else{
		return true;}
	}
	@Override
	public boolean validateDate(Date d,Date d1,Date d2) {
		if(d.before(d1) && d.after(d2)){
		
			return true;
		}
		else{
			System.out.println("Please enter valid date of birth and after 1 January 1999");
			System.exit(0);}
		return false;
	}
	@Override
	public boolean validateInterviewDate(Date interviewDate, Date d) {
		if(interviewDate.equals(d) || interviewDate.after(d))
		{
			return true;
		}
		else{
			System.out.println("Interview Date should be after today or today");
			System.exit(0);
		}
		return false;
	}

	@Override
	public int insertParticipant(ParticipantBean participant) throws UniversityException {
		dao=new UasDaoImpl();
		return dao.insertParticipant(participant);
	}
	@Override
	public ArrayList<ParticipantBean> retrieveParticipant1() throws UniversityException {
		dao=new UasDaoImpl();
		return dao.retrieveParticipant1();
	
}
	@Override
	public ArrayList<ApplicationBean> retrieveApplicant() throws UniversityException {
		dao=new UasDaoImpl();
		return dao.retrieveApplicant();
	}
}
