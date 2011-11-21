public class IntStack {
	/* a stack containing values of type IntNode */

	private class Entry {
		Entry next;
		IntNode node;
		public Entry(Entry next, IntNode node) {
			this.next = next;
			this.node = node;
		}
	}

	private Entry head = null;

	/* pushes node (node != null) onto the stack
	 * as new head element */
	public void push(IntNode node) {
		head = new Entry(head, node);
	}

	/* returns the head element and removes it from the stack.
	 * returns null if stack is empty */
	public IntNode pop() {
		if (head == null) {
			return null;
		}
		IntNode node = head.node;
		head = head.next;
		return node;
	}

	/* returns the head element without removing it from the stack.
	 * returns null if stack is empty */
	public IntNode peek() {
		if (head == null) {
			return null;
		}
		return head.node;
	}
}
/* vim: set noet ts=4 sw=4: */
