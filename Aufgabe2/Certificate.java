/**
 * The final grade for a Course object.
 */

import java.util.Date;

public class Certificate {
	private final boolean passed;
	private final Date dateOfIssue;
	private final Course course;
	private final String grade;

	public Certificate(Course course, Date dateOfIssue, boolean passed, String grade)
	{
		this.passed = passed;
		this.dateOfIssue = dateOfIssue;
		this.course = course;
		this.grade = grade;
		/* TODO validate */
	}

	public boolean getPassed() {
		return passed;
	}

	public Course getCourse() {
		return course;
	}
}
/* vim: set noet ts=4 sw=4: */
