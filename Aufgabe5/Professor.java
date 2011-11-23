public class Professor extends Person {
	private final String institut;

	public Professor(String name, String institut) {
		super(name);
		this.institut = institut;
	}

	public String getInstitut() {
		return institut;
	}
}
/* vim: set noet ts=4 sw=4: */
