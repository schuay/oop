import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Collections;
import java.util.Date;
import java.text.SimpleDateFormat;

public abstract class BasicEvent implements Enrollable {
	private String title;
	private Date enrollFrom;
	private Date enrollTo;
	private Date unenrollTo;
	private int maxParticipants;

	/* use a LinkedHashSet to preserve order */
	private final LinkedHashSet<Student> students = new LinkedHashSet<Student>();

	public BasicEvent(String title, Date enrollFrom, Date enrollTo,
			Date unenrollTo, int maxParticipants) {
		setTitle(title);
		setEnrollFrom(enrollFrom);
		setEnrollTo(enrollTo);
		setUnenrollTo(unenrollTo);
		setMaxParticipants(maxParticipants);
	}

	/**
	 * Get the Events's name.
	 * @return The Events's name
	 */
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		notifyAll("Title", this.title, title);
		this.title = Util.validateString(title);
	}
	
	public int getMaxParticipants() {
		return maxParticipants;
	}
	
	public void setMaxParticipants(int maxParticipants) {
		this.maxParticipants = maxParticipants;
	}

	public void setEnrollFrom(Date enrollFrom) {
		if (enrollTo != null && enrollFrom.after(enrollTo)) {
			throw new IllegalArgumentException();
		}

		notifyAll("EnrollFrom", this.enrollFrom, enrollFrom);
		this.enrollFrom = (Date)Util.validateObject(enrollFrom);
	}

	public void setEnrollTo(Date enrollTo) {
		if (enrollFrom != null && enrollFrom.after(enrollTo)) {
			throw new IllegalArgumentException();
		}
		
		notifyAll("EnrollTo", this.enrollTo, enrollTo);
		this.enrollTo = (Date)Util.validateObject(enrollTo);
	}

	public void setUnenrollTo(Date unenrollTo) {
		notifyAll("UnenrollTo", this.unenrollTo, unenrollTo);
		this.unenrollTo = (Date)Util.validateObject(unenrollTo);
	}

	protected void notifyAll(String changed, Object oldValue, Object newValue) {
		String msg = String.format("Property '%s' has changed: '%s' -> '%s'",
				changed, oldValue, newValue);
		for (Student s : students) {
			s.notify(title, msg);
		}
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
	 * Enroll a student
	 * @param s The student
	 * @return false on failure, true on success
	 */
	public boolean enroll(Student s) {
		if (students.contains(s)) {
			return false;
		}
		if (students.size() >= maxParticipants) {
			return false;
		}

		Date now = new Date();
		if (now.before(enrollFrom) || now.after(enrollTo)) {
			return false;
		}

		students.add(s);
		s.notify(title, String.format("Enrolled to %s", title));

		return true;
	}

	/**
	 * Unenroll a student
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
		s.notify(title, String.format("Unenrolled from %s", title));

		return true;
	}
	
	/**
	 * Creates a String representation of the Event.
	 * @return A String representation of the Event
	 */
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		return String.format(
				"%s; enroll from %s - %s unenroll until %s; %d enrolled",
				title, sdf.format(enrollFrom), sdf.format(enrollTo),
				sdf.format(unenrollTo), students.size());
	}
}
