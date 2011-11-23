public class Student extends Person {
	private final int matrikelnummer;

	public Student(String name, int matrikelnummer) {
		super(name);
		this.matrikelnummer = matrikelnummer;
	}

	public int getMatrikelnummer() {
		return matrikelnummer;
	}
}
/* vim: set noet ts=4 sw=4: */
