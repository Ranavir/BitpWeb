
package team.tcc.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import com.google.gson.JsonElement;

import team.tcc.vo.ApplicationVO;
import team.tcc.vo.NotificationResultVO;
import team.tcc.vo.StudentExamProfileVO;
import team.tcc.vo.StudentTrainingProfileVO;

/**
 * @author 
 *
 */
public interface AppService extends Serializable{
	/*****************************************************************
	 * This method creates a new notification based on the
	 * data supplied
	 * 
	 * @param hm
	 * @return
	 * @date 18032017
	 * @author 
	 ***************************************************************/
	boolean createNewNotification(HashMap<String, Object> hm);
	/****************************************************************************
	 * This method returns the profile information of a particular user id
	 * 
	 * @param int
	 * @return JSONObject
	 * @author Amod
	 * @date 21032017
	 ***************************************************************************/
	JSONObject getUserProfileInfo(int uid);
	/****************************************************************************
	 * This method updates the profile by taking the key values
	 * present in the request object
	 *  
	 * @param reqProfileObj
	 * @return
	 * @author Amod
	 * @date 21032017
	 ***************************************************************************/
	boolean updateProfile(JSONObject reqProfileObj);
	/**************************************************************************
	 * This method checks whether an application available with this 
	 * userid and notification id
	 * 
	 * @param user_id
	 * @param notification_id
	 * @return
	 * @author Amod
	 * @date 21032017
	 **************************************************************************/
	int checkApplication(int user_id, String notification_id);
	/***************************************************************************
	 * This method applies the user to this notification
	 * 
	 * @param user_id
	 * @param notification_id
	 * @return
	 * @author Amod
	 * @date 21032017
	 **************************************************************************/
	int applyToNotification(int user_id, String notification_id);
	/****************************************************************************
	 * This method gets the application details for a notification with specified
	 * status
	 * 
	 * @param notification_id
	 * @param string
	 * @return
	 * @author Amod
	 * @date 21032017
	 ***************************************************************************/
	ArrayList<ApplicationVO> getApplicationsByStatus(String notification_id, String status);
	/****************************************************************************
	 * This task is used to STOP the notification by changing
     * status of this notification to 'processed' and also makes
     * the status of all the applications under this to 'Accepted'
     * Then maps this exam_code to this notification
     * 
	 * @param notification_id
	 * @param exam_code
	 * @param admin_user_id
	 * @return
	 * @author Amod
	 * @date 21032017
	 ***************************************************************************/
	boolean processApplications(String notification_id, String exam_code, int admin_user_id);

	/*****************************************************************************
	 * This method retrieves the student's exam profile
	 * 
	 * @param exam_code
	 * @param user_id
	 * @return
	 * @author Amod
	 * @param exam_code 
	 * @date 22032017
	 ****************************************************************************/
	StudentExamProfileVO getStudentExamProfile(String notification_id,String exam_code, int user_id);
	/******************************************************************************
	 * This method updates the student interview mark
	 * 
	 * @param notification_id
	 * @param user_id
	 * @param exam_code
	 * @param mark
	 * @return
	 * @author Amod
	 * @param updated_by 
	 * @date 22032017
	 *****************************************************************************/
	boolean updateExamProfile(String notification_id,int user_id, String exam_code, double mark, String updated_by);
	/*****************************************************************
	 * This method used to created a new placement news
	 * in system
	 * 
	 * @param hm
	 * @return
	 * @date 23032017
	 * @author 
	 ***************************************************************/
	boolean createPlacementNews(HashMap<String, Object> hm);
	/*****************************************************************
	 * This method used to de-active a placement news
	 * 
	 * @param hm
	 * @return
	 * @date 23032017
	 * @author 
	 ***************************************************************/
	boolean dropPlacementNews(HashMap<String, Object> hm);
	/******************************************************************
	 * This method used to create a new training activity.
	 * 
	 * @param hm
	 * @return
	 * @date 24032017
	 * @author 
	 *****************************************************************/
	boolean createTraining(HashMap<String, Object> hm);
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
	 * @param hm
	 * @return
	 ******************************************************************/
	int addTrainees(HashMap<String, Object> hm);
	/*****************************************************************************
	 * This method retrieves the student's training profile
	 * 
	 * @param exam_code
	 * @param user_id
	 * @return
	 * @author Amod
	 * @date 25032017
	 ****************************************************************************/
	StudentTrainingProfileVO getStudentTrainingProfile(String training_code, int user_id);
	/*****************************************************************************
	 * This method used to update a feedback against one training
	 * program by the trainee for a particular month
	 * 
	 * @param user_id
	 * @param training_code
	 * @param month
	 * @param feedback
	 * @return
	 * @author Amod
	 * @date 25032017
	 ****************************************************************************/
	boolean updateStudentTrainingFeedback(int user_id, String training_code, String month, String feedback);
	/*****************************************************************************
	 * This method used to give the notification specific details when 
	 * the notification is in processed state and one exam is associated
	 * with the notification
	 * 
	 * @param notification_id
	 * @return
	 * @author Amod
	 * @date 25032017
	 ****************************************************************************/
	NotificationResultVO fetchNotificationResults(String notification_id);
	/*******************************************************************************
	 * This method used to select or reject a student
	 * based on it's exam performance
	 * 
	 * @param exam_code
	 * @param selection_status
	 * @param user_id
	 * @return
	 * @author Amod
	 * @date 25032017
	 ******************************************************************************/
	boolean selectByExamResult(String exam_code, String selection_status, int user_id);
	/********************************************************************************
	 * The task is to pay stipend amount to all the
	 *  trainees enrolled in a particular training for a particular
	 * month
	 * @param training_code
	 * @param month
	 * @param updated_by
	 * @return
	 *******************************************************************************/
	boolean payStipend(String training_code, String mont);
	/*********************************************************************************
	 *  This method used to issue of a certificate to the 
	 * user for an enrolled training by training code and
	 * student id.
	 * 
	 * @param training_code
	 * @param user_id
	 * @return
	 ********************************************************************************/
	boolean issueCertificate(String training_code, int user_id);
	/*********************************************************************************
	 * This method used to submit project report by updating
	 * report submit status in backend
	 * 
	 * @param training_code
	 * @param user_id
	 * @return
	 ********************************************************************************/
	boolean submitProject(String training_code, int user_id);
	
	
	
	

}//end class
