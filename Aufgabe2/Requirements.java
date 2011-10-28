import java.util.LinkedHashSet;

public class Requirements {
	private LinkedHashSet<Course> deps = new LinkedHashSet<Course>();

	/**
	 * Checks if a student fulfills all requirements.
	 * @param s The student
	 * @return If requirements have been fulfilled.
	 */
	public boolean fulfilled(Student s) {
		for (Course c : deps) {
			if (!hasPassed(s, c)) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Checks if a student has passed a certain course.
	 * @param s The student.
	 * @param c The course.
	 * @return If the student has passed the course.
	 */
	private boolean hasPassed(Student s, Course c) {
		Certificate matchingCert = null;
		
		/* first, retrieve the most current certificate for course c */
		for (Certificate cert : s.getCertificates()) {
			if (cert.getCourse() != c) {
				continue;
			}
			if (matchingCert == null) {
				matchingCert = cert;
			} else if (matchingCert.getDateOfIssue().before(cert.getDateOfIssue())) {
				matchingCert = cert;
			}
		}
		
		/* passing requires an existing cert with passed == true */ 
		return (matchingCert != null && matchingCert.getPassed());
	}

	public void add(Course dep) {
		deps.add(dep);
	}
}
/* vim: set noet ts=4 sw=4: */
