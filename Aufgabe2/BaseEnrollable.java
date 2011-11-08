import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Collections;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The basic enrollable object. This is the super-class of all Objects where students
 * can enroll.
 */
public abstract class BaseEnrollable implements Enrollable {
	private String title;
	private Date enrollFrom;
	private Date enrollTo;
	private Date unenrollTo;
	private int maxParticipants;

	/* use a LinkedHashSet to preserve order */
	private final LinkedHashSet<Student> students = new LinkedHashSet<Student>();

	/**
	 * Create a new BaseEnrollable with the title title.
	 * Sets the enrollment term from the current time to 30 days in the future.
	 *
	 * @param title The title of the BaseEnrollable.
	 */
	public BaseEnrollable(String title) {
		setTitle(title);
		
		Calendar c = Calendar.getInstance();
		setEnrollFrom(c.getTime());
		
		c.add(Calendar.DATE, 30);
		setEnrollTo(c.getTime());
		setUnenrollTo(c.getTime());
		
		setMaxParticipants(Integer.MAX_VALUE);
	}

	public String getTitle() {
		return title;
	}

	/**
	 * Set the title of the enrollable.
	 * This also calls notifyAll to inform affected users.
	 * @param title The new title.
	 * @see notifyAll()
	 */
	public void setTitle(String title) {
		notifyAll("Title", this.title, title);
		this.title = Util.validateString(title);
	}

	public int getMaxParticipants() {
		return maxParticipants;
	}

	public void setMaxParticipants(int maxParticipants) {
		if (maxParticipants < 1) {
			throw new IllegalArgumentException();
		}
		this.maxParticipants = maxParticipants;
	}

	public Date getEnrollFrom() {
		return enrollFrom;
	}

	/**
	 * Set the start date of the enrollment term.
	 * This also calls notifyAll to inform affected users.
	 * @param enrollFrom The start date of the enrollment term.
	 * @see notifyAll()
	 */
	public void setEnrollFrom(Date enrollFrom) {
		if (enrollTo != null && enrollFrom.after(enrollTo)) {
			throw new IllegalArgumentException();
		}

		notifyAll("EnrollFrom", this.enrollFrom, enrollFrom);
		this.enrollFrom = Util.validateObject(enrollFrom);
	}

	public Date getEnrollTo() {
		return enrollTo;
	}

	/**
	 * Set the end date of the enrollment term.
	 * This also calls notifyAll to inform affected users.
	 * @param enrollTo The end date of the enrollment term.
	 * @see notifyAll()
	 */
	public void setEnrollTo(Date enrollTo) {
		if (enrollFrom != null && enrollFrom.after(enrollTo)) {
			throw new IllegalArgumentException();
		}

		notifyAll("EnrollTo", this.enrollTo, enrollTo);
		this.enrollTo = Util.validateObject(enrollTo);
	}

	public Date getUnenrollTo() {
		return unenrollTo;
	}

	/**
	 * Set the date until which students are allowed to unenroll.
	 * This also calls notifyAll to inform affected users.
	 * @param enrollTo The new value for said date.
	 * @see notifyAll()
	 */
	public void setUnenrollTo(Date unenrollTo) {
		notifyAll("UnenrollTo", this.unenrollTo, unenrollTo);
		this.unenrollTo = Util.validateObject(unenrollTo);
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
	 * Notify all enrolled students about changes.
	 * @param changed What has changed?
	 * @param oldValue The old value.
	 * @param newValue The new one.
	 * @see Student.notify()
	 */
	protected void notifyAll(String changed, Object oldValue, Object newValue) {
		String msg = String.format("Property '%s' has changed: '%s' -> '%s'",
				changed, oldValue, newValue);
		for (Student s : students) {
			s.notify(title, msg);
		}
	}

	public boolean enroll(Student s) {
		if (students.size() >= maxParticipants) {
			return false;
		}

		Date now = new Date();
		if (now.before(enrollFrom) || now.after(enrollTo)) {
			return false;
		}

		if (!students.add(s)) {
			return false;
		}
		s.notify(title, String.format("Enrolled to %s", title));

		return true;
	}

	public boolean unenroll(Student s) {
		if (new Date().after(unenrollTo)) {
			return false;
		}

		if (!students.remove(s)) {
			return false;
		}
		s.notify(title, String.format("Unenrolled from %s", title));

		return true;
	}

	/**
	 * Creates a String representation of the Event.
	 * @return A String representation of the Event.
	 */
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		return String.format(
				"%s; enroll from %s - %s unenroll until %s; %d enrolled",
				title, sdf.format(enrollFrom), sdf.format(enrollTo),
				sdf.format(unenrollTo), students.size());
	}
}
/* vim: set noet ts=4 sw=4: */
