import java.util.Date;

/**
 * A view on everything that students can enroll in.
 * Only methods required to perform enrollment and unenrollment are present.
 */
public interface Enrollable {
	public String getTitle();
	public Date getEnrollFrom();
	public Date getEnrollTo();
	public Date getUnenrollTo();

	/**
	 * Enroll a student.
	 * Also calls the students notify method to inform about successfull enrollment.
	 * @param s The student
	 * @return false on failure, true on success
	 * @see Student.notify()
	 */
	public boolean enroll(Student s);

	/**
	 * Unenroll a student
	 * Also calls the students notify method to inform about successfull unenrollment.
	 * @param s The student
	 * @return false on failure, true on success
	 * @see Student.notify()
	 */
	public boolean unenroll(Student s);
}
/* vim: set noet ts=4 sw=4: */
