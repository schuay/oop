import java.util.*;

public class ExerciseInterview extends BaseEnrollable implements Gradeable {

	private Course course;
	private Date date;

	public ExerciseInterview (Date enrollfrom, Date enrollto, Date unenrollto, int maxparticipants, Date date)
	{
		/* TODO title */
		super("Interview", enrollfrom, enrollto, unenrollto, maxparticipants);
		setDate(date);
	}

	@Override
	public Course forWhichCourse() {

		return this.course;
	}

	public Date getDate()
	{
		return this.date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public void grade(Student s, Grade g)
	{
		if (getStudents().contains(s))
		{
			s.addGrade(g);
		}			
	}
}
/* vim: set noet ts=4 sw=4: */
