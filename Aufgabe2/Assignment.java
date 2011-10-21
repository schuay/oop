import java.util.Date;


public class Assignment implements Gradeable {
	Date dateFrom, dateUntil;
	private Course course;
	
	public Assignment(Date dateFrom, Date dateUntil)
	{
		setDateFrom(dateFrom);
		setDateUntil(dateUntil);
	}
	
	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateUntil() {
		return dateUntil;
	}

	public void setDateUntil(Date dateUntil) {
		this.dateUntil = dateUntil;
	}

	public Course forWhichCourse() {
		return this.course;
	}
	
	public void grade(Student s, Grade g)
	{
		if (course.getStudents().contains(s))
		{
			s.addGrade(g);
		}			
	}
}
