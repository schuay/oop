/**
 * Interface for stuff that can be graded (Exams etc.)
 */
public interface Gradeable {
	public Course getCourse();

	/**
	 * Grade a student.
	 * @param s The student.
	 * @param g The grade.
	 */
	public void grade(Student s, Grade g);
}
/* vim: set noet ts=4 sw=4: */
