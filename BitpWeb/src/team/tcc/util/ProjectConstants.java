
package team.tcc.util;

import java.util.Locale;

/***************************************************************
 * This class holds all Project level constants
 * @author 
 * @date 18032017
 ***************************************************************/
public class ProjectConstants {
	//Date 19032017
	public final static int REG_REQ_ID 														= 1;
	public final static int LOGIN_REQ_ID 													= 2;
	public final static int PW_RECOVERY_REQ_ID 												= 3;
	public final static int PW_CHANGE_REQ_ID 												= 4;
	//public static final int LOGOUT_REQ_ID													= 7 ;
	public final static int GET_PROFILE_INFO                                                = 20;
	
	public static final int FETCH_NOTIFIATIONS                               				= 51;
	public static final int FETCH_COMPANY 												    = 52;
	public static final int NEW_NOTIFICATION 											    = 53;
	public static final int PROFILE_UPDATE 								     				= 54;
	public static final int APPLY_TO_NOTIFICATION                            				= 55;
	public static final int APPLICATIONS_PRE_PROCESSING                      				= 56;
    public static final int APPLICATIONS_PROCESSING                          				= 57;
    public static final int FETCH_EXAMS                                      				= 58;
    public static final int FETCH_EXAM_PROFILE												= 59;
    public static final int UPDATE_EXAM_INTERVEIW											= 60;
    public static final int FETCH_PLACEMENT_NEWS                             				= 61;
    public static final int NEW_PLACEMENT                                    				= 62;
    public static final int DROP_PLACEMENT_NEWS                              				= 63;
    public static final int NEW_TRAINING                                     				= 64;
    public static final int FETCH_TRAININGS                                  				= 65;
    public static final int ADD_TRAINEES                                     				= 66;
    public static final int FETCH_TRAINING_PROFILE                          				= 67;
    public static final int PUSH_FEEDBACK                                    				= 68;
    public static final int NOTIFICATION_RESULT 											= 69;
    public static final int EXAM_SELECTION                                   				= 70;
    public static final int PAY_STIPEND  													= 71;
    public static final int SUBMIT_PROJECT													= 72;
    public static final int ISSUE_CERTIFICATE												= 73;
    public static final int PUSH_ADMIN_FEEDBACK                              				= 74;
    public static final int FETCH_TRAININGS_BY_COMPANY                       				= 75;
    public static final int FETCH_TRAINING_REVIEWS                           				= 76;
    
	//Project CODEs
	public static final int CODE_QUERY_SUCCESS = 1 ;
	public static final int CODE_QUERY_FAILURE = -1 ;
	public static final int CODE_QUERY_NOREC_FOUND = 0 ;
	
	public static final int CODE_SELECT_SUCCESS = 3001 ;
	public static final int CODE_INSERT_SUCCESS = 3002 ;
	public static final int CODE_UPDATE_SUCCESS = 3003 ;
	public static final int CODE_DELETE_SUCCESS = 3004 ;
	
	
	
	//Project messages
	public static final String MSG_COMMON_SUCCESS = "SUCCESS" ;
	public static final String MSG_COMMON_FAILURE = "FAILURE" ;
	public static final String MSG_NO_REC_FOUND = "NO RECORD FOUND" ;
	
	public final static String MSG_DUPLICATE_REGISTRATION 									= "DUPLICATE REGISTRATION" ;
	public final static String MSG_SERVER_ERROR												= "SERVER ERROR";
	public final static String MSG_INVALID_USER												= "Invalid username or password";
	
	//Project Bitp Queries
	//Date: 18032017
	public static final String QUERY_CHECK_USER_REGISTRATION = "SELECT user_id FROM bitp_user where username = '' and password = ''" ;//CHK USR EXISTENCE
	public static final String QUERY_USER_ID_NEXT_SEQ = "SELECT COALESCE(max(user_id),0)+1 FROM bitp_user" ;
	public static final String QUERY_NEW_REGISTRATION = "INSERT INTO bitp_user( user_id, phnno, email, username, password, name, user_type, created_on,  created_by,active) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', CURRENT_TIMESTAMP,'SYSTEM','Y')";
	public static final String QUERY_VALIDATE_LOGIN = "select * from  bitp_user where username = '%s' and password = '%s' and active = 'Y'";
	public static final String QUERY_CHANGE_PASSWORD = "update bitp_user set password = '%s',updated_on = CURRENT_TIMESTAMP where user_id = %s ";//CHK USR TYPE
	//public static final String QUERY_INSERT_RULE_SCHEDULE = "INSERT INTO rule_schedule(slno, rule_code, dbcon_name, active) VALUES ((SELECT COALESCE(max(slno),0)+1 FROM RULE_SCHEDULE), ?, ?, ?)";
	public static final String QUERY_GET_NOTIFICATIONS = "SELECT * FROM bitp_notification WHERE STATUS = '%s'";
	public static final String QUERY_GET_COMPANIES = "select * from company_master";
	public static final String QUERY_NEXT_NOTIFICATION_SLNO = "(SELECT COALESCE(max(slno),0)+1 FROM bitp_notification)";
	public static final String QUERY_NEW_NOTIFICATION = "INSERT INTO bitp_notification( slno, notification_id, comp_code, notification, hsc_percentage,intrm_percentage, grad_percentage, pg_percentage, created_on,  created_by) "+
														"VALUES (%s, '%s', '%s', '%s', %s, %s, %s, %s,CURRENT_TIMESTAMP, '%s')";
	public static final String QUERY_GET_USER_PROFILE = "select * from bitp_user_profile where user_id = %s";
	public static final String QUERY_INSERT_USER_PROFILE = "INSERT INTO bitp_user_profile (user_id,created_on) values (%s,CURRENT_TIMESTAMP)";
	public static final String QUERY_UPDATE_USER_PROFILE = "UPDATE bitp_user_profile SET  gender='%s', dob='%s', name_father='%s', name_mother='%s', occupation_father='%s', "+
       "occupation_mother='%s', nationality='%s', address='%s', quota='%s', disability='%s', identification_mark='%s', hsc_board='%s', percentage_hsc=%s, yop_hsc=%s, "+
       "intrm_board='%s', percentage_intrm=%s, yop_intrm=%s, grad_board='%s',percentage_grad=%s, yop_grad=%s, updated_on=CURRENT_TIMESTAMP WHERE user_id=%s";
	public static final String QUERY_CHECK_APPLICATION = "select application_no from bitp_application where user_id = %s and notification_id = '%s'";
	public static final String QUERY_INSERT_APPLICATION = "INSERT INTO bitp_application(application_no, notification_id, user_id,created_on) VALUES (%s, '%s', %s, CURRENT_TIMESTAMP)";
	public static final String QUERY_GET_EXAMS = "SELECT exam_code, exam_title, exam_desc,exam_welcometext,  exam_endtext, marks_exam_total, marks_interview_total,marks_total_cutoff, time_total FROM bitp_exam where active = 'Y'";
	public static final String QUERY_GET_APPLICATION_PRE_PROCESS_DETAILS = "SELECT application_no, user_id FROM bitp_application where status = '%s' and notification_id = '%s'";
	public static final String QUERY_PROCESS_NOTIFICATION = "UPDATE bitp_notification SET updated_on=CURRENT_TIMESTAMP, updated_by='%s', status='%s', exam_code='%s' WHERE notification_id = '%s'";
	public static final String QUERY_PROCESS_APPLICATIONS = "UPDATE bitp_application SET status='%s', updated_on=CURRENT_TIMESTAMP, updated_by='%s' WHERE notification_id = '%s'";
	
	public static final String QUERY_INSERT_EXAM_RESULT = "INSERT INTO bitp_exam_results ( exam_code,notification_id,user_id ) SELECT '%s','%s', user_id FROM bitp_application where notification_id = '%s' and status = 'Accepted'";
	public static final String QUERY_GET_EXAM_PROFILE = "select usr.user_id,usr.name,usr.phnno,usr.email,"+
		"exam.exam_code,exam.exam_title,exam.exam_desc,exam.marks_exam_total,exam.marks_interview_total,exam.marks_total_cutoff,exam.time_total,"+
		"result.start_time,result.end_time,result.student_session_id,result.marks_exam_acquired,result.marks_interview_acquired,result.selection_status,result.notification_id from "+ 
		"bitp_user usr,bitp_exam exam,bitp_exam_results result  "+ 
		"where usr.user_id = result.user_id and exam.exam_code = result.exam_code and result.notification_id = '%s'and usr.user_id = %s and exam.exam_code = '%s'";
	public static final String QUERY_UPDATE_EXAM_INTERVIEW = "update bitp_exam_results set marks_interview_acquired = %s,updated_by='%s' where notification_id ='%s' and exam_code = '%s' and user_id = %s";
	public static final String QUERY_NEXT_PLACEMENT_SLNO = "(SELECT COALESCE(max(slno),0)+1 FROM bitp_placement_news)";
	public static final String QUERY_NEW_PLACEMENT = "INSERT INTO bitp_placement_news( slno, placement_code, comp_code, news, created_on, created_by ) VALUES (%s, '%s', '%s', '%s', CURRENT_TIMESTAMP, '%s')";
	public static final String QUERY_GET_PLACEMENTS = "SELECT * FROM bitp_placement_news WHERE active = '%s'";
	public static final String QUERY_DROP_PLACEMENT_NEWS = "UPDATE bitp_placement_news SET active='N', updated_on=CURRENT_TIMESTAMP, updated_by='%s' WHERE placement_code = '%s'";
	public static final String QUERY_NEXT_TRAINING_SLNO = "(SELECT COALESCE(max(slno),0)+1 FROM bitp_training)";
	public static final String QUERY_NEW_TRAINING = "INSERT INTO bitp_training( slno, training_code, training_desc, mth1_stipen_amt, mth2_stipen_amt, mth3_stipen_amt, mth4_stipen_amt,"+ 
            "mth5_stipen_amt, mth6_stipen_amt, created_on,created_by, comp_code) VALUES (%s, '%s', '%s', %s, %s, %s, %s, %s, %s,CURRENT_TIMESTAMP, '%s', '%s')";
	public static final String QUERY_GET_TRAININGS = "SELECT slno, training_code, training_desc, mth1_stipen_amt, mth2_stipen_amt, mth3_stipen_amt, mth4_stipen_amt, mth5_stipen_amt, mth6_stipen_amt, created_on, created_by,"+ 
	       "comp_code FROM bitp_training WHERE ACTIVE = 'Y'";
	public static final String QUERY_GET_SELECTED_STUDENTS_IN_EXAM = "select distinct user_id from bitp_exam_results where notification_id = '%s' and selection_status='selected'";
	public static final String QUERY_GET_INVOLVED_STUDENTS_IN_TRAINING = "select user_id from bitp_trainee_details where training_code = '%s'";
	public static final String QUERY_ADD_TRAINEES = "INSERT INTO bitp_trainee_details(training_code, user_id,created_by) VALUES ('%s', %s, '%s')";
	public static final String QUERY_GET_TRAINING_PROFILE = "select usr.user_id,usr.name,usr.phnno,usr.email,"+
"training.training_code,training.training_desc,training.mth1_stipen_amt,training.mth2_stipen_amt,training.mth3_stipen_amt,training.mth4_stipen_amt,training.mth5_stipen_amt,training.mth6_stipen_amt,training.comp_code,"+
"td.mth1_feedback,td.mth2_feedback,td.mth3_feedback,td.mth4_feedback,td.mth5_feedback,td.mth6_feedback,"+
"td.mth1_stipen_rcv,td.mth2_stipen_rcv,td.mth3_stipen_rcv,td.mth4_stipen_rcv,td.mth5_stipen_rcv,td.mth6_stipen_rcv,"+
"td.project_submission_status,td.issue_certificate_status "+ 
"from bitp_user usr,bitp_training training,bitp_trainee_details td "+ 
"where  usr.user_id = td.user_id and training.training_code = td.training_code and usr.user_id = %s and training.training_code = '%s'";
	public static final String QUERY_UPDATE_TRAINING_FEEDBACK = "UPDATE bitp_trainee_details SET  %s='%s' WHERE training_code = '%s' and user_id = %s";
	public static final String QUERY_GET_NOTIFICATION_RESULT = "select exam_code,marks_exam_acquired,selection_status from bitp_exam_results where exam_code = (select exam_code from bitp_notification where notification_id ='%s')"+
	" and user_id in(SELECT user_id FROM bitp_application where notification_id = '%s')";
	public static final String QUERY_UPDATE_EXAM_SELECTION_STATUS = "UPDATE bitp_exam_results SET selection_status='%s' WHERE exam_code = '%s' and user_id = %s";
	public static final String QUERY_PAY_STIPEND = "UPDATE bitp_trainee_details SET %s = 'Y' where training_code= '%s'" ;
	public static final String QUERY_UPDATE_ISSUE_CERT = "UPDATE bitp_trainee_details SET issue_certificate_status = 'Y' where training_code= '%s' and user_id = %s";
	public static final String QUERY_SUBMIT_PROJECT_REPORT = "UPDATE bitp_trainee_details SET project_submission_status = 'Y' where training_code= '%s' AND user_id = %s";
	
	//NEW
	public static final String QUERY_INSERT_TRAINING_FEEDBACK = "INSERT INTO bitp_student_training_feedbacks( student_id, training_code, feedback_category, feedback,  month) VALUES (%s, '%s', '%s', '%s', '%s')";
	public static final String QUERY_GET_FEEDBACK_PROFILE1 = "SELECT  feedback_category, feedback, month, updated_on FROM bitp_student_training_feedbacks where student_id = %s and training_code = '%s'";
	public static final String QUERY_GET_FEEDBACK_PROFILE2 = "SELECT admin_id, feedback_category, feedback, updated_on FROM bitp_admin_training_feedbacks where student_id = %s and training_code = '%s'";
	public static final String QUERY_IS_EXIST_STUDENT_FEEDBACK = "select feedback from bitp_student_training_feedbacks where student_id = %s and training_code = '%s' and feedback_category = '%s' and month = '%s'";
	public static final String QUERY_IS_EXIST_ADMIN_FEEDBACK = "SELECT  feedback FROM bitp_admin_training_feedbacks where student_id = %s and training_code = '%s' and feedback_category = '%s'";
	public static final String QUERY_INSERT_ADMIN_TRAINING_FEEDBACK = "INSERT INTO bitp_admin_training_feedbacks( admin_id, student_id, training_code, feedback_category, feedback) VALUES (%s, %s, '%s', '%s', '%s')";
	public static final String QUERY_IS_VALID_TRAINEE = "SELECT * FROM bitp_trainee_details where training_code = '%s' and user_id = %s";
	public static final String QUERY_GET_TRAININGS_BY_COMPANY = "SELECT slno, training_code, training_desc, mth1_stipen_amt, mth2_stipen_amt, mth3_stipen_amt, mth4_stipen_amt,"+ 
		       "mth5_stipen_amt, mth6_stipen_amt, created_on, created_by,comp_code FROM bitp_training WHERE ACTIVE = 'Y' and comp_code = '%s'";
	public static final String QUERY_GET_TRAINING_REVIEWS = "select bu.name,bstf.feedback_category,bstf.feedback from  bitp_student_training_feedbacks bstf,bitp_user bu where bstf.student_id = bu.user_id and bstf.training_code = '%s'";
	
	/*************************
	 * Portal Query
	 *************************/
	
	public static final String QUERY_GET_USER_DETAILS = "select user_id,user_type,active from bitp_user where username = '%s' and password = '%s'";
	public static final String QUERY_GET_EXAMCODE_LIST = "select exam_code,exam_title from bitp_exam";
	public static final String QUERY_GET_USER_MARK = "select ber.user_id as UserId,usr.name as StudentName,be.exam_code as ExamCode,be.exam_title as ExamTitle,be.marks_exam_total as OnlineExamMarkTotal,"
			+ "be.marks_interview_total as InterviewMarkTotal,be.marks_total_cutoff as TotalCutoffMark,"
			+ "ber.start_time as ExamStartTime,ber.end_time as ExamEndTime,ber.student_session_id as ExamSessionId,ber.marks_exam_acquired as MarkSecuredOnlineExam,"
			+ "ber.marks_interview_acquired as MarkSecuredInterview,ber.selection_status as SelectionStatus"
			+ " from bitp_exam_results ber,bitp_user usr,bitp_exam be where usr.user_id = ber.user_id and be.exam_code = ber.exam_code and be.exam_code = '%s'";
	
	public static final String QUERY_UPDATE_MARK = "update bitp_exam_results set marks_exam_acquired = %s where exam_code = '%s' and user_id = %s and selection_status = 'pending'";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
