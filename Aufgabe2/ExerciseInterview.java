import java.util.*;

public class ExerciseInterview extends BaseEnrollable implements Gradeable {
	private Course course;
	private Date date;

	public ExerciseInterview (String title, Course course, Date date)
	{
		super(title);
		setDate(date);
		setCourse(course);
	}

	public Date getDate()
	{
		return this.date;
	}

	public void setDate(Date date)
	{
		this.date = Util.validateObject(date);
	}

	public void grade(Student s, Grade g)
	{
		if (getStudents().contains(s)) {
			s.addGrade(g);
		}			
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = Util.validateObject(course);
	}
}
/* vim: set noet ts=4 sw=4: */
