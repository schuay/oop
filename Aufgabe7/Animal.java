
/* Every animal has a name. */
public abstract class Animal {
	
	private final String name;
	
	/*
	 * Creates a new animal with the passed name 
	 * (name != null)
	 */
	public Animal(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}

/* vim: set noet ts=4 sw=4: */
