public class StringStack {
	
	private class Entry {
		Entry next;
		StringNode node;
		public Entry(Entry next, StringNode node) {
			this.next = next;
			this.node = node;
		}
	}
	
	private Entry head = null;
	
	public void push(StringNode node) {
		head = new Entry(head, node);
	}
	
	public StringNode pop() {
		if (head == null) {
			return null;
		}
		StringNode node = head.node;
		head = head.next;
		return node;
	}
	
	public StringNode peek() {
		if (head == null) {
			return null;
		}
		return head.node;
	}
}