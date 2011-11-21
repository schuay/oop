public class IntTree implements Replaceable {
	/* binary tree; each node has an integer value */

	ReplaceableTree t = new ReplaceableTree();

	/* returns true if the tree contains node, or false otherwise. */
	public boolean contains(int node) {
		Integer i = node;
		return t.contains(i.toString());
	}

	/* searches entire tree for node.
	 * returns space separated path to node if found
	 * or an error message if not found. */
	public String search(int node) {
		Integer i = node;
		return t.search(i.toString());
	}

	/* adds node to the tree.
	 * does not modify existing tree positions.
	 * node is added to leftmost position with least depth. */
	public void add(int node) {
		Integer i = node;
		t.add(i.toString());
	}

	/* replaces tree at position (!= null, path description as formatted
	 * by search()) with subTree (!= null, subtree description as formatted by
	 * toString()).
	 * tree is not modified if an error occurs. */
	public void replace(String position, String subTree) {
		t.replace(position, subTree);
	}
	
	public String toString() {
		return t.toString();
	}
}
/* vim: set noet ts=4 sw=4: */
