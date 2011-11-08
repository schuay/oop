import java.util.Date;

/**
 * A class for Exams.
 * Students can enroll in an Exam and the Exam can be graded.
 */
public class Exam extends BaseEnrollable implements Gradeable {
	private Course course;
	private Date date;

	public Exam(String title, Course course, Date date)
	{
		super(title);
		setDate(date);
		setCourse(course);
	}

	public void grade(Student s, Grade g)
	{
		if (getStudents().contains(s))
		{
			s.addGrade(g);
		}			
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = Util.validateObject(date);
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = Util.validateObject(course);
	}
}
/* vim: set noet ts=4 sw=4: */
