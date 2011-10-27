import java.util.HashSet;
import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class Course extends BaseEnrollable{
	public static HashSet<CourseType> steop = null;
	
	private String nr;
	private boolean visible = true;
	private boolean requiresSTEG;	/* TODO replace STEG/STEOP with CourseType deps */
	private boolean requiresSTEOP;
	private CourseType courseType;
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
	/* TODO reduce nr of args needed in ctor */
	public Course(String nr, String title, Date enrollFrom, Date enrollTo,
			Date unenrollTo, int maxParticipants, CourseType courseType) {
		super (title, enrollFrom, enrollTo, unenrollTo, maxParticipants);
		setNumber(nr);
		setCourseType(courseType);
	}
	
	public String getNr() {
		return nr;
	}

	public void setNumber(String number) {
		notifyAll("Number", this.nr, number);
		this.nr = Util.validateString(number);
	}

	public boolean getVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		notifyAll("Visible", this.visible, visible);
		this.visible = visible;
	}

	public CourseType getCoursetype() {
		return courseType;
	}

	public void setCourseType(CourseType courseType) {
		notifyAll("CourseType", this.courseType, courseType);
		this.courseType = (CourseType)Util.validateObject(courseType);
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
		return dependencies.add(courseType);
	}
	
	public boolean enroll(Student s) {
		if (!checkDependencies(s)) {
			return false;
		}
		return super.enroll(s);
	}
	
	public void cancel() {
		notifyAll("Status", "open", "cancelled");
	}

	private boolean checkDependencies(Student s)
	{
		ArrayList<Certificate> certificates = s.getCertificates(); 
		boolean passed = false;
		
		if (requiresSTEG && !s.hasSteg()) {
				return false;
		}
		if (requiresSTEOP)	/* TODO refactor this entire section - maybe let CourseType validate a student? courseType.validate(s) */
		{
			for (CourseType ct : steop)
			{
				passed = false;
				for (int i=0;i<certificates.size();i++)
				{
					Certificate c = certificates.get(i);
					if (ct.equals(c.getForGradeable().forWhichCourse().getCoursetype()))
						if (!c.hasPassed()) {
							return false;
						} else { 
							passed = true;
						}
				}
				if (!passed) {
					return false;
				}
			}
		}	
		for (CourseType ct : dependencies)
		{
			passed = false;
			for (int i=0;i<certificates.size();i++)
			{
				Certificate c = certificates.get(i);
				if (ct.equals(c.getForGradeable().forWhichCourse().getCoursetype()))
					if (!c.hasPassed()) {
						return false;
					} else { 
						passed = true;
					}
			}
			if (!passed) {
				return false;
			}
		}
		return true;
	}
	
}