
package team.tcc.daoImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import team.tcc.dao.PortalDao;
import team.tcc.util.ConnectionFactory;
import team.tcc.util.ProjectConstants;
import team.tcc.util.Utils;

/**************************************************************
 * @author 
 * Date : Mar, 18 2017
 **************************************************************/
public class PortalDaoImpl implements PortalDao {
	private static PortalDaoImpl portalDaoImpl = null;
	private static Logger logger = null;
	String methodname="";
	String userId = "";
	
	public PortalDaoImpl() {
		logger = Logger.getLogger(PortalDaoImpl.class) ;
	}
	
	public static PortalDaoImpl getInstance(){
		if(portalDaoImpl == null){
			portalDaoImpl = new PortalDaoImpl();
			logger.info("PortalDaoImpl Instantiated...");
		}
		return portalDaoImpl;
	}
	
	/*public static void main(String[] args) {
		getInstance();
		ConnectionFactory.getLocalConnection() ;
	}*/
	
	
	
	/**************************************************            Business Logic Starts            *********************************************/
	/***********************************************
	 * Login Process
	 * after getting the username and password 
	 * the method will check wheather the usertype is admin or not and also check it is active or not 
	 * then only the user can view the Reconcilation page
	 * "status":"success/failure"
	 * "message"
	 * @author 
	 ***********************************************/
	@Override
	public JSONObject checkUserInfoExistance(String userName, String password) {
		
		methodname = "checkUserInfoExistance";
		logger.info("Entry Dao--->"+methodname);
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		String userType = "";
		String active = "";
		JSONObject retObject  = new JSONObject();
		try {
			query = String.format(ProjectConstants.QUERY_GET_USER_DETAILS,userName,password);
			logger.info("query--->"+query);
			con = ConnectionFactory.getConnection();
			if(con != null ) {
				stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				if(rs.next()) {
					userType = rs.getString(2);
					if(userType.equalsIgnoreCase("admin")) {
						active = rs.getString(3);
						if(active.equalsIgnoreCase("y")) {
							retObject.put("status","success");
							retObject.put("message","Process For Login");
						}else {
							retObject.put("status","failure");
							retObject.put("message","You are Not Activated");
						}
					}else {
						retObject.put("status","failure");
						retObject.put("message","You are Not Admin");
					}
					
				}else {
					retObject.put("status","failure");
					retObject.put("message","Invalid Credentials");
				}
				
			}//end of connection checking
		}catch (Exception e) {
			try {
				retObject.put("status","failure");
				retObject.put("message","Server Error");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			Utils.closeDB(stmt,rs,con);
		}
		logger.info("Exit Dao--->"+methodname);
		return retObject;
	}
	
	
	/*****************************************
	 * Get Exam Code List
	 * Return JSONArray
	 *****************************************/
	@Override
	public JSONArray getExamCodeList() {
		methodname = "getExamCodeList";
		logger.info("Entry Dao--->"+methodname);
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		JSONObject obj = null;
		JSONArray retArr = new JSONArray();
		try {
			query = String.format(ProjectConstants.QUERY_GET_EXAMCODE_LIST);
			logger.info("query--->"+query);
			con = ConnectionFactory.getConnection();
			if(con != null ) {
				stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					obj = new JSONObject();
					obj.put("exam_code", rs.getString(1));
					obj.put("exam_title", rs.getString(2));
					retArr.put(obj);
				}//End Of While Loop
			}
		}catch(Exception e) {
			
		}finally {
			Utils.closeDB(stmt,rs,con);
		}
		logger.info("Exit Dao--->"+methodname);
		return retArr;
	}
	
	/******************************************************************
	 * Method that will get all the student answer details by exam code 
	 * Input value Exam Code
	 * Response JsonArray
	 *******************************************************************/
	@Override
	public JSONArray getUserResultByExamCode(String exam_code) {
		methodname = "getUserResultByExamCode";
		logger.info("Entry Dao--->"+methodname);
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		JSONObject obj = null;
		JSONArray retArr = new JSONArray();
		try {
			query = String.format(ProjectConstants.QUERY_GET_USER_MARK,exam_code);
			logger.info("query--->"+query);
			con = ConnectionFactory.getConnection();
			if(con != null ) {
				stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					//obj.put("sid",!(rs.getString(1) == null)? rs.getString(1): "NA");
					obj = new JSONObject();
					obj.put("uid",!(rs.getString(1) == null)? rs.getString(1): "NA");
					obj.put("sn",!(rs.getString(2) == null)? rs.getString(2): "NA");
					obj.put("ec",!(rs.getString(3) == null)? rs.getString(3): "NA");
					obj.put("etitle",!(rs.getString(4) == null)? rs.getString(4): "NA");
					obj.put("oemt",!(rs.getString(5) == null)? rs.getString(5): "NA");
					obj.put("imt",!(rs.getString(6) == null)? rs.getString(6): "NA");
					obj.put("tcm",!(rs.getString(7) == null)? rs.getString(7): "NA");
					obj.put("est",!(rs.getString(8) == null)? rs.getString(8): "NA");
					obj.put("eet",!(rs.getString(9) == null)? rs.getString(9): "NA");
					obj.put("esid",!(rs.getString(10) == null)? rs.getString(0): "NA");
					obj.put("msoe",!(rs.getString(11) == null)? rs.getString(11): "NA");
					obj.put("msie",!(rs.getString(12) == null)? rs.getString(12): "NA");
					obj.put("ss",!(rs.getString(13) == null)? rs.getString(13): "NA");
					
					retArr.put(obj);
				}//End Of While Loop
			}
		}catch(Exception e) {
			
		}finally {
			Utils.closeDB(stmt,rs,con);
		}
		logger.info("Exit Dao--->"+methodname);
		return retArr;
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
		methodname = "updateNewMark";
		logger.info("Entry Dao--->"+methodname);
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		String strRet = "success";
		try {
			query = String.format(ProjectConstants.QUERY_UPDATE_MARK,newmark,examCode,uid);
			logger.info("query--->"+query);
			con = ConnectionFactory.getConnection();
			if(con != null ) {
				stmt = con.createStatement();
				int result =  stmt.executeUpdate(query);
			}
		}catch(Exception e) {
			
		}finally {
			Utils.closeDB(stmt,rs,con);
		}
		logger.info("Exit Dao--->"+methodname);
		return strRet;
	}

	
	/**************************************************            Business Logic Ends              *********************************************/
	
}
