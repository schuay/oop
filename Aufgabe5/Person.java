public abstract class Person implements Comparable<Person> {
	private final String name;

	public Person(String name) {
		this.name = name;
	}

	public int compareTo(Person o) {
		return name.compareToIgnoreCase(o.name);
	}

	public boolean equals(Object o) {
		if (o instanceof Person) {
			Person p = (Person)o;
			return (compareTo(p) == 0);
		}
		return super.equals(o);
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return getName();
	}
}
/* vim: set noet ts=4 sw=4: */
