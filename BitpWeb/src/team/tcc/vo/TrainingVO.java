package team.tcc.vo;

/**
 * Created by office on 24-Mar-17.
 */
public class TrainingVO {
    private int slno;
    private String training_code;
    private String training_desc;
    private String active;
    private double mth1_stipen_amt;
    private double mth2_stipen_amt;
    private double mth3_stipen_amt;
    private double mth4_stipen_amt;
    private double mth5_stipen_amt;
    private double mth6_stipen_amt;
    private String created_on;
    private String created_by;
    private String comp_code;

    @Override
    public String toString() {
        return "TrainingModel{" +
                "training_code='" + training_code + '\'' +
                ", training_desc='" + training_desc + '\'' +
                ", active='" + active + '\'' +
                ", Stipend 1=" + mth1_stipen_amt +
                ", Stipend 2=" + mth2_stipen_amt +
                ", Stipend 3=" + mth3_stipen_amt +
                ", Stipend 4=" + mth4_stipen_amt +
                ", Stipend 5=" + mth5_stipen_amt +
                ", Stipend 6=" + mth6_stipen_amt +
                ", created_by='" + created_by + '\'' +
                ", comp_code='" + comp_code + '\'' +
                '}';
    }

    public int getSlno() {
        return slno;
    }

    public void setSlno(int slno) {
        this.slno = slno;
    }

    public String getTraining_code() {
        return training_code;
    }

    public void setTraining_code(String training_code) {
        this.training_code = training_code;
    }

    public String getTraining_desc() {
        return training_desc;
    }

    public void setTraining_desc(String training_desc) {
        this.training_desc = training_desc;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public double getMth1_stipen_amt() {
        return mth1_stipen_amt;
    }

    public void setMth1_stipen_amt(double mth1_stipen_amt) {
        this.mth1_stipen_amt = mth1_stipen_amt;
    }

    public double getMth2_stipen_amt() {
        return mth2_stipen_amt;
    }

    public void setMth2_stipen_amt(double mth2_stipen_amt) {
        this.mth2_stipen_amt = mth2_stipen_amt;
    }

    public double getMth3_stipen_amt() {
        return mth3_stipen_amt;
    }

    public void setMth3_stipen_amt(double mth3_stipen_amt) {
        this.mth3_stipen_amt = mth3_stipen_amt;
    }

    public double getMth4_stipen_amt() {
        return mth4_stipen_amt;
    }

    public void setMth4_stipen_amt(double mth4_stipen_amt) {
        this.mth4_stipen_amt = mth4_stipen_amt;
    }

    public double getMth5_stipen_amt() {
        return mth5_stipen_amt;
    }

    public void setMth5_stipen_amt(double mth5_stipen_amt) {
        this.mth5_stipen_amt = mth5_stipen_amt;
    }

    public double getMth6_stipen_amt() {
        return mth6_stipen_amt;
    }

    public void setMth6_stipen_amt(double mth6_stipen_amt) {
        this.mth6_stipen_amt = mth6_stipen_amt;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    
    public String getComp_code() {
        return comp_code;
    }

    public void setComp_code(String comp_code) {
        this.comp_code = comp_code;
    }
}
