import java.util.HashSet;
import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class Course extends BaseEnrollable{
	private String nr;
	private boolean visible = true;
	private Dependencies deps;
	private LinkedHashSet<Enrollable> enrollables = new LinkedHashSet<Enrollable>();
	/* TODO staff list (dynamic binding) of Employable */

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
			Date unenrollTo, int maxParticipants) {
		super (title, enrollFrom, enrollTo, unenrollTo, maxParticipants);
		setNumber(nr);
	}

	public String getNr() {
		return nr;
	}

	public Dependencies getDependencies() {
		return deps;
	}

	public void setDependencies(Dependencies deps) {
		this.deps = (Dependencies)Util.validateObject(deps);
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

	public boolean enroll(Student s) {
		if (!deps.fulfilled(s)) {
			return false;
		}
		return super.enroll(s);
	}

	public void cancel() {
		notifyAll("Status", "open", "cancelled");
	}
}
/* vim: set noet ts=4 sw=4: */
