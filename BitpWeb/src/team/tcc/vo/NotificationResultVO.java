package team.tcc.vo;

public class NotificationResultVO {
	private String notification_id;
	private String exam_code;
	private int applied;
	private int exam_attended;
	private int selected;
	private int rejected;
	/**
	 * @return the notification_id
	 */
	public String getNotification_id() {
		return notification_id;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NotificationResultVO [notification_id=" + notification_id + ", exam_code=" + exam_code + ", applied=" + applied + ", exam_attended=" + exam_attended + ", selected=" + selected + ", rejected=" + rejected + "]";
	}
	/**
	 * @param notification_id the notification_id to set
	 */
	public void setNotification_id(String notification_id) {
		this.notification_id = notification_id;
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
	 * @return the applied
	 */
	public int getApplied() {
		return applied;
	}
	/**
	 * @param applied the applied to set
	 */
	public void setApplied(int applied) {
		this.applied = applied;
	}
	/**
	 * @return the exam_attended
	 */
	public int getExam_attended() {
		return exam_attended;
	}
	/**
	 * @param exam_attended the exam_attended to set
	 */
	public void setExam_attended(int exam_attended) {
		this.exam_attended = exam_attended;
	}
	/**
	 * @return the selected
	 */
	public int getSelected() {
		return selected;
	}
	/**
	 * @param selected the selected to set
	 */
	public void setSelected(int selected) {
		this.selected = selected;
	}
	/**
	 * @return the rejected
	 */
	public int getRejected() {
		return rejected;
	}
	/**
	 * @param rejected the rejected to set
	 */
	public void setRejected(int rejected) {
		this.rejected = rejected;
	}
	
}//end class
