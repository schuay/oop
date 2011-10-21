import java.util.*;

public class Student extends Person {
	private final String matrNr;
	private boolean hasSteg = false;
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
		return String.format("%s (%s)", name, matrNr);
	}
	
	public boolean addCertificate(Certificate certificate)
	{
		if(certificates.contains(certificate))
			return false;
		else
			certificates.add(certificate);
		return true;
	}
	
	public ArrayList<Certificate> getCertificates() {
		return certificates;
	}
	
	public boolean hasSteg() {
		return hasSteg;
	}

	public void setHasSteg(boolean hasSteg) {
		this.hasSteg = hasSteg;
	}
	
	public void addGrade(Grade g)
	{
		grades.add(g);
	}
}
