public class Quarry extends Storage {

	/* capacity > 1 */
	public Quarry(int capacity) {
		super(capacity);
		if (capacity < 2) {
			throw new IllegalArgumentException();
		}
	}

	protected String getName() {
		return "Quarry";
	}
}

/* vim: set noet ts=4 sw=4: */
