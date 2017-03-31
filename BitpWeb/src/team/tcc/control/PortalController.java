package team.tcc.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import team.tcc.service.PortalService;
import team.tcc.serviceImpl.PortalServiceImpl;

/**
 * Servlet implementation class PortalController
 */
@WebServlet(description = "Handles request for Providing services for Web application", 
			urlPatterns = { "/PortalController" }, 
			loadOnStartup = 1)
public class PortalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * Declare and initialize  required variables
	 */
	String methodname = "" ;
	String userId = "";
	
	private static Logger logger = null;
	private static PortalService portalService = null ;
       @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	try
		{
			
			logger = Logger.getLogger(PortalController.class) ;
			portalService = PortalServiceImpl.getInstance() ;
			
		}catch (Exception ge) {
			logger.error(ge.toString(), ge.fillInStackTrace());
		}
		logger.info("PortalController servlet initialized successfully...");
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PortalController() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		logger.info("UIController servlet destroyed sucessfully...");
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		methodname = "doPost" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		Integer iReqId = !StringUtils.isEmpty(request.getParameter("reqId"))?Integer.parseInt(request.getParameter("reqId")):0 ;
		System.out.println(iReqId);
		switch(iReqId){
			case 0:
				loginProcess(request,response);
				break;
			case 1:
				getExamCodeList(request,response) ;//written
				
				break ;
			case 2:
				getUserResultByExamCode(request,response) ;
				break ;
			case 3:
				updateNewMark(request,response) ;//written
				break ;
			default:
				return ;
		}
		
		logger.info("EXIT---> methodname : "+methodname);
	}
	
	/******************************************************************
	 * Update New MArk 
	 * Input value new mark
	 * Response JsonArray
	 * @throws IOException 
	 * @throws JSONException 
	 *******************************************************************/
	private void updateNewMark(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		methodname="updateNewMark";
		logger.info("ENTRY---> methodname : "+methodname);
		PrintWriter out = response.getWriter();
		Long newmark = Long.parseLong(request.getParameter("newmark"));
		Long uid = Long.parseLong(request.getParameter("userid"));
		String examCode = request.getParameter("exam_code");
		logger.info(newmark);
		logger.info(uid);
		logger.info(examCode);
		String result = portalService.updateNewMark(newmark,uid,examCode);
		
		logger.info("Exit---> methodname : "+methodname);
		out.print(result);
	}
	/******************************************************************
	 * Method that will get all the student answer details by exam code 
	 * Input value Exam Code
	 * Response JsonArray
	 * @throws IOException 
	 * @throws JSONException 
	 *******************************************************************/
	
	private void getUserResultByExamCode(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		methodname="getExamCodeList";
		logger.info("ENTRY---> methodname : "+methodname);
		PrintWriter out = response.getWriter();
		String exam_code = request.getParameter("exam_code");
		logger.info("Exam Code is:"+exam_code);
		JSONArray jArr = portalService.getUserResultByExamCode(exam_code);
		JSONObject retObj = new JSONObject();
		logger.info("data:"+jArr.toString());
		try {
			retObj.put("data", jArr);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		logger.info("Exit---> methodname : "+methodname);
		out.print(retObj);
		
	}
	/*************************************
	 * Get Exam Code List
	 * Return JSONArray
	 * @throws IOException 
	 *****************************************/
	
	private void getExamCodeList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		methodname="getExamCodeList";
		logger.info("ENTRY---> methodname : "+methodname);
		PrintWriter out = response.getWriter();
		JSONArray retArr = portalService.getExamCodeList();
		logger.info(retArr.toString());
		out.print(retArr);
		logger.info("Exit---> methodname : "+methodname);
	}
	
	
	
	/***********************************************
	 * Login Process
	 * after getting the username and password 
	 * the method will check wheather the usertype is admin or not and also check it is active or not 
	 * then only the user can view the Reconcilation page
	 * @return JSONObj
	 * "status":"success/failure"
	 * "message"
	 * @author 
	 * @throws JSONException 
	 * @throws IOException 
	 * @throws ServletException 
	 ***********************************************/
	
	private void loginProcess(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out=response.getWriter();  
		methodname = "loginProcess";
		logger.info("ENTRY---> methodname : "+methodname);
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		logger.info("userName :"+userName);
		logger.info("userName :"+password);
		JSONObject retObj = portalService.checkUserInfoExistance(userName,password);
		logger.info(retObj.toString());
		try {
			if(retObj.get("status").toString().equals("success")) {
				 HttpSession session=request.getSession();  
			     session.setAttribute("name","sankar");  
			     response.sendRedirect("landing.jsp");
			}else {
				  request.setAttribute("errorMessage", retObj.get("message").toString());
			      RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			      rd.forward(request, response);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		logger.info("Exit---> methodname : "+methodname);
		  out.close();  
	}
	
	
	
	
	
	
	
	
	
	/*****************************************************************************************************************************************************/
	
	
}//end class
