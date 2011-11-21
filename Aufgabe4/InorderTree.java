public class InorderTree extends SortedTree {
	/* traverse() returns a space separated string containing
	 * the values of contained nodes (order: inorder) */

	protected void traverseRecursive(StringNode curNode, StringBuilder sb) {
		if (curNode == null) {
			return;
		}

		traverseRecursive(curNode.getLeft(), sb);
		sb.append(curNode.getValue()).append(separator);
		traverseRecursive(curNode.getRight(), sb);
	}
}
/* vim: set noet ts=4 sw=4: */
