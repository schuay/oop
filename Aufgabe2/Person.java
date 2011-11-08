/**
 * A Person. Everything which is a person inherits from this class.
 */
public abstract class Person {
	private final String name;

	public Person(String name) {
		this.name = Util.validateString(name);
	}

	/**
	 * Notify the person about something.
	 *
	 * @param sender Who sent the notification?
	 * @param message The message.
	 */
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
/* vim: set noet ts=4 sw=4: */
