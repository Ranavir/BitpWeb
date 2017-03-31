package team.tcc.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import team.tcc.helper.EmailClient;
import team.tcc.service.AppService;
import team.tcc.service.CommonService;
import team.tcc.serviceImpl.AppServiceImpl;
import team.tcc.serviceImpl.CommonServiceImpl;
import team.tcc.util.ConnectionFactory;
import team.tcc.util.ProjectConstants;
import team.tcc.util.Utils;
import team.tcc.vo.ApplicationVO;
import team.tcc.vo.NotificationResultVO;
import team.tcc.vo.StudentExamProfileVO;
import team.tcc.vo.StudentTrainingProfileVO;

import com.google.gson.Gson;

/**
 * Servlet implementation class AppController
 */
@WebServlet(description = "Handles request for Android application", 
			urlPatterns = { "/AppController" }, 
			loadOnStartup = 1)
public class AppController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * Declare and initialize  required variables
	 */
	String methodname = "" ;
	String userId = "";
	
	private static Logger logger = null;
	private static AppService appService = null ;
	private static CommonService commonService = null ;
       @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	try
		{
			
			logger = Logger.getLogger(AppController.class) ;
			appService = AppServiceImpl.getInstance() ;
			commonService = CommonServiceImpl.getInstance() ;
		}catch (Exception ge) {
			logger.error(ge.toString(), ge.fillInStackTrace());
		}
		logger.info("AppController servlet initialized successfully...");
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppController() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		logger.info("UIController servlet destroyed sucessfully...");
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		methodname = "doPost" ;
		logger.info("ENTRY---> methodname : "+methodname);
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Integer iReqId = !StringUtils.isEmpty(request.getParameter("reqId"))?Integer.parseInt(request.getParameter("reqId")):0 ;
		System.out.println(iReqId);
		switch(iReqId){
			case ProjectConstants.REG_REQ_ID://login
				saveUserRegistration(request, response);
				break ;
			case ProjectConstants.LOGIN_REQ_ID:
				validateLogin(request, response);
				break;
			case ProjectConstants.PW_RECOVERY_REQ_ID:
				forgotPassword(request, response);
				break;
			case ProjectConstants.PW_CHANGE_REQ_ID:
				changePassword(request, response);
				break;
			case ProjectConstants.FETCH_NOTIFIATIONS:
				fetchNotifications(request, response);
				break ;
			case ProjectConstants.FETCH_COMPANY:
				fetchCompanies(request, response);
				break ;
			case ProjectConstants.NEW_NOTIFICATION:
				createNotification(request, response);
				break ;
			case ProjectConstants.PROFILE_UPDATE:
				updateProfile(request, response);
				break ;
			case ProjectConstants.APPLY_TO_NOTIFICATION:
				applyToNotification(request, response);
				break ;
			case ProjectConstants.APPLICATIONS_PRE_PROCESSING:
				getPreProcessingApplicationDetails(request, response);
				break ;
			case ProjectConstants.APPLICATIONS_PROCESSING:
				processApplications(request, response);
				break ;
			case ProjectConstants.FETCH_EXAMS:
				fetchExams(request, response);
				break ;
			case ProjectConstants.FETCH_EXAM_PROFILE:
				fetchExamProfile(request, response);
				break ;
			case ProjectConstants.UPDATE_EXAM_INTERVEIW:
				updateExamProfile(request, response);
				break ;
			case ProjectConstants.FETCH_PLACEMENT_NEWS:
				fetchPlacements(request, response);
				break ;
			case ProjectConstants.NEW_PLACEMENT:
				createPlacementNews(request, response);
				break ;
			case ProjectConstants.DROP_PLACEMENT_NEWS:
				dropPlacementNews(request, response);
				break ;
			case ProjectConstants.NEW_TRAINING:
				createTraining(request, response);
				break ;
			case ProjectConstants.FETCH_TRAININGS:
				fetchTrainings(request, response);
				break ;
			case ProjectConstants.ADD_TRAINEES:
				addTrainees(request, response);
				break ;
			case ProjectConstants.FETCH_TRAINING_PROFILE:
				fetchTrainingProfile(request, response);
				break ;
			case ProjectConstants.PUSH_FEEDBACK:
				updateStudentTrainingFeedback(request, response);
				break ;
			case ProjectConstants.NOTIFICATION_RESULT:
				fetchNotificationResults(request, response);
				break ;
			case ProjectConstants.EXAM_SELECTION:
				selectByExamResult(request, response);
				break ;
			case ProjectConstants.PAY_STIPEND:
				payStipend(request, response);
				break ;
			case ProjectConstants.SUBMIT_PROJECT:
				submitProject(request, response);
				break ;
			case ProjectConstants.ISSUE_CERTIFICATE:
				issueCertificate(request, response);
				break ;
			default:
				return ;
		}
		
		logger.info("EXIT---> methodname : "+methodname);
	}//end doPost
	/**********************************************************************
	 * This method used to issue of a certificate to the 
	 * user for an enrolled training by training code and
	 * student id.
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 *********************************************************************/
	private void issueCertificate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String methodname = "issueCertificate";
		logger.info("Entry--->" + this.getClass().getName() + " method---->" + methodname);
		PrintWriter out = response.getWriter();
		
		JSONObject respObj = new JSONObject();
		try {
			respObj.put("status", "FAILURE");
			respObj.put("msg", "Failed to issue Training certificate.");
			// 
			
			JSONObject reqObj = new JSONObject(request.getParameter("data"));
			logger.debug("requested profile --->"+reqObj.toString());
			int user_id = !StringUtils.isEmpty(reqObj.getString("user_id")) ? Integer.parseInt(reqObj.getString("user_id")): -1;
			String training_code = !StringUtils.isEmpty(reqObj.getString("training_code")) ? reqObj.getString("training_code"): "";
			
			if(user_id != -1 && !StringUtils.isEmpty(training_code)){
				boolean status = appService.issueCertificate(training_code,user_id);
				if(status){
					respObj.put("status", "SUCCESS");
					respObj.put("msg", "Training Certificate issued successfully.");					 
				}else{
					respObj.put("status", "FAILURE");
					respObj.put("msg", "Failed to issue Training certificate."); 
				} 
			}else{
				respObj.put("msg", "Invalid request.");
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				respObj = new JSONObject();
				respObj.put("status", "FAILURE");
				respObj.put("msg", ProjectConstants.MSG_SERVER_ERROR);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		logger.info("Response ---->" + respObj.toString());
		out.print(respObj);
		logger.info("Exit--->" + this.getClass().getName() + " method---->" + methodname);
	}//end of issueCertificate
	
	/**********************************************************************
	 * This method used to submit project report by updating
	 * report submit status in backend
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 *********************************************************************/
	private void submitProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String methodname = "submitProject";
		logger.info("Entry--->" + this.getClass().getName() + " method---->" + methodname);
		PrintWriter out = response.getWriter();
		
		JSONObject respObj = new JSONObject();
		try {
			respObj.put("status", "FAILURE");
			respObj.put("msg", "Unable to process your application.");
			// 
			
			JSONObject reqObj = new JSONObject(request.getParameter("data"));
			logger.debug("requested profile --->"+reqObj.toString());
			int user_id = !StringUtils.isEmpty(reqObj.getString("user_id")) ? Integer.parseInt(reqObj.getString("user_id")): -1;
			String training_code = !StringUtils.isEmpty(reqObj.getString("training_code")) ? reqObj.getString("training_code"): "";
			
			if(user_id != -1 && !StringUtils.isEmpty(training_code)){
				boolean status = appService.submitProject(training_code,user_id);
				if(status){
					respObj.put("status", "SUCCESS");
					respObj.put("msg", "Project submitted successfully.");					 
				}else{
					respObj.put("status", "FAILURE");
					respObj.put("msg", "Failed to submit project report.."); 
				} 
			}else{
				respObj.put("msg", "Invalid request.");
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				respObj = new JSONObject();
				respObj.put("status", "FAILURE");
				respObj.put("msg", ProjectConstants.MSG_SERVER_ERROR);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		logger.info("Response ---->" + respObj.toString());
		out.print(respObj);
		logger.info("Exit--->" + this.getClass().getName() + " method---->" + methodname);
	}//end of submitProject
	/*********************************************************************
	 * This task does the followings:
     * - The task is to pay stipend amount to all the
     * trainees enrolled in a particular training for a particular
     * month
     * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 ********************************************************************/
	private void payStipend(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String methodname = "payStipend";
		logger.info("Entry--->" + this.getClass().getName() + " method---->" + methodname);
		PrintWriter out = response.getWriter();
		
		JSONObject respObj = new JSONObject();
		try {
			respObj.put("status", "FAILURE");
			respObj.put("msg", "Sorry ! Failed to make stipend payment.");
			
			JSONObject reqObj = new JSONObject(request.getParameter("data"));
			logger.debug("requested data --->"+reqObj.toString());
			String month = "" ;
			
			String training_code = !StringUtils.isEmpty(reqObj.getString("training_code")) ? reqObj.getString("training_code"): "";

			if(reqObj.has("mth1_stipen_rcv")){
				month = "mth1_stipen_rcv";
			}
			if(reqObj.has("mth2_stipen_rcv")){
				month = "mth2_stipen_rcv";
			}
			if(reqObj.has("mth3_stipen_rcv")){
				month = "mth3_stipen_rcv";
			}
			if(reqObj.has("mth4_stipen_rcv")){
				month = "mth4_stipen_rcv";
			}
			if(reqObj.has("mth5_stipen_rcv")){
				month = "mth5_stipen_rcv";
			}
			if(reqObj.has("mth6_stipen_rcv")){
				month = "mth6_stipen_rcv";
			}
			
			
			if(!StringUtils.isEmpty(training_code)){
				boolean status = appService.payStipend(training_code,month);
				if(status){
					respObj.put("status", "SUCCESS");
					respObj.put("msg", "All Trainees paid stipends successfully.");					 
				}else{
					respObj.put("status", "FAILURE");
					respObj.put("msg", "Sorry ! Failed to pay stipends."); 
				} 
			}else{
				respObj.put("msg", "Invalid request.");
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				respObj = new JSONObject();
				respObj.put("status", "FAILURE");
				respObj.put("msg", ProjectConstants.MSG_SERVER_ERROR);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		logger.info("Response ---->" + respObj.toString());
		out.print(respObj);
		logger.info("Exit--->" + this.getClass().getName() + " method---->" + methodname);
	}//end of payStipend
	/********************************************************************
	 * This method used to select or reject a student
	 * based on it's exam performance
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 *******************************************************************/
	private void selectByExamResult(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String methodname = "selectByExamResult";
		logger.info("Entry--->" + this.getClass().getName() + " method---->" + methodname);
		PrintWriter out = response.getWriter();
		
		JSONObject respObj = new JSONObject();
		try {
			respObj.put("status", "FAILURE");
			
			JSONObject reqObj = new JSONObject(request.getParameter("data"));
			logger.debug("request data --->"+reqObj.toString());
			String selection_status = !StringUtils.isEmpty(reqObj.getString("selection_status")) ? reqObj.getString("selection_status") : "";
			String exam_code = !StringUtils.isEmpty(reqObj.getString("exam_code")) ? reqObj.getString("exam_code") : "";
			int user_id = !StringUtils.isEmpty(reqObj.getString("selection_status")) ? Integer.parseInt(reqObj.getString("user_id")) : -1;
			
			if(user_id != -1 && !StringUtils.isEmpty(selection_status) && !StringUtils.isEmpty(exam_code)){
				boolean status = appService.selectByExamResult(exam_code,selection_status,user_id);
				if(status){
					respObj.put("status", "SUCCESS");
					respObj.put("msg", "User selection status updated successfully.");					 
				}else{
					respObj.put("status", "FAILURE");
					respObj.put("msg", "Failed to update selection status."); 
				} 
			}else{
				respObj.put("msg", "Invalid request.");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				respObj = new JSONObject();
				respObj.put("status", "FAILURE");
				respObj.put("msg", ProjectConstants.MSG_SERVER_ERROR);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		logger.info("Response ---->" + respObj.toString());
		out.print(respObj);
		logger.info("Exit--->" + this.getClass().getName() + " method---->" + methodname);
	}//end of selectByExamResult
	/******************************************************************
	 * This method used to give the notification specific details when 
	 * the notification is in processed state and one exam is associated
	 * with the notification
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 *****************************************************************/
	private void fetchNotificationResults(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String methodname = "fetchNotificationResults";
		logger.info("Entry--->" + this.getClass().getName() + " method---->" + methodname);
		PrintWriter out = response.getWriter();
		
		JSONObject respObj = new JSONObject();
		JSONObject jObjNotificationResults = new JSONObject();
		try {
			respObj.put("status", "SUCCESS");
			respObj.put("msg", "");
            
			JSONObject reqObj = new JSONObject(request.getParameter("data"));
			logger.debug("request data --->"+reqObj.toString());
			String notification_id = !StringUtils.isEmpty(reqObj.getString("notification_id")) ? reqObj.getString("notification_id"): "";
			if(!StringUtils.isEmpty(notification_id)){
				NotificationResultVO nrvo = appService.fetchNotificationResults(notification_id);
				if(null != nrvo){
					jObjNotificationResults = new JSONObject(new Gson().toJson(nrvo));
				}
			}
			respObj.put("notification_results", jObjNotificationResults);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				respObj = new JSONObject();
				respObj.put("status", "FAILURE");
				respObj.put("msg", ProjectConstants.MSG_SERVER_ERROR);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		logger.info("Response ---->" + respObj.toString());
		out.print(respObj);
		logger.info("Exit--->" + this.getClass().getName() + " method---->" + methodname);
	}//end of fetchNotificationResults
	/*****************************************************************
	 * This method used to update a feedback against one training
	 * program by the trainee for a particular month
	 * @param request
	 * @param response
	 * @throws IOException 
	 ****************************************************************/
	private void updateStudentTrainingFeedback(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String methodname = "updateStudentTrainingFeedback";
		logger.info("Entry--->" + this.getClass().getName() + " method---->" + methodname);
		PrintWriter out = response.getWriter();
		
		JSONObject respObj = new JSONObject();
		try {
			respObj.put("status", "FAILURE");
			respObj.put("msg", "Sorry ! Failed to submit your feedback.");
			
			JSONObject reqObj = new JSONObject(request.getParameter("data"));
			logger.debug("requested data --->"+reqObj.toString());
			String month = "" ;
			String feedback = "";
			
			String training_code = !StringUtils.isEmpty(reqObj.getString("training_code")) ? reqObj.getString("training_code"): "";
			int user_id = !StringUtils.isEmpty(reqObj.getString("user_id")) ? Integer.parseInt(reqObj.getString("user_id")): -1;
			if(reqObj.has("mth1_feedback")){
				month = "mth1_feedback";
				feedback = !StringUtils.isEmpty(reqObj.getString("mth1_feedback")) ? reqObj.getString("mth1_feedback"): "";
			}
			if(reqObj.has("mth2_feedback")){
				month = "mth2_feedback";
				feedback = !StringUtils.isEmpty(reqObj.getString("mth2_feedback")) ? reqObj.getString("mth2_feedback"): "";
			}
			if(reqObj.has("mth3_feedback")){
				month = "mth3_feedback";
				feedback = !StringUtils.isEmpty(reqObj.getString("mth3_feedback")) ? reqObj.getString("mth3_feedback"): "";
			}
			if(reqObj.has("mth4_feedback")){
				month = "mth4_feedback";
				feedback = !StringUtils.isEmpty(reqObj.getString("mth4_feedback")) ? reqObj.getString("mth4_feedback"): "";
			}
			if(reqObj.has("mth5_feedback")){
				month = "mth5_feedback";
				feedback = !StringUtils.isEmpty(reqObj.getString("mth5_feedback")) ? reqObj.getString("mth5_feedback"): "";
			}
			if(reqObj.has("mth6_feedback")){
				month = "mth6_feedback";
				feedback = !StringUtils.isEmpty(reqObj.getString("mth6_feedback")) ? reqObj.getString("mth6_feedback"): "";
			}
			
			
			if(user_id != -1 && !StringUtils.isEmpty(training_code) && !StringUtils.isEmpty(feedback) && !StringUtils.isEmpty(month)){
				boolean status = appService.updateStudentTrainingFeedback(user_id,training_code,month,feedback);
				if(status){
					respObj.put("status", "SUCCESS");
					respObj.put("msg", "Your feedback submitted successfully.");					 
				}else{
					respObj.put("status", "FAILURE");
					respObj.put("msg", "Sorry ! Failed to submit your feedback."); 
				} 
			}else{
				respObj.put("msg", "Invalid request.");
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				respObj = new JSONObject();
				respObj.put("status", "FAILURE");
				respObj.put("msg", ProjectConstants.MSG_SERVER_ERROR);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		logger.info("Response ---->" + respObj.toString());
		out.print(respObj);
		logger.info("Exit--->" + this.getClass().getName() + " method---->" + methodname);
	}//end of updateStudentTrainingFeedback
	/****************************************************************
	 * This method retrieves the student's training profile
	 * @param request
	 * @param response
	 * @throws IOException 
	 *****************************************************************/
	private void fetchTrainingProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String methodname = "fetchTrainingProfile";
		logger.info("Entry--->" + this.getClass().getName() + " method---->" + methodname);
		PrintWriter out = response.getWriter();
		
		JSONObject respObj = new JSONObject();
		JSONObject jObjProfile = new JSONObject();
		try {
			respObj.put("status", "SUCCESS");
			respObj.put("msg", "");
            
			JSONObject reqObj = new JSONObject(request.getParameter("data"));
			logger.debug("request data --->"+reqObj.toString());
			String training_code = !StringUtils.isEmpty(reqObj.getString("training_code")) ? reqObj.getString("training_code"): "";
			int user_id = !StringUtils.isEmpty(reqObj.getString("user_id")) ? Integer.parseInt(reqObj.getString("user_id")): -1;
			if(!StringUtils.isEmpty(training_code) && user_id != -1){
				StudentTrainingProfileVO stpvo =  appService.getStudentTrainingProfile(training_code, user_id);
				if(null != stpvo){
					jObjProfile = new JSONObject(new Gson().toJson(stpvo));
				}
			}
			respObj.put("training_profile", jObjProfile);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				respObj = new JSONObject();
				respObj.put("status", "FAILURE");
				respObj.put("msg", ProjectConstants.MSG_SERVER_ERROR);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		logger.info("Response ---->" + respObj.toString());
		out.print(respObj);
		logger.info("Exit--->" + this.getClass().getName() + " method---->" + methodname);
	}//end of fetchTrainingProfile
	/*******************************************************************
	 * This task does the followings:
     * - It takes the notification id with (processed status)
     * - It takes the exam code of that notification and get
     * all the selected students list(Generated after
     * match-making process)
     * - Gets all the students list under selected training
     * (active)
     * - Checks the list of students which are new for this
     * training
     * - Insert those new students list in training as created by
     * this admin user
     * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 ******************************************************************/
	private void addTrainees(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String methodname = "addTrainees";
		logger.info("Entry--->" + this.getClass().getName() + " method---->" + methodname);
		PrintWriter out = response.getWriter();
		
		JSONObject respObj = new JSONObject();
		try {
			respObj.put("status", "SUCCESS");
			
			
			
			JSONObject reqObj = new JSONObject(request.getParameter("data"));
			String training_code = !StringUtils.isEmpty(reqObj.getString("training_code")) ? reqObj.getString("training_code") : "";
			String notification_id = !StringUtils.isEmpty(reqObj.getString("notification_id")) ? reqObj.getString("notification_id") : "";
			String created_by = !StringUtils.isEmpty(reqObj.getString("created_by")) ? reqObj.getString("created_by") : "";
			System.out.println(reqObj.toString());
			
			HashMap<String,Object> hm = new HashMap<>();
			hm.put("training_code", training_code);
			hm.put("notification_id", notification_id);
			hm.put("created_by", created_by);
			
			
            
			
			int count = appService.addTrainees(hm);
			if(count == -1){
				respObj.put("status", "FAILURE");
				respObj.put("msg", "Adding trainees failed.");
			}else{
				respObj.put("msg", count+" Trainees are added successfully.");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				respObj = new JSONObject();
				respObj.put("status", "FAILURE");
				respObj.put("msg", ProjectConstants.MSG_SERVER_ERROR);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		logger.info("Response ---->" + respObj.toString());
		out.print(respObj);
		logger.info("Exit--->" + this.getClass().getName() + " method---->" + methodname);
	}//end of addTrainees
	/*****************************************************************
	 * This method used to fetch the available active training
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 ******************************************************************/
	private void fetchTrainings(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String methodname = "fetchExams";
		logger.info("Entry--->" + this.getClass().getName() + " method---->" + methodname);
		PrintWriter out = response.getWriter();
		
		JSONObject respObj = new JSONObject();
		try {
			respObj.put("status", "SUCCESS");
			respObj.put("msg", "");
			
			
			JSONArray jaTrainings = commonService.getAvailableTraining();
			respObj.put("trainings", jaTrainings);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				respObj = new JSONObject();
				respObj.put("status", "FAILURE");
				respObj.put("msg", ProjectConstants.MSG_SERVER_ERROR);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		logger.info("Response ---->" + respObj.toString());
		out.print(respObj);
		logger.info("Exit--->" + this.getClass().getName() + " method---->" + methodname);
	}// end of fetchExams
	/****************************************************************
	 * This method used to create a new training activity
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 ***************************************************************/
	private void createTraining(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String methodname = "createTraining";
		logger.info("Entry--->" + this.getClass().getName() + " method---->" + methodname);
		PrintWriter out = response.getWriter();
		
		JSONObject respObj = new JSONObject();
		try {
			respObj.put("status", "SUCCESS");
			respObj.put("msg", "New Training created successfully.");
			
			JSONObject reqObj = new JSONObject(request.getParameter("data"));
			String comp_code = !StringUtils.isEmpty(reqObj.getString("comp_code")) ? reqObj.getString("comp_code") : "";
			String training_desc = !StringUtils.isEmpty(reqObj.getString("training_desc")) ? reqObj.getString("training_desc") : "";
			String created_by = !StringUtils.isEmpty(reqObj.getString("created_by")) ? reqObj.getString("created_by") : "";
			double mth1_stipen_amt = !StringUtils.isEmpty(reqObj.getString("mth1_stipen_amt")) ? Double.parseDouble(reqObj.getString("mth1_stipen_amt")) : 0 ;
			double mth2_stipen_amt = !StringUtils.isEmpty(reqObj.getString("mth1_stipen_amt")) ? Double.parseDouble(reqObj.getString("mth1_stipen_amt")) : 0 ;
			double mth3_stipen_amt = !StringUtils.isEmpty(reqObj.getString("mth1_stipen_amt")) ? Double.parseDouble(reqObj.getString("mth1_stipen_amt")) : 0 ;
			double mth4_stipen_amt = !StringUtils.isEmpty(reqObj.getString("mth1_stipen_amt")) ? Double.parseDouble(reqObj.getString("mth1_stipen_amt")) : 0 ;
			double mth5_stipen_amt = !StringUtils.isEmpty(reqObj.getString("mth1_stipen_amt")) ? Double.parseDouble(reqObj.getString("mth1_stipen_amt")) : 0 ;
			double mth6_stipen_amt = !StringUtils.isEmpty(reqObj.getString("mth1_stipen_amt")) ? Double.parseDouble(reqObj.getString("mth1_stipen_amt")) : 0 ;
			
			HashMap<String,Object> hm = new HashMap<>();
			hm.put("comp_code", comp_code);
			hm.put("training_desc", training_desc);
			hm.put("created_by", created_by);
			hm.put("mth1_stipen_amt", mth1_stipen_amt);
			hm.put("mth2_stipen_amt", mth2_stipen_amt);
			hm.put("mth3_stipen_amt", mth3_stipen_amt);
			hm.put("mth4_stipen_amt", mth4_stipen_amt);
			hm.put("mth5_stipen_amt", mth5_stipen_amt);
			hm.put("mth6_stipen_amt", mth6_stipen_amt);
			
            
			
			boolean status = appService.createTraining(hm);
			if(!status){
				respObj.put("status", "FAILURE");
				respObj.put("msg", "Training creation failed.");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				respObj = new JSONObject();
				respObj.put("status", "FAILURE");
				respObj.put("msg", ProjectConstants.MSG_SERVER_ERROR);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		logger.info("Response ---->" + respObj.toString());
		out.print(respObj);
		logger.info("Exit--->" + this.getClass().getName() + " method---->" + methodname);
	}//end of createTraining
	/**************************************************************
	 * This method used to de-active a placement news
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 *************************************************************/
	private void dropPlacementNews(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String methodname = "dropPlacementNews";
		logger.info("Entry--->" + this.getClass().getName() + " method---->" + methodname);
		PrintWriter out = response.getWriter();
		
		JSONObject respObj = new JSONObject();
		try {
			respObj.put("status", "SUCCESS");
			respObj.put("msg", "Placement news dropped successfully.");
			
			JSONObject reqObj = new JSONObject(request.getParameter("data"));
			String placement_code = !StringUtils.isEmpty(reqObj.getString("placement_code")) ? reqObj.getString("placement_code") : "";
			String updated_by = !StringUtils.isEmpty(reqObj.getString("user_id")) ? reqObj.getString("user_id") : "";
			HashMap<String,Object> hm = new HashMap<>();
			hm.put("placement_code", placement_code);
			hm.put("updated_by", updated_by);
			
			boolean status = appService.dropPlacementNews(hm);
			if(!status){
				respObj.put("status", "FAILURE");
				respObj.put("msg", "Unable to drop placement news");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				respObj = new JSONObject();
				respObj.put("status", "FAILURE");
				respObj.put("msg", ProjectConstants.MSG_SERVER_ERROR);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		logger.info("Response ---->" + respObj.toString());
		out.print(respObj);
		logger.info("Exit--->" + this.getClass().getName() + " method---->" + methodname);
	}//end of createPlacementNews
	/*************************************************************
	 * This method gets the placement news based on status
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 ************************************************************/
	private void fetchPlacements(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String methodname = "fetchPlacements";
		logger.info("Entry--->" + this.getClass().getName() + " method---->" + methodname);
		PrintWriter out = response.getWriter();
		
		JSONObject respObj = new JSONObject();
		String status = "";
		try {
			respObj.put("status", "SUCCESS");
			respObj.put("msg", "");
			
			JSONObject reqObj = new JSONObject(request.getParameter("data"));
			status = !StringUtils.isEmpty(reqObj.getString("status")) ? reqObj.getString("status") : "Y" ;
			logger.info("requested placement news with status--->" + status);
			
			JSONArray jaPlacements = commonService.getPlacementsByStatus(status);
			respObj.put("placements", jaPlacements);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				respObj = new JSONObject();
				respObj.put("status", "FAILURE");
				respObj.put("msg", ProjectConstants.MSG_SERVER_ERROR);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		logger.info("Response ---->" + respObj.toString());
		out.print(respObj);
		logger.info("Exit--->" + this.getClass().getName() + " method---->" + methodname);
	}// end of fetchNotifications
	/************************************************************
	 * This method used to created a new placement news
	 * in system
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 *************************************************************/
	private void createPlacementNews(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String methodname = "createPlacementNews";
		logger.info("Entry--->" + this.getClass().getName() + " method---->" + methodname);
		PrintWriter out = response.getWriter();
		
		JSONObject respObj = new JSONObject();
		try {
			respObj.put("status", "SUCCESS");
			respObj.put("msg", "New placement news posted successfully.");
			
			JSONObject reqObj = new JSONObject(request.getParameter("data"));
			String comp_code = !StringUtils.isEmpty(reqObj.getString("comp_code")) ? reqObj.getString("comp_code") : "";
			String news = !StringUtils.isEmpty(reqObj.getString("news")) ? reqObj.getString("news") : "";
			String created_by = !StringUtils.isEmpty(reqObj.getString("created_by")) ? reqObj.getString("created_by") : "";
			HashMap<String,Object> hm = new HashMap<>();
			hm.put("comp_code", comp_code);
			hm.put("news", news);
			hm.put("created_by", created_by);
			
			boolean status = appService.createPlacementNews(hm);
			if(!status){
				respObj.put("status", "FAILURE");
				respObj.put("msg", "Unable to add placement news");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				respObj = new JSONObject();
				respObj.put("status", "FAILURE");
				respObj.put("msg", ProjectConstants.MSG_SERVER_ERROR);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		logger.info("Response ---->" + respObj.toString());
		out.print(respObj);
		logger.info("Exit--->" + this.getClass().getName() + " method---->" + methodname);
	}//end of createPlacementNews
	/******************************************************************
	 * This method updates the student interview mark
	 * @param request
	 * @param response
	 * @throws IOException 
	 *****************************************************************/
	private void updateExamProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String methodname = "updateExamProfile";
		logger.info("Entry--->" + this.getClass().getName() + " method---->" + methodname);
		PrintWriter out = response.getWriter();
		
		JSONObject respObj = new JSONObject();
		try {
			respObj.put("status", "FAILURE");
			respObj.put("msg", "Failed to update interview mark.");
			// 
			
			JSONObject reqObj = new JSONObject(request.getParameter("data"));
			logger.debug("requested profile --->"+reqObj.toString());
			int user_id = !StringUtils.isEmpty(reqObj.getString("user_id")) ? Integer.parseInt(reqObj.getString("user_id")): -1;
			String exam_code = !StringUtils.isEmpty(reqObj.getString("exam_code")) ? reqObj.getString("exam_code"): "";
			String notification_id = !StringUtils.isEmpty(reqObj.getString("notification_id")) ? reqObj.getString("notification_id"): "";
			String updated_by = !StringUtils.isEmpty(reqObj.getString("updated_by")) ? reqObj.getString("updated_by"): "";
			double mark = !StringUtils.isEmpty(reqObj.getString("marks_interview_acquired")) ? Double.parseDouble(reqObj.getString("marks_interview_acquired")): 0;
			
			if(user_id != -1 && !StringUtils.isEmpty(exam_code) && !StringUtils.isEmpty(notification_id)){
				boolean status = appService.updateExamProfile(notification_id,user_id,exam_code,mark,updated_by);
				if(status){
					respObj.put("status", "SUCCESS");
					respObj.put("msg", "Interview mark update success.");					 
				}else{
					respObj.put("status", "FAILURE");
					respObj.put("msg", "Failed to update interview mark."); 
				} 
			}else{
				respObj.put("msg", "Invalid request.");
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				respObj = new JSONObject();
				respObj.put("status", "FAILURE");
				respObj.put("msg", ProjectConstants.MSG_SERVER_ERROR);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		logger.info("Response ---->" + respObj.toString());
		out.print(respObj);
		logger.info("Exit--->" + this.getClass().getName() + " method---->" + methodname);
	}//end of updateExamProfile
	/*****************************************************************
	 * This method retrieves the student's exam profile
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 ****************************************************************/
	private void fetchExamProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String methodname = "fetchExamProfile";
		logger.info("Entry--->" + this.getClass().getName() + " method---->" + methodname);
		PrintWriter out = response.getWriter();
		
		JSONObject respObj = new JSONObject();
		JSONObject jObjProfile = new JSONObject();
		try {
			respObj.put("status", "SUCCESS");
			respObj.put("msg", "");
			
			JSONObject reqObj = new JSONObject(request.getParameter("data"));
			logger.debug("request data --->"+reqObj.toString());
			String exam_code = !StringUtils.isEmpty(reqObj.getString("exam_code")) ? reqObj.getString("exam_code"): "";
			String notification_id = !StringUtils.isEmpty(reqObj.getString("notification_id")) ? reqObj.getString("notification_id"): "";
			int user_id = !StringUtils.isEmpty(reqObj.getString("user_id")) ? Integer.parseInt(reqObj.getString("user_id")): -1;
			if(!StringUtils.isEmpty(exam_code) && user_id != -1){
				StudentExamProfileVO sepvo = appService.getStudentExamProfile(notification_id,exam_code, user_id);
				if(null != sepvo){
					jObjProfile = new JSONObject(new Gson().toJson(sepvo));
				}
				
			}
			respObj.put("exam_profile", jObjProfile);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				respObj = new JSONObject();
				respObj.put("status", "FAILURE");
				respObj.put("msg", ProjectConstants.MSG_SERVER_ERROR);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		logger.info("Response ---->" + respObj.toString());
		out.print(respObj);
		logger.info("Exit--->" + this.getClass().getName() + " method---->" + methodname);
	}//end of fetchExamProfile
	/****************************************************************
	 * This task is used to STOP the notification by changing
     * status of this notification to 'processed' and also makes
     * the status of all the applications under this to 'Accepted'
     * Then maps this exam_code to this notification
     * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 ***************************************************************/
	private void processApplications(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String methodname = "processApplications";
		logger.info("Entry--->" + this.getClass().getName() + " method---->" + methodname);
		PrintWriter out = response.getWriter();
		
		JSONObject respObj = new JSONObject();
		try {
			respObj.put("status", "FAILURE");
			respObj.put("msg", "Unable to process applications.");
			
			JSONObject reqObj = new JSONObject(request.getParameter("data"));
			logger.debug("requested data --->"+reqObj.toString());
			int admin_user_id = !StringUtils.isEmpty(reqObj.getString("admin_user_id")) ? Integer.parseInt(reqObj.getString("admin_user_id")): 0;
			String notification_id = !StringUtils.isEmpty(reqObj.getString("notification_id")) ? reqObj.getString("notification_id"): "";
			String exam_code = !StringUtils.isEmpty(reqObj.getString("exam_code")) ? reqObj.getString("exam_code"): "";
			if(admin_user_id != 0 && !StringUtils.isEmpty(notification_id) && !StringUtils.isEmpty(exam_code)){
				
				boolean status = appService.processApplications(notification_id,exam_code,admin_user_id);
				if(status){
					respObj.put("status", "SUCCESS");
					respObj.put("msg", "All applications processed successfully.");
				}
			}else{
				respObj.put("msg", "Invalid request.");
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				respObj = new JSONObject();
				respObj.put("status", "FAILURE");
				respObj.put("msg", ProjectConstants.MSG_SERVER_ERROR);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		logger.info("Response ---->" + respObj.toString());
		out.print(respObj);
		logger.info("Exit--->" + this.getClass().getName() + " method---->" + methodname);
	}//end of processApplications
	/***************************************************************
	 * This method used to fetch valid exam lists along with that
     * the total applied count for this notification
     * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 ****************************************************************/
	private void getPreProcessingApplicationDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String methodname = "getPreProcessingApplicationDetails";
		logger.info("Entry--->" + this.getClass().getName() + " method---->" + methodname);
		PrintWriter out = response.getWriter();
		
		JSONObject respObj = new JSONObject();
		JSONArray jaExams = new JSONArray();
		int iAppliedCount = 0;
		try {
			respObj.put("status", "SUCCESS");
			respObj.put("msg", "");
			
			JSONObject reqObj = new JSONObject(request.getParameter("data"));
			logger.debug("request data --->"+reqObj.toString());
			String notification_id = !StringUtils.isEmpty(reqObj.getString("notification_id")) ? reqObj.getString("notification_id"): "";
			if(!StringUtils.isEmpty(notification_id)){
				iAppliedCount = ((ArrayList<ApplicationVO>)appService.getApplicationsByStatus(notification_id,"Pending")).size();
				jaExams = commonService.getAvailableExams();
			}
			respObj.put("applied", iAppliedCount);
			respObj.put("exams", jaExams);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				respObj = new JSONObject();
				respObj.put("status", "FAILURE");
				respObj.put("msg", ProjectConstants.MSG_SERVER_ERROR);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		logger.info("Response ---->" + respObj.toString());
		out.print(respObj);
		logger.info("Exit--->" + this.getClass().getName() + " method---->" + methodname);
	}//end of getPreProcessingApplicationDetails
	/*********************************************************************************************
	 * This gets the available exams
	 * 
	 * @date 19-03-2017
	 * @author Amod
	 *********************************************************************************************/
	private void fetchExams(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String methodname = "fetchExams";
		logger.info("Entry--->" + this.getClass().getName() + " method---->" + methodname);
		PrintWriter out = response.getWriter();
		
		JSONObject respObj = new JSONObject();
		try {
			respObj.put("status", "SUCCESS");
			respObj.put("msg", "");
			
			
			JSONArray jaExams = commonService.getAvailableExams();
			respObj.put("exams", jaExams);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				respObj = new JSONObject();
				respObj.put("status", "FAILURE");
				respObj.put("msg", ProjectConstants.MSG_SERVER_ERROR);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		logger.info("Response ---->" + respObj.toString());
		out.print(respObj);
		logger.info("Exit--->" + this.getClass().getName() + " method---->" + methodname);
	}// end of fetchExams
	/******************************************************
	 * This method used to save students application to a
	 * particular notification
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 *******************************************************/
	private void applyToNotification(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String methodname = "applyToNotification";
		logger.info("Entry--->" + this.getClass().getName() + " method---->" + methodname);
		PrintWriter out = response.getWriter();
		
		JSONObject respObj = new JSONObject();
		try {
			respObj.put("status", "FAILURE");
			respObj.put("msg", "Unable to process your application.");
			
			JSONObject reqProfileObj = new JSONObject(request.getParameter("data"));
			logger.debug("requested profile --->"+reqProfileObj.toString());
			int user_id = !StringUtils.isEmpty(reqProfileObj.getString("user_id")) ? Integer.parseInt(reqProfileObj.getString("user_id")): -1;
			String notification_id = !StringUtils.isEmpty(reqProfileObj.getString("notification_id")) ? reqProfileObj.getString("notification_id"): "";
			if(user_id != -1 && !StringUtils.isEmpty(notification_id)){
				int applicationId = appService.checkApplication(user_id,notification_id);
				if(applicationId == -1){
					applicationId = appService.applyToNotification(user_id,notification_id);
					if(applicationId != -1){
						respObj.put("status", "SUCCESS");
						respObj.put("msg", "Application submitted successfully...");
						respObj.put("application_id", applicationId);
					}
					 
				}else{
					respObj.put("status", "FAILURE");
					respObj.put("msg", "You have already applied and your Application id is: "+applicationId); 
				} 
			}else{
				respObj.put("msg", "Invalid request.");
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				respObj = new JSONObject();
				respObj.put("status", "FAILURE");
				respObj.put("msg", ProjectConstants.MSG_SERVER_ERROR);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		logger.info("Response ---->" + respObj.toString());
		out.print(respObj);
		logger.info("Exit--->" + this.getClass().getName() + " method---->" + methodname);
	}//end of applyToNotification
	/******************************************************
	 * This method updates the user profile
	 * @param request
	 * @param response
	 * @throws IOException 
	 *******************************************************/
	private void updateProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String methodname = "updateProfile";
		logger.info("Entry--->" + this.getClass().getName() + " method---->" + methodname);
		PrintWriter out = response.getWriter();
		
		JSONObject respObj = new JSONObject();
		try {
			respObj.put("status", "SUCCESS");
			respObj.put("msg", "User profile updated successfully.");
			
			JSONObject reqProfileObj = new JSONObject(request.getParameter("data"));
			logger.debug("requested profile --->"+reqProfileObj.toString());
			
			
			boolean status = appService.updateProfile(reqProfileObj);
			if(!status){
				respObj.put("status", "FAILURE");
				respObj.put("msg", "Unable to do profile update.");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				respObj = new JSONObject();
				respObj.put("status", "FAILURE");
				respObj.put("msg", ProjectConstants.MSG_SERVER_ERROR);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		logger.info("Response ---->" + respObj.toString());
		out.print(respObj);
		logger.info("Exit--->" + this.getClass().getName() + " method---->" + methodname);
		
	}//end of updateProfile
	/*********************************************************************************************
	 * This method creates new notifications
	 * 
	 * @date 19-03-2017
	 * @author Amod
	 * @throws IOException 
	 *********************************************************************************************/
	private void createNotification(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String methodname = "createNotification";
		logger.info("Entry--->" + this.getClass().getName() + " method---->" + methodname);
		PrintWriter out = response.getWriter();
		
		JSONObject respObj = new JSONObject();
		try {
			respObj.put("status", "SUCCESS");
			respObj.put("msg", "New notification posted successfully.");
			
			JSONObject reqObj = new JSONObject(request.getParameter("data"));
			String comp_code = !StringUtils.isEmpty(reqObj.getString("comp_code")) ? reqObj.getString("comp_code") : "";
			String notification = !StringUtils.isEmpty(reqObj.getString("notification")) ? reqObj.getString("notification") : "";
			double hsc_percentage = !StringUtils.isEmpty(reqObj.getString("hsc_percentage")) ? Double.parseDouble(reqObj.getString("hsc_percentage")) : 0.0;
			double intrm_percentage = !StringUtils.isEmpty(reqObj.getString("intrm_percentage")) ? Double.parseDouble(reqObj.getString("intrm_percentage")) : 0.0;
			double grad_percentage = !StringUtils.isEmpty(reqObj.getString("grad_percentage")) ? Double.parseDouble(reqObj.getString("grad_percentage")) : 0.0;
			double pg_percentage = !StringUtils.isEmpty(reqObj.getString("pg_percentage")) ? Double.parseDouble(reqObj.getString("pg_percentage")) : 0.0;
			String created_by = !StringUtils.isEmpty(reqObj.getString("created_by")) ? reqObj.getString("created_by") : "";
			HashMap<String,Object> hm = new HashMap<>();
			hm.put("comp_code", comp_code);
			hm.put("notification", notification);
			hm.put("hsc_percentage", hsc_percentage);
			hm.put("intrm_percentage", intrm_percentage);
			hm.put("grad_percentage", grad_percentage);
			hm.put("pg_percentage", pg_percentage);
			hm.put("created_by", created_by);
			
			boolean status = appService.createNewNotification(hm);
			if(!status){
				respObj.put("status", "FAILURE");
				respObj.put("msg", "Unable to add new notification");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				respObj = new JSONObject();
				respObj.put("status", "FAILURE");
				respObj.put("msg", ProjectConstants.MSG_SERVER_ERROR);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		logger.info("Response ---->" + respObj.toString());
		out.print(respObj);
		logger.info("Exit--->" + this.getClass().getName() + " method---->" + methodname);
	}//end of createNotification
	/*********************************************************************************************
	 * This gets the available companies
	 * 
	 * @date 19-03-2017
	 * @author Amod
	 *********************************************************************************************/
	private void fetchCompanies(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String methodname = "fetchCompanies";
		logger.info("Entry--->" + this.getClass().getName() + " method---->" + methodname);
		PrintWriter out = response.getWriter();
		
		JSONObject respObj = new JSONObject();
		try {
			respObj.put("status", "SUCCESS");
			respObj.put("msg", "");
			
			
			JSONArray jaCompanies = commonService.getAvailableCompanies();
			respObj.put("companies", jaCompanies);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				respObj = new JSONObject();
				respObj.put("status", "FAILURE");
				respObj.put("msg", ProjectConstants.MSG_SERVER_ERROR);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		logger.info("Response ---->" + respObj.toString());
		out.print(respObj);
		logger.info("Exit--->" + this.getClass().getName() + " method---->" + methodname);
	}// end of fetchNotifications
	/*********************************************************************************************
	 * This gets the notifications based on status
	 * 
	 * @date 19-03-2017
	 * @author Amod
	 *********************************************************************************************/
	private void fetchNotifications(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String methodname = "fetchNotifications";
		logger.info("Entry--->" + this.getClass().getName() + " method---->" + methodname);
		PrintWriter out = response.getWriter();
		
		JSONObject respObj = new JSONObject();
		String status = "";
		try {
			respObj.put("status", "SUCCESS");
			respObj.put("msg", "");
			
			JSONObject reqObj = new JSONObject(request.getParameter("data"));
			status = !StringUtils.isEmpty(reqObj.getString("status"))?reqObj.getString("status"):"new";
			logger.info("requested notifications with status--->" + status);
			
			JSONArray jaNotifications = commonService.getNotificationsByStatus(status);
			respObj.put("notifications", jaNotifications);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				respObj = new JSONObject();
				respObj.put("status", "FAILURE");
				respObj.put("msg", ProjectConstants.MSG_SERVER_ERROR);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		logger.info("Response ---->" + respObj.toString());
		out.print(respObj);
		logger.info("Exit--->" + this.getClass().getName() + " method---->" + methodname);
	}// end of fetchNotifications
	/********************************************************************************************
	 * This method helps to get new password of user and save it to user table
	 * as user wants to change his/her password
	 * 
	 * @date 19-03-2017
	 * @author Amod
	 ********************************************************************************************/
	private void changePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String methodname = "changePassword";
		logger.info("Entry--->" + this.getClass().getName() + " method---->" + methodname);

		PrintWriter out = response.getWriter();
		Connection con = null;
		Statement st = null;
		JSONObject respObj = new JSONObject();
		try {
			respObj.put("status", "FAILURE");
			con = ConnectionFactory.getConnection();
			JSONObject reqObj = new JSONObject(request.getParameter("data"));
			if (con != null) {
				String user_id = reqObj.getString("user_id");
				String new_password = reqObj.getString("password");

				String update_query = String.format(ProjectConstants.QUERY_CHANGE_PASSWORD, new_password, user_id);

				st = con.createStatement();
				logger.info("query--->" + update_query);
				int update_rs = st.executeUpdate(update_query);
				if (update_rs > 0) {
					logger.info("bitp_user new password  is  updated Successfully");
					respObj.put("status", "SUCCESS");
					respObj.put("msg", "New Password is Updated");

				} else {
					logger.info("stl_user_info is not updated");
					respObj.put("status", "FAILURE");
					respObj.put("msg", "New Password Update Failed");
				}

				st.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				respObj = new JSONObject();
				respObj.put("status", "FAILURE");
				respObj.put("msg", ProjectConstants.MSG_SERVER_ERROR);
			} catch (JSONException je) {
				je.printStackTrace();
			}
		} finally {
			Utils.closeDB(st, con);
		}
		logger.info("Response ---->" + respObj.toString());
		out.println(respObj.toString());

		logger.info("Exit--->" + this.getClass().getName() + " method---->" + methodname);

	}// end of changePassword

	/********************************************************************************************
	 * This method helps to retrieve password if a registered active user forgotten his/her
	 * password with unique email id
	 * 
	 * @date 19-03-2017
	 * @author Amod
	 ********************************************************************************************/

	private void forgotPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String methodname = "forgotPassword";
		logger.info("Entry--->" + this.getClass().getName() + " method---->" + methodname);

		PrintWriter out = response.getWriter();
		Connection con = null;
		JSONObject respObj = new JSONObject();
		try {
			respObj.put("status", "FAILURE");
			con = ConnectionFactory.getConnection();
			JSONObject reqObj = new JSONObject(request.getParameter("data"));
			if (con != null) {
				String email = reqObj.getString("email");
				
				// generate a random password
				String newPW = RandomStringUtils.randomAlphanumeric(5);

				// update password in stl_user_info table

				String update_query = String.format("update bitp_user set password = '%s',updated_on = CURRENT_TIMESTAMP where email = '%s'  and active = 'Y'", newPW, email);

				Statement update_st;

				update_st = con.createStatement();
				logger.info("query--->" + update_query);
				int update_rs = update_st.executeUpdate(update_query);
				if (update_rs > 0) {
					logger.info("bitp_user password  is  updated Successfully");
					respObj.put("msg", "New Password is Updated");
					
					respObj.put("status", "SUCCESS");
					if (!StringUtils.isEmpty(email)) {
						logger.info("Sending re-generated password Mail to " + email + " ...");
						JSONObject jObjEmailResp = new EmailClient().postMail(new String[]{email},new String[]{},new String[]{}, "BITP re-generated password", "Dear user, <br>this is your new password "+newPW+" you can change this in the app<br>"+
									"<br><br>If you believe you have received this email in error, please ignore it.<br><br>Regards,<br>BITP Admin");
					logger.info("Email Response--->"+jObjEmailResp.toString());
					} else {
						logger.info("bitp_user is not updated");
						respObj.put("status", "FAILURE");
						respObj.put("msg", "New Password Update Failed");
					}
				update_st.close();
				}
			}//end if valid con
		} catch (Exception e) {
			e.printStackTrace();
			try {
				respObj = new JSONObject();
				respObj.put("status", "FAILURE");
				respObj.put("msg", ProjectConstants.MSG_SERVER_ERROR);
			} catch (JSONException je) {
				je.printStackTrace();
			}
		} finally {
			Utils.closeDB(con);
		}
		logger.info("Response ---->" + respObj.toString());
		out.println(respObj.toString());

		logger.info("Exit--->" + this.getClass().getName() + " method---->" + methodname);

	}// end of forgotPassword
	/*********************************************************************************************
	 * This validates the user credentials for login to the app
	 * 
	 * @date 19-03-2017
	 * @author Amod
	 *********************************************************************************************/
	private void validateLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String methodname = "validateLogin";
		logger.info("Entry--->" + this.getClass().getName() + " method---->" + methodname);
		PrintWriter out = response.getWriter();
		Connection con = null;
		Statement st = null;
		Statement stUpdateLogin = null;
		ResultSet rsInput = null;
		String password = "";
		String username = "";
		int uid = -1;
		String query = "";
		JSONObject respObj = new JSONObject();

		try {
			respObj.put("status", "FAILURE");
			respObj.put("msg", ProjectConstants.MSG_INVALID_USER);

			con = ConnectionFactory.getConnection();
			JSONObject reqObj = new JSONObject(request.getParameter("data"));
			username = !StringUtils.isEmpty(reqObj.getString("username"))?reqObj.getString("username"):"";
			password = !StringUtils.isEmpty(reqObj.getString("password"))?reqObj.getString("password"):"";
			logger.info("username--->" + username + " password--->" + password );
			if (con != null) {
				st = con.createStatement();
				if(!StringUtils.isEmpty(username) || !StringUtils.isEmpty(password)){
					query = String.format(ProjectConstants.QUERY_VALIDATE_LOGIN, username, password);
					logger.info("query--->" + query);
					rsInput = st.executeQuery(query);
	
					if (rsInput.next()) {
						respObj.put("status", "SUCCESS");
						uid = (rsInput.getString("user_id") != null ? Integer.parseInt(rsInput.getString("user_id").trim()) : -1);// save in uid for use
						respObj.put("user_id", uid);
						respObj.put("user_type", (rsInput.getString("user_type")) != null ? rsInput.getString("user_type").trim() : "");
						respObj.put("phnno", (rsInput.getString("phnno")) != null ? rsInput.getString("phnno").trim() : "");
						respObj.put("email", (rsInput.getString("email")) != null ? rsInput.getString("email").trim() : "");
						respObj.put("name", (rsInput.getString("name")) != null ? rsInput.getString("name").trim() : "");
						
						respObj.put("msg", "Valid User");
	
					}
					// Get profile details if avaialble
					if ((uid != -1)) {
						JSONObject profile = appService.getUserProfileInfo(uid);
						logger.info("Profile information--->" + profile);
						respObj.put("profile_info", profile);
					}
				}//end if empty details check
			}//end if valid connection check
		} catch (Exception e) {
			e.printStackTrace();
			try {
				respObj = new JSONObject();
				respObj.put("status", "FAILURE");
				respObj.put("msg", ProjectConstants.MSG_SERVER_ERROR);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			Utils.closeDB(rsInput, st, stUpdateLogin, con);
		}
		logger.info("Response ---->" + respObj.toString());
		out.print(respObj);
		logger.info("Exit--->" + this.getClass().getName() + " method---->" + methodname);
	}// end of validateLogin
	/********************************************************************************************
	 * This checks whether user is already registered or not If No,
	 * do the new registration and return SUCCESS message
	 * 
	 * @date 19-03-2017
	 * @author Amod
	 *********************************************************************************************/
	private void saveUserRegistration(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String methodname = "saveUserRegistration";
		logger.info("Entry--->" + this.getClass().getName() + " method---->" + methodname);
		PrintWriter out = response.getWriter();
		Connection con = null;
		Statement stmtIsRegistered = null;
		Statement stmtRegistration = null;
		Statement stmtUserId = null;
		ResultSet rs_registered_query = null;
		String query = "";
		int uc = 0;
		JSONObject respObj = new JSONObject();
		boolean isNew = true;
		try {
			respObj.put("status", "SUCCESS");
			con = ConnectionFactory.getConnection();
            
			JSONObject reqObj = new JSONObject(request.getParameter("data"));
			String name = !StringUtils.isEmpty(reqObj.getString("name"))?reqObj.getString("name"):"";
			String email = !StringUtils.isEmpty(reqObj.getString("email"))?reqObj.getString("email"):"";
			String phone = !StringUtils.isEmpty(reqObj.getString("phone"))?reqObj.getString("phone"):"";
			String uname = !StringUtils.isEmpty(reqObj.getString("uname"))?reqObj.getString("uname"):"";
			String pass = !StringUtils.isEmpty(reqObj.getString("pass"))?reqObj.getString("pass"):"";
			String user_type = !StringUtils.isEmpty(reqObj.getString("user_type"))?reqObj.getString("user_type"):"";
			int userId = 0;

			if (con != null) {
				// Check is user registered previously
				stmtIsRegistered = con.createStatement();
				logger.info("request Data--->" + reqObj.toString());
				String is_registered_query = String.format(ProjectConstants.QUERY_CHECK_USER_REGISTRATION, uname,pass);
				logger.info("query check registration--->" + is_registered_query);
				rs_registered_query = stmtIsRegistered.executeQuery(is_registered_query);

				if (rs_registered_query.next()) {// Already registered
					logger.info("Request for duplicate Registration");
					isNew = false;
					// Get existing user id
					userId = rs_registered_query.getInt("user_id");
					
            		
					logger.info("bitp_user duplicate registration discarded");
					respObj.put("user_id", userId);
					respObj.put("msg", ProjectConstants.MSG_DUPLICATE_REGISTRATION);
					respObj.put("status", "SUCCESS");
				} else {
					// Generate new user id from sequence
					stmtUserId = con.createStatement();
					logger.info("query--->" + ProjectConstants.QUERY_USER_ID_NEXT_SEQ);
					ResultSet rsId = stmtUserId.executeQuery(ProjectConstants.QUERY_USER_ID_NEXT_SEQ);
					if (rsId.next()) {
						userId = rsId.getInt(1);
					}
					logger.info("New User Id created--->" + userId);
					//INSERT INTO bitp_user( user_id, phnno, email, username, password, name, user_type, created_on,  created_by,active) VALUES (?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, ?)
				    query = String.format(ProjectConstants.QUERY_NEW_REGISTRATION ,userId,phone,email,uname,pass,name,user_type );
					stmtRegistration = con.createStatement();
					logger.info("query --->" + query);
					int rs = stmtRegistration.executeUpdate(query);
					query = String.format(ProjectConstants.QUERY_INSERT_USER_PROFILE, userId);
					logger.info("query --->" + query);
					rs = stmtRegistration.executeUpdate(query);
					
					if (rs > 0) {
						logger.info("Registration success...");
						
						// send mail
						JSONObject jObjEmailResp = new EmailClient().postMail(new String[]{email},new String[]{},new String[]{}, "BITP New Registration", "Dear user, <br>Thank you for downloading BITP App!<br>"
	+ "<br><br>If you believe you have received this email in error, please ignore it.<br><br>Regards,<br>BITP Admin");
						logger.info("Email Response--->"+jObjEmailResp.toString());
						respObj.put("user_id", userId);
						respObj.put("msg", "Registration Success");
						respObj.put("status", "SUCCESS");
					} else {
						logger.info("Registration failed!!!");
						respObj.put("msg", "Registration failed due to server error");
						con.rollback();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				respObj = new JSONObject();
				respObj.put("status", "FAILURE");
				respObj.put("msg", ProjectConstants.MSG_SERVER_ERROR);
			} catch (JSONException je) {
				je.printStackTrace();
			}
		} finally {
			Utils.closeDB(rs_registered_query,stmtIsRegistered, stmtUserId,stmtRegistration, con);
		}
		logger.info("Response--->" + respObj.toString());
		out.println(respObj.toString());
		logger.info("Exit--->" + this.getClass().getName() + " method---->" + methodname);
	}// end of saveUserRegistration
	
	
	
	
	
	
	
	/*****************************************************************************************************************************************************/
	
	
}//end class
