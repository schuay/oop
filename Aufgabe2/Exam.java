import java.util.Date;

public class Exam extends BasicEvent implements Gradeable {
	private Course course;
	private Date date;
	private String type;

	public Exam (Date enrollfrom, Date enrollto, Date unenrollto, int maxparticipants, Date date)
	{
		/* TODO title */
		super("Pruefung", enrollfrom, enrollto, unenrollto, maxparticipants);
		this.date = date;
	}

	public Course forWhichCourse() {
		return this.course;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void grade(Student s, Grade g)
	{
		if (getStudents().contains(s))
		{
			s.addGrade(g);
		}			
	}
}
