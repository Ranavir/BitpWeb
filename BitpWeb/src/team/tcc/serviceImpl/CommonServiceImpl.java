
package team.tcc.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;

import team.tcc.dao.CommonDao;
import team.tcc.daoImpl.CommonDaoImpl;
import team.tcc.service.CommonService;
import team.tcc.vo.CompanyVO;
import team.tcc.vo.ExamVO;
import team.tcc.vo.NotificationVO;
import team.tcc.vo.PlacementVO;
import team.tcc.vo.TrainingVO;

import com.google.gson.Gson;
/*************************************************************
 * @author 
 * Date : Mar, 17 2017
 *************************************************************/
public class CommonServiceImpl implements CommonService {
	private static CommonServiceImpl commonServiceImpl = null;
	private static CommonDao commonDao = null ;
	private static Logger logger = null;
	String methodname="";
	String userId = "";
	
	
	public CommonServiceImpl() {
		logger = Logger.getLogger(CommonServiceImpl.class) ;
		commonDao = CommonDaoImpl.getInstance() ;
	}
	
	public static CommonServiceImpl getInstance(){
		if(commonServiceImpl == null){
			commonServiceImpl = new CommonServiceImpl();
			logger.info("CommonServiceImpl Instantiated...");
		}
		return commonServiceImpl;
	}
	
	/*****************************************************************
	 * This method returns the available notifications 
	 * based on a particular status
	 * @param status
	 * @return
	 * @date 18032017
	 * @author 
	 ****************************************************************/
	@Override
	public JSONArray getNotificationsByStatus(String status) {
		String methodname = "getNotificationsByStatus" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		JSONArray jaNotifications = new JSONArray();
		ArrayList<NotificationVO> alNotificationVOs = null ;
		alNotificationVOs = commonDao.getNotificationsByStatus(status);
		try {
			if(alNotificationVOs.size()>0){
				jaNotifications = new JSONArray(new Gson().toJson(alNotificationVOs));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		logger.info("EXIT---> methodname : "+methodname);
		return jaNotifications;
	}//end of getNotificationsByStatus
	/*********************************************************************************************
	 * This gets the available companies
	 * 
	 * @date 19-03-2017
	 * @author Amod
	 *********************************************************************************************/
	@Override
	public JSONArray getAvailableCompanies() {
		String methodname = "getAvailableCompanies" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		JSONArray jaCompanies = new JSONArray();
		List<CompanyVO> alCompanies= commonDao.getAvailableCompanies();
		try {
			jaCompanies = new JSONArray(new Gson().toJson(alCompanies));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		logger.info("EXIT---> methodname : "+methodname);
		return jaCompanies;
	}//end of getAvailableCompanies
	/*********************************************************************************************
	 * This gets the available exams
	 * 
	 * @date 19-03-2017
	 * @author Amod
	 *********************************************************************************************/
	@Override
	public JSONArray getAvailableExams() {
		String methodname = "getAvailableExams" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		JSONArray jaExams = new JSONArray();
		List<ExamVO> alExams = commonDao.getAvailableExams();
		try {
			jaExams = new JSONArray(new Gson().toJson(alExams));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		logger.info("EXIT---> methodname : "+methodname);
		return jaExams;
	}//end of getAvailableExams
	/*****************************************************************
	 * This method returns the available placement news 
	 * based on a particular status
	 * 
	 * @param status
	 * @return
	 * @date 22032017
	 * @author 
	 ****************************************************************/
	@Override
	public JSONArray getPlacementsByStatus(String status) {
		String methodname = "getPlacementsByStatus" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		JSONArray jaPlacements = new JSONArray();
		ArrayList<PlacementVO> alPlacementVOs = null ;
		alPlacementVOs = commonDao.getPlacementsByStatus(status);
		try {
			jaPlacements = new JSONArray(new Gson().toJson(alPlacementVOs));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		logger.info("EXIT---> methodname : "+methodname);
		return jaPlacements;
	}//end of getPlacementsByStatus
	/******************************************************************
	 * This method used to fetch the available active training
	 * 
	 * @return
	 * @date 22032017
	 * @author 
	 *****************************************************************/
	@Override
	public JSONArray getAvailableTraining() {
		String methodname = "getAvailableTraining" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		JSONArray jaExams = new JSONArray();
		List<TrainingVO> alTraining = commonDao.getAvailableTraining();
		try {
			jaExams = new JSONArray(new Gson().toJson(alTraining));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		logger.info("EXIT---> methodname : "+methodname);
		return jaExams;
	}//end of getAvailableTraining
	/********************************************************************
	 * This method gives the available trainings for a company
	 * @param comp_code
	 * @return
	 *******************************************************************/
	@Override
	public JSONArray getAvailableTrainingsByCompany(String comp_code) {
		String methodname = "getAvailableTrainingsByCompany" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		JSONArray jaExams = new JSONArray();
		List<TrainingVO> alTraining = commonDao.getAvailableTrainingsByCompany(comp_code);
		try {
			jaExams = new JSONArray(new Gson().toJson(alTraining));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		logger.info("EXIT---> methodname : "+methodname);
		return jaExams;
	}//end of getAvailableTrainingsByCompany
}//end class
