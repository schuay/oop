public class InorderTree extends SortedTree {

	/* returns the values of contained nodes (inorder) */

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
