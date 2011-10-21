public abstract class Person {
	protected final String name;

	public Person(String name) {
		this.name = Util.validateString(name);
	}

	public void notify(Object sender, String message) {
		/* Code for person notifications can be added here. */
	}

	/**
	 * Get the Persons's name.
	 * @return The Persons's name.
	 */
	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}
}
