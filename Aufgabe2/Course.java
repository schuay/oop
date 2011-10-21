import java.util.*;
import java.text.SimpleDateFormat;

public class Course extends BasicEvent{
	public static HashSet<CourseType> steop = null;
	
	private String nr;
	private String semester;
	private boolean visible = true;
	private boolean requiresSTEG;
	private boolean requiresSTEOP;
	private CourseType coursetype;
	private HashSet<CourseType> dependencies = new HashSet<CourseType>();
	
	/**
	 * Create a new Course.
	 * 
	 * @param nr
	 *            The number of the Course, if used with CourseManager, it
	 *            should be unique
	 * @param title
	 *            The Course's name
	 * @param enrollFrom
	 *            Before this date no students are allowed to enroll
	 * @param enrollTo
	 *            After this date no students are allowed to enroll
	 * @param unenrollTo
	 *            After this date no students are allowed unenroll
	 */
	public Course(String nr, String title, String semester, Date enrollFrom, Date enrollTo,
			Date unenrollTo, int maxparticipants, CourseType coursetype) {
		super (title, enrollFrom, enrollTo, unenrollTo, maxparticipants);
		setSemester(semester);
		setNumber(nr);
		setCourseType(coursetype);
	}

	public void setNumber(String number) {
		if (Util.stringIsNullOrEmpty(number)) {
			throw new IllegalArgumentException();
		}
		notifyAll("Number", this.nr, number);
		this.nr = number;
	}

	public void setVisible(boolean visible) {
		notifyAll("Visible", this.visible, visible);
		this.visible = visible;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public CourseType getCoursetype() {
		return coursetype;
	}

	public void setCourseType(CourseType coursetype) {
		this.coursetype = coursetype;
	}

	public boolean getVisible() {
		return visible;
	}

	/**
	 * Get the Course's number.
	 * 
	 * @return The Course's number
	 */
	public String getNr() {
		return nr;
	}
	
	/**
	 * Creates a String representation of the Course.
	 * 
	 * @return A String representation of the Course
	 */
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

		return String.format(
				"%s %s; enroll from %s - %s unenroll until %s; %d enrolled",
				nr, getTitle(), sdf.format(getEnrollFrom()), sdf.format(getEnrollTo()), sdf
						.format(getUnenrollTo()), getStudents().size());
	}

	public boolean addDependency(CourseType courseType)
	{
		if (dependencies.contains(courseType))
			return false;
		return dependencies.add(courseType);
	}
	
	public boolean enroll(Student s) {
		if (!checkDependencies(s)) {
			return false;
		}
		return super.enroll(s);
	}

	private boolean checkDependencies(Student s)
	{
		ArrayList<Certificate> certificates = s.getCertificates(); 
		boolean passed = false;
		
		if (requiresSTEG)
			if (!s.hasSteg())
				return false;
		if (requiresSTEOP)
		{
			for (CourseType ct : steop)
			{
				passed = false;
				for (int i=0;i<certificates.size();i++)
				{
					Certificate c = certificates.get(i);
					if (ct.equals(c.getForGradeable().forWhichCourse().getCoursetype()))
						if (!c.hasPassed())
							return false;
						else 
							passed = true;
				}
				if (!passed)
					return false;
			}
		}	
		for (CourseType ct : dependencies)
		{
			passed = false;
			for (int i=0;i<certificates.size();i++)
			{
				Certificate c = certificates.get(i);
				if (ct.equals(c.getForGradeable().forWhichCourse().getCoursetype()))
					if (!c.hasPassed())
						return false;
					else 
						passed = true;
			}
			if (!passed)
				return false;		
		}
		return true;
	}
	
}