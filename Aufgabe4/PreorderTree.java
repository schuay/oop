public class PreorderTree extends SortedTree {
	/* traverse() returns a space separated string containing
	 * the values of contained nodes (order: preorder) */
	
	protected void traverseRecursive(StringNode curNode, StringBuilder sb) {
		if (curNode == null) {
			return;
		}

		sb.append(curNode.getValue()).append(separator);
		traverseRecursive(curNode.getLeft(), sb);
		traverseRecursive(curNode.getRight(), sb);
	}
}
/* vim: set noet ts=4 sw=4: */
