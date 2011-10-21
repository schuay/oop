import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;


public class SignUp {
	private String title;
	private Date enrollFrom;
	private Date enrollTo;
	private Date unenrollTo;
	private int maxParticipants;
	private boolean enrollPossible = true;
	
	/* use a LinkedHashSet to preserve order */
	private final LinkedHashSet<Student> students = new LinkedHashSet<Student>();
	
	protected SignUp(Date enrollFrom, Date enrollTo, Date unenrollTo, int maxParticipants)
	{
		setEnrollFrom(enrollFrom);
		setEnrollTo(enrollTo);
		setUnenrollTo(unenrollTo);
		setMaxParticipants(maxParticipants);
	}
	

	protected void notifyAll(String changed, Object oldValue, Object newValue) {
		String msg = String.format("Property '%s' has changed: '%s' -> '%s'",
				changed, oldValue, newValue);
		for (Student s : students) {
			s.notify(this, msg);
		}
	}
	
	/**
	 * Get a Set containing all enrolled students. An iteration over this Set
	 * should return the students in the order in which they enrolled. 
	 * 
	 * @return A Set with all enrolled students
	 */
	public Set<Student> getStudents() {
		return students;
	}
	
	/**
	 * Get the Course's name.
	 * 
	 * @return The Course's name
	 */
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (Util.stringIsNullOrEmpty(title)) {
			throw new IllegalArgumentException();
		}
		notifyAll("Title", this.title, title);
		this.title = title;
	}
	
	public int getMaxParticipants() {
		return maxParticipants;
	}
	public void setMaxParticipants(int maxParticipants) {
		this.maxParticipants = maxParticipants;
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
	
	/**
	 * Get the start date of the enrollment term.
	 * 
	 * @return The start date of the enrollment term
	 */
	public Date getEnrollFrom() {
		return enrollFrom;
	}

	/**
	 * Get the end date of the enrollment term.
	 * 
	 * @return The end date of the enrollment term
	 */
	public Date getEnrollTo() {
		return enrollTo;
	}
	
	/**
	 * Get the date until which students are allowed to unenroll.
	 * 
	 * @return Said date
	 */
	public Date getUnenrollTo() {
		return unenrollTo;
	}
	
	public boolean getEnrollPossible() {
		return enrollPossible;
	}

	public void setEnrollPossible(boolean enrollPossible) {
		notifyAll("EnrollPossible", this.enrollPossible, enrollPossible);
		this.enrollPossible = enrollPossible;
	}

	/**
	 * Enroll a student in the Course
	 * 
	 * @param s
	 *            The student
	 * @return false on failure, true on success
	 */
	public boolean enroll(Student s) {
		if (!enrollPossible)
			return false;
		if (students.contains(s)) {
			return false;
		}

		Date now = new Date();
		if (now.before(enrollFrom) || now.after(enrollTo)) {
			return false;
		}
		if (!hasFreeSpots())
			return false;
		students.add(s);

		return true;
	}

	/**
	 * Unenroll a student from the Course
	 * 
	 * @param s
	 *            The student
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
	
	protected boolean hasFreeSpots()
	{
		if (students.size() == maxParticipants)
			return false;
		else
			return true;
	}
}
