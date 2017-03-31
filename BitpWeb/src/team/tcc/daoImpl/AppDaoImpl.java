
package team.tcc.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import team.tcc.dao.AppDao;
import team.tcc.util.ConnectionFactory;
import team.tcc.util.ProjectConstants;
import team.tcc.util.Utils;
import team.tcc.vo.ApplicationVO;
import team.tcc.vo.NotificationResultVO;
import team.tcc.vo.ProfileVO;
import team.tcc.vo.StudentExamProfileVO;
import team.tcc.vo.StudentTrainingProfileVO;

/**************************************************************
 * @author 
 * Date : Mar, 18 2017
 **************************************************************/
public class AppDaoImpl implements AppDao {
	private static AppDaoImpl appDaoImpl = null;
	private static Logger logger = null;
	String methodname="";
	String userId = "";
	
	public AppDaoImpl() {
		logger = Logger.getLogger(AppDaoImpl.class) ;
	}
	
	public static AppDaoImpl getInstance(){
		if(appDaoImpl == null){
			appDaoImpl = new AppDaoImpl();
			logger.info("AppDaoImpl Instantiated...");
		}
		return appDaoImpl;
	}
	
	public static void main(String[] args) {
		getInstance();
		ConnectionFactory.getLocalConnection() ;
	}
	

	
	
	/**************************************************            Business Logic Starts            *********************************************/
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
		
		Connection con = null;
		Statement stmtNxtSlno = null,stmtCreateNotification = null;
		ResultSet rs = null ;
		int uc = -1 ;
		String queryGetNextSlno = ProjectConstants.QUERY_NEXT_NOTIFICATION_SLNO ;
		String queryCreateNotification = "" ;
		int iNxtSlno = 0 ;
		double hsc_percentage = (double)hm.get("hsc_percentage");
		double intrm_percentage = (double)hm.get("intrm_percentage");
		double pg_percentage = (double)hm.get("pg_percentage");
		double grad_percentage = (double)hm.get("grad_percentage");
		String created_by = (String)hm.get("created_by");
		String comp_code = (String)hm.get("comp_code");
		String notification = (String)hm.get("notification");
		String notificatio_id = "";
		boolean status = false;
		try {
			logger.info("query--->"+queryGetNextSlno);
			con = ConnectionFactory.getConnection();
			stmtNxtSlno = con.createStatement();
			rs = stmtNxtSlno.executeQuery(queryGetNextSlno);
			if (rs.next()) {
				iNxtSlno = rs.getInt(1);
			}// end of while loop
			
			if(iNxtSlno != 0){
				//generate new notification id
				notificatio_id = "N0"+iNxtSlno;
				queryCreateNotification = String.format(ProjectConstants.QUERY_NEW_NOTIFICATION, iNxtSlno,notificatio_id,comp_code,notification,hsc_percentage,intrm_percentage,grad_percentage,pg_percentage,created_by);
				logger.info("query--->"+queryCreateNotification);
				stmtCreateNotification = con.createStatement();
				uc = stmtCreateNotification.executeUpdate(queryCreateNotification);
				if(uc > 0){
					status = true;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			Utils.closeDB(stmtNxtSlno,rs,stmtCreateNotification,con);
		}//end of finally
		
		
		 
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
	public ProfileVO getUserProfileInfo(int userid) {
		String methodname = "getUserProfileInfo";
		logger.info("Entry--->"+this.getClass().getName()+" method---->"+methodname);
		
		logger.info("User id --->"+userid);
		Connection con = null;
		ResultSet rs = null;
		Statement stmt = null;
		ProfileVO profile = null;
		//select * from bitp_user_profile where user_id = %s
		String query = "";
		try {
			query = String.format(ProjectConstants.QUERY_GET_USER_PROFILE,userid);
			logger.info("query--->"+query);
			con = ConnectionFactory.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				profile = new ProfileVO();
				
				profile.setUser_id(rs.getInt("user_id"));
				profile.setGender(!StringUtils.isEmpty(rs.getString("gender")) ? rs.getString("gender").trim() : "");
				profile.setDob(!StringUtils.isEmpty(rs.getString("dob")) ? rs.getString("dob").trim() : "");
				profile.setName_father(!StringUtils.isEmpty(rs.getString("name_father")) ? rs.getString("name_father").trim() : "");
				profile.setName_mother(!StringUtils.isEmpty(rs.getString("name_mother")) ? rs.getString("name_mother").trim() : "");
				profile.setOccupation_father(!StringUtils.isEmpty(rs.getString("occupation_father")) ? rs.getString("occupation_father").trim() : "");
				profile.setOccupation_mother(!StringUtils.isEmpty(rs.getString("occupation_mother")) ? rs.getString("occupation_mother").trim() : "");
				profile.setNationality(!StringUtils.isEmpty(rs.getString("nationality")) ? rs.getString("nationality").trim() : "");
				profile.setAddress(!StringUtils.isEmpty(rs.getString("address")) ? rs.getString("address").trim() : "");
				profile.setQuota(!StringUtils.isEmpty(rs.getString("quota")) ? rs.getString("quota").trim() : "");
				profile.setDisability(!StringUtils.isEmpty(rs.getString("disability")) ? rs.getString("disability").trim() : "");
				profile.setIdentification_mark(!StringUtils.isEmpty(rs.getString("identification_mark")) ? rs.getString("identification_mark").trim() : "");
				profile.setHsc_board(!StringUtils.isEmpty(rs.getString("hsc_board")) ? rs.getString("hsc_board").trim() : "");
				profile.setPercentage_hsc(!StringUtils.isEmpty(rs.getString("percentage_hsc")) ? rs.getString("percentage_hsc").trim() : "0");
				profile.setYop_hsc(!StringUtils.isEmpty(rs.getString("yop_hsc")) ? rs.getString("yop_hsc").trim() : "");
				profile.setIntrm_board(!StringUtils.isEmpty(rs.getString("intrm_board")) ? rs.getString("intrm_board").trim() : "");
				profile.setPercentage_intrm(!StringUtils.isEmpty(rs.getString("percentage_intrm")) ? rs.getString("percentage_intrm").trim() : "0");
				profile.setYop_intrm(!StringUtils.isEmpty(rs.getString("yop_intrm")) ? rs.getString("yop_intrm").trim() : "");
				profile.setGrad_board(!StringUtils.isEmpty(rs.getString("grad_board")) ? rs.getString("grad_board").trim() : "");
				profile.setPercentage_grad(!StringUtils.isEmpty(rs.getString("percentage_grad")) ? rs.getString("percentage_grad").trim() : "0");
				profile.setYop_grad(!StringUtils.isEmpty(rs.getString("yop_grad")) ? rs.getString("yop_grad").trim() : "");
				profile.setPg_board(!StringUtils.isEmpty(rs.getString("pg_board")) ? rs.getString("pg_board").trim() : "");
				profile.setPercentage_pg(!StringUtils.isEmpty(rs.getString("percentage_pg")) ? rs.getString("percentage_pg").trim() : "0");
				profile.setYop_pg(!StringUtils.isEmpty(rs.getString("yop_pg")) ? rs.getString("yop_pg").trim() : "");
				profile.setImg_cert_hsc(!StringUtils.isEmpty(rs.getString("img_cert_hsc")) ? rs.getString("img_cert_hsc").trim() : "");
				profile.setImg_cert_intemediate(!StringUtils.isEmpty(rs.getString("img_cert_intemediate")) ? rs.getString("img_cert_intemediate").trim() : "");
				profile.setImg_cert_grad(!StringUtils.isEmpty(rs.getString("img_cert_grad")) ? rs.getString("img_cert_grad").trim() : "");
				profile.setImg_cert_pg(!StringUtils.isEmpty(rs.getString("img_cert_pg")) ? rs.getString("img_cert_pg").trim() : "");
				profile.setImg_signature(!StringUtils.isEmpty(rs.getString("img_signature")) ? rs.getString("img_signature").trim() : "");
				profile.setImg_profile(!StringUtils.isEmpty(rs.getString("img_profile")) ? rs.getString("img_profile").trim() : "");
				
				  
				
			}// end if
			stmt.close();
			rs.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Utils.closeDB(stmt,rs,con);
		}//end of finally
		logger.info("Exit--->"+this.getClass().getName()+" method---->"+methodname);
		return profile;
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
		
		Connection con = null;
		Statement stmtUpdateProfile = null;
		int uc = -1 ;
		boolean status = false;
		
		/*UPDATE bitp_user_profile
		   SET  gender='%s', dob='%s', name_father='%s', name_mother='%s', occupation_father='%s', 
		       occupation_mother='%s', nationality='%s', address='%s', quota='%s', disability='%s', 
		       identification_mark='%s', hsc_board='%s', percentage_hsc=%s, yop_hsc=%s, 
		       intrm_board='%s', percentage_intrm=%s, yop_intrm=%s, grad_board='%s', 
		       percentage_grad=%s, yop_grad=%s, updated_on=CURRENT_TIMESTAMP
		 WHERE user_id=%s*/
		 
		
		
		try {
			String queryUpdateProfile = String.format(ProjectConstants.QUERY_UPDATE_USER_PROFILE,
					!StringUtils.isEmpty(reqProfileObj.getString("gender")) ? reqProfileObj.getString("gender").trim() : "",
					!StringUtils.isEmpty(reqProfileObj.getString("dob")) ? reqProfileObj.getString("dob").trim() : "",
					!StringUtils.isEmpty(reqProfileObj.getString("name_father")) ? reqProfileObj.getString("name_father").trim() : "",
					!StringUtils.isEmpty(reqProfileObj.getString("name_mother")) ? reqProfileObj.getString("name_mother").trim() : "",
					!StringUtils.isEmpty(reqProfileObj.getString("occupation_father")) ? reqProfileObj.getString("occupation_father").trim() : "",
					!StringUtils.isEmpty(reqProfileObj.getString("occupation_mother")) ? reqProfileObj.getString("occupation_mother").trim() : "",
					!StringUtils.isEmpty(reqProfileObj.getString("nationality")) ? reqProfileObj.getString("nationality").trim() : "",
					!StringUtils.isEmpty(reqProfileObj.getString("address")) ? reqProfileObj.getString("address").trim() : "",
					!StringUtils.isEmpty(reqProfileObj.getString("quota")) ? reqProfileObj.getString("quota").trim() : "",
					!StringUtils.isEmpty(reqProfileObj.getString("disability")) ? reqProfileObj.getString("disability").trim() : "",
					!StringUtils.isEmpty(reqProfileObj.getString("identification_mark")) ? reqProfileObj.getString("identification_mark").trim() : "",
					!StringUtils.isEmpty(reqProfileObj.getString("hsc_board")) ? reqProfileObj.getString("hsc_board").trim() : "",
					!StringUtils.isEmpty(reqProfileObj.getString("percentage_hsc")) ? reqProfileObj.getString("percentage_hsc").trim() : "0.0",
					!StringUtils.isEmpty(reqProfileObj.getString("yop_hsc")) ? reqProfileObj.getString("yop_hsc").trim() : "0",
					!StringUtils.isEmpty(reqProfileObj.getString("intrm_board")) ? reqProfileObj.getString("intrm_board").trim() : "",
					!StringUtils.isEmpty(reqProfileObj.getString("percentage_intrm")) ? reqProfileObj.getString("percentage_intrm").trim() : "0.0",
					!StringUtils.isEmpty(reqProfileObj.getString("yop_intrm")) ? reqProfileObj.getString("yop_intrm").trim() : "0",
					!StringUtils.isEmpty(reqProfileObj.getString("grad_board")) ? reqProfileObj.getString("grad_board").trim() : "",
					!StringUtils.isEmpty(reqProfileObj.getString("percentage_grad")) ? reqProfileObj.getString("percentage_grad").trim() : "0.0",
					!StringUtils.isEmpty(reqProfileObj.getString("yop_grad")) ? reqProfileObj.getString("yop_grad").trim() : "0",
					!StringUtils.isEmpty(reqProfileObj.getString("user_id")) ? reqProfileObj.getString("user_id").trim() : ""
					) ;
			
			logger.info("query--->"+queryUpdateProfile);
			con = ConnectionFactory.getConnection();
			stmtUpdateProfile = con.createStatement();
			uc = stmtUpdateProfile.executeUpdate(queryUpdateProfile);
			
			if(uc >= 0){
				status = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Utils.closeDB(stmtUpdateProfile,con);
		}//end of finally
		
		
		 
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
		String methodname = "checkApplication";
		logger.info("Entry--->"+this.getClass().getName()+" method---->"+methodname);
		
		logger.info("User id --->"+user_id+" notification id--->"+notification_id);
		Connection con = null;
		ResultSet rs = null;
		Statement stmt = null;
		int applId = -1 ;
		
		//select application_no from bitp_application where user_id = %s and notification_id = '%s'
		String query = "";
		try {
			query = String.format(ProjectConstants.QUERY_CHECK_APPLICATION,user_id,notification_id);
			logger.info("query--->"+query);
			con = ConnectionFactory.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				applId = rs.getInt(1);
			}// end if
			stmt.close();
			rs.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Utils.closeDB(stmt,rs,con);
		}//end of finally
		logger.info("Exit--->"+this.getClass().getName()+" method---->"+methodname);
		return applId;
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
		String methodname = "applyToNotification";
		logger.info("Entry--->"+this.getClass().getName()+" method---->"+methodname);
		
		logger.info("User id --->"+user_id+" notification id--->"+notification_id);
		Connection con = null;
		Statement stmt = null;
		int applId = -1 ;
		
		//INSERT INTO bitp_application(application_no, notification_id, user_id,created_on) VALUES (%s, '%s', ?, CURRENT_TIMESTAMP)
		String query = "";
		try {
			int id = this.getSequenceID("bitp_application", "application_no");
			query = String.format(ProjectConstants.QUERY_INSERT_APPLICATION,id,notification_id,user_id);
			logger.info("query--->"+query);
			con = ConnectionFactory.getConnection();
			stmt = con.createStatement();
			int uc = stmt.executeUpdate(query);
			if (uc > 0) {
				applId = id;
			}// end if
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Utils.closeDB(stmt,con);
		}//end of finally
		logger.info("Exit--->"+this.getClass().getName()+" method---->"+methodname);
		return applId;
	}//end of applyToNotification
	
	// get Sequence ID
	public int getSequenceID(String tableName, String pkid)
	{
		int id = 0;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try
		{
			con = ConnectionFactory.getConnection();
			st = con.createStatement();
			rs = st.executeQuery("select COALESCE(max("+pkid+"),0) from "+tableName); 
			if(rs.next())
				id=rs.getInt(1);
			id++;
		}
		catch(Exception e)
		{
			logger.error(e);
		}
		finally
		{
			Utils.closeDB(st,rs,con);
		}
		return id;
	}//end of getSequenceID
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
		String methodname = "getApplicationsByStatus";
		logger.info("Entry--->"+this.getClass().getName()+" method---->"+methodname);
		
		logger.info("notification_id --->"+notification_id+" status--->"+status);
		Connection con = null;
		ResultSet rs = null;
		Statement stmt = null;
		ArrayList<ApplicationVO> alAppVO = new ArrayList<>();
		ApplicationVO appVo = null;
		//SELECT application_no, user_id FROM bitp_application where status = '%s' and notification_id = '%s'
		String query = "";
		try {
			query = String.format(ProjectConstants.QUERY_GET_APPLICATION_PRE_PROCESS_DETAILS,status,notification_id);
			logger.info("query--->"+query);
			con = ConnectionFactory.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				appVo = new ApplicationVO();
				
				appVo.setApplication_no(rs.getInt(1));
				appVo.setUser_id(rs.getInt(2));
				appVo.setNotification_id(notification_id);
				appVo.setStatus(status);
				
				alAppVO.add(appVo);
			}// end if
			stmt.close();
			rs.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Utils.closeDB(stmt,rs,con);
		}//end of finally
		logger.info("Exit--->"+this.getClass().getName()+" method---->"+methodname);
		return alAppVO;
	
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
		String methodname = "processApplications";
		logger.info("Entry--->"+this.getClass().getName()+" method---->"+methodname);
		 
		logger.info("exam_code --->"+exam_code+" notification id--->"+notification_id+" Logged by --->"+admin_user_id);
		Connection con = null;
		Statement stmt = null;
		boolean status = false;
		
		//UPDATE bitp_application SET status='%s', updated_on=CURRENT_TIMESTAMP, updated_by='%s' WHERE notification_id = '%s'
		//UPDATE bitp_notification SET updated_on=CURRENT_TIMESTAMP, updated_by='%s', status='%s', exam_code='%s' WHERE notification_id = '%s'
		//INSERT INTO bitp_exam_results ( exam_code,user_id ) SELECT '%s', user_id FROM bitp_application where notification_id = '%s' and status = 'Accepted'
		
		String query = "";
		try {
			query = String.format(ProjectConstants.QUERY_PROCESS_APPLICATIONS,"Accepted",admin_user_id,notification_id);
			logger.info("query--->"+query);
			con = ConnectionFactory.getConnection();
			con.setAutoCommit(false);
			stmt = con.createStatement();
			int uc = stmt.executeUpdate(query);
			if (uc > 0) {
				query = String.format(ProjectConstants.QUERY_PROCESS_NOTIFICATION,admin_user_id,"processed",exam_code,notification_id);
				uc = stmt.executeUpdate(query);
				if(uc > 0){
					query = String.format(ProjectConstants.QUERY_INSERT_EXAM_RESULT,exam_code,notification_id,notification_id);
					uc = stmt.executeUpdate(query);
					if(uc > 0){
						status = true;
					}
					
				}
				con.commit();
			}// end if			
		}catch(Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			Utils.closeDB(stmt,con);
		}//end of finally
		logger.info("Exit--->"+this.getClass().getName()+" method---->"+methodname);
		return status;
	}//end of processApplications
	/*****************************************************************************
	 * This method retrieves the student's exam profile
	 * 
	 * @param exam_code
	 * @param user_id
	 * @return
	 * @author Amod
	 * @date 22032017
	 ****************************************************************************/
	@Override
	public StudentExamProfileVO getStudentExamProfile(String notification_id,String exam_code, int user_id) {
		String methodname = "getStudentExamProfile";
		logger.info("Entry--->"+this.getClass().getName()+" method---->"+methodname);
		
		logger.info("user id --->"+user_id+" exam_code --->"+exam_code);
		Connection con = null;
		ResultSet rs = null;
		Statement stmt = null;
		StudentExamProfileVO profile = null;
		
		/*select usr.user_id,usr.name,usr.phnno,usr.email,
		exam.exam_code,exam.exam_title,exam.exam_desc,exam.marks_exam_total,exam.marks_interview_total,exam.marks_total_cutoff,exam.time_total,
		result.start_time,result.end_time,result.student_session_id,result.marks_exam_acquired,result.marks_interview_acquired,result.selection_status from 
		bitp_user usr,bitp_exam exam,bitp_exam_results result 
		where usr.user_id = result.user_id and exam.exam_code = result.exam_code and usr.user_id = %s and exam.exam_code = '%s'*/
		
		String query = "";
		try {
			query = String.format(ProjectConstants.QUERY_GET_EXAM_PROFILE,notification_id,user_id,exam_code);
			logger.info("query--->"+query);
			con = ConnectionFactory.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				profile = new StudentExamProfileVO();
				
				profile.setUser_id(rs.getInt(1));
				profile.setName(!StringUtils.isEmpty(rs.getString(2)) ? rs.getString(2).trim() : "");
				profile.setPhnno(!StringUtils.isEmpty(rs.getString(3)) ? rs.getString(3).trim() : "");
				profile.setEmail(!StringUtils.isEmpty(rs.getString(4)) ? rs.getString(4).trim() : "");
				profile.setExam_code(!StringUtils.isEmpty(rs.getString(5)) ? rs.getString(5).trim() : "");
				profile.setExam_title(!StringUtils.isEmpty(rs.getString(6)) ? rs.getString(6).trim() : "");
				profile.setExam_desc(!StringUtils.isEmpty(rs.getString(7)) ? rs.getString(7).trim() : "");
				profile.setMarks_exam_total(!StringUtils.isEmpty(rs.getString(8)) ? Double.parseDouble(rs.getString(8).trim()) : 0);
				profile.setMarks_interview_total(!StringUtils.isEmpty(rs.getString(9)) ? Double.parseDouble(rs.getString(9).trim()) : 0);
				profile.setMarks_total_cutoff(!StringUtils.isEmpty(rs.getString(10)) ? Double.parseDouble(rs.getString(10).trim()) : 0);
				profile.setTime_total(!StringUtils.isEmpty(rs.getString(11)) ? Double.parseDouble(rs.getString(11).trim()) : 0);
				profile.setStart_time(!StringUtils.isEmpty(rs.getString(12)) ? rs.getString(12).trim() : "");
				profile.setEnd_time(!StringUtils.isEmpty(rs.getString(13)) ? rs.getString(13).trim() : "");
				profile.setStudent_session_id(!StringUtils.isEmpty(rs.getString(14)) ? rs.getString(14).trim() : "");
				profile.setMarks_exam_acquired(!StringUtils.isEmpty(rs.getString(15)) ? Double.parseDouble(rs.getString(15).trim()) : 0);
				profile.setMarks_interview_acquired(!StringUtils.isEmpty(rs.getString(16)) ? Double.parseDouble(rs.getString(16).trim()) : 0);
				profile.setSelection_status(!StringUtils.isEmpty(rs.getString(17)) ? rs.getString(17).trim() : "");
				profile.setNotification_id(!StringUtils.isEmpty(rs.getString(18)) ? rs.getString(18).trim() : "");
				
				  
				
			}// end if
			stmt.close();
			rs.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Utils.closeDB(stmt,rs,con);
		}//end of finally
		logger.info("Exit--->"+this.getClass().getName()+" method---->"+methodname);
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
		String methodname = "updateProfile" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		Connection con = null;
		Statement stmt = null;
		int uc = -1 ;
		boolean status = false;
		
		//update bitp_exam_results set marks_interview_acquired = %s,updated_by='%s' where notification_id ='%s' and exam_code = '%s' and user_id = %s
		try {
			String query = String.format(ProjectConstants.QUERY_UPDATE_EXAM_INTERVIEW,mark,updated_by,notification_id,exam_code,user_id) ;
			
			logger.info("query--->"+query);
			con = ConnectionFactory.getConnection();
			stmt = con.createStatement();
			uc = stmt.executeUpdate(query);
			
			if(uc >= 0){
				status = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Utils.closeDB(stmt,con);
		}//end of finally
		
		
		 
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
		
		Connection con = null;
		Statement stmtNxtSlno = null,stmtCreatePlacement = null;
		ResultSet rs = null ;
		int uc = -1 ;
		String queryGetNextSlno = ProjectConstants.QUERY_NEXT_PLACEMENT_SLNO ;
		String queryCreatePlacement = "" ;//INSERT INTO bitp_placement_news( slno, placement_code, comp_code, news, created_on, created_by ) VALUES (%s, '%s', '%s', '%s', CURRENT_TIMESTAMP, '%s')
		int iNxtSlno = 0 ;
		
		String created_by = (String)hm.get("created_by");
		String comp_code = (String)hm.get("comp_code");
		String news = (String)hm.get("news");
		String placement_code = "";
		boolean status = false;
		try {
			logger.info("query--->"+queryGetNextSlno);
			con = ConnectionFactory.getConnection();
			stmtNxtSlno = con.createStatement();
			rs = stmtNxtSlno.executeQuery(queryGetNextSlno);
			if (rs.next()) {
				iNxtSlno = rs.getInt(1);
			}// end of while loop
			
			if(iNxtSlno != 0){
				//generate new notification id
				placement_code = "P0"+iNxtSlno;
				queryCreatePlacement = String.format(ProjectConstants.QUERY_NEW_PLACEMENT, iNxtSlno,placement_code,comp_code,news,created_by);
				logger.info("query--->"+queryCreatePlacement);
				stmtCreatePlacement = con.createStatement();
				uc = stmtCreatePlacement.executeUpdate(queryCreatePlacement);
				if(uc > 0){
					status = true;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			Utils.closeDB(stmtNxtSlno,rs,stmtCreatePlacement,con);
		}//end of finally
		
		
		 
		 logger.info("EXIT---> methodname : "+methodname);
		return status;
	}//end of createPlacementNews
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
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null ;
		int uc = -1 ;
		String query = ProjectConstants.QUERY_DROP_PLACEMENT_NEWS ;//UPDATE bitp_placement_news SET active='N', updated_on=CURRENT_TIMESTAMP, updated_by='%s' WHERE placement_code = '%s'
		
		String updated_by = (String)hm.get("updated_by");
		String placement_code = (String)hm.get("placement_code");
		boolean status = false;
		try {
			query = String.format(ProjectConstants.QUERY_DROP_PLACEMENT_NEWS, updated_by,placement_code);
			logger.info("query--->"+query);
			con = ConnectionFactory.getConnection();
			stmt = con.createStatement();
			uc = stmt.executeUpdate(query);
			if(uc > 0){
				status = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			Utils.closeDB(stmt,rs,con);
		}//end of finally
		
		
		 
		 logger.info("EXIT---> methodname : "+methodname);
		return status;
	}//end of dropPlacementNews
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
		String methodname = "createTraining" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		Connection con = null;
		Statement stmtNxtSlno = null,stmtCreateTraining = null;
		ResultSet rs = null ;
		int uc = -1 ;
		String queryGetNextSlno = ProjectConstants.QUERY_NEXT_TRAINING_SLNO ;
		//INSERT INTO bitp_training( slno, training_code, training_desc, mth1_stipen_amt, mth2_stipen_amt, mth3_stipen_amt, mth4_stipen_amt, mth5_stipen_amt, mth6_stipen_amt, created_on,created_by, comp_code) 
		//VALUES (%s, '%s', '%s', %s, %s, %s, %s, %s, %s,CURRENT_TIMESTAMP, '%s', '%s')
		String queryCreateTraining = "" ;
		int iNxtSlno = 0 ;
		
		String created_by = (String)hm.get("created_by");
		String training_desc = (String)hm.get("training_desc");
		String comp_code = (String)hm.get("comp_code");
		double mth1_stipen_amt = (Double)hm.get("mth1_stipen_amt");
		double mth2_stipen_amt = (Double)hm.get("mth2_stipen_amt");
		double mth3_stipen_amt = (Double)hm.get("mth3_stipen_amt");
		double mth4_stipen_amt = (Double)hm.get("mth4_stipen_amt");
		double mth5_stipen_amt = (Double)hm.get("mth5_stipen_amt");
		double mth6_stipen_amt = (Double)hm.get("mth6_stipen_amt");
		
		String training_code = "";
		boolean status = false;
		try {
			logger.info("query--->"+queryGetNextSlno);
			con = ConnectionFactory.getConnection();
			stmtNxtSlno = con.createStatement();
			rs = stmtNxtSlno.executeQuery(queryGetNextSlno);
			if (rs.next()) {
				iNxtSlno = rs.getInt(1);
			}// end of while loop
			
			if(iNxtSlno != 0){
				//generate new notification id
				training_code = "T0"+iNxtSlno;
				queryCreateTraining = String.format(ProjectConstants.QUERY_NEW_TRAINING, iNxtSlno,training_code,training_desc,mth1_stipen_amt,mth2_stipen_amt,mth3_stipen_amt,mth4_stipen_amt,mth5_stipen_amt,mth6_stipen_amt,created_by,comp_code);
				logger.info("query--->"+queryCreateTraining);
				stmtCreateTraining = con.createStatement();
				uc = stmtCreateTraining.executeUpdate(queryCreateTraining);
				if(uc > 0){
					status = true;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			Utils.closeDB(stmtNxtSlno,rs,stmtCreateTraining,con);
		}//end of finally
		
		
		 
		 logger.info("EXIT---> methodname : "+methodname);
		return status;
	}//end of createTraining
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
		
		Connection con = null;
		Statement stmtGetSelectedUid = null,stmtGetAddedTrainee = null,stmtAddTrainee = null;
		ResultSet rs = null ;
		int count = 0 ;
		String query = "" ;
		List<Integer> listSelectedUid = new ArrayList<>();
		List<Integer> listAddedUid = new ArrayList<>();
		//select user_id from bitp_exam_results where notification_id = '%s' and selection_status='selected'
		//select user_id from bitp_trainee_details where training_code = '%s'
		//INSERT INTO bitp_trainee_details(training_code, user_id,created_by) VALUES ('%s', %s, '%s')
		
		String notification_id = (String)hm.get("notification_id");
		String created_by = (String)hm.get("created_by");
		String training_code = (String)hm.get("training_code");
		
		try {
			query = String.format(ProjectConstants.QUERY_GET_SELECTED_STUDENTS_IN_EXAM,notification_id);
			logger.info("query--->"+query);
			con = ConnectionFactory.getConnection();
			con.setAutoCommit(false);
			stmtGetSelectedUid = con.createStatement();
			rs = stmtGetSelectedUid.executeQuery(query);
			while (rs.next()) {
				listSelectedUid.add(rs.getInt(1));
			}// end of while loop
			rs.close();
			logger.info("Total selected students found--->"+listSelectedUid.size());
			if(listSelectedUid.size() > 0){//There exist some selected students who must involve in training
				//get the list of user_ids who are already involved in this training
				query = String.format(ProjectConstants.QUERY_GET_INVOLVED_STUDENTS_IN_TRAINING,training_code);
				logger.info("query--->"+query);
				stmtGetAddedTrainee = con.createStatement();
				rs = stmtGetAddedTrainee.executeQuery(query);
				while (rs.next()) {
					listAddedUid.add(rs.getInt(1));
				}// end of while loop
				rs.close();
				logger.info("Total students already added--->"+listAddedUid.size());
			}
			listSelectedUid.removeAll(listAddedUid);
			logger.info("Total students found to be added newly into training--->"+listSelectedUid.size());
			if(listSelectedUid.size() > 0 ){
				
				stmtAddTrainee = con.createStatement();
				for(int uid : listSelectedUid){
					query = String.format(ProjectConstants.QUERY_ADD_TRAINEES,training_code,uid,created_by);
					logger.info("query--->"+query);
					stmtAddTrainee.addBatch(query);
				}
				int ctr[] = stmtAddTrainee.executeBatch();
				count = ctr.length;
			}
			con.commit();
			logger.info(count+" Total new trainees are added...");
			
		}catch(Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			Utils.closeDB(stmtAddTrainee,stmtGetAddedTrainee,rs,stmtGetSelectedUid,con);
		}//end of finally
		
		
		 
		 
	
		logger.info("EXIT---> methodname : "+methodname);
		return count;
	}//end of createNewNotification
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
		String methodname = "getStudentTrainingProfile";
		logger.info("Entry--->"+this.getClass().getName()+" method---->"+methodname);
		
		logger.info("user id --->"+user_id+" training_code --->"+training_code);
		Connection con = null;
		ResultSet rs = null;
		Statement stmt = null;
		StudentTrainingProfileVO profile = null;
		
		/*select usr.user_id,usr.name,usr.phnno,usr.email,
training.training_code,training.training_desc,training.mth1_stipen_amt,training.mth2_stipen_amt,training.mth3_stipen_amt,training.mth4_stipen_amt,training.mth5_stipen_amt,training.mth6_stipen_amt,training.comp_code,
td.mth1_feedback,td.mth2_feedback,td.mth3_feedback,td.mth4_feedback,td.mth5_feedback,td.mth6_feedback,
td.mth1_stipen_rcv,td.mth2_stipen_rcv,td.mth3_stipen_rcv,td.mth4_stipen_rcv,td.mth5_stipen_rcv,td.mth6_stipen_rcv,
td.project_submission_status,td.issue_certificate_status 
from bitp_user usr,bitp_training training,bitp_trainee_details td 
where  usr.user_id = td.user_id and training.training_code = td.training_code and usr.user_id = %s and training.training_code = '%s'*/
		
		String query = "";
		try {
			query = String.format(ProjectConstants.QUERY_GET_TRAINING_PROFILE,user_id,training_code);
			logger.info("query--->"+query);
			con = ConnectionFactory.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				profile = new StudentTrainingProfileVO();
				
				profile.setUser_id(rs.getInt(1));
				profile.setName(!StringUtils.isEmpty(rs.getString(2)) ? rs.getString(2).trim() : "");
				profile.setPhnno(!StringUtils.isEmpty(rs.getString(3)) ? rs.getString(3).trim() : "");
				profile.setEmail(!StringUtils.isEmpty(rs.getString(4)) ? rs.getString(4).trim() : "");
				
				profile.setTraining_code(!StringUtils.isEmpty(rs.getString(5)) ? rs.getString(5).trim() : "");
				profile.setTraining_desc(!StringUtils.isEmpty(rs.getString(6)) ? rs.getString(6).trim() : "");
				
				profile.setMth1_stipen_amt(!StringUtils.isEmpty(rs.getString(7)) ? Double.parseDouble(rs.getString(7).trim()) : 0);
				profile.setMth2_stipen_amt(!StringUtils.isEmpty(rs.getString(8)) ? Double.parseDouble(rs.getString(8).trim()) : 0);
				profile.setMth3_stipen_amt(!StringUtils.isEmpty(rs.getString(9)) ? Double.parseDouble(rs.getString(9).trim()) : 0);
				profile.setMth4_stipen_amt(!StringUtils.isEmpty(rs.getString(10)) ? Double.parseDouble(rs.getString(10).trim()) : 0);
				profile.setMth5_stipen_amt(!StringUtils.isEmpty(rs.getString(11)) ? Double.parseDouble(rs.getString(11).trim()) : 0);
				profile.setMth6_stipen_amt(!StringUtils.isEmpty(rs.getString(12)) ? Double.parseDouble(rs.getString(12).trim()) : 0);
				profile.setComp_code(!StringUtils.isEmpty(rs.getString(13)) ? rs.getString(13).trim() : "");
				
				profile.setMth1_feedback(!StringUtils.isEmpty(rs.getString(14)) ? rs.getString(14).trim() : "");
				profile.setMth2_feedback(!StringUtils.isEmpty(rs.getString(15)) ? rs.getString(15).trim() : "");
				profile.setMth3_feedback(!StringUtils.isEmpty(rs.getString(16)) ? rs.getString(16).trim() : "");
				profile.setMth4_feedback(!StringUtils.isEmpty(rs.getString(17)) ? rs.getString(17).trim() : "");
				profile.setMth5_feedback(!StringUtils.isEmpty(rs.getString(18)) ? rs.getString(18).trim() : "");
				profile.setMth6_feedback(!StringUtils.isEmpty(rs.getString(19)) ? rs.getString(19).trim() : "");
				
				profile.setMth1_stipen_rcv(!StringUtils.isEmpty(rs.getString(20)) ? rs.getString(20).trim() : "");
				profile.setMth2_stipen_rcv(!StringUtils.isEmpty(rs.getString(21)) ? rs.getString(21).trim() : "");
				profile.setMth3_stipen_rcv(!StringUtils.isEmpty(rs.getString(22)) ? rs.getString(22).trim() : "");
				profile.setMth4_stipen_rcv(!StringUtils.isEmpty(rs.getString(23)) ? rs.getString(23).trim() : "");
				profile.setMth5_stipen_rcv(!StringUtils.isEmpty(rs.getString(24)) ? rs.getString(24).trim() : "");
				profile.setMth6_stipen_rcv(!StringUtils.isEmpty(rs.getString(25)) ? rs.getString(25).trim() : "");
				
				profile.setProject_submission_status(!StringUtils.isEmpty(rs.getString(26)) ? rs.getString(26).trim() : "");
				profile.setIssue_certificate_status(!StringUtils.isEmpty(rs.getString(27)) ? rs.getString(27).trim() : "");
				
			}// end if
			stmt.close();
			rs.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Utils.closeDB(stmt,rs,con);
		}//end of finally
		logger.info("Exit--->"+this.getClass().getName()+" method---->"+methodname);
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
		
		Connection con = null;
		Statement stmtUpdateFeedback = null;
		int uc = -1 ;
		boolean status = false;
		
		/*UPDATE bitp_trainee_details SET  %s='%s' WHERE training_code = '%s' and user_id = %s*/
		 
		
		
		try {
			String queryUpdateFeedback = String.format(ProjectConstants.QUERY_UPDATE_TRAINING_FEEDBACK,month,feedback,training_code,user_id ) ;
			
			logger.info("query--->"+queryUpdateFeedback);
			con = ConnectionFactory.getConnection();
			stmtUpdateFeedback = con.createStatement();
			uc = stmtUpdateFeedback.executeUpdate(queryUpdateFeedback);
			
			if(uc >= 0){
				status = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Utils.closeDB(stmtUpdateFeedback,con);
		}//end of finally
		
		
		 
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
		String methodname = "fetchNotificationResults";
		logger.info("Entry--->"+this.getClass().getName()+" method---->"+methodname);
		
		logger.info("notification_id --->"+notification_id);
		Connection con = null;
		ResultSet rs = null;
		Statement stmt = null;
		NotificationResultVO objVO = new NotificationResultVO();
		int iAppliedCtr = 0 ;
		int iSelectedCtr = 0;
		int iRejectedCtr = 0;
		
		/*select exam_code,marks_exam_acquired,selection_status from bitp_exam_results where exam_code = (select exam_code from bitp_notification where notification_id ='N01')
		 and user_id in(SELECT user_id FROM bitp_application where notification_id = 'N01')*/
		
		String query = "";
		try {
			query = String.format(ProjectConstants.QUERY_GET_NOTIFICATION_RESULT,notification_id,notification_id);
			logger.info("query--->"+query);
			con = ConnectionFactory.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				objVO.setExam_code(rs.getString("exam_code"));
				iAppliedCtr++;
				if(StringUtils.equalsIgnoreCase(rs.getString(3), "selected")){
					iSelectedCtr++;
				}
				if(StringUtils.equalsIgnoreCase(rs.getString(3), "rejected")){
					iRejectedCtr++;
				}
				
			}// end if
			objVO.setApplied(iAppliedCtr);
			objVO.setSelected(iSelectedCtr);
			objVO.setRejected(iRejectedCtr);
			objVO.setNotification_id(notification_id);
			stmt.close();
			rs.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Utils.closeDB(stmt,rs,con);
		}//end of finally
		logger.info("Exit--->"+this.getClass().getName()+" method---->"+methodname);
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
		
		Connection con = null;
		Statement stmt = null;
		int uc = -1 ;
		boolean status = false;
		
		/*UPDATE bitp_exam_results SET selection_status='%s' WHERE exam_code = '%s' and user_id = %s*/
		 
		
		
		try {
			String query = String.format(ProjectConstants.QUERY_UPDATE_EXAM_SELECTION_STATUS,selection_status,exam_code,user_id ) ;
			
			logger.info("query--->"+query);
			con = ConnectionFactory.getConnection();
			stmt = con.createStatement();
			uc = stmt.executeUpdate(query);
			
			if(uc >= 0){
				status = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Utils.closeDB(stmt,con);
		}//end of finally
		
		
		 
		 logger.info("EXIT---> methodname : "+methodname);
		return status;
	}//end of selectByExamResult
	/*******************************************************************************
	 * This task does the followings:
     * - The task is to pay stipend amount to all the
     * trainees enrolled in a particular training for a particular
     * month
	 * 
	 * @param training_code
	 * @param month
	 * @return
	 * @author Amod
	 * @date 25032017
	 ******************************************************************************/
	@Override
	public boolean payStipend(String training_code, String month) {
		String methodname = "payStipend" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		Connection con = null;
		Statement stmt = null;
		int uc = -1 ;
		boolean status = false;
		
		/*UPDATE bitp_trainee_details SET %s = 'Y' where training_code= '%s'*/
		 
		
		
		try {
			String query = String.format(ProjectConstants.QUERY_PAY_STIPEND,month,training_code) ;
			
			logger.info("query--->"+query);
			con = ConnectionFactory.getConnection();
			stmt = con.createStatement();
			uc = stmt.executeUpdate(query);
			
			if(uc > 0){
				status = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Utils.closeDB(stmt,con);
		}//end of finally
		
		
		 
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
		
		Connection con = null;
		Statement stmt = null;
		int uc = -1 ;
		boolean status = false;
		
		/*UPDATE bitp_trainee_details SET issue_certificate_status = 'Y' where training_code= '%s' and user_id = %s*/
		 
		try {
			String query = String.format(ProjectConstants.QUERY_UPDATE_ISSUE_CERT,training_code,user_id) ;
			
			logger.info("query--->"+query);
			con = ConnectionFactory.getConnection();
			stmt = con.createStatement();
			uc = stmt.executeUpdate(query);
			
			if(uc > 0){
				status = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Utils.closeDB(stmt,con);
		}//end of finally
		
		
		 
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
		
		Connection con = null;
		Statement stmt = null;
		int uc = -1 ;
		boolean status = false;
		
		/*UPDATE bitp_trainee_details SET project_submission_status = 'Y' where training_code= '%s' AND user_id = %s*/
		 
		
		
		try {
			String query = String.format(ProjectConstants.QUERY_SUBMIT_PROJECT_REPORT,training_code,user_id) ;
			
			logger.info("query--->"+query);
			con = ConnectionFactory.getConnection();
			stmt = con.createStatement();
			uc = stmt.executeUpdate(query);
			
			if(uc > 0){
				status = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Utils.closeDB(stmt,con);
		}//end of finally
		
		
		 
		 logger.info("EXIT---> methodname : "+methodname);
		return status;
	}//end of payStipend
	
	
	
	
	
	
	
	
	
	
	
	/**************************************************            Business Logic Ends              *********************************************/
	
}
