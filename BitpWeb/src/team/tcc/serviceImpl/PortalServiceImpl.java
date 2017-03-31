
package team.tcc.serviceImpl;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import team.tcc.dao.PortalDao;
import team.tcc.daoImpl.PortalDaoImpl;
import team.tcc.service.PortalService;
/*************************************************************
 * @author 
 * Date : Mar, 17 2017
 *************************************************************/
public class PortalServiceImpl implements PortalService {
	private static PortalServiceImpl portalServiceImpl = null;
	private static PortalDao portalDao = null ;
	private static Logger logger = null;
	String methodname="";
	String userId = "";
	
	
	public PortalServiceImpl() {
		logger = Logger.getLogger(PortalServiceImpl.class) ;
		portalDao = PortalDaoImpl.getInstance() ;
	}
	
	public static PortalServiceImpl getInstance(){
		if(portalServiceImpl == null){
			portalServiceImpl = new PortalServiceImpl();
			logger.info("PortalServiceImpl Instantiated...");
		}
		return portalServiceImpl;
	}
	

	/***********************************************
	 * Login Process
	 * after getting the username and password 
	 * the method will check wheather the usertype is admin or not and also check it is active or not 
	 * then only the user can view the Reconcilation page
	 * "status":"success/failure"
	 * "message"
	 * @author Sankar Patra
	 ***********************************************/
	
	@Override
	public JSONObject checkUserInfoExistance(String userName, String password) {
		return portalDao.checkUserInfoExistance(userName,password);
	}//end checkUserInfoExistance
	
	
	/*************************************
	 * Get Exam Code List
	 * Return JSONArray
	 *****************************************/
	@Override
	public JSONArray getExamCodeList() {
		// TODO Auto-generated method stub
		return portalDao.getExamCodeList();
	}
	/******************************************************************
	 * Method that will get all the student answer details by exam code 
	 * Input value Exam Code
	 * Response JsonArray
	 *******************************************************************/
	@Override
	public JSONArray getUserResultByExamCode(String exam_code) {
		// TODO Auto-generated method stub
		return portalDao.getUserResultByExamCode(exam_code);
	}
	/******************************************************************
	 * Update New MArk 
	 * Input value new mark
	 * Response JsonArray
	 * @throws IOException 
	 * @throws JSONException 
	 *******************************************************************/
	@Override
	public String updateNewMark(Long newmark, Long uid,String examCode) {
		// TODO Auto-generated method stub
		return portalDao.updateNewMark(newmark,uid,examCode);
	}
	
	
	
	
	
	
	
	
	
	
}//end class
