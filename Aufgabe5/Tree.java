import java.util.EmptyStackException;

public abstract class Tree<A> {
	/* Represents a binary tree */

	Node root = null;

	/* Returns null if the tree doesn't contain value,
	 * otherwise a TreeIter over the subtree with the node
	 * containing value as root.
	 * value != null */
	public TreeIter<A> contains(A value) {
		/* search tree */
		Iter<Boolean> it = recursiveSearch(value, root);
		if (it == null) {
			return null;
		}
		/* walk tree */
		Node n = walk(it);
		if (n == null) {
			return null;
		}
		return specificIterator(n);
	}

	/* Returns the node pointed to by position, or null if
	 * it doesn't exist. */
	protected Node walk(Iter<Boolean> position) {
		Node n = root;
		while (position.hasNext()) {
			if (n == null) {
				return null;
			}

			boolean b = position.next();
			if (b) {
				n = n.getRight();
			} else {
				n = n.getLeft();
			}
		}

		return n;
	}

	/* Returns an iterator over all values in the tree. */
	public TreeIter<A> iterator() {
		return specificIterator(root);
	}

	/* Returns an iterator over the subtree with node as root;
	 * The iterator type depends on the type of the tree. */
	protected abstract TreeIter<A> specificIterator(Node node);

	/* Returns an iterator containing the path from root to
	 * the node containing value, with false representing
	 * the left and true representing the right subtree.
	 * If the tree doesn't contain value null is returned.
	 * Always returns the path to the same node contains
	 * returns when called with the same value.
	 * value != null */
	public Iter<Boolean> search(A value) {
		return recursiveSearch(value, root);
	}

	protected LinkedListIter<Boolean> recursiveSearch(A value, Node node) {

		if (node == null) {
			return null;
		} else if (node.getValue().equals(value)) {
			/* found - return empty iter */
			return new LinkedListIter<Boolean>();
		}

		LinkedListIter<Boolean> it;

		/* search left subtree */
		it = recursiveSearch(value, node.getLeft());
		if (it != null) {
			return it.prepend(false);
		}

		/* search right subtree */
		it = recursiveSearch(value, node.getRight());
		if (it != null) {
			return it.prepend(true);
		}

		/* not found */
		return null;
	}

	/* Adds an element to the tree.
	 * value != null */
	public abstract void add(A value);

	protected class Stack<B> {
		/* The Stack class represents a last-in-first-out (LIFO) stack of objects. */

		private class Entry {
			final Entry next;
			final B value;
			public Entry(Entry next, B value) {
				this.next = next;
				this.value = value;
			}
		}

		private Entry head = null;

		/* Pushes an item onto the top of this stack. */
		public void push(B value) {
			head = new Entry(head, value);
		}

		/* Removes the object at the top of this stack and returns that
		 * object as the value of this function.
		 * Throws EmptyStackException if stack is empty. */
		public B pop() {
			if (head == null) {
				throw new EmptyStackException();
			}
			B value = head.value;
			head = head.next;
			return value;
		}

		/* Looks at the object at the top of this stack without
		 * removing it from the stack.
		 * Throws EmptyStackException if stack is empty. */
		public B peek() {
			if (head == null) {
				throw new EmptyStackException();
			}
			return head.value;
		}

		/* Tests if this stack is empty. */
		public boolean empty() {
			return (head == null);
		}
	}

	protected class LinkedListIter<B> implements Iter<B> {

		private class LinkedList {
			private LinkedList next = null;
			private LinkedList prev = null;
			private B value;
			public LinkedList(B value) {
				this.value = value;
			}
			public LinkedList getNext() {
				return next;
			}
			public LinkedList getPrev() {
				return prev;
			}
			public B getValue() {
				return value;
			}
			public void append(LinkedList next) {
				this.next = next;
				next.prev = this;
			}
		}

		/* only equals null if iter is empty */
		LinkedList current = null;
		LinkedList head = null;
		LinkedList tail = null;

		public LinkedListIter<B> prepend(B value) {
			LinkedList l = new LinkedList(value);
			if (head == null) {
				tail = head = l;
			} else {
				l.append(head);
				head = l;
			}
			return this;
		}

		private LinkedList pred() {
			if (head == null) {
				/* empty iter */
				return null;
			} else if (current == null) {
				/* first call to next */
				return null;
			} else if (current.getPrev() != null) {
				/* prev element exists */
				return current.getPrev();
			}

			/* at beginning of iter */
			return null;
		}

		private LinkedList succ() {
			if (head == null) {
				/* empty iter */
				return null;
			} else if (current == null) {
				/* first call to next */
				return head;
			} else if (current.getNext() != null) {
				/* next element exists */
				return current.getNext();
			}

			/* at end of iter */
			return null;
		}

		public B next() {
			LinkedList l = succ();

			if (l == null) {
				return null;
			}
			current = l;
			return l.getValue();
		}

		public B previous() {
			LinkedList l = pred();

			if (l == null) {
				return null;
			}
			current = l;
			return l.getValue();
		}

		public boolean hasNext() {
			return (succ() != null);
		}

		public boolean hasPrevious() {
			return (pred() != null);
		}
	}

	protected abstract class AbstractTreeIter implements TreeIter<A> {

		/* is null if and only if next() and previous()
		 * have never been called, or root == null */
		private Node current = null;

		/* can be null for empty iter */
		private final Node root;

		public AbstractTreeIter(Node root) {
			this.root = root;
		}

		/* Returns successor node if it exists, otherwise null. */
		protected abstract Node succ();

		/* Returns predecessor node if it exists, otherwise null. */
		protected abstract Node pred();

		public A next() {
			Node n = succ();
			if (n == null) {
				return null;
			}
			setCurrent(n);
			return n.value;
		}

		public A previous() {
			Node n = pred();
			if (n == null) {
				return null;
			}
			setCurrent(n);
			return n.value;
		}

		public boolean hasNext() {
			return (succ() != null);
		}

		public boolean hasPrevious() {
			return (pred() != null);
		}

		/* Return appropriate type of TreeIter in subclasses
		 *
			if (current == null) {
				return new AbstractTreeIter(succ());
			}
			return new AbstractTreeIter(current);
		 */
		public abstract TreeIter<A> down();

		protected Node getRoot() {
			return root;
		}

		protected Node getCurrent() {
			return current;
		}

		protected void setCurrent(Node current) {
			this.current = current;
		}
	}

	protected class Node {
		private Node left;
		private Node right;

		private final A value;

		public Node(A value) {
			this.value = value;
		}

		public boolean hasLeft() {
			return (getLeft() != null);
		}

		public boolean hasRight() {
			return (getRight() != null);
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}

		public A getValue() {
			return value;
		}
	}
}
/* vim: set noet ts=4 sw=4: */
