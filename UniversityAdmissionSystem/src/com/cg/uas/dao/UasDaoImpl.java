package com.cg.uas.dao;

import java.io.IOException;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.uas.bean.LoginBean;
import com.cg.uas.bean.ApplicationBean;
import com.cg.uas.bean.ParticipantBean;
import com.cg.uas.bean.ProgramsScheduledBean;
import com.cg.uas.bean.ProgramsOfferedBean;
import com.cg.uas.dbutil.DbUtil;
import com.cg.uas.exception.UniversityException;

public class UasDaoImpl implements IUasDao {

	ProgramsOfferedBean pgmOfferedbean=new ProgramsOfferedBean();
	Connection conn=null;
	Logger logger=Logger.getRootLogger();
	public UasDaoImpl(){
		PropertyConfigurator.configure("Log4j.properties");
	}
	int result=0;
	public int insertData(ApplicationBean bean) throws UniversityException {
		try {
			conn=DbUtil.getConnection();
			
		String insertQuery="Insert into Application values(Application_id_seq.nextval,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps=conn.prepareStatement(insertQuery);
		ps.setString(1,bean.getFullName());
		ps.setDate(2,bean.getDateOfBirth());
		ps.setString(3, bean.getQualification());
		ps.setInt(4,bean.getMarks());
		ps.setString(5,bean.getGoals());
		ps.setString(6,bean.getEmail());
		ps.setString(7,bean.getProgramId());
		ps.setString(8,bean.getStatus());
		ps.setDate(9,bean.getInterviewDate());
		ps.setString(10,bean.getUniversity());
		
		int res=ps.executeUpdate();
		
		
		String sql="select Application_id_seq.currval from Application";
		Statement pst=conn.createStatement();
		
		ResultSet rs=pst.executeQuery(sql);
		
		while(rs.next())
		{
			result=rs.getInt(1);
		}
		logger.info("Executed successfully");
		} catch (IOException|SQLException e) {
			logger.error("Error Message"+e.getMessage());
			//System.out.println("Error Message"+e.getMessage());
			throw new UniversityException(e.getMessage());	
			
		}
		return result;
	}
	

	@Override
	public ArrayList<LoginBean> allLogin(LoginBean mac) throws UniversityException {
		String login=null;
		String password=null;
		ArrayList<LoginBean> list = new ArrayList<LoginBean>();
		try {
			conn=DbUtil.getConnection();
			
			
			String sql="select login_id,password from users where role IN ('MAC')";
			Statement pst=conn.createStatement();
			
			ResultSet rs=pst.executeQuery(sql);
			
			
			while(rs.next())
			{
				
				login=rs.getString(1);
				
				password=rs.getString(2);
				
				list.add(new LoginBean(login,password));
				
			}
			logger.info("Executed successfully");

		} catch (IOException | SQLException e) {
			logger.error("Error Message"+e.getMessage());
			throw new UniversityException(e.getMessage());
		}

		return list;
	}
	@Override
	public ArrayList<LoginBean> adminLogin(LoginBean mac) throws UniversityException {
		String login=null;
		String password=null;
		ArrayList<LoginBean> list = new ArrayList<LoginBean>();
		try {
			conn=DbUtil.getConnection();
			
			
			String sql="select login_id,password from users where role IN ('Admin')";
			Statement pst=conn.createStatement();
			
			ResultSet rs=pst.executeQuery(sql);
			
			
			while(rs.next())
			{
				
				login=rs.getString(1);
				
				password=rs.getString(2);
				
				list.add(new LoginBean(login,password));
			
			}
			logger.info("Executed successfully");
			
		} catch (IOException | SQLException e) {
			logger.error("Error Message"+e.getMessage());
			throw new UniversityException(e.getMessage());
		}
		
		return list;
	}
	@Override
	public String getId(String id) throws UniversityException {
		String programId=null;
		ArrayList list = new ArrayList();
		try {
			conn=DbUtil.getConnection();

			String sql="select Scheduled_program_id from Programs_Scheduled where ProgramName IN (?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				programId=rs.getString(1);
				
			}
			logger.info("Executed successfully");
	}
		catch(IOException | SQLException e) {
			logger.error("Error Message"+e.getMessage());
			throw new UniversityException(e.getMessage());
		}

		return programId;
}
	
	public ArrayList<String> retrieveDetails(String university) throws UniversityException {
		ArrayList<String> list = new ArrayList<String>();
		try {
			conn=DbUtil.getConnection();
			
			
			String sql="select * from Programs_Offered where University_name= ? ";
			PreparedStatement pst=conn.prepareStatement(sql);
			
			pst.setString(1,university);
			
			ResultSet rs=pst.executeQuery();
			
			
			while(rs.next())
			{
				String programName=rs.getString(1);
				String description=rs.getString(2);
				String eligibility=rs.getString(3);
				Long duration=rs.getLong(4);
				String courseDuration=(duration).toString();
				String degreeCertificate=rs.getString(5);
				list.add("Program Name: "+programName);
				list.add("Description of course: "+description);
				list.add("Eligibility Criteria: "+eligibility);
				list.add("Duration of course(in years): "+courseDuration);
				list.add("Degree certificate: "+degreeCertificate);
				list.add(" ");
			}
			System.out.println(" ");
			logger.info("Executed successfully");
	}
		catch(IOException | SQLException e) {
			logger.error("Error Message"+e.getMessage());
			throw new UniversityException(e.getMessage());
		}

		return list;
	}
	@Override
	public ArrayList<ApplicationBean> getApplicantList(String programName) throws UniversityException {
		ArrayList<ApplicationBean> list = new ArrayList<ApplicationBean>();

		try {
			conn=DbUtil.getConnection();
			
			String sql="select * from Application where status  LIKE 'Applied' AND Scheduled_program_id=(select Scheduled_program_id from Programs_Scheduled where ProgramName IN (?))";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1,programName);
			
			ResultSet rs=ps.executeQuery();

			while(rs.next())
			{
				
				int id=rs.getInt(1);
				String fullName=rs.getString(2);
				Date dob=rs.getDate(3);
				String qualification=rs.getString(4);
				int marksObt=rs.getInt(5);
				String goals=rs.getString(6);
				String emailId=rs.getString(7);
				String pgmId=rs.getString(8);
				String status=rs.getString(9);
				Date dateOfInterview=rs.getDate(10);
				
				list.add(new ApplicationBean(id,fullName,dob,qualification,marksObt,goals,emailId,pgmId,status,dateOfInterview));
			}
			logger.info("Executed successfully");
		}
		catch(IOException | SQLException e) {
			logger.error("Error Message"+e.getMessage());
			throw new UniversityException(e.getMessage());
		}
		return list;
	}
	@Override
	public int updateStatus(ApplicationBean bean) throws UniversityException {
		int  result=0;
		
	       try
	       {
			conn= DbUtil.getConnection();
			String insertQuery= "Update application set status=?,date_of_interview=? where application_id=? ";
			PreparedStatement ps= conn.prepareStatement(insertQuery);
			ps.setString(1,bean.getStatus());
			ps.setDate(2,bean.getInterviewDate());
			
			ps.setInt(3,bean.getApplicationId());
			
			result=ps.executeUpdate();
			 logger.info("Executed successfully");
		}
	      
	       catch(IOException | SQLException e) {
	    	   logger.error("Error Message"+e.getMessage());
	    	   throw new UniversityException(e.getMessage());
			}
	       return result;
		
	}
	@Override
	public ArrayList<ApplicationBean> retrieveStatus(String programName) throws UniversityException {
		ArrayList<ApplicationBean> list = new ArrayList<ApplicationBean>();
		int id=0;
		String fullName=null;
		String status=null;
		
		try {
			conn=DbUtil.getConnection();
			
			String sql="select Application_id,full_name,status from Application where status LIKE 'ACCEPTED' "; 
			
			Statement pst=conn.createStatement();
			
			ResultSet rs=pst.executeQuery(sql);
			
		
			while(rs.next())
			{
				id=rs.getInt(1);
			
				fullName=rs.getString(2);
				
				status=rs.getString(3);
				
				list.add(new ApplicationBean(id,fullName,status));
			}
			logger.info("Executed successfully");
		}
		catch(IOException | SQLException e) {
			logger.error("Error Message"+e.getMessage());
			throw new UniversityException(e.getMessage());
		}

		return list;
	}
	@Override
	public int updateCnfStatus(ApplicationBean bean) throws UniversityException {
		int  result=0;
		
	       try
	       {
			conn= DbUtil.getConnection();
			String insertQuery= "Update application set status=? where application_id=? ";
			PreparedStatement ps= conn.prepareStatement(insertQuery);
			ps.setString(1,bean.getStatus());
			ps.setInt(2,bean.getApplicationId());
			
			result=ps.executeUpdate();
			logger.info("Executed successfully");
		}
	       catch(IOException | SQLException e) {
	    	   logger.error("Error Message"+e.getMessage());
	    	   throw new UniversityException(e.getMessage());
			}
	       return result;

	}
	@Override
	public ArrayList<ApplicationBean> getCnfApplicantList(String programName) throws UniversityException {
		ArrayList<ApplicationBean> list = new ArrayList<ApplicationBean>();
		int id=0;
		String fullName=null;
		String status=null;
		
		try {
			conn=DbUtil.getConnection();
			
			String sql="select Application_id,full_name,status from Application where status NOT LIKE 'Applied' AND Scheduled_program_id=(select Scheduled_program_id from Programs_Scheduled where ProgramName IN (?))";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1,programName);
			
			ResultSet rs=ps.executeQuery();
			
		
			while(rs.next())
			{
				id=rs.getInt(1);
				fullName=rs.getString(2);
				status=rs.getString(3);
				
				list.add(new ApplicationBean(id,fullName,status));
			}
			logger.info("Executed successfully");
		}
		catch(IOException | SQLException e) {
			logger.error("Error Message"+e.getMessage());
			throw new UniversityException(e.getMessage());
		}
		return list;
	}
	@Override
	public ArrayList<ProgramsScheduledBean> retrievePgms() throws UniversityException {
		ArrayList<ProgramsScheduledBean> list = new ArrayList<ProgramsScheduledBean>();
		try {
			conn=DbUtil.getConnection();
			
			
			String sql="select * from Programs_Scheduled ";
			PreparedStatement ps=conn.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();

			while(rs.next())
			{
				String programID=rs.getString(1);
				String description=rs.getString(2);
				String location=rs.getString(3);
				Date startDate=rs.getDate(4);
				Date endDate=rs.getDate(5);
				int sessions=rs.getInt(6);
				String university=rs.getString(7);
				list.add(new ProgramsScheduledBean(programID,description,location,startDate,endDate,sessions,university));

			}
			logger.info("Executed successfully");

	}
		catch(IOException | SQLException e) {
			logger.error("Error Message"+e.getMessage());
			throw new UniversityException(e.getMessage());
		}
		return list;
	}
	@Override
	public ArrayList<ApplicationBean> getApplicantStatus(int applicationId) throws UniversityException {
		ArrayList<ApplicationBean> list = new ArrayList<ApplicationBean>();
		int id=0;
		String fullName=null;
		String status=null;
		
		try {
			conn=DbUtil.getConnection();
	

			String sql="select Application_id,full_name,status,Date_Of_Interview from Application where Application_id=?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1,applicationId);
			
			ResultSet rs=ps.executeQuery();

			while(rs.next())
			{
				id=rs.getInt(1);
				fullName=rs.getString(2);
				status=rs.getString(3);
				Date interviewDate=rs.getDate(4);
				
				list.add(new ApplicationBean(id,fullName,status,interviewDate));
			}
			logger.info("Executed successfully");
		}
		catch(IOException | SQLException e) {
			logger.error("Error Message"+e.getMessage());
			throw new UniversityException(e.getMessage());
		}
		return list;
	}
	@Override
	public ArrayList<String> retrievePrograms() throws UniversityException {
		ArrayList<String> list = new ArrayList<String>();
		
		String programName=null;
		
		
		try {
			conn=DbUtil.getConnection();
			
			String sql="select ProgramName from Programs_Offered";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs=ps.executeQuery();

			while(rs.next())
			{
				programName=rs.getString(1);

				list.add(programName);
			}
			logger.info("Executed successfully");
		}
		catch(IOException | SQLException e) {
			logger.error("Error Message"+e.getMessage());
			throw new UniversityException(e.getMessage());
		}
		return list;
	}
	@Override
	public ArrayList<ApplicationBean> retrieveAllDetails() throws UniversityException {
	ArrayList<ApplicationBean> list = new ArrayList<ApplicationBean>();

		try {
			conn=DbUtil.getConnection();
			
			String sql="select * from Application where status  LIKE 'Applied' ";
			
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs=ps.executeQuery();

			while(rs.next())
			{
				
				int id=rs.getInt(1);
				String fullName=rs.getString(2);
				Date dob=rs.getDate(3);
				String qualification=rs.getString(4);
				int marksObt=rs.getInt(5);
				String goals=rs.getString(6);
				String emailId=rs.getString(7);
				String pgmId=rs.getString(8);
				String status=rs.getString(9);
				Date dateOfInterview=rs.getDate(10);
				
				list.add(new ApplicationBean(id,fullName,dob,qualification,marksObt,goals,emailId,pgmId,status,dateOfInterview));
			}
			logger.info("Executed successfully");
		
		}
		catch(IOException | SQLException e) {
			logger.error("Error Message"+e.getMessage());
			throw new UniversityException(e.getMessage());
		}
		return list;
	}
	
	@Override
	public int addProgramOffered(ProgramsOfferedBean pgmbean) throws UniversityException {
		
		int result=0;
	       try
	       {
			conn= DbUtil.getConnection();
			String insertQuery= "INSERT INTO Programs_Offered values(?,?,?,?,?,?)";
			PreparedStatement ps= conn.prepareStatement(insertQuery);
			ps.setString(1, pgmbean.getProgramName());
			ps.setString(2, pgmbean.getDesc());
			ps.setString(3, pgmbean.getEligibility());
			ps.setInt(4, pgmbean.getDuration());
			ps.setString(5, pgmbean.getCertificate());
			ps.setString(6, pgmbean.getUniversity());

	
			result=ps.executeUpdate();
			logger.info("Executed successfully");
		}
		catch(IOException|SQLException e)
		{
			logger.error("Error Message"+e.getMessage());
			throw new UniversityException(e.getMessage());
		}
	
return result;
}
	@Override
	public int deleteProgramOffered(String programName) throws UniversityException {
		Connection conn= null;
		try{
			
			conn= DbUtil.getConnection();
			String sql = "delete from Participant where Scheduled_program_id=(select Scheduled_program_id from Programs_Scheduled where ProgramName=?)";
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setString(1,programName);
			int res=ps.executeUpdate();
			String sql1 = "delete from Application where Scheduled_program_id=(select Scheduled_program_id from Programs_Scheduled where ProgramName=?)";
			PreparedStatement pst= conn.prepareStatement(sql1);
			pst.setString(1,programName);
			int res1=pst.executeUpdate();
			String sql2 = "delete from Programs_Scheduled where ProgramName=? ";
			PreparedStatement pstr= conn.prepareStatement(sql2);
			pstr.setString(1,programName);
			int res2=pstr.executeUpdate();
			String sql3="delete from Programs_Offered where ProgramName =?";
			PreparedStatement pstrq= conn.prepareStatement(sql3);
			pstrq.setString(1,programName);
			result=pstrq.executeUpdate();
			
			
			logger.info("Executed successfully");
		}
		catch(IOException | SQLException e)
		{
			logger.error("Error Message"+e.getMessage());
			throw new UniversityException(e.getMessage());
		}
		return result;
		
	}
	
	@Override
	public int addScheduleProgram(ProgramsScheduledBean progmbean) throws UniversityException {
		int  result=0;
		
	       try
	       {
			conn= DbUtil.getConnection();
			String insertQuery= "insert into Programs_Scheduled values(?,?,?,?,?,?,?)";
			PreparedStatement ps= conn.prepareStatement(insertQuery);
			ps.setString(1, progmbean.getSchedulePgmId());
			ps.setString(2, progmbean.getProgramName());
			ps.setString(3, progmbean.getLocation());
			ps.setDate(4, progmbean.getStartDate());
			ps.setDate(5, progmbean.getEndDate());
			ps.setInt(6, progmbean.getSessionsPerWeek());
			ps.setString(7, progmbean.getUniversity());

			result=ps.executeUpdate();
			logger.info("Executed successfully");
		}
		catch(IOException|SQLException e)
		{
			logger.error("Error Message"+e.getMessage());
			throw new UniversityException(e.getMessage());
		}
	
			return result; 
	}
	@Override
	public int deleteProgramSchedule(String programSchId) throws UniversityException {
		Connection conn= null;
		int result=0;
		try{
			conn= DbUtil.getConnection();
			String sql = "delete from Participant where Scheduled_program_id=?";
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setString(1,programSchId);
			int res=ps.executeUpdate();
			String sql1 = "delete from Application where Scheduled_program_id=?";
			PreparedStatement pst= conn.prepareStatement(sql1);
			pst.setString(1,programSchId);
			int res1=pst.executeUpdate();
			String sql2 = "delete from Programs_Scheduled where Scheduled_program_id  = ? ";
			PreparedStatement pstr= conn.prepareStatement(sql2);
			pstr.setString(1,programSchId);
			result=pstr.executeUpdate();
			
			logger.info("Executed successfully");
		}
		catch(IOException|SQLException e)
		{
			logger.error("Error Message"+e.getMessage());
			throw new UniversityException(e.getMessage());
		}
		return result;

		
	}
	
	@Override
	public ArrayList<ApplicationBean> getApplicantStatusList(String programName) throws UniversityException {
		ArrayList<ApplicationBean> list = new ArrayList<ApplicationBean>();

		try {
			conn=DbUtil.getConnection();
			
			String sql="select Application_id,status  from Application WHERE Scheduled_program_id =(select Scheduled_program_id from Programs_Scheduled where ProgramName = ?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, programName);
			
			ResultSet rs=ps.executeQuery();

			while(rs.next())
			{
				
				int applicationId=rs.getInt(1);
				String status=rs.getString(2);
				
				list.add(new ApplicationBean(applicationId,status));
			
			}
			logger.info("Executed successfully");
		}
		catch(IOException | SQLException e) {
			logger.error("Error Message"+e.getMessage());
			throw new UniversityException(e.getMessage());
		}
		return list;
	}


	@Override
	public String getProgramId() {
	
		return null;
	}


	@Override
	public ArrayList<String> retrievePgmId() throws UniversityException {
		ArrayList<String> list = new ArrayList<String>();
		try {
			conn=DbUtil.getConnection();
			
			
			String sql="select Scheduled_program_id  from Programs_Scheduled ";
			PreparedStatement ps=conn.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();

			while(rs.next())
			{
				String programID=rs.getString(1);
			
				list.add(programID);

			}
			logger.info("Executed successfully");

	}
		catch(IOException | SQLException e) {
			logger.error("Error Message"+e.getMessage());
			throw new UniversityException(e.getMessage());
		}
		return list;
	}


	@Override
	public ArrayList<String> retrievePgmName() throws UniversityException {
		ArrayList<String> list = new ArrayList<String>();
		try {
			conn=DbUtil.getConnection();
			
			
			String sql="select ProgramName from Programs_Offered ";
			PreparedStatement ps=conn.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();

			while(rs.next())
			{
				String programName=rs.getString(1);
			
				list.add(programName);

			}
			logger.info("Executed successfully");
	}
		catch(IOException | SQLException e) {
			logger.error("Error Message"+e.getMessage());
			throw new UniversityException(e.getMessage());
		}
		return list;
	}


	@Override
	public ArrayList<ApplicationBean> getApplicantStatList(String programSchId) throws UniversityException {
		ArrayList<ApplicationBean> list = new ArrayList<ApplicationBean>();

		try {
			conn=DbUtil.getConnection();
			
			String sql="select Application_id,status  from Application WHERE Scheduled_program_id =?";
			
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, programSchId);
			
			ResultSet rs=ps.executeQuery();

			while(rs.next())
			{
				
				int applicationId=rs.getInt(1);
				String status=rs.getString(2);
				
				list.add(new ApplicationBean(applicationId,status));
			
			}
			logger.info("Executed successfully");
		
		}
		catch(IOException | SQLException e) {
			logger.error("Error Message"+e.getMessage());
			throw new UniversityException(e.getMessage());
		}
		return list;
	}

	@Override
	public int insertParticipant(ParticipantBean participant) throws UniversityException {
		try {
			conn=DbUtil.getConnection();
			
		String insertQuery="Insert into Participant values(Roll_no_seq.nextval,?,?,?,?)";
		PreparedStatement ps=conn.prepareStatement(insertQuery);

		ps.setString(1,participant.getEmailId());
		ps.setInt(2, participant.getApplicationId());
		ps.setString(3,participant.getPgmId());
		ps.setString(4,participant.getUniversity());
		
		int result=ps.executeUpdate();	
		logger.info("Executed successfully");
		}
		catch(IOException|SQLException e){
			logger.error("Error Message"+e.getMessage());
			throw new UniversityException(e.getMessage());	
		}
		return result;
	
	}


	@Override
	public int updateDetail(ProgramsOfferedBean pgmbean)
			throws UniversityException {
		int  result=0;
		
	       try
	       {
			conn= DbUtil.getConnection();
			String insertQuery= "UPDATE Programs_Offered SET description=?,applicant_eligibility  =?,duration = ?,degree_certificate_offered =? where ProgramName=?";
			PreparedStatement ps= conn.prepareStatement(insertQuery);
			
			ps.setString(1, pgmbean.getDesc());
			ps.setString(2, pgmbean.getEligibility());
			ps.setInt(3, pgmbean.getDuration());
			ps.setString(4, pgmbean.getCertificate());
			ps.setString(5, pgmbean.getProgramName());
			ps.setString(6,pgmbean.getUniversity());
			result=ps.executeUpdate();
			logger.info("Executed successfully");
		}
		catch(IOException|SQLException e)
		{
			logger.error("Error Message"+e.getMessage());
			throw new UniversityException(e.getMessage());
		}
	
		return result;
		
	}


	@Override
	public ArrayList<ParticipantBean> retrieveParticipant1() throws UniversityException {
		ArrayList<ParticipantBean> list = new ArrayList<ParticipantBean>();

		try {
			conn=DbUtil.getConnection();
			
			String sql="select * from participant order by roll_no";
			
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs=ps.executeQuery();

			while(rs.next())
			{
				
				int rollNo=rs.getInt(1);
				String emailId=rs.getString(2);
				int id=rs.getInt(3);
				String pgmId=rs.getString(4);
				String university=rs.getString(5);

				list.add(new ParticipantBean(rollNo,emailId,id,pgmId,university));
			}
			logger.info("Executed successfully");
		
		}
		catch(IOException | SQLException e) {
			logger.error("Error Message"+e.getMessage());
			throw new UniversityException(e.getMessage());
		}
		return list;
	}


	@Override
	public ArrayList<ApplicationBean> retrieveApplicant() throws UniversityException {
		ArrayList<ApplicationBean> list = new ArrayList<ApplicationBean>();

		try {
			conn=DbUtil.getConnection();
			
			String sql="select * from Application where status  LIKE 'CONFIRMED' ";
			
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs=ps.executeQuery();

			while(rs.next())
			{
				
				int id=rs.getInt(1);
				String fullName=rs.getString(2);
				Date dob=rs.getDate(3);
				String qualification=rs.getString(4);
				int marksObt=rs.getInt(5);
				String goals=rs.getString(6);
				String emailId=rs.getString(7);
				String pgmId=rs.getString(8);
				String status=rs.getString(9);
				Date dateOfInterview=rs.getDate(10);
				String university=rs.getString(11);
				
				list.add(new ApplicationBean(id,fullName,dob,qualification,marksObt,goals,emailId,pgmId,status,dateOfInterview,university));
			}
			logger.info("Executed successfully");
		
		}
		catch(IOException | SQLException e) {
			logger.error("Error Message"+e.getMessage());
			throw new UniversityException(e.getMessage());
		}
		return list;
	}}
