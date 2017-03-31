
package team.tcc.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import team.tcc.vo.CompanyVO;
import team.tcc.vo.ExamVO;
import team.tcc.vo.NotificationVO;
import team.tcc.vo.PlacementVO;
import team.tcc.vo.TrainingVO;
/**
 * @author 
 *
 */
public interface CommonDao extends Serializable{
	/*****************************************************************
	 * This method returns the available notifications 
	 * based on a particular status
	 * @param status
	 * @return
	 * @date 18032017
	 * @author 
	 ****************************************************************/
	ArrayList<NotificationVO> getNotificationsByStatus(String status);
	/*********************************************************************************************
	 * This gets the available companies
	 * 
	 * @date 19-03-2017
	 * @author Amod
	 *********************************************************************************************/
	List<CompanyVO> getAvailableCompanies();
	/*********************************************************************************************
	 * This gets the available exams
	 * 
	 * @date 19-03-2017
	 * @author Amod
	 *********************************************************************************************/
	List<ExamVO> getAvailableExams();
	/*****************************************************************
	 * This method returns the available placement news 
	 * based on a particular status
	 * 
	 * @param status
	 * @return
	 * @date 22032017
	 * @author 
	 ****************************************************************/
	ArrayList<PlacementVO> getPlacementsByStatus(String status);
	/******************************************************************
	 * This method used to fetch the available active training
	 * 
	 * @return
	 * @date 22032017
	 * @author 
	 *****************************************************************/
	List<TrainingVO> getAvailableTraining();
	
}
