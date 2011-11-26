public class PreorderTree<A extends Comparable<? super A>> extends SortedTree<A> {

	/* iterator(): Values are iterated in preorder. */

	protected TreeIter<A> specificIterator(Node<A> node) {
		return new PreorderTreeIter(node);
	}

	protected class PreorderTreeIter extends SortedTreeIter {
		public PreorderTreeIter(Node<A> root) {
			super(root);
		}
		
		protected Node<A> succ() {
			if (getRoot() == null) {
				return null;
			}

			Node<A> cur;

			if (getCurrent() == null) {
				return getRoot();
			}

			Stack<Node<A>> state = getCurrentStack();
			cur = state.pop();
			
			if (cur.getLeft() != null) {
				return cur.getLeft();
			}

			if (cur.getRight() != null) {
				return cur.getRight();
			}

			Node<A> parent;
			while (!state.empty()) {
				parent = state.pop();
				if (parent.getRight() != null && parent.getRight() != cur) {
					return parent.getRight();
				}

				cur = parent;
			}

			return null;
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
			
			if (state.empty()) {
				return null;
			}

			Node<A> parent = state.pop();
			if (parent.getLeft() == null || parent.getLeft() == cur) {
				return parent;
			}

			cur = parent.getLeft();
			while (cur.getRight() != null) {
				cur = cur.getRight();
			}

			return cur;
		}

		public TreeIter<A> down() {
			if (getCurrent() == null) {
				return new PreorderTreeIter(succ());
			}
			return new PreorderTreeIter(getCurrent());
		}
	}
}
/* vim: set noet ts=4 sw=4: */
