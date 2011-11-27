public class ReplaceableTree<A> extends Tree<A> {
	
	/* Replaces subtree at position with a copy of subTree.
	 * If the parent of the element at position doesn't exist
	 * no changes are made.
	 * The order of the elements in the copy of subTree is not
	 * retained.
	 * position != null, subTree != null */
	public void replace(Iter<Boolean> position, Tree<? extends A> subTree) {

		/* clone tree */
		Node<A> n = new Node<A>(subTree.getRoot());

		/* empty position, replace root. */
		if (!position.hasNext()) {
			setRoot(n);
			return;
		}

		/* walk position */
		Node<A> m = getRoot();
		Boolean rightChild = position.next();
		while (position.hasNext()) {
			if (!rightChild) {
				m = m.getLeft();
			} else {
				m = m.getRight();
			}
			rightChild = position.next();
		}

		/* m now points to the parent node of the tree which needs to be
		 * replaced, and turnRight tells us which child will be replaced. */

		if (!rightChild) {
			m.setLeft(n);
		} else {
			m.setRight(n);
		}
	}

	/* Adds the new node at the leftmost available position with
	 * minimal depth. Except for the additional node, the tree's
	 * structure is not modified. */
	public void add(A value) {
	}

	protected TreeIter<A> specificIterator(Node<A> node) {
		return new ReplaceableTreeIter(node);
	}

	protected class ReplaceableTreeIter extends AbstractTreeIter<A> {
		/* Iterates tree inorder. */

		public ReplaceableTreeIter(Tree.Node<A> root) {
			super(root);
		}

		/* current != null, root != null, current exists in tree */
		private Stack<Node<A>> getCurrentStack() {

			Node<A> needle = getCurrent();
			Stack<Node<A>> stack = new Stack<Tree.Node<A>>();

			Node<A> cur = getRoot();
			stack.push(cur);

			while (!stack.empty()) {
				if (cur == needle) {
					return stack;
				}

				if (cur.hasLeft()) {
					/* enter left subtree */
					cur = cur.getLeft();
				} else if (cur.hasRight()) {
					/* enter right subtree */
					cur = cur.getRight();
				} else {
					/* pop until we reach an unprocessed right subtree */
					Node<A> prev, rightChild;
					do {
						prev = cur;
						cur = stack.pop();
						rightChild = cur.getRight();
					} while (!stack.empty() && (prev == rightChild || rightChild == null));
					/* and process it */
					cur = rightChild;
				}
				stack.push(cur);
			}

			return stack;
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
			if (cur.getRight() != null) {
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

			if (cur.getLeft() != null) {
				cur = cur.getLeft();
				while(cur.getRight() != null) {
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
				return new ReplaceableTreeIter(succ());
			}
			return new ReplaceableTreeIter(getCurrent());
		}
	}
}
/* vim: set noet ts=4 sw=4: */
