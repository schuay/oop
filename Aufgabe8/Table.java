public class Table extends Storage {

	/* capacity > 4 */
	public Table(int capacity) {
		super(capacity);
		if (capacity < 5) {
			throw new IllegalArgumentException();
		}
	}

	protected String getName() {
		return "Table";
	}
}

/* vim: set noet ts=4 sw=4: */
