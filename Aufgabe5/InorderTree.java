public class InorderTree<A extends Comparable<? super A>> extends SortedTree<A> {

	/* iterator(): Values are iterated in inorder. */

	protected TreeIter<A> specificIterator(Node<A> node) {
		return new InorderTreeIter(node);
	}

	protected class InorderTreeIter extends SortedTreeIter {
		public InorderTreeIter(Node<A> root) {
			super(root);
		}
		
		protected Node<A> succ() {
			if (getRoot() == null) {
				return null;
			}

			Node<A> cur;

			/* only called if iter is uninitialized.
			 * first element is leftmost element of tree */
			if (getCurrent() == null) {
				cur = getRoot();
				while(cur.hasLeft()) {
					cur = cur.getLeft();
				}

				return cur;
			}

			Stack<Node<A>> state = getCurrentStack();
			cur = state.pop();	/* == getCurrent() */

			/* right subtree exists:
			 * return leftmost element of right subtree */
			if (cur.hasRight()) {
				cur = cur.getRight();
				while(cur.hasLeft()) {
					cur = cur.getLeft();
				}

				return cur;
			}

			/* walk parent hierarchy and return the first parent
			 * that has the current element in its left subtree */
			while (!state.empty()) {
				Node<A> next = state.pop();
				if (cur == next.getLeft()) {
					return next;
				}

				cur = next;
			}

			return null;
		}

		protected Node<A> pred() {
			if (getRoot() == null) {
				return null;
			}

			if (getCurrent() == null) {
				return null;
			}

			Stack<Node<A>> state = getCurrentStack();
			Node<A> cur = state.pop();

			if (cur.hasLeft()) {
				cur = cur.getLeft();
				while(cur.hasRight()) {
					cur = cur.getRight();
				}

				return cur;
			}

			while (!state.empty()) {
				if (cur == state.peek().getRight()) {
					return state.pop();
				}

				cur = state.pop();
			}

			return null;
		}

		public TreeIter<A> down() {
			if (getCurrent() == null) {
				return new InorderTreeIter(succ());
			}
			return new InorderTreeIter(getCurrent());
		}
	}
}
/* vim: set noet ts=4 sw=4: */
