public class ReplaceableTree extends StringTree implements Replaceable {
	/* entire subtrees of ReplaceableTrees can be replaced by other subtrees */

	/* add(String node) adds node (!= null) to the tree.
	 * does not modify existing tree positions.
	 * node is added to leftmost position with least depth. */
	
	/* called by add(String node).
	 * at this point, it's guaranteed that the current tree is nonempty
	 * and that node is a valid string. */
	protected void addNode(String node) {
		final StringNode parent = getFreeNode(getRoot());
		final StringNode n = new StringNode(node, parent.getLevel() + 1);

		if (!parent.hasLeft()) {
			parent.setLeft(n);
		} else {
			parent.setRight(n);
		}
	}

	private static StringNode getFreeNode(StringNode curNode) {

		/* current node is available */
		if (!curNode.isFull()) {
			return curNode;
		}

		final StringNode bestOfRight = getFreeNode(curNode.getRight());
		final StringNode bestOfLeft = getFreeNode(curNode.getLeft());

		/* does left subtree have an available node with equal or less
		 * depth? */
		if (bestOfRight.getLevel() < bestOfLeft.getLevel()) {
			return bestOfRight;
		}
		return bestOfLeft;
	}

	private static StringNode parseTree(String subTree, int baseLevel) {
		final char dash = '-';

		final String[] lines = subTree.split("\n");
		final StringStack s = new StringStack();
		final StringNode root = new StringNode(lines[0], baseLevel);
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
			final StringNode node =
					new StringNode(value, baseLevel + level); /* the new child */

			/* invalid input (cannot go down more than 1 level in 1 step) */
			if (level > prevlevel + 1) {
				throw new IllegalArgumentException();
			}

			/* descend one level, head is parent */
			StringNode parent;
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

	public void replace(String position, String subTree) {
		
		if (position == null || subTree == null) {
			throw new NullPointerException();			
		}
		
		final String trimmedPos = position.trim();

		/* replace root, */
		if (trimmedPos.equals(emptyString)) {
			setRoot(parseTree(subTree, 0));
		} else {
			final StringNode parent = traversePath(trimmedPos);
			if (parent == null) {
				throw new IllegalArgumentException();
			}
			final StringNode newTree = parseTree(subTree, parent.getLevel() + 1);
			/* left */
			if (trimmedPos.endsWith(leftStep)) {
				parent.setLeft(newTree);
			/* or right subtree */
			} else {
				parent.setRight(newTree);
			}
		}
	}
}
/* vim: set noet ts=4 sw=4: */
