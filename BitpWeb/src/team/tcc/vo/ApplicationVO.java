package team.tcc.vo;

public class ApplicationVO {
	private int application_no;
	private String notification_id;
	private int user_id;
	private String status;
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ApplicationVO [application_no=" + application_no + ", notification_id=" + notification_id + ", user_id=" + user_id + ", status=" + status + "]";
	}
	/**
	 * @return the application_no
	 */
	public int getApplication_no() {
		return application_no;
	}
	/**
	 * @param application_no the application_no to set
	 */
	public void setApplication_no(int application_no) {
		this.application_no = application_no;
	}
	/**
	 * @return the notification_id
	 */
	public String getNotification_id() {
		return notification_id;
	}
	/**
	 * @param notification_id the notification_id to set
	 */
	public void setNotification_id(String notification_id) {
		this.notification_id = notification_id;
	}
	/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

	
}
