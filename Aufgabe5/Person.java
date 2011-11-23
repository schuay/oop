public abstract class Person implements Comparable<Person> {
	private final String name;

	public Person(String name) {
		this.name = name;
	}

	public int compareTo(Person o) {
		return name.compareToIgnoreCase(o.name);
	}

	public boolean equals(Person o) {
		return (compareTo(o) == 0);
	}

	public String getName() {
		return name;
	}
}
/* vim: set noet ts=4 sw=4: */
