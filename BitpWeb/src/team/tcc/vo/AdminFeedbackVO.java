package team.tcc.vo;

public class AdminFeedbackVO {
	  
	 private int admin_id;
	 private int student_id;
	 private String training_code;
	 private String feedback_category;
	 private String feedback;
	 private String updated_on;
	/**
	 * @return the admin_id
	 */
	public int getAdmin_id() {
		return admin_id;
	}
	/**
	 * @param admin_id the admin_id to set
	 */
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	/**
	 * @return the student_id
	 */
	public int getStudent_id() {
		return student_id;
	}
	/**
	 * @param student_id the student_id to set
	 */
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	/**
	 * @return the training_code
	 */
	public String getTraining_code() {
		return training_code;
	}
	/**
	 * @param training_code the training_code to set
	 */
	public void setTraining_code(String training_code) {
		this.training_code = training_code;
	}
	/**
	 * @return the feedback_category
	 */
	public String getFeedback_category() {
		return feedback_category;
	}
	/**
	 * @param feedback_category the feedback_category to set
	 */
	public void setFeedback_category(String feedback_category) {
		this.feedback_category = feedback_category;
	}
	/**
	 * @return the feedback
	 */
	public String getFeedback() {
		return feedback;
	}
	/**
	 * @param feedback the feedback to set
	 */
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	/**
	 * @return the updated_on
	 */
	public String getUpdated_on() {
		return updated_on;
	}
	/**
	 * @param updated_on the updated_on to set
	 */
	public void setUpdated_on(String updated_on) {
		this.updated_on = updated_on;
	}
	 
	 
}
