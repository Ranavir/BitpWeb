
package team.tcc.service;

import java.io.Serializable;

import org.json.JSONArray;

/**
 * @author 
 *
 */
public interface CommonService extends Serializable{
	/*****************************************************************
	 * This method returns the available notifications 
	 * based on a particular status
	 * @param status
	 * @return
	 * @date 18032017
	 * @author 
	 ****************************************************************/
	JSONArray getNotificationsByStatus(String status);
	/*********************************************************************************************
	 * This gets the available companies
	 * 
	 * @date 19-03-2017
	 * @author Amod
	 *********************************************************************************************/
	JSONArray getAvailableCompanies();
	/*********************************************************************************************
	 * This gets the available exams
	 * 
	 * @date 19-03-2017
	 * @author Amod
	 *********************************************************************************************/
	JSONArray getAvailableExams();
	/*****************************************************************
	 * This method returns the available placement news 
	 * based on a particular status
	 * 
	 * @param status
	 * @return
	 * @date 22032017
	 * @author 
	 ****************************************************************/
	JSONArray getPlacementsByStatus(String status);
	/******************************************************************
	 * This method used to fetch the available active training
	 * 
	 * @return
	 * @date 22032017
	 * @author 
	 *****************************************************************/
	JSONArray getAvailableTraining();
	/********************************************************************
	 * This method gives the available trainings for a company
	 * @param comp_code
	 * @return
	 *******************************************************************/
	JSONArray getAvailableTrainingsByCompany(String comp_code);

	
	
	
	
	

}//end class
