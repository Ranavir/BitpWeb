
package team.tcc.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
/***************************************************************
 * This class holds all public static utility methods
 * can be used by any class object
 * @author office
 * @date 25042016
 ***************************************************************/
public class Utils {
	private static Logger logger = Logger.getLogger(Utils.class) ;
	public static void main(String[] args) {

		
	}
	/********************************************************************************************************/
	
	
	/****************************************************
	  * This utility method is used to close the opened database
	  * connections
	  * @param con
	  * @param stRefresh
	  * @param rsResult
	  * @author Ranavir Dash
	  * @date 12092016
	  ***************************************************/
	 //public static void closeDB(Connection con, Statement st,ResultSet rs) {
	 public static void closeDB(Object...args) {
	  try {
	   for(int i=0; i<args.length; i++ ){
	    if(args[i] != null){
	     if(args[i] instanceof ResultSet){
	      ((ResultSet)args[i]).close();
	     }
	     if(args[i] instanceof Statement){//work for statement,preparedstmt,callablestmt
	      ((Statement)args[i]).close();
	     }
	     if(args[i] instanceof Connection){
	      ((Connection)args[i]).close();
	     }
	    }
	   }//end for
	  } catch (SQLException e) {
	   e.printStackTrace();
	  }
	 }//end of closeDB
	 
	
	/********************************************************************************************************/
	
	
}

