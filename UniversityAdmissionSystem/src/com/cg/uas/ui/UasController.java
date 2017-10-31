package com.cg.uas.ui;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;


import com.cg.uas.bean.LoginBean;
import com.cg.uas.bean.ApplicationBean;
import com.cg.uas.bean.ParticipantBean;
import com.cg.uas.bean.ProgramsScheduledBean;
import com.cg.uas.bean.ProgramsOfferedBean;
import com.cg.uas.exception.UniversityException;
import com.cg.uas.service.IUasService;
import com.cg.uas.service.UasServiceImpl;
import com.cg.uas.ui.UasController;

public class UasController {

	static Scanner sc=new Scanner(System.in);
	static ApplicationBean bean=null;
	static LoginBean mac=null;
	
	
	
	public static void main(String[] args)  throws UniversityException{
		UasController  c=new UasController();
		bean=new ApplicationBean();
		mac=new LoginBean();
		ProgramsOfferedBean pgmbean=new ProgramsOfferedBean();
		ProgramsScheduledBean progmbean=new ProgramsScheduledBean();
		ArrayList<LoginBean> list=new ArrayList<LoginBean>();
		ArrayList<String> list1=new ArrayList<String>();
		IUasService service=new UasServiceImpl();
		
		int res=0;
		
		try{
		
		while(true){
		System.out.println("\n Enter your role");
		System.out.println("\n 1.Applicant"
						  +"\n 2.MAC"
						  +"\n 3.Admin"
						  +"\n 4.Exit");
		int role=sc.nextInt();
		switch(role){
		
		case 1:
			System.out.println("Enter your desired operation");
			System.out.println("\n 1.Application Fillup Form"
					+ "\n 2.Application Status check"
					+ "\n 3.Exit");
			int choose=sc.nextInt();
			switch(choose){
			case 1:
				System.out.println("List of Universities ");
			System.out.println("\n 1.Mumbai"
						  	  +"\n 2.Pune"
						  	  +"\n 3.Exit");
			System.out.println("Enter your desired university:");
			int university1=sc.nextInt();
			
			
			switch(university1){
			
			case 1:
				list1=service.retrieveDetails("Mumbai");
				System.out.println("Program Courses available are: "+"\n");
				for(String l:list1){
					System.out.println(l+"\t");
				}
				
				System.out.println("Enter your desired program course");
				System.out.println("\n 1.EXTC(E100)"
						+ "\n 2.IT(I100)"
						+ "\n 3.Exit");
				
				int course=sc.nextInt();
				
				switch(course){
				
				case 1:
					String programId=null;
					System.out.println("Application form for EXTC course");
					bean=c.fillForm();
					programId=service.getId("EXTC");
					bean.setProgramId(programId);
					bean.setStatus("Applied");
					bean.setInterviewDate(null);
					bean.setUniversity("Mumbai");
					res=service.insertData(bean);
					System.out.println("Your application id is:"+res);
					break;
					
				case 2:
					System.out.println("Application form for IT course");
					bean=c.fillForm();
					programId=service.getId("IT");
					bean.setProgramId(programId);
					bean.setStatus("Applied");
					bean.setInterviewDate(null);
					bean.setUniversity("Mumbai");
					res=service.insertData(bean);
					System.out.println("Your application id is:"+res);
					break;
					
				case 3:
					System.exit(0);
					break;
				}
				break;
			case 2:
				list1=service.retrieveDetails("Pune");
				System.out.println("Program Courses available are: "+"\n");
				for(String l:list1){
					System.out.println(l+"\t");
				}
				
				System.out.println("Enter your desired program course");
				System.out.println("\n 1.MECH(M101)"
						+ "\n 2.CIVIL(C101)"
						+ "\n 3.Exit");
				
				int course1=sc.nextInt();
				
				switch(course1){
				
				case 1:
					String programId=null;
					System.out.println("Application form for MECH course");
					bean=c.fillForm();
					programId=service.getId("MECH");
					bean.setProgramId(programId);
					bean.setStatus("Applied");
					bean.setInterviewDate(null);
					bean.setUniversity("Pune");
					res=service.insertData(bean);
					System.out.println("Your application id is:"+res);
					break;
					
				case 2:
					System.out.println("Application form for CIVIL course");
					bean=c.fillForm();
					programId=service.getId("CIVIL");
					bean.setProgramId(programId);
					bean.setStatus("Applied");
					bean.setInterviewDate(null);
					bean.setUniversity("Pune");
					res=service.insertData(bean);
					System.out.println("Your application id is:"+res);
					break;
					
				case 3:
					System.exit(0);
					break;
				}
			}
		break;
			case 2:
				ArrayList<ApplicationBean> statusCheck=new ArrayList<ApplicationBean>();
				System.out.println("Enter application id");
				int applicationId=sc.nextInt();
				statusCheck=service.getApplicantStatus(applicationId);
				if(service.validateStatusCheck(statusCheck)){
				for(ApplicationBean out:statusCheck){
					
					System.out.print("\n Application Id:"+out.getApplicationId());
					System.out.print("\n Full Name:"+out.getFullName());
					System.out.print("\n Status:"+out.getStatus());
					if(out.getStatus().equalsIgnoreCase("ACCEPTED")){
					System.out.println("\n Interview Date:"+out.getInterviewDate());
				}
					}}
				break;
			case 3:
				System.exit(0);
				break;
			}
			
			break;
		
		case 2:
			boolean b=true;
			System.out.println("Enter the login id");
			String id=sc.next();
			mac.setLoginId(id);
			
			System.out.println("Enter the password");
			mac.setPassword(sc.next());
			
			b=service.checkLogin(mac);
		
			
			if(b==true){
				System.out.println("Authenticated");
				System.out.println("Choose your desired operation");
				
				System.out.println("\n 1.View Applied applicants for a specific program"
						+ "\n 2.Update status and Date of Interview"
						+ "\n 3.Update confirmation status"
						+ "\n 4.Update Participants");
				
				int macOp=sc.nextInt();
				
				switch(macOp){
				
				case 1:
					
					ArrayList<ApplicationBean> list2=new ArrayList<ApplicationBean>();
					ArrayList<String> programList=new ArrayList<String>();
					programList=service.retrievePrograms();
					System.out.println("The Programs offered are:");
					for(String out:programList){
						System.out.println(out);
					}
					System.out.println("Enter program name");
					String programName=sc.next();
					c.getDetails(programName);
					
					break;
				case 2:
					ArrayList<ApplicationBean> allDetails=new ArrayList<ApplicationBean>();
					allDetails=service.retrieveAllDetails();

					if(service.checkDetails(allDetails)){
					System.out.println("List of applied applicants:");
					System.out.println("ApplicationId"+"\t"+"FullName"+"\t"+"DateOfBirth"+"\t"+"Qualification"
					+"\t"+"Marks"+"\t"+"Goals"+"\t"+"\t"+"Email"+"\t"+"\t"+"\t"+"ProgramId"+"\t"+"Status"+"\t"+"\t"+"InterviewDate"+"\n");
					for(ApplicationBean out:allDetails){
						System.out.print(out.getApplicationId()+"\t"+"\t");
						System.out.print(out.getFullName()+"\t"+"\t");
						System.out.print(out.getDateOfBirth()+"\t");
						System.out.print(out.getQualification()+"\t");
						System.out.print(out.getMarks()+"\t");
						System.out.print(out.getGoals()+"\t"+"\t");
						System.out.print(out.getEmail()+"\t"+"\t");
						System.out.print(out.getProgramId()+"\t"+"\t");
						System.out.print(out.getStatus()+"\t"+"\t");
						System.out.print(out.getInterviewDate()+"\n");	
					}
					System.out.println("Enter the Application Id:");
					int applicationId=sc.nextInt();
					bean.setApplicationId(applicationId);
					System.out.println("Enter the Status of applicant like ACCEPTED/REJECTED:");
					String status=sc.next();
					bean.setStatus(status);
					if(status.equalsIgnoreCase("accepted"))
					{
						System.out.println("Enter your date of interview in this dd-mm-yyyy format");
						String intdate=sc.next();
						DateTimeFormatter df=DateTimeFormatter.ofPattern("dd-MM-yyyy");
						
						try {
							LocalDate localdate1 = LocalDate.now();
							LocalDate date=LocalDate.parse(intdate,df);
							Date interviewDate=Date.valueOf(date);
							Date d=Date.valueOf(localdate1);
							if(service.validateInterviewDate(interviewDate,d)){
							bean.setInterviewDate(interviewDate);}
						} catch (DateTimeParseException e) {
							
							System.out.println("Please enter the correct date format as mentioned");
							System.exit(0);
						}
					
					res=service.updateStatus(bean);
					}

					else
					{
					bean.setInterviewDate(null);
					res=service.updateStatus(bean);
					
					}
					if(res==1)
					System.out.println("Updated  successfully");
					else
					{
					System.out.println("Cannot be updated");
					}
					}
					break;
				
				case 3:
					ArrayList<ApplicationBean> list3=new ArrayList<ApplicationBean>();
					ArrayList<String> programList1=new ArrayList<String>();
					programList1=service.retrievePrograms();
					System.out.println("The Programs offered  are:");
					for(String out:programList1){
						System.out.println(out);
					}
					System.out.println("Enter program name");
					String pgmName=sc.next();
					list3=service.retrieveStatus(pgmName);
					if(service.checkRetrieveStatus(list3)){
						System.out.println("List of Accepted applicants:");
						System.out.println("ApplicationId"+"\t"+"FullName"+"\t"+"\t"+"Status"+"\n");
					for(ApplicationBean out:list3){
						
						System.out.print(out.getApplicationId()+"\t"+"\t");
						System.out.print(out.getFullName()+"\t"+"\t");
						System.out.print(out.getStatus()+"\n");
					}
					System.out.println("Enter the application Id");
					int appId=sc.nextInt();
					bean.setApplicationId(appId);
					System.out.println("Enter the Status of applicant like CONFIRMED/REJECTED:");
					String status1=sc.next();
					bean.setStatus(status1);
					res=service.updateCnfStatus(bean);
					if(res==1)
					System.out.println("Updated  successfully");
					else
					System.out.println("Error");
					break;
					}
			case 4:
				ParticipantBean participant =new ParticipantBean();
				ArrayList<ApplicationBean> cnfList1=new ArrayList<ApplicationBean>();
				cnfList1=service.retrieveApplicant();
				System.out.println("List of applied applicants:");
				System.out.println("ApplicationId"+"\t"+"FullName"+"\t"+"DateOfBirth"+"\t"+"Qualification"
				+"\t"+"Marks"+"\t"+"Goals"+"\t"+"\t"+"Email"+"\t"+"\t"+"\t"+"ProgramId"+"\t"+"Status"+"\t"+"\t"+"InterviewDate"+"\t"+"University"+"\n");
				for(ApplicationBean out:cnfList1){
					
					System.out.print(out.getApplicationId()+"\t"+"\t");
					System.out.print(out.getFullName()+"\t");
					System.out.print(out.getDateOfBirth()+"\t"+"\t");
					System.out.print(out.getQualification()+"\t");
					System.out.print(out.getMarks()+"\t");
					System.out.print(out.getGoals()+"\t"+"\t");
					System.out.print(out.getEmail()+"\t"+"\t");
					System.out.print(out.getProgramId()+"\t"+"\t");
					System.out.print(out.getStatus()+"\t"+"\t");
					System.out.print(out.getInterviewDate()+"\n");
				}
				System.out.println("Enter application id");
				int appId=sc.nextInt();
				participant.setApplicationId(appId);
				System.out.println("Enter email id");
				String emailId=sc.next();
				participant.setEmailId(emailId);
				System.out.println("Enter program Id");
				String pgmId=sc.next();
				participant.setPgmId(pgmId);
				System.out.println("Enter university");
				String university=sc.next();
				participant.setUniversity(university);
				int res1=service.insertParticipant(participant);
				if(res==1){
					System.out.println("updated successfully");
				}
				else{
					System.out.println("Failed to update");
				}
				break;
				}
			}
			
			
			else{
				System.out.println("Wrong credentials");}

			break;
			
		case 3:
			boolean d=true;
			System.out.println("Enter the login id");
			String login=sc.next();
			mac.setLoginId(login);
			System.out.println("Enter the password");
			mac.setPassword(sc.next());
			d=service.checkAdminLogin(mac);
			
			
			if(d==true){
				System.out.println("Authenticated");
				System.out.println("Choose your desired operation");
				
				System.out.println("\n 1.Update Programs Offered"
						+ "\n 2.Update schedule of programs offered"
						+ "\n 3.View Status of Applicant for a specific program"
						+ "\n 4.View program scheduled in a given time period"
						+ "\n 5.Display number of participants ");
				
				int adminOp=sc.nextInt();
			
				switch(adminOp){
				
				case 1:
					System.out.println("1.Add Program");
					System.out.println("2.Delete Program");
					System.out.println("3.Update Program");
					int choice=sc.nextInt();
					switch (choice){
					case 1:
						ArrayList<String> programList=new ArrayList<String>();
						programList=service.retrievePrograms();
						for(String out:programList){
							System.out.println(out);
						}
						System.out.println("Enter the Program name:");
						String programName=sc.next();
						pgmbean.setProgramName(programName);
						System.out.println("Enter the Program description:");
						String programDesc=sc.next();
						pgmbean.setDesc(programDesc);
						System.out.println("Enter the Applicant Eligibility");
						String programEligibilty=sc.next();
						pgmbean.setEligibility(programEligibilty);
						System.out.println("Enter the Program Duration");
						int programDuration=sc.nextInt();
						pgmbean.setDuration(programDuration);
						System.out.println("Enter the degree certificate offered");
						String degreeCertificate =sc.next();
						pgmbean.setCertificate(degreeCertificate);
						System.out.println("Enter the university in which the course is offered");
						String university =sc.next();
						pgmbean.setUniversity(university);
						int result=service.addProgramOffered(pgmbean);
						if(result==1)
						System.out.println("updated");
						else
						System.out.println("Not Updated");
						break;
					case 2:
						ArrayList<String> programList1=new ArrayList<String>();
						programList1=service.retrievePrograms();
						for(String out:programList1){
							System.out.println(out);
						}
						System.out.println("Enter the Program Name:");
						String programName1=sc.next();
						
						result=service.deleteProgramOffered(programName1);
						if(result==1)
						{
						System.out.println("Program Deleted");
						}
						else
							System.out.println("Cannot be deleted since applicant exists");
						
						break;
					case 3:
						System.out.println("Enter the updated details about the program");
						ArrayList<String> pgmName=new ArrayList<String>();
						pgmName=service.retrievePgmName();
						for(String out:pgmName)
						{System.out.println(out);}
						System.out.println("Enter the Program name:");
						String programName2=sc.next();
						pgmbean.setProgramName(programName2);
						System.out.println("Enter the Program description:");
						String programDesc1=sc.next();
						pgmbean.setDesc(programDesc1);
						System.out.println("Enter the Applicant Eligibility");
						String programEligibilty1=sc.next();
						pgmbean.setEligibility(programEligibilty1);
						System.out.println("Enter the Program Duration");
						int programDuration1=sc.nextInt();
						pgmbean.setDuration(programDuration1);
						System.out.println("Enter the degree certificate offered");
						String degreeCertificate1 =sc.next();
						pgmbean.setCertificate(degreeCertificate1);
						System.out.println("Enter the university in which the course is offered");
						String university1 =sc.next();
						pgmbean.setUniversity(university1);
						int result1=service.updateDetail(pgmbean);
						if(result1==1){
							System.out.println("Updated Successfully");
						}
						break;
				}
					break;
				case 2:
					System.out.println("Choose your desired operation");
					System.out.println("1.Add Program Schedule");
					System.out.println("2.Delete Program Schedule");
					int choice1=sc.nextInt();
					switch(choice1){
					case 1:
						
						System.out.println("Enter the Program Schedule Id :");
						String programScheduleId=sc.next();
						progmbean.setSchedulePgmId(programScheduleId);
						System.out.println("Enter the Program Name:");
						String programName=sc.next();
						progmbean.setProgramName(programName);
						System.out.println("Enter the Location");
						String programLocation=sc.next();
						progmbean.setLocation(programLocation);
						System.out.println("Enter the Program Start Date in dd-mm-yyyy format");
						String programStartDate=sc.next();
						DateTimeFormatter df=DateTimeFormatter.ofPattern("dd-MM-yyyy");
						try {
							LocalDate date=LocalDate.parse(programStartDate,df);
							Date d1=Date.valueOf(date);
							
							
							progmbean.setStartDate(d1);
						} catch (DateTimeParseException e) {
							
							e.printStackTrace();
						}
						System.out.println("Enter the program End Date in dd-mm-yyyy format");
						String programEndDate=sc.next();
						DateTimeFormatter df1=DateTimeFormatter.ofPattern("dd-MM-yyyy");
						try {
							LocalDate date=LocalDate.parse(programStartDate,df1);
							Date d2=Date.valueOf(date);
							
							
							progmbean.setEndDate(d2);
						} catch (DateTimeParseException e) {
							
							System.out.println("Please enter the correct date format as mentioned");
							System.exit(0);
						}
						System.out.println("Enter the program Session Per Week");
						int programSession=sc.nextInt();
						progmbean.setSessionsPerWeek(programSession);
						System.out.println("Enter the university in which the course is offered");
						String university =sc.next();
						progmbean.setUniversity(university);
						int result=service.addScheduleProgram(progmbean);
						if(result==1)
						System.out.println("updated");
						else
						System.out.println("Not Updated");
						break;
					case 2:
						int result1=0;
						ArrayList<String> pgmId=new ArrayList<String>();
						pgmId=service.retrievePgmId();
						for(String out:pgmId){
							System.out.println(out);
						}
						System.out.println("Enter the Program Schedule Id to be Deleted");
						String programSchId=sc.next();
						result1=service.deleteProgramSchedule(programSchId);
						
						if(result1==1)
						{
						System.out.println("Program Schedule Deleted");}
						else
						System.out.println("Program Scheduled cannot be deleted since applicant exists");
						
						break;
					}
					break;
				case 3:
					ArrayList<String> programList=new ArrayList<String>();
					programList=service.retrievePrograms();
					for(String out:programList){
						System.out.println(out);
					}
					System.out.println("Enter program name");
					String programName2=sc.next();
					ArrayList<ApplicationBean> cnfList=new ArrayList<ApplicationBean>();
					cnfList=service.getCnfApplicantList(programName2);
					if(service.checkConfirmList(cnfList)){
					for(ApplicationBean out:cnfList){
						
						System.out.print(out.getApplicationId()+"\t");
						System.out.print(out.getFullName()+"\t");
						System.out.print(out.getStatus()+"\n");
					}}
					break;
				case 4:
					
					ArrayList<ProgramsScheduledBean> programSchedule=new ArrayList<ProgramsScheduledBean>();	
					programSchedule=service.retrievePgms();
					if(service.checkprogramSchedule(programSchedule)){
					System.out.println("Schedule Program Id"+"\t"+"Program Name"+"\t"+"Location"+"\t"+"Start Date"
							+"\t"+"End Date"+"\t"+"Sessions per week"+"\n");
					for(ProgramsScheduledBean out:programSchedule){
						System.out.print(out.getSchedulePgmId()+"\t"+"\t"+"\t");
						System.out.print(out.getProgramName()+"\t"+"\t");
						System.out.print(out.getLocation()+"\t"+"\t");
						System.out.print(out.getStartDate()+"\t");
						System.out.print(out.getEndDate()+"\t");
						System.out.print(out.getSessionsPerWeek()+"\n");
					
					}
				}break;
				case 5:
				
					ArrayList<ParticipantBean> listParticipant=new ArrayList<ParticipantBean>();
					listParticipant	= service.retrieveParticipant1();
					System.out.println("Roll No"+"\t"+"Email Id"+"\t"+"Application Id"+"\t"+"Schedule Program Id"
							+"\t"+"University"+"\n");
					for(ParticipantBean out:listParticipant){
						System.out.print(out.getRollNo()+"\t");
						System.out.print(out.getEmailId()+"\t");
						System.out.print(out.getApplicationId()+"\t"+"\t");
						System.out.print(out.getPgmId()+"\t"+"\t"+"\t"+"\t");
						System.out.print(out.getUniversity()+"\n");
					}
					break;
					}
				
				
				
				}
			else{
				System.out.println("Wrong credentials");
		}

			break;
			
		case 4:
			System.exit(0);
			break;
		}}}
		catch(UniversityException e)
		{
			System.out.println(e);
	}
		}
	
	
	
	

	
	public ApplicationBean fillForm()
	{	
		IUasService service=new UasServiceImpl();
		String firstName=null;
		String lastName=null;
		String fullName=null;
		int flag=0;
		bean=new ApplicationBean();
		System.out.println("Enter your first name");
		firstName=sc.next();
		if(service.checkPattern(firstName)){
		System.out.println("Enter your last name");
		lastName=sc.next();
		if(service.checkPattern(lastName)){
		fullName=firstName.concat(" ").concat(lastName);
		bean.setFullName(fullName);
		sc.nextLine();
		System.out.println("Enter your date of birth in this dd-mm-yyyy format");
		String dob=sc.nextLine();
		DateTimeFormatter df=DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		try {
			LocalDate localdate1 = LocalDate.now();
			LocalDate localdate2= LocalDate.of(1999,Month.JANUARY,1);
			LocalDate date=LocalDate.parse(dob,df);
			Date d=Date.valueOf(date);
			Date d1=Date.valueOf(localdate1);
			Date d2=Date.valueOf(localdate2);
			
			if(service.validateDate(d,d1,d2)){
			bean.setDateOfBirth(d);}
		} catch (DateTimeParseException e) {
			
			System.out.println("Please enter the correct date format as mentioned");
			System.exit(0);
		}
		
		System.out.println("Enter the highest qualification");
		String qualification=sc.next();
		if(service.validateForm(qualification)){
		bean.setQualification(qualification);
		System.out.println("Enter the marks obtained");
		int marksObt=sc.nextInt();
		bean.setMarks(marksObt);
		sc.nextLine();
		System.out.println("Enter the goals");
		String goals=sc.nextLine();
		bean.setGoals(goals);
		System.out.println("Enter your emailid");
		String emailId=sc.next();
		bean.setEmail(emailId);
		}}}
		return bean;
		
	}
	
	
public void getDetails(String name) throws UniversityException{
	ArrayList<ApplicationBean> list2=new ArrayList<ApplicationBean>();
	IUasService service=new UasServiceImpl();
	list2=service.getApplicantList(name);
	if(service.checkAllDetails(list2)){
	System.out.println("List of applied applicants:");
	System.out.println("ApplicationId"+"\t"+"FullName"+"\t"+"DateOfBirth"+"\t"+"Qualification"
	+"\t"+"Marks"+"\t"+"Goals"+"\t"+"\t"+"Email"+"\t"+"\t"+"\t"+"ProgramId"+"\t"+"Status"+"\t"+"\t"+"InterviewDate"+"\n");
	for(ApplicationBean out:list2){
		
		System.out.print(out.getApplicationId()+"\t"+"\t");
		System.out.print(out.getFullName()+"\t");
		System.out.print(out.getDateOfBirth()+"\t"+"\t");
		System.out.print(out.getQualification()+"\t");
		System.out.print(out.getMarks()+"\t");
		System.out.print(out.getGoals()+"\t"+"\t");
		System.out.print(out.getEmail()+"\t"+"\t");
		System.out.print(out.getProgramId()+"\t"+"\t");
		System.out.print(out.getStatus()+"\t"+"\t");
		System.out.print(out.getInterviewDate()+"\n");
	}
	}
}
}
