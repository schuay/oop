import java.util.Date;

/**
 * The final grade for a Course object.
 */
public class Certificate {
	private final boolean passed;
	private final Date dateOfIssue;
	private final Course course;
	private final String grade;

	/**
	 * Issue a new certificate for course.
	 *
	 * @param course The course for which the certificate is issued.
	 * @param dateOfIssue When it was issued.
	 * @param passed Did the student pass?
	 * @param grade What grade did the student achieve?
	 */
	public Certificate(Course course, Date dateOfIssue, boolean passed, String grade)
	{
		this.passed = passed;
		this.dateOfIssue = Util.validateObject(dateOfIssue);
		this.course = Util.validateObject(course);
		this.grade = Util.validateString(grade);
	}

	public boolean getPassed() {
		return passed;
	}

	public Course getCourse() {
		return course;
	}

	public Date getDateOfIssue() {
		return dateOfIssue;
	}

	public String getGrade() {
		return grade;
	}
}
/* vim: set noet ts=4 sw=4: */
