public abstract class StringTree {
	/* binary tree; each node has a string value */

	protected static final String leftStep = "left";
	protected static final String rightStep = "right";
	protected static final String separator = " ";
	protected static final String emptyString = "";
	protected static final String elementNotFound = "Knoten wurde nicht gefunden";

	private StringNode root = null;

	protected StringNode getRoot() {
		return root;
	}

	protected void setRoot(StringNode root) {
		this.root = root;
	}

	/* node != null
	 * returns true if the tree contains node, or false otherwise. */
	public boolean contains(String node) {
		return (search(node, root) != null);
	}

	/* searches entire tree for node (!= null).
	 * returns space separated path to node if found
	 * or an error message if not found. */
	public String search(String node) {
		String path = search(node, root);
		if (path == null) {
			return elementNotFound;
		}
		return path.trim();
	}

	private static String search(String value, StringNode curNode) {
		String path;
		final String format = "%s%s%s";

		if (value == null) {
			throw new NullPointerException();
		}
		
		final StringNode needle = new StringNode(value, 0);

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

	/* adds node (!= null && != "") to the tree */
	public void add(String node) {

		if (node == null) {
			throw new NullPointerException();
		}

		final StringNode root = getRoot();

		if (root == null) {
			setRoot(new StringNode(node, 0));
			return;
		}

		addNode(node);
	}
	
	protected abstract void addNode(String node);

	/* returns null on error, doesn't alter the tree */
	protected StringNode traversePath(String path) {
		final String[] paths = path.trim().split(separator);
		final StringStack s = new StringStack();

		StringNode n = root;
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

	/* return a string representation of the tree */
	public String toString() {
		if (getRoot() == null) {
			return emptyString;
		}
		return getRoot().toStringTree();
	}
}
/* vim: set noet ts=4 sw=4: */
