import java.util.LinkedList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class LVA {
	private final String title;
	private final String nr;

	private final Date enrollFrom;
	private final Date enrollTo;

	private final Date unenrollTo;

	/* use a list for now to preserve order */
	private final LinkedList<Student> students = new LinkedList<Student>();

	public LVA(String nr, String title, Date enrollFrom, Date enrollTo,
			Date unenrollTo) {

		this.title = title;
		this.nr = nr;
		this.enrollFrom = enrollFrom;
		this.enrollTo = enrollTo;
		this.unenrollTo = unenrollTo;
	}

	public String getTitle() {
		return title;
	}

	public String getNr() {
		return nr;
	}

	public Date getEnrollFrom() {
		return enrollFrom;
	}

	public Date getEnrollTo() {
		return enrollTo;
	}

	public Date getUnenrollTo() {
		return unenrollTo;
	}

	public LinkedList<Student> getStudents() {
		return students;
	}

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
