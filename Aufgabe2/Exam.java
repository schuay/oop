import java.util.Date;

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
		this.date = (Date)Util.validateObject(date);
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = (Course)Util.validateObject(course);
	}
}
/* vim: set noet ts=4 sw=4: */
