import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Collections;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Course {
	private String title;
	private String nr;

	private Date enrollFrom;
	private Date enrollTo;

	private Date unenrollTo;

	private boolean visible = true;

	/* use a LinkedHashSet to preserve order */
	private final LinkedHashSet<Student> students = new LinkedHashSet<Student>();

	public void setTitle(String title) {
		if (Util.StringIsNullOrEmpty(title)) {
			throw new IllegalArgumentException();
		}
		notifyAll("Title", this.title, title);
		this.title = title;
	}

	public void setNumber(String number) {
		if (Util.StringIsNullOrEmpty(number)) {
			throw new IllegalArgumentException();
		}
		notifyAll("Number", this.nr, number);
		this.nr = number;
	}

	public void setEnrollFrom(Date enrollFrom) {
		if (enrollFrom == null) {
			throw new IllegalArgumentException();
		} else if (enrollTo != null && enrollFrom.after(enrollTo)) {
			throw new IllegalArgumentException();
		}
		notifyAll("EnrollFrom", this.enrollFrom, enrollFrom);
		this.enrollFrom = enrollFrom;
	}

	public void setEnrollTo(Date enrollTo) {
		if (enrollTo == null) {
			throw new IllegalArgumentException();
		} else if (enrollFrom != null && enrollFrom.after(enrollTo)) {
			throw new IllegalArgumentException();
		}
		notifyAll("EnrollTo", this.enrollTo, enrollTo);
		this.enrollTo = enrollTo;
	}

	public void setUnenrollTo(Date unenrollTo) {
		if (unenrollTo == null) {
			throw new IllegalArgumentException();
		}
		notifyAll("UnenrollTo", this.unenrollTo, unenrollTo);
		this.unenrollTo = unenrollTo;
	}

	public void setVisible(boolean visible) {
		notifyAll("Visible", this.visible, visible);
		this.visible = visible;
	}

	private void notifyAll(String changed, Object oldValue, Object newValue) {
		String msg = String.format("Property '%s' has changed: '%s' -> '%s'",
				changed, oldValue, newValue);
		for (Student s : students) {
			s.notify(msg);
		}
	}

	/**
	 * Create a new Course.
	 * @param nr The number of the Course, if used with CourseManager, it should be unique
	 * @param title The Course's name
	 * @param enrollFrom Before this date no students are allowed to enroll
	 * @param enrollTo After this date no students are allowed to enroll
	 * @param unenrollTo After this date no students are allowed unenroll
	 */
	public Course(String nr, String title, Date enrollFrom, Date enrollTo,
			Date unenrollTo) {

		setTitle(title);
		setNumber(nr);
		setEnrollFrom(enrollFrom);
		setEnrollTo(enrollTo);
		setUnenrollTo(unenrollTo);
	}

	public boolean getVisible() {
		return visible;
	}

	/**
	 * Get the Course's name.
	 * @return The Course's name
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Get the Course's number.
	 * @return The Course's number
	 */
	public String getNr() {
		return nr;
	}

	/**
	 * Get the start date of the enrollment term.
	 * @return The start date of the enrollment term
	 */
	public Date getEnrollFrom() {
		return enrollFrom;
	}

	/**
	 * Get the end date of the enrollment term.
	 * @return The end date of the enrollment term
	 */
	public Date getEnrollTo() {
		return enrollTo;
	}

	/**
	 * Get the date until which students are allowed to unenroll.
	 * @return Said date
	 */
	public Date getUnenrollTo() {
		return unenrollTo;
	}

	/**
	 * Get a Set containing all enrolled students. An iteration over this Set should
	 * return the students in the order in which they enrolled. The returned Set
	 * cannot be modified.
	 * @return A Set with all enrolled students
	 */
	public Set<Student> getStudents() {
		return Collections.unmodifiableSet(students);
	}

	/**
	 * Creates a String representation of the Course.
	 * @return A String representation of the Course
	 */
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

		return String.format(
				"%s %s; enroll from %s - %s unenroll until %s; %d enrolled",
				nr, title, sdf.format(enrollFrom), sdf.format(enrollTo),
				sdf.format(unenrollTo), students.size());
	}

	/**
	 * Enroll a student in the Course
	 * @param s The student
	 * @return false on failure, true on success
	 */
	public boolean enroll(Student s) {
		if (students.contains(s)) {
			return false;
		}

		Date now = new Date();
		if (now.before(enrollFrom) || now.after(enrollTo)) {
			return false;
		}

		students.add(s);

		return true;
	}

	/**
	 * Unenroll a student from the Course
	 * @param s The student
	 * @return false on failure, true on success
	 */
	public boolean unenroll(Student s) {
		if (!students.contains(s)) {
			return false;
		}

		if (new Date().after(unenrollTo)) {
			return false;
		}

		students.remove(s);

		return true;
	}
}
