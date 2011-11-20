public class IntTree extends Tree implements Replaceable {
	/* binary tree; each node has an integer value */

	private IntNode root = null;

	protected IntNode getRoot() {
		return root;
	}
	
	protected void setRoot(IntNode root) {
		this.root = root;
	}

	/* returns true if the tree contains node, or false otherwise. */
	public boolean contains(int node) {
		return (search(node, root) != null);
	}

	/* searches entire tree for node.
	 * returns space separated path to node if found
	 * or an error message if not found. */
	public String search(int node) {
		String path = search(node, root);
		if (path == null) {
			return elementNotFound;
		}
		return path.trim();
	}

	private static String search(int value, IntNode curNode) {
		String path;
		final String format = "%s%s%s";
		
		final IntNode needle = new IntNode(value, 0);

		/* current node does not exist or empty value passed */
		if (curNode == null) {
			return null;
		}

		/* found */
		if (curNode.compareTo(needle) == 0) {
			return emptyString;
		}

		/* search left */
		path = search(value, curNode.getLeft());
		if (path != null) {
			return String.format(format, leftStep, separator, path);
		}

		/* and right tree */
		path = search(value, curNode.getRight());
		if (path != null) {
			return String.format(format, rightStep, separator,  path);
		}

		/* not found */
		return null;
	}

	/* adds node to the tree.
	 * does not modify existing tree positions.
	 * node is added to leftmost position with least depth. */
	public void add(int node) {

		final IntNode root = getRoot();

		if (root == null) {
			setRoot(new IntNode(node, 0));
			return;
		}

		addNode(node);
	}
	
	/* called by add(int node).
	 * at this point, it's guaranteed that the current tree is nonempty. */
	protected void addNode(int node) {
		final IntNode parent = getFreeNode(getRoot());
		final IntNode n = new IntNode(node, parent.getLevel() + 1);

		if (!parent.hasLeft()) {
			parent.setLeft(n);
		} else {
			parent.setRight(n);
		}
	}

	private static IntNode getFreeNode(IntNode curNode) {

		/* current node is available */
		if (!curNode.isFull()) {
			return curNode;
		}

		final IntNode bestOfRight = getFreeNode(curNode.getRight());
		final IntNode bestOfLeft = getFreeNode(curNode.getLeft());

		/* does left subtree have an available node with equal or less
		 * depth? */
		if (bestOfRight.getLevel() < bestOfLeft.getLevel()) {
			return bestOfRight;
		}
		return bestOfLeft;
	}

	/* replaces tree at position (!= null, path description as formatted
	 * by search()) with subTree (!= null, subtree description as formatted by
	 * toString()).
	 * tree is not modified if an error occurs. */
	public void replace(String position, String subTree) {
		
		if (position == null || subTree == null) {
			throw new NullPointerException();			
		}
		
		final String trimmedPos = position.trim();

		/* replace root, */
		if (trimmedPos.equals(emptyString)) {
			setRoot(parseTree(subTree, 0));
		} else {
			final IntNode parent = traversePath(trimmedPos);
			if (parent == null) {
				throw new IllegalArgumentException();
			}
			final IntNode newTree = parseTree(subTree, parent.getLevel() + 1);
			/* left */
			if (trimmedPos.endsWith(leftStep)) {
				parent.setLeft(newTree);
			/* or right subtree */
			} else {
				parent.setRight(newTree);
			}
		}
	}

	/* returns null on error */
	protected IntNode traversePath(String path) {
		final String[] paths = path.trim().split(separator);
		final IntStack s = new IntStack();

		IntNode n = root;
		s.push(n);

		for (String step : paths) {
			if (n == null) {
				return null;
			} else if (step.equals(leftStep)) {
				n = n.getLeft();
			} else if (step.equals(rightStep)) {
				n = n.getRight();
			} else {
				return null;
			}
			s.push(n);
		}

		/* ensure we are pointing to valid node */
		if (s.peek() == null) {
			return null;
		}

		/* return second to last */
		s.pop();

		return s.peek();
	}

	private static IntNode parseTree(String subTree, int baseLevel) {
		final char dash = '-'; /* TODO */

		final String[] lines = subTree.split("\n");
		final IntStack s = new IntStack();
		final IntNode root = new IntNode(Integer.parseInt(lines[0]), baseLevel);
		int prevlevel = 0;

		s.push(root);

		for (int i = 1; i < lines.length; i++) {
			final String line = lines[i];
			final int dashIndex = line.indexOf(dash);

			/* no dash (illegal syntax) or no label */
			if (dashIndex < 0 || dashIndex == line.length() - 1) {
				throw new IllegalArgumentException();
			}

			final int level = (dashIndex / 2) + 1; /* '  - a' is level 2, '    - b level 3 */
			final String value = line.substring(dashIndex + 1).trim();
			final boolean isEmptyLeaf = (value.length() == 0);
			final IntNode node =
					new IntNode(Integer.parseInt(value), baseLevel + level); /* the new child */

			/* invalid input (cannot go down more than 1 level in 1 step) */
			if (level > prevlevel + 1) {
				throw new IllegalArgumentException();
			}

			/* descend one level, head is parent */
			IntNode parent;
			if (level == prevlevel + 1) {
				parent = s.peek();
				if (!isEmptyLeaf) {
					parent.setLeft(node);
				}
			/* pop stack until we reach parent */
			} else {
				final int pops = prevlevel - level + 1;
				for (int j = 0; j < pops; j++) {
					s.pop();
				}
				parent = s.peek();
				if (parent.hasRight()) {
					throw new IllegalArgumentException();
				}
				if (!isEmptyLeaf) {
					parent.setRight(node);
				}
			}

			s.push(node);
			prevlevel = level;
		}

		return root;
	}
}
/* vim: set noet ts=4 sw=4: */
