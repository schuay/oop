public class StringStack {
	/* a stack containing values of type StringNode */
	
	private class Entry {
		final Entry next;
		final StringNode node;
		public Entry(Entry next, StringNode node) {
			this.next = next;
			this.node = node;
		}
	}
	
	private Entry head = null;

	/* pushes node (node != null) onto the stack
	 * as new head element */
	public void push(StringNode node) {
		head = new Entry(head, node);
	}

	/* returns the head element and removes it from the stack.
	 * returns null if stack is empty */
	public StringNode pop() {
		if (head == null) {
			return null;
		}
		StringNode node = head.node;
		head = head.next;
		return node;
	}

	/* returns the head element without removing it from the stack.
	 * returns null if stack is empty */
	public StringNode peek() {
		if (head == null) {
			return null;
		}
		return head.node;
	}
}
/* vim: set noet ts=4 sw=4: */
