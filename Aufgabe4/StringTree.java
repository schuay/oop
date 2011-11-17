public abstract class StringTree {
	/* binary tree; each node has a string value */

	protected static final String leftStep = "left";
	protected static final String rightStep = "right";

	/* node != null
	 * returns true if the tree contains node, or false otherwise. */
	public boolean contains(String node) {
		return false;
	}

	/* searches entire tree for node (!= null).
	 * returns space separated path to node if found
	 * or an error message if not found. */
	public String search(String node) {
		return null;
	}

	/* adds node (!= null) to the tree */
	public abstract void add(String node);

	/* return a string representation of the tree */
	public String toString() {
		return null;
	}
}
/* vim: set noet ts=4 sw=4: */
