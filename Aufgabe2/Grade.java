/**
 * The basic class for grading Gradeable events.
 * Think assignments, quizzes, etc.
 */

import java.util.Date;


public class Grade {
	private final String performance;
	private final Gradeable forGradeable;
	private final Date dateOfIssue;

	public Grade(Gradeable forGradeable, String performance, Date dateOfIssue)
	{
		this.forGradeable = (Gradeable)Util.validateObject(forGradeable);
		this.performance = Util.validateString(performance);
		this.dateOfIssue = (Date)Util.validateObject(dateOfIssue);
	}
	
	public Date getDateOfIssue() {
		return dateOfIssue;
	}

	public String getPerformance() {
		return performance;
	}

	public Gradeable getForGradeable() {
		return forGradeable;
	}
	
	public String toString(){
		return String.format("Grade: %s for %s", performance, forGradeable);
	}
}
