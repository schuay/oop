public class PostorderTree<A extends Comparable<? super A>> extends SortedTree<A> {

	/* iterator(): Values are iterated in postorder. */

	protected TreeIter<A> specificIterator(Node<A> node) {
		return new PostorderTreeIter(node);
	}

	protected class PostorderTreeIter extends SortedTreeIter {
		public PostorderTreeIter(Node<A> root) {
			super(root);
		}
		
		protected Node<A> succ() {
			if (getRoot() == null) {
				return null;
			}

			Node<A> cur;

			if (getCurrent() == null) {
				cur = getRoot();
				while (true) {
					if (cur.hasLeft()) {
						cur = cur.getLeft();
					} else if (cur.hasRight()) {
						cur = cur.getRight();
					} else {
						return cur;
					}
				}
			}

			Stack<Node<A>> state = getCurrentStack();
			cur = state.pop();

			if (state.empty()) {
				return null;
			}

			Node<A> parent = state.pop();

			if (!parent.hasRight() || parent.getRight() == cur) {
				return parent;
			}

			cur = parent.getRight();
			while (true) {
				if (cur.hasLeft()) {
					cur = cur.getLeft();
				} else if (cur.hasRight()) {
					cur = cur.getRight();
				} else {
					break;
				}
			}
			
			return cur;
		}

		protected Node<A> pred() {
			if (getRoot() == null) {
				return null;
			}

			Node<A> cur;

			if (getCurrent() == null) {
				return null;
			}

			Stack<Node<A>> state = getCurrentStack();
			cur = state.pop();

			if (cur.hasRight()) {
				return cur.getRight();
			}

			if (cur.hasLeft()) {
				return cur.getLeft();
			}
			
			Node<A> parent;
			while (!state.empty()) {
				parent = state.pop();
				if (parent.hasLeft() && parent.getLeft() != cur) {
					return parent.getLeft();
				}

				cur = parent;
			}

			return null;
		}

		public TreeIter<A> down() {
			if (getCurrent() == null) {
				return new PostorderTreeIter(succ());
			}
			return new PostorderTreeIter(getCurrent());
		}
	}

}
/* vim: set noet ts=4 sw=4: */
