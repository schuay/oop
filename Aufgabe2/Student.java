import java.util.ArrayList;

public class Student extends Person {
	private final String matrNr;
	private ArrayList<Certificate> certificates;
	private ArrayList<Grade> grades;

	/**
	 * Create a new Student.
	 * 
	 * @param matrNr
	 *            The Student's matriculation number
	 * @param name
	 *            The Student's name
	 */
	public Student(String matrNr, String name) {
		super(name);

		this.matrNr = Util.validateString(matrNr);
		certificates = new ArrayList<Certificate>();
		grades = new ArrayList<Grade>();
	}

	/**
	 * Get the Student's matriculation number.
	 * 
	 * @return The Student's matriculation number.
	 */
	public String getMatrNr() {
		return matrNr;
	}

	/**
	 * Creates a String representation of the Student.
	 * 
	 * @return A String representation of the Student.
	 */
	public String toString() {
		return String.format("%s (%s)", getName(), matrNr);
	}

	public boolean addCertificate(Certificate certificate)
	{
		if (!certificates.add(certificate)) {
			return false;
		}
		return true;
	}

	public ArrayList<Certificate> getCertificates() {
		return certificates;
	}

	public void addGrade(Grade g)
	{
		grades.add(g);
	}

	public ArrayList<Grade> getGrades() {
		return grades;
	}
}
/* vim: set noet ts=4 sw=4: */
