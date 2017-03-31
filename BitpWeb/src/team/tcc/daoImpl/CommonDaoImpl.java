
package team.tcc.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import team.tcc.dao.CommonDao;
import team.tcc.util.ConnectionFactory;
import team.tcc.util.ProjectConstants;
import team.tcc.util.Utils;
import team.tcc.vo.CompanyVO;
import team.tcc.vo.ExamVO;
import team.tcc.vo.NotificationVO;
import team.tcc.vo.PlacementVO;
import team.tcc.vo.TrainingVO;

/**************************************************************
 * @author 
 * Date : Mar, 18 2017
 **************************************************************/
public class CommonDaoImpl implements CommonDao {
	private static CommonDaoImpl commonDaoImpl = null;
	private static Logger logger = null;
	String methodname="";
	String userId = "";
	
	public CommonDaoImpl() {
		logger = Logger.getLogger(CommonDaoImpl.class) ;
	}
	
	public static CommonDaoImpl getInstance(){
		if(commonDaoImpl == null){
			commonDaoImpl = new CommonDaoImpl();
			logger.info("CommonDaoImpl Instantiated...");
		}
		return commonDaoImpl;
	}
	
	public static void main(String[] args) {
		getInstance();
		ConnectionFactory.getLocalConnection() ;
	}
	

	
	
	/**************************************************            Business Logic Starts            *********************************************/
	/*****************************************************************
	 * This method returns the available notifications 
	 * based on a particular status
	 * @param status
	 * @return
	 * @date 18032017
	 * @author 
	 ****************************************************************/
	@Override
	public ArrayList<NotificationVO> getNotificationsByStatus(String status) {
		String methodname = "getNotificationsByStatus" ;
		logger.info("ENTRY---> methodname : "+methodname);
		ArrayList<NotificationVO> alNotificationVOs = new ArrayList<>() ;
		NotificationVO voObj = null ;
		Connection con = null;
		Statement stGetNotifications = null ;
		ResultSet rsGetNotifications = null ;
		//SELECT * FROM bitp_notification WHERE STATUS = '%s'
		String queryGetNotifications =  String.format(ProjectConstants.QUERY_GET_NOTIFICATIONS, status);
		try{
			con = ConnectionFactory.getConnection();
			if (con != null) {
				logger.info("query:"+queryGetNotifications);
				stGetNotifications = con.createStatement() ;
				
				rsGetNotifications = stGetNotifications.executeQuery(queryGetNotifications) ;
				while(rsGetNotifications.next()){
					voObj = new NotificationVO() ;
					
					voObj.setNotification_id(rsGetNotifications.getString("notification_id"));
					voObj.setComp_code(rsGetNotifications.getString("comp_code"));
					voObj.setNotification(rsGetNotifications.getString("notification"));
					voObj.setHsc_percentage(rsGetNotifications.getDouble("hsc_percentage"));
					voObj.setIntrm_percentage(rsGetNotifications.getDouble("intrm_percentage"));
					voObj.setGrad_percentage(rsGetNotifications.getDouble("grad_percentage"));
					voObj.setPg_percentage(rsGetNotifications.getDouble("pg_percentage"));
					voObj.setStatus(rsGetNotifications.getString("status"));
					voObj.setExam_code(rsGetNotifications.getString("exam_code"));
					voObj.setTraining_code(rsGetNotifications.getString("training_code"));
                    alNotificationVOs.add(voObj);
				}
			}//end of If
		 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Utils.closeDB(rsGetNotifications,stGetNotifications,con);
		}//end of try / catch
		
		logger.info("EXIT---> methodname : "+methodname);
		return alNotificationVOs;
	}//end of getNotificationsByStatus
	
	/*********************************************************************************************
	 * This gets the available companies
	 * 
	 * @date 19-03-2017
	 * @author Amod
	 *********************************************************************************************/
	@Override
	public List<CompanyVO> getAvailableCompanies() {
		String methodname = "getAvailableCompanies" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		ArrayList<CompanyVO> alCompanies =  new ArrayList<CompanyVO>() ;
		CompanyVO voObj = null;
		Connection con = null;
		Statement stGetCompanies = null ;
		ResultSet rsGetCompanies = null ;
		//select * from company_master
		String queryGetCompanies =  ProjectConstants.QUERY_GET_COMPANIES ;
		try{
			con = ConnectionFactory.getConnection();
			if (con != null) {
				logger.info("query:"+queryGetCompanies);
				stGetCompanies = con.createStatement() ;
				
				rsGetCompanies = stGetCompanies.executeQuery(queryGetCompanies) ;
				while(rsGetCompanies.next()){//member is valid
					voObj = new CompanyVO() ;
					
					voObj.setComp_code(rsGetCompanies.getString(2));
					voObj.setComp_name(rsGetCompanies.getString(3));
					
					alCompanies.add(voObj) ;
				}
			}//end of If
		 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Utils.closeDB(rsGetCompanies,stGetCompanies,con);
		}//end of try / catch
		//System.out.println(alModuleDtos);
		logger.info("EXIT---> methodname : "+methodname);
		return alCompanies;
	}//end of getAvailableCompanies
	/*********************************************************************************************
	 * This gets the available exams
	 * 
	 * @date 19-03-2017
	 * @author Amod
	 *********************************************************************************************/
	@Override
	public List<ExamVO> getAvailableExams() {
		String methodname = "getAvailableExams" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		ArrayList<ExamVO> alExams =  new ArrayList<ExamVO>() ;
		ExamVO voObj = null;
		Connection con = null;
		Statement st = null ;
		ResultSet rs = null ;
		//SELECT exam_code, exam_title, exam_desc,exam_welcometext,  exam_endtext, marks_exam_total, marks_interview_total,marks_total_cutoff, time_total FROM bitp_exam where active = 'Y'
		String queryGetExams =  ProjectConstants.QUERY_GET_EXAMS ;
		try{
			con = ConnectionFactory.getConnection();
			if (con != null) {
				logger.info("query:"+queryGetExams);
				st = con.createStatement() ;
				
				rs = st.executeQuery(queryGetExams) ;
				while(rs.next()){//member is valid
					voObj = new ExamVO() ;
					
					voObj.setExam_code(rs.getString(1));
					voObj.setExam_title(rs.getString(2));
					voObj.setExam_desc(rs.getString(3));
					voObj.setExam_welcometext(rs.getString(4));
					voObj.setExam_endtext(rs.getString(5));
					voObj.setMarks_exam_total(rs.getDouble(6));
					voObj.setMarks_interview_total(rs.getDouble(7));
					voObj.setMarks_total_cutoff(rs.getDouble(8));
					voObj.setTime_total(rs.getDouble(9));
					
					
					alExams.add(voObj) ;
				}
			}//end of If
		 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Utils.closeDB(rs,st,con);
		}//end of try / catch
		//System.out.println(alModuleDtos);
		logger.info("EXIT---> methodname : "+methodname);
		return alExams;
	}//end of getAvailableCompanies
	/*****************************************************************
	 * This method returns the available placement news 
	 * based on a particular status
	 * 
	 * @param status
	 * @return
	 * @date 23032017
	 * @author 
	 ****************************************************************/
	@Override
	public ArrayList<PlacementVO> getPlacementsByStatus(String status) {
		String methodname = "getPlacementsByStatus" ;
		logger.info("ENTRY---> methodname : "+methodname);
		ArrayList<PlacementVO> alPlacementVOs = new ArrayList<>() ;
		PlacementVO voObj = null ;
		Connection con = null;
		Statement st = null ;
		ResultSet rs = null ;
		//SELECT * FROM bitp_placement_news WHERE active = '%s'
		String query =  String.format(ProjectConstants.QUERY_GET_PLACEMENTS, status);
		try{
			con = ConnectionFactory.getConnection();
			if (con != null) {
				logger.info("query:"+query);
				st = con.createStatement() ;
				
				rs = st.executeQuery(query) ;
				while(rs.next()){
					voObj = new PlacementVO() ;
					
					voObj.setSlno(rs.getInt("slno"));
					voObj.setComp_code(rs.getString("comp_code"));
					voObj.setActive(rs.getString("active"));
					voObj.setNews(rs.getString("news"));
					voObj.setPlacement_code(rs.getString("placement_code"));
					voObj.setCreated_by(rs.getString("created_by"));
					voObj.setCreated_on(rs.getString("created_on"));
					
					alPlacementVOs.add(voObj);
				}
			}//end of If
		 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Utils.closeDB(rs,st,con);
		}//end of try / catch
		
		logger.info("EXIT---> methodname : "+methodname);
		return alPlacementVOs;
	}//end of getPlacementsByStatus
	/******************************************************************
	 * This method used to fetch the available active training
	 * 
	 * @return
	 * @date 22032017
	 * @author 
	 *****************************************************************/

	@Override
	public List<TrainingVO> getAvailableTraining() {
		String methodname = "getAvailableTraining" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		ArrayList<TrainingVO> alTraining =  new ArrayList<TrainingVO>() ;
		TrainingVO voObj = null;
		Connection con = null;
		Statement st = null ;
		ResultSet rs = null ;
		/*SELECT slno, training_code, training_desc, 
	       mth1_stipen_amt, mth2_stipen_amt, mth3_stipen_amt, mth4_stipen_amt, 
	       mth5_stipen_amt, mth6_stipen_amt, created_on, created_by,comp_code
	  FROM bitp_training WHERE ACTIVE = 'Y'*/
		String query =  ProjectConstants.QUERY_GET_TRAININGS ;
		try{
			con = ConnectionFactory.getConnection();
			if (con != null) {
				logger.info("query:"+query);
				st = con.createStatement() ;
				
				rs = st.executeQuery(query) ;
				while(rs.next()){//member is valid
					voObj = new TrainingVO() ;
					
					voObj.setSlno(rs.getInt(1));
					voObj.setTraining_code(rs.getString(2));
					voObj.setTraining_desc(rs.getString(3));
					voObj.setMth1_stipen_amt(rs.getDouble(4));
					voObj.setMth2_stipen_amt(rs.getDouble(5));
					voObj.setMth3_stipen_amt(rs.getDouble(6));
					voObj.setMth4_stipen_amt(rs.getDouble(7));
					voObj.setMth5_stipen_amt(rs.getDouble(8));
					voObj.setMth6_stipen_amt(rs.getDouble(9));
					voObj.setCreated_on(rs.getString(10));
					voObj.setCreated_by(rs.getString(11));
					voObj.setComp_code(rs.getString(12));
					voObj.setActive("Y");
					
					alTraining.add(voObj) ;
				}
			}//end of If
		 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Utils.closeDB(rs,st,con);
		}//end of try / catch
		//System.out.println(alModuleDtos);
		logger.info("EXIT---> methodname : "+methodname);
		return alTraining;
	}//end of getAvailableTraining
	
	/**************************************************            Business Logic Ends              *********************************************/
	
	
}//end class
