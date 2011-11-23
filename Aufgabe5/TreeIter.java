public interface TreeIter<A> extends Iter<A> {
	/* An iterator over a tree-like collection. */

	/* Returns a new iterator containing all elements of
	 * the subtree with the current element as root.
	 * The current element is the element returned by the most
	 * recent call to previous() or next(), or the first element
	 * if neither has been called.
	 * Returns an empty iterator if the current element is null. */
	TreeIter<A> down();
}
/* vim: set noet ts=4 sw=4: */
