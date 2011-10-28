import java.util.LinkedHashSet;

public class Dependencies {
	private LinkedHashSet<Course> deps = new LinkedHashSet<Course>();

	public boolean fulfilled(Student s) {
		/* TODO */
		return false;
	}

	public void add(Course dep) {
		deps.add(dep);
	}
}
/* vim: set noet ts=4 sw=4: */
