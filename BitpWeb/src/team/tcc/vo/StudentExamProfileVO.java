package team.tcc.vo;

public class StudentExamProfileVO {
	//bitp_user
	private Integer user_id;
	private String name;
	private String phnno;
	private String email;
	  
	//bitp_exam
	private String exam_code;
	private String exam_title;
	private String exam_desc;
	private Double marks_exam_total;
	private Double marks_interview_total;
	private Double marks_total_cutoff;
	private Double time_total;
	  
	//bitp_exam_results
	private String start_time;
	private String end_time;
	private String student_session_id;
	private Double marks_exam_acquired;
	private Double marks_interview_acquired;
	private String selection_status;
	private String notification_id;
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StudentExamProfileVO [user_id=" + user_id + ", name=" + name + ", phnno=" + phnno + ", email=" + email + ", exam_code=" + exam_code + ", exam_title=" + exam_title + ", exam_desc=" + exam_desc + ", marks_exam_total=" + marks_exam_total + ", marks_interview_total=" + marks_interview_total + ", marks_total_cutoff=" + marks_total_cutoff + ", time_total=" + time_total + ", start_time="
				+ start_time + ", end_time=" + end_time + ", student_session_id=" + student_session_id + ", marks_exam_acquired=" + marks_exam_acquired + ", marks_interview_acquired=" + marks_interview_acquired + ", selection_status=" + selection_status + ", notification_id=" + notification_id + "]";
	}
	/**
	 * @return the user_id
	 */
	public Integer getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the phnno
	 */
	public String getPhnno() {
		return phnno;
	}
	/**
	 * @param phnno the phnno to set
	 */
	public void setPhnno(String phnno) {
		this.phnno = phnno;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the exam_code
	 */
	public String getExam_code() {
		return exam_code;
	}
	/**
	 * @param exam_code the exam_code to set
	 */
	public void setExam_code(String exam_code) {
		this.exam_code = exam_code;
	}
	/**
	 * @return the exam_title
	 */
	public String getExam_title() {
		return exam_title;
	}
	/**
	 * @param exam_title the exam_title to set
	 */
	public void setExam_title(String exam_title) {
		this.exam_title = exam_title;
	}
	/**
	 * @return the exam_desc
	 */
	public String getExam_desc() {
		return exam_desc;
	}
	/**
	 * @param exam_desc the exam_desc to set
	 */
	public void setExam_desc(String exam_desc) {
		this.exam_desc = exam_desc;
	}
	/**
	 * @return the marks_exam_total
	 */
	public Double getMarks_exam_total() {
		return marks_exam_total;
	}
	/**
	 * @param marks_exam_total the marks_exam_total to set
	 */
	public void setMarks_exam_total(Double marks_exam_total) {
		this.marks_exam_total = marks_exam_total;
	}
	/**
	 * @return the marks_interview_total
	 */
	public Double getMarks_interview_total() {
		return marks_interview_total;
	}
	/**
	 * @param marks_interview_total the marks_interview_total to set
	 */
	public void setMarks_interview_total(Double marks_interview_total) {
		this.marks_interview_total = marks_interview_total;
	}
	/**
	 * @return the marks_total_cutoff
	 */
	public Double getMarks_total_cutoff() {
		return marks_total_cutoff;
	}
	/**
	 * @param marks_total_cutoff the marks_total_cutoff to set
	 */
	public void setMarks_total_cutoff(Double marks_total_cutoff) {
		this.marks_total_cutoff = marks_total_cutoff;
	}
	/**
	 * @return the time_total
	 */
	public Double getTime_total() {
		return time_total;
	}
	/**
	 * @param time_total the time_total to set
	 */
	public void setTime_total(Double time_total) {
		this.time_total = time_total;
	}
	/**
	 * @return the start_time
	 */
	public String getStart_time() {
		return start_time;
	}
	/**
	 * @param start_time the start_time to set
	 */
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	/**
	 * @return the end_time
	 */
	public String getEnd_time() {
		return end_time;
	}
	/**
	 * @param end_time the end_time to set
	 */
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	/**
	 * @return the student_session_id
	 */
	public String getStudent_session_id() {
		return student_session_id;
	}
	/**
	 * @param student_session_id the student_session_id to set
	 */
	public void setStudent_session_id(String student_session_id) {
		this.student_session_id = student_session_id;
	}
	/**
	 * @return the marks_exam_acquired
	 */
	public Double getMarks_exam_acquired() {
		return marks_exam_acquired;
	}
	/**
	 * @param marks_exam_acquired the marks_exam_acquired to set
	 */
	public void setMarks_exam_acquired(Double marks_exam_acquired) {
		this.marks_exam_acquired = marks_exam_acquired;
	}
	/**
	 * @return the marks_interview_acquired
	 */
	public Double getMarks_interview_acquired() {
		return marks_interview_acquired;
	}
	/**
	 * @param marks_interview_acquired the marks_interview_acquired to set
	 */
	public void setMarks_interview_acquired(Double marks_interview_acquired) {
		this.marks_interview_acquired = marks_interview_acquired;
	}
	/**
	 * @return the selection_status
	 */
	public String getSelection_status() {
		return selection_status;
	}
	/**
	 * @param selection_status the selection_status to set
	 */
	public void setSelection_status(String selection_status) {
		this.selection_status = selection_status;
	}
	public String getNotification_id() {
		return notification_id;
	}
	public void setNotification_id(String notification_id) {
		this.notification_id = notification_id;
	}
	  
}//end class
