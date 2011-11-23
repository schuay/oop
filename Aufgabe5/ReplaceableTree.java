public class ReplaceableTree<A> extends Tree<A> {
	
	/* Replaces subtree at position with a copy of subTree.
	 * If the parent of the element at position doesn't exist
	 * no changes are made.
	 * The order of the elements in the copy of subTree is not
	 * retained.
	 * position != null, subTree != null */
	public void replace(Iter<Boolean> position, Tree<? extends A> subTree) {
	}

	/* Adds the new node at the leftmost available position with
	 * minimal depth. Except for the additional node, the tree's
	 * structure is not modified. */
	public void add(A value) {
	}
}
/* vim: set noet ts=4 sw=4: */
