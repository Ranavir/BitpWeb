package team.tcc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class ConnectionFactory {
	private static Logger logger = Logger.getLogger(ConnectionFactory.class) ;
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) envContext
					.lookup("jdbc/bitpdb");
			conn = dataSource.getConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;

	}// end getConnection tomcat dbcp

	/**
	 * This method is used to get Postgresql DB Connection
	 * 
	 * @return Connection
	 */
	public static Connection getLocalConnection() {
		Connection conn = null;
		try {

			DriverManager.registerDriver(new org.postgresql.Driver());

			conn = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/bitp", "postgres",
					"techlab");

			if (conn != null) {
				System.out.println("Connection Established Successfully");
			}

		} catch (SQLException sqe) {
			System.out.println("Not able to Establish Connection ");
		} catch (Exception exe) {
			System.out.println("Not able to Establish Connection ");
		}
		return conn;
	}
	/**************************************************
	 * To get the maximum sequence id of a table 
	 * @param tableName
	 * @param pkid
	 * @return long
	 * @author Ranvir
	 * @date 10-12-2016
	 *************************************************/
	public static synchronized long getSequenceID(String tableName, String pkid) {
		long id = 0;
		Connection con = null ;
		Statement st = null ;
		ResultSet rs = null ;
		try
		{
			con = getConnection();
			st = con.createStatement();
			rs = st.executeQuery("select max("+pkid+") from "+tableName); 
			if(rs.next())
				id=rs.getLong(1);
			id++;
		}
		catch(SQLException se)
		{
			logger.warn(se);
		}
		catch(Exception e)
		{
			logger.warn(e);
		}
		finally
		{
			Utils.closeDB(st,rs,con);
		}
		logger.info("Next value of column "+pkid+" of table "+tableName+" = "+id);
		return id;
	}//end getSequenceID
	public static void main(String... args) {
		System.out.println(getLocalConnection());
	}

}
