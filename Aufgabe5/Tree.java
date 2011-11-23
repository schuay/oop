public abstract class Tree<A> {
	/* Represents a binary tree */

	/* Returns null if the tree doesn't contain value,
	 * otherwise a TreeIter over the subtree with the node
	 * containing value as root.
	 * value != null */
	public TreeIter<A> contains(A value) {
		return null;
	}

	/* Returns an iterator over all values in the tree. */
	public TreeIter<A> iterator() {
		return null;
	}

	/* Returns an iterator containing the path from root to
	 * the node containing value, with false representing
	 * the left and true representing the right subtree.
	 * If the tree doesn't contain value null is returned.
	 * Always returns the path to the same node contains
	 * returns when called with the same value.
	 * value != null */
	public Iter<Boolean> search(A value) {
		return null;
	}

	/* Adds an element to the tree.
	 * value != null */
	public abstract void add(A value);
}
/* vim: set noet ts=4 sw=4: */
