
package team.tcc.service;

import java.io.IOException;
import java.io.Serializable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author 
 *
 */
public interface PortalService extends Serializable{
	/***********************************************
	 * Login Process
	 * after getting the username and password 
	 * the method will check wheather the usertype is admin or not and also check it is active or not 
	 * then only the user can view the Reconcilation page
	 * "status":"success/failure"
	 * "message"
	 * @author 
	 ***********************************************/
		
	JSONObject checkUserInfoExistance(String userName, String password);

	

	/******************************************************************
	 * Get Exam Code List
	 * Return JSONArray
	 ******************************************************************/
	JSONArray getExamCodeList();

	/******************************************************************
	 * Method that will get all the student answer details by exam code 
	 * Input value Exam Code
	 * Response JsonArray
	 *******************************************************************/

	JSONArray getUserResultByExamCode(String exam_code);

	/******************************************************************
	 * Update New MArk 
	 * Input value new mark
	 * Response JsonArray
	 * @throws IOException 
	 * @throws JSONException 
	 *******************************************************************/

	String updateNewMark(Long newmark, Long uid,String examCode);
		
		
	
	
	
	
	

}//end class
