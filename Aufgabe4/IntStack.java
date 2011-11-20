public class IntStack {
	
	private class Entry {
		Entry next;
		IntNode node;
		public Entry(Entry next, IntNode node) {
			this.next = next;
			this.node = node;
		}
	}
	
	private Entry head = null;
	
	public void push(IntNode node) {
		head = new Entry(head, node);
	}
	
	public IntNode pop() {
		if (head == null) {
			return null;
		}
		IntNode node = head.node;
		head = head.next;
		return node;
	}
	
	public IntNode peek() {
		if (head == null) {
			return null;
		}
		return head.node;
	}
}