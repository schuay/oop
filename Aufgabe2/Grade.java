import java.util.Date;


public class Grade {
	private String performance;
	private Gradeable forGradeable;
	private Date dateofissue;
	
	public Date getDateofissue() {
		return dateofissue;
	}

	public void setDateofissue(Date dateofissue) {
		this.dateofissue = dateofissue;
	}

	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	public Gradeable getForGradeable() {
		return forGradeable;
	}

	public void setForGradeable(Gradeable forGradeable) {
		this.forGradeable = forGradeable;
	}

	public Grade(Gradeable forGradeable, String performance, Date dateofissue)
	{
		this.forGradeable = forGradeable;
		this.performance = performance;
	}
	
	public String toString(){
		return "Grade: "+performance+" for "+forGradeable;
	}
}
