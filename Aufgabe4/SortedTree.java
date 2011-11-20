public abstract class SortedTree extends StringTree {
	/* for all nodes:
	 * all values contained in the left subtree < the node value
	 * all values contained in the right subtree >= the node value. */

	public String search(String node) {

		if (node == null) {
			throw new NullPointerException();
		}
		
		StringNode n = getRoot();
		final StringNode needle = new StringNode(node, 0);
		final StringBuilder sb = new StringBuilder();
		
		while (true) {
			if (n == null) {
				return elementNotFound;
			}
			final int cmp = needle.compareTo(n);
			
			/* go left */
			if (cmp < 0) {
				n = n.getLeft();
				sb.append(leftStep + separator);
			/* go right */
			} else if (cmp > 0) {
				n = n.getRight();
				sb.append(rightStep + separator);
			} else {
				return sb.toString().trim();
			}
		}
	}

	/* add(String node) adds node (!= null) to the tree.
	 * the tree remains sorted (but not necessarily balanced).
	 */
	
	/* adds node such that the tree remains sorted */
	protected void addNode(String node) {
		StringNode n = getRoot();
		
		while (true) {
			/* go left */
			if (n.compareTo(node) > 0) {
				if (n.getLeft() == null) {
					n.setLeft(new StringNode(node, n.getLevel() + 1));
					return;
				}
				n = n.getLeft();
			/* go right */
			} else {
				if (n.getRight() == null) {
					n.setRight(new StringNode(node, n.getLevel() + 1));
					return;
				}
				n = n.getRight();
			}
		}
	}

	/* returns the values of contained nodes
	 * in a specific order determined by subtypes */
	public String traverse() {
		final StringBuilder sb = new StringBuilder();
		
		traverseRecursive(getRoot(), sb);
		
		return sb.toString().trim();
	}
	
	protected abstract void traverseRecursive(StringNode curNode, StringBuilder sb);
}
/* vim: set noet ts=4 sw=4: */
