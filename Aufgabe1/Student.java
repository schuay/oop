public class Student {
	private final String matrNr;
	private final String name;

	public Student(String matrNr, String name) {
		this.matrNr = matrNr;
		this.name = name;
	}

	public String getMatrNr() {
		return matrNr;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return String.format("%s (%s)", name, matrNr);
	}
}
