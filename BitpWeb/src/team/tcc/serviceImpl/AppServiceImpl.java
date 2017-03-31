
package team.tcc.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import team.tcc.dao.AppDao;
import team.tcc.daoImpl.AppDaoImpl;
import team.tcc.service.AppService;
import team.tcc.vo.ApplicationVO;
import team.tcc.vo.NotificationResultVO;
import team.tcc.vo.ProfileVO;
import team.tcc.vo.StudentExamProfileVO;
import team.tcc.vo.StudentTrainingProfileVO;
/*************************************************************
 * @author 
 * Date : Mar, 17 2017
 *************************************************************/
public class AppServiceImpl implements AppService {
	private static AppServiceImpl appServiceImpl = null;
	private static AppDao appDao = null ;
	private static Logger logger = null;
	String methodname="";
	String userId = "";
	
	
	public AppServiceImpl() {
		logger = Logger.getLogger(AppServiceImpl.class) ;
		appDao = AppDaoImpl.getInstance() ;
	}
	
	public static AppServiceImpl getInstance(){
		if(appServiceImpl == null){
			appServiceImpl = new AppServiceImpl();
			logger.info("AppServiceImpl Instantiated...");
		}
		return appServiceImpl;
	}
	
	/*****************************************************************
	 * This method creates a new notification based on the
	 * data supplied
	 * 
	 * @param hm
	 * @return
	 * @date 18032017
	 * @author 
	 ***************************************************************/
	@Override
	public boolean createNewNotification(HashMap<String, Object> hm) {
		String methodname = "createNewNotification" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		boolean status = appDao.createNewNotification(hm);
		
		
		logger.info("EXIT---> methodname : "+methodname);
		return status;
	}//end of createNewNotification
	/****************************************************************************
	 * This method returns the profile information of a particular user id
	 * 
	 * @param int
	 * @return JSONObject
	 * @author Amod
	 * @date 21032017
	 ***************************************************************************/
	@Override
	public JSONObject getUserProfileInfo(int uid) {
		String methodname = "getUserProfileInfo";
		logger.info("Entry--->"+this.getClass().getName()+" method---->"+methodname);
		
		JSONObject jObjProfile = new JSONObject();
		ProfileVO profile = null;
		
		profile = appDao.getUserProfileInfo(uid);
		if(null != profile){
			
			try {
				jObjProfile = new JSONObject(new Gson().toJson(profile));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			logger.info("User Profile--->"+jObjProfile.toString());		
		}
		//Iterate over entire status object list to refine your required details
		
		
		logger.info("Exit--->"+this.getClass().getName()+" method---->"+methodname);
		return jObjProfile;
	}//end of getUserProfileInfo
	/****************************************************************************
	 * This method updates the profile by taking the key values
	 * present in the request object
	 *  
	 * @param reqProfileObj
	 * @return
	 * @author Amod
	 * @date 21032017
	 ***************************************************************************/
	@Override
	public boolean updateProfile(JSONObject reqProfileObj) {
		String methodname = "updateProfile" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		boolean status = appDao.updateProfile(reqProfileObj);
		
		
		logger.info("EXIT---> methodname : "+methodname);
		return status;
	}//end of updateProfile
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
	@Override
	public int checkApplication(int user_id, String notification_id) {
		String methodname = "checkApplication" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		int id = appDao.checkApplication(user_id,notification_id);
		
		
		logger.info("EXIT---> methodname : "+methodname);
		return id;
	}//end of checkApplication
	/***************************************************************************
	 * This method applies the user to this notification
	 * 
	 * @param user_id
	 * @param notification_id
	 * @return
	 * @author Amod
	 * @date 21032017
	 **************************************************************************/
	@Override
	public int applyToNotification(int user_id, String notification_id) {
		String methodname = "applyToNotification" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		int id = appDao.applyToNotification(user_id,notification_id);
		
		
		logger.info("EXIT---> methodname : "+methodname);
		return id;
	}//end of applyToNotification
	/****************************************************************************
	 * This method gets the application details for a notification with specified
	 * status
	 * 
	 * @param notification_id
	 * @param string
	 * @return
	 ***************************************************************************/
	@Override
	public ArrayList<ApplicationVO> getApplicationsByStatus(String notification_id, String status) {
		String methodname = "getApplicationsByStatus" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		ArrayList<ApplicationVO> alAppVOs = appDao.getApplicationsByStatus(notification_id,status);
		
		
		logger.info("EXIT---> methodname : "+methodname);
		return alAppVOs;
	}//end of getApplicationsByStatus
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
	@Override
	public boolean processApplications(String notification_id, String exam_code, int admin_user_id) {
		String methodname = "processApplications" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		boolean status = appDao.processApplications(notification_id,exam_code,admin_user_id);
		
		
		logger.info("EXIT---> methodname : "+methodname);
		return status;
	}//end of processApplications
	/*****************************************************************************
	 * This method retrieves the student's exam profile
	 * 
	 * @param notification_id
	 * @param exam_code
	 * @param user_id
	 * @return
	 * @author Amod
	 * @date 22032017
	 ****************************************************************************/
	@Override
	public StudentExamProfileVO getStudentExamProfile(String notification_id,String exam_code, int user_id) {
		String methodname = "getStudentExamProfile" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		StudentExamProfileVO profile = appDao.getStudentExamProfile(notification_id,exam_code,user_id);
		
		
		logger.info("EXIT---> methodname : "+methodname);
		return profile;
	}//end of getStudentExamProfile
	/******************************************************************************
	 * This method updates the student interview mark
	 * 
	 * @param notification_id
	 * @param user_id
	 * @param exam_code
	 * @param mark
	 * @param updated_by
	 * @return
	 * @author Amod
	 * @date 22032017
	 *****************************************************************************/
	@Override
	public boolean updateExamProfile(String notification_id,int user_id, String exam_code, double mark,String updated_by) {
		String methodname = "updateExamProfile" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		boolean status = appDao.updateExamProfile(notification_id,user_id,exam_code,mark,updated_by);
		
		
		logger.info("EXIT---> methodname : "+methodname);
		return status;
	}//end of updateExamProfile
	/*****************************************************************
	 * This method used to created a new placement news
	 * in system
	 * 
	 * @param hm
	 * @return
	 * @date 23032017
	 * @author 
	 ***************************************************************/
	@Override
	public boolean createPlacementNews(HashMap<String, Object> hm) {
		String methodname = "createPlacementNews" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		boolean status = appDao.createPlacementNews(hm);
		
		
		logger.info("EXIT---> methodname : "+methodname);
		return status;
	}//end of createPlacementNews
	/******************************************************************
	 * This method used to create a new training activity.
	 * 
	 * @param hm
	 * @return
	 * @date 24032017
	 * @author 
	 *****************************************************************/
	@Override
	public boolean createTraining(HashMap<String, Object> hm) {
		String methodname = "createPlacementNews" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		boolean status = appDao.createTraining(hm);
		
		
		logger.info("EXIT---> methodname : "+methodname);
		return status;
	}//end of createTraining
	/*****************************************************************
	 * This method used to de-active a placement news
	 * 
	 * @param hm
	 * @return
	 * @date 23032017
	 * @author 
	 ***************************************************************/
	@Override
	public boolean dropPlacementNews(HashMap<String, Object> hm) {
		String methodname = "dropPlacementNews" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		boolean status = appDao.dropPlacementNews(hm);
		
		
		logger.info("EXIT---> methodname : "+methodname);
		return status;
	}//end of dropPlacementNews
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
	@Override
	public int addTrainees(HashMap<String, Object> hm) {
		String methodname = "addTrainees" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		int count = appDao.addTrainees(hm);
		
		
		logger.info("EXIT---> methodname : "+methodname);
		return count;
	}//end of addTrainees
	/*****************************************************************************
	 * This method retrieves the student's training profile
	 * 
	 * @param exam_code
	 * @param user_id
	 * @return
	 * @author Amod
	 * @date 25032017
	 ****************************************************************************/
	@Override
	public StudentTrainingProfileVO getStudentTrainingProfile(String training_code, int user_id) {
		String methodname = "getStudentTrainingProfile" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		StudentTrainingProfileVO profile = appDao.getStudentTrainingProfile(training_code,user_id);
		
		
		logger.info("EXIT---> methodname : "+methodname);
		return profile;
	}//end of getStudentTrainingProfile
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
	@Override
	public boolean updateStudentTrainingFeedback(int user_id, String training_code, String month, String feedback) {
		String methodname = "updateStudentTrainingFeedback" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		boolean status = appDao.updateStudentTrainingFeedback(user_id,training_code,month,feedback);
		
		
		logger.info("EXIT---> methodname : "+methodname);
		return status;
	}//end of updateStudentTrainingFeedback
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
	@Override
	public NotificationResultVO fetchNotificationResults(String notification_id) {
		String methodname = "fetchNotificationResults" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		NotificationResultVO objVO = appDao.fetchNotificationResults(notification_id);
		
		
		logger.info("EXIT---> methodname : "+methodname);
		return objVO;
	}//end of fetchNotificationResults
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
	@Override
	public boolean selectByExamResult(String exam_code, String selection_status, int user_id) {
		String methodname = "selectByExamResult" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		boolean status = appDao.selectByExamResult(exam_code,selection_status,user_id);
		
		
		logger.info("EXIT---> methodname : "+methodname);
		return status;
	}//end of selectByExamResult
	/*******************************************************************************
	 * This task does the followings:
     * - The task is to pay stipend amount to all the
     * trainees enrolled in a particular training for a particular
     * month
	 ******************************************************************************/
	@Override
	public boolean payStipend(String training_code, String month) {
		String methodname = "payStipend" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		boolean status = appDao.payStipend(training_code,month);
		
		
		logger.info("EXIT---> methodname : "+methodname);
		return status;
	}//end of payStipend
	/*********************************************************************************
	 *  This method used to issue of a certificate to the 
	 * user for an enrolled training by training code and
	 * student id.
	 * 
	 * @param training_code
	 * @param user_id
	 * @return
	 ********************************************************************************/
	@Override
	public boolean issueCertificate(String training_code, int user_id) {
		String methodname = "issueCertificate" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		boolean status = appDao.issueCertificate(training_code,user_id);
		
		
		logger.info("EXIT---> methodname : "+methodname);
		return status;
	}//end of issueCertificate
	/*********************************************************************************
	 * This method used to submit project report by updating
	 * report submit status in backend
	 * 
	 * @param training_code
	 * @param user_id
	 * @return
	 ********************************************************************************/
	@Override
	public boolean submitProject(String training_code, int user_id) {
		String methodname = "submitProject" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		boolean status = appDao.submitProject(training_code,user_id);
		
		
		logger.info("EXIT---> methodname : "+methodname);
		return status;
	}//end of submitProject
}//end class
