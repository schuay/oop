import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Collections;
import java.util.Date;
import java.text.SimpleDateFormat;

public class LVA {
	private final String title;
	private final String nr;

	private final Date enrollFrom;
	private final Date enrollTo;

	private final Date unenrollTo;

	/* use a LinkedHashSet to preserve order */
	private final LinkedHashSet<Student> students = new LinkedHashSet<Student>();

	/**
	 * Create a new LVA.
	 * @param nr The number of the LVA, if used with LVAManager, it should be unique
	 * @param title The LVA's name
	 * @param enrollFrom Before this date no students are allowed to enroll
	 * @param enrollTo After this date no students are allowed to enroll
	 * @param unenrollTo After this date no students are allowed unenroll
	 */
	public LVA(String nr, String title, Date enrollFrom, Date enrollTo,
			Date unenrollTo) {

		this.title = title;
		this.nr = nr;
		this.enrollFrom = enrollFrom;
		this.enrollTo = enrollTo;
		this.unenrollTo = unenrollTo;
	}

	/**
	 * Get the LVA's name.
	 * @return The LVA's name
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Get the LVA's number.
	 * @return The LVA's number
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
	 * Creates a String representation of the LVA.
	 * @return A String representation of the LVA
	 */
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

		return String.format(
				"%s %s; enroll from %s - %s unenroll until %s; %d enrolled",
				nr, title, sdf.format(enrollFrom), sdf.format(enrollTo),
				sdf.format(unenrollTo), students.size());
	}

	/**
	 * Enroll a student in the LVA
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
	 * Unenroll a student from the LVA
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
