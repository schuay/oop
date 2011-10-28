import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.text.SimpleDateFormat;

public class Course extends BaseEnrollable{
	private String nr;
	private boolean visible = true;
	private Requirements deps = new Requirements();
	private LinkedHashSet<Enrollable> enrollables = new LinkedHashSet<Enrollable>();
	private LinkedHashSet<Employable> staff = new LinkedHashSet<Employable>();

	public Course(String nr, String title) {
		super(title);
		setNumber(nr);
	}

	public String getNr() {
		return nr;
	}

	public Requirements getDependencies() {
		return deps;
	}

	public void setDependencies(Requirements deps) {
		this.deps = (Requirements)Util.validateObject(deps);
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
				nr, getTitle(), sdf.format(getEnrollFrom()), sdf.format(getEnrollTo()),
				sdf.format(getUnenrollTo()), getStudents().size());
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
	
	public boolean addEnrollable(Enrollable e) {
		return enrollables.add(e);
	}
	
	public Set<Enrollable> getEnrollables() {
		return Collections.unmodifiableSet(enrollables);
	}

	public Set<Employable> getStaff() {
		return Collections.unmodifiableSet(staff);
	}

	public boolean addStaff(Employable e) {
		return staff.add(e);
	}
}
/* vim: set noet ts=4 sw=4: */
