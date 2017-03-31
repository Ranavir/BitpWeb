package team.tcc.vo;

public class ProfileVO {
  private Integer user_id;
  private String gender;
  private String dob;
  private String name_father;
  private String name_mother;
  private String occupation_father;
  private String occupation_mother;
  private String nationality;
  private String address;
  private String quota;
  private String disability;
  private String identification_mark;
  private String hsc_board;
  private String percentage_hsc;
  private String yop_hsc;
  private String intrm_board;
  private String percentage_intrm;
  private String yop_intrm;
  private String grad_board;
  private String percentage_grad;
  private String yop_grad;
  private String pg_board;
  private String percentage_pg;
  private String yop_pg;
  private String img_cert_hsc;
  private String img_cert_intemediate;
  private String img_cert_grad;
  private String img_cert_pg;
  private String img_signature;
  private String img_profile;
  private String created_on;
  private String updated_on;
  

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "ProfileVO [user_id=" + user_id + ", gender=" + gender + ", dob=" + dob + ", name_father=" + name_father + ", name_mother=" + name_mother + ", occupation_father=" + occupation_father + ", occupation_mother=" + occupation_mother + ", nationality=" + nationality + ", address=" + address + ", quota=" + quota + ", disability=" + disability + ", identification_mark=" + identification_mark
			+ ", hsc_board=" + hsc_board + ", percentage_hsc=" + percentage_hsc + ", yop_hsc=" + yop_hsc + ", intrm_board=" + intrm_board + ", percentage_intrm=" + percentage_intrm + ", yop_intrm=" + yop_intrm + ", grad_board=" + grad_board + ", percentage_grad=" + percentage_grad + "]";
}
/* (non-Javadoc)
 * @see java.lang.Object#hashCode()
 */
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
	return result;
}
/* (non-Javadoc)
 * @see java.lang.Object#equals(java.lang.Object)
 */
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	ProfileVO other = (ProfileVO) obj;
	if (user_id == null) {
		if (other.user_id != null)
			return false;
	} else if (!user_id.equals(other.user_id))
		return false;
	return true;
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
 * @return the gender
 */
public String getGender() {
	return gender;
}
/**
 * @param gender the gender to set
 */
public void setGender(String gender) {
	this.gender = gender;
}
/**
 * @return the dob
 */
public String getDob() {
	return dob;
}
/**
 * @param dob the dob to set
 */
public void setDob(String dob) {
	this.dob = dob;
}
/**
 * @return the name_father
 */
public String getName_father() {
	return name_father;
}
/**
 * @param name_father the name_father to set
 */
public void setName_father(String name_father) {
	this.name_father = name_father;
}
/**
 * @return the name_mother
 */
public String getName_mother() {
	return name_mother;
}
/**
 * @param name_mother the name_mother to set
 */
public void setName_mother(String name_mother) {
	this.name_mother = name_mother;
}
/**
 * @return the occupation_father
 */
public String getOccupation_father() {
	return occupation_father;
}
/**
 * @param occupation_father the occupation_father to set
 */
public void setOccupation_father(String occupation_father) {
	this.occupation_father = occupation_father;
}
/**
 * @return the occupation_mother
 */
public String getOccupation_mother() {
	return occupation_mother;
}
/**
 * @param occupation_mother the occupation_mother to set
 */
public void setOccupation_mother(String occupation_mother) {
	this.occupation_mother = occupation_mother;
}
/**
 * @return the nationality
 */
public String getNationality() {
	return nationality;
}
/**
 * @param nationality the nationality to set
 */
public void setNationality(String nationality) {
	this.nationality = nationality;
}
/**
 * @return the address
 */
public String getAddress() {
	return address;
}
/**
 * @param address the address to set
 */
public void setAddress(String address) {
	this.address = address;
}
/**
 * @return the quota
 */
public String getQuota() {
	return quota;
}
/**
 * @param quota the quota to set
 */
public void setQuota(String quota) {
	this.quota = quota;
}
/**
 * @return the disability
 */
public String getDisability() {
	return disability;
}
/**
 * @param disability the disability to set
 */
public void setDisability(String disability) {
	this.disability = disability;
}
/**
 * @return the identification_mark
 */
public String getIdentification_mark() {
	return identification_mark;
}
/**
 * @param identification_mark the identification_mark to set
 */
public void setIdentification_mark(String identification_mark) {
	this.identification_mark = identification_mark;
}
/**
 * @return the hsc_board
 */
public String getHsc_board() {
	return hsc_board;
}
/**
 * @param hsc_board the hsc_board to set
 */
public void setHsc_board(String hsc_board) {
	this.hsc_board = hsc_board;
}
/**
 * @return the percentage_hsc
 */
public String getPercentage_hsc() {
	return percentage_hsc;
}
/**
 * @param percentage_hsc the percentage_hsc to set
 */
public void setPercentage_hsc(String percentage_hsc) {
	this.percentage_hsc = percentage_hsc;
}
/**
 * @return the yop_hsc
 */
public String getYop_hsc() {
	return yop_hsc;
}
/**
 * @param yop_hsc the yop_hsc to set
 */
public void setYop_hsc(String yop_hsc) {
	this.yop_hsc = yop_hsc;
}
/**
 * @return the intrm_board
 */
public String getIntrm_board() {
	return intrm_board;
}
/**
 * @param intrm_board the intrm_board to set
 */
public void setIntrm_board(String intrm_board) {
	this.intrm_board = intrm_board;
}
/**
 * @return the percentage_intrm
 */
public String getPercentage_intrm() {
	return percentage_intrm;
}
/**
 * @param percentage_intrm the percentage_intrm to set
 */
public void setPercentage_intrm(String percentage_intrm) {
	this.percentage_intrm = percentage_intrm;
}
/**
 * @return the yop_intrm
 */
public String getYop_intrm() {
	return yop_intrm;
}
/**
 * @param yop_intrm the yop_intrm to set
 */
public void setYop_intrm(String yop_intrm) {
	this.yop_intrm = yop_intrm;
}
/**
 * @return the grad_board
 */
public String getGrad_board() {
	return grad_board;
}
/**
 * @param grad_board the grad_board to set
 */
public void setGrad_board(String grad_board) {
	this.grad_board = grad_board;
}
/**
 * @return the percentage_grad
 */
public String getPercentage_grad() {
	return percentage_grad;
}
/**
 * @param percentage_grad the percentage_grad to set
 */
public void setPercentage_grad(String percentage_grad) {
	this.percentage_grad = percentage_grad;
}
/**
 * @return the yop_grad
 */
public String getYop_grad() {
	return yop_grad;
}
/**
 * @param yop_grad the yop_grad to set
 */
public void setYop_grad(String yop_grad) {
	this.yop_grad = yop_grad;
}
/**
 * @return the pg_board
 */
public String getPg_board() {
	return pg_board;
}
/**
 * @param pg_board the pg_board to set
 */
public void setPg_board(String pg_board) {
	this.pg_board = pg_board;
}
/**
 * @return the percentage_pg
 */
public String getPercentage_pg() {
	return percentage_pg;
}
/**
 * @param percentage_pg the percentage_pg to set
 */
public void setPercentage_pg(String percentage_pg) {
	this.percentage_pg = percentage_pg;
}
/**
 * @return the yop_pg
 */
public String getYop_pg() {
	return yop_pg;
}
/**
 * @param yop_pg the yop_pg to set
 */
public void setYop_pg(String yop_pg) {
	this.yop_pg = yop_pg;
}
/**
 * @return the img_cert_hsc
 */
public String getImg_cert_hsc() {
	return img_cert_hsc;
}
/**
 * @param img_cert_hsc the img_cert_hsc to set
 */
public void setImg_cert_hsc(String img_cert_hsc) {
	this.img_cert_hsc = img_cert_hsc;
}
/**
 * @return the img_cert_intemediate
 */
public String getImg_cert_intemediate() {
	return img_cert_intemediate;
}
/**
 * @param img_cert_intemediate the img_cert_intemediate to set
 */
public void setImg_cert_intemediate(String img_cert_intemediate) {
	this.img_cert_intemediate = img_cert_intemediate;
}
/**
 * @return the img_cert_grad
 */
public String getImg_cert_grad() {
	return img_cert_grad;
}
/**
 * @param img_cert_grad the img_cert_grad to set
 */
public void setImg_cert_grad(String img_cert_grad) {
	this.img_cert_grad = img_cert_grad;
}
/**
 * @return the img_cert_pg
 */
public String getImg_cert_pg() {
	return img_cert_pg;
}
/**
 * @param img_cert_pg the img_cert_pg to set
 */
public void setImg_cert_pg(String img_cert_pg) {
	this.img_cert_pg = img_cert_pg;
}
/**
 * @return the img_signature
 */
public String getImg_signature() {
	return img_signature;
}
/**
 * @param img_signature the img_signature to set
 */
public void setImg_signature(String img_signature) {
	this.img_signature = img_signature;
}
/**
 * @return the img_profile
 */
public String getImg_profile() {
	return img_profile;
}
/**
 * @param img_profile the img_profile to set
 */
public void setImg_profile(String img_profile) {
	this.img_profile = img_profile;
}
/**
 * @return the created_on
 */
public String getCreated_on() {
	return created_on;
}
/**
 * @param created_on the created_on to set
 */
public void setCreated_on(String created_on) {
	this.created_on = created_on;
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
  
  
}//end class
