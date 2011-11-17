public abstract class SortedTree extends StringTree {
	/* for all nodes:
	 * all values contained in the left subtree < the node value
	 * all values contained in the right subtree >= the node value. */

	public String search(String node) {
		return null;
	}

	/* adds node (!= null) to the tree.
	 * the tree remains sorted.
	 */
	public void add(String node) {
	}

	/* returns the values of contained nodes
	 * in a specific order determined by subtypes */
	public abstract String traverse();
}
/* vim: set noet ts=4 sw=4: */
