import java.lang.NullPointerException;

public class Student {
	private final String matrNr;
	private final String name;

	/**
	 * Create a new Student.
	 * @param matrNr The Student's matriculation number
	 * @param name The Student's name
	 */
	public Student(String matrNr, String name) {

		if (matrNr == null || matrNr.trim().equals("") ||
			name == null || name.trim().equals("")) {
			throw new NullPointerException();
		}

		this.matrNr = matrNr;
		this.name = name;
	}

	/**
	 * Get the Student's matriculation number.
	 * @return The Student's matriculation number.
	 */
	public String getMatrNr() {
		return matrNr;
	}

	/**
	 * Get the Student's name.
	 * @return The Student's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Creates a String representation of the Student.
	 * @return A String representation of the Student.
	 */
	public String toString() {
		return String.format("%s (%s)", name, matrNr);
	}
}
