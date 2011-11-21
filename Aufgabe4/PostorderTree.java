public class PostorderTree extends SortedTree {
	/* traverse() returns a space separated string containing
	 * the values of contained nodes (order: postorder) */
	
	protected void traverseRecursive(StringNode curNode, StringBuilder sb) {
		if (curNode == null) {
			return;
		}

		traverseRecursive(curNode.getLeft(), sb);
		traverseRecursive(curNode.getRight(), sb);
		sb.append(curNode.getValue()).append(separator);
	}
}
/* vim: set noet ts=4 sw=4: */
