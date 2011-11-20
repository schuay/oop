public class PostorderTree extends SortedTree {

	/* traverse() returns the values of contained nodes (postorder) */
	
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
