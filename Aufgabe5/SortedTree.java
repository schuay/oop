public abstract class SortedTree<A extends Comparable<? super A>> extends Tree<A> {
	/* Represents a sorted tree. */

	/* The tree remains sorted after adding the new node. */
	public void add(A value) {
		Node<A> cur = getRoot();
		Node<A> newNode = new Node<A>(value);

		if (cur == null) {
			setRoot(newNode);
			return;
		}

		while (true) {
			if (value.compareTo(cur.getValue()) < 0) {
				if (cur.getLeft() == null) {
					cur.setLeft(newNode);
					return;
				}
				cur = cur.getLeft();
			} else {
				if (cur.getRight() == null) {
					cur.setRight(newNode);
					return;
				}
				cur = cur.getRight();
			}
		}
	}

	protected LinkedListIter<Boolean> recursiveSearch(A value, Node<A> node) {
		if (node == null) {
			return null;
		}

		LinkedListIter<Boolean> it;

		if (value.compareTo(node.getValue()) < 0) {
			it = recursiveSearch(value, node.getLeft());
			if (it == null) {
				return null;
			}

			return it.prepend(false);
		}

		if (value.compareTo(node.getValue()) > 0) {
			it = recursiveSearch(value, node.getRight());
			if (it == null) {
				return null;
			}

			return it.prepend(true);
		}

		return new LinkedListIter<Boolean>();
	}
	
	protected abstract class SortedTreeIter extends AbstractTreeIter<A> {
		public SortedTreeIter(Node<A> root) {
			super(root);
		}

		/* Returns a stack of all parents leading up to the current
		 * iter element plus the element itself, or null if the iterator
		 * is empty or there is no current element. */
		protected Stack<Node<A>> getCurrentStack() {
			if (getRoot() == null || getCurrent() == null) {
				return null;
			}

			Stack<Node<A>> state = new Stack<Node<A>>();
			Node<A> cur = getRoot();

			while (cur != getCurrent()) {
				state.push(cur);
				if (cur.getValue().compareTo(getCurrent().getValue()) > 0) {
					cur = cur.getLeft();
				} else {
					cur = cur.getRight();
				}
			}

			state.push(cur);

			return state;
		}
	}
}
/* vim: set noet ts=4 sw=4: */
