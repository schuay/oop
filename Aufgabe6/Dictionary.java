public class Dictionary {

	private Entry head = null;
	private Entry tail = null;
	private int size = 0;

	private class DictIter implements Iter {
		private Entry cur;

		public DictIter() {
			cur = head;
		}

		public boolean hasNext() {
			return (cur != null);
		}

		public Object next() {
			if (!hasNext()) {
				return null;
			}

			Object ret = cur.getValue();
			cur = cur.getNext();

			return ret;
		}
	}

	private static class Entry {
		private final Object key;
		private final Object value;
		private Entry next;
		private Entry prev;

		public Entry(Object key, Object value) {
			Util.checkNullArg(key);
			Util.checkNullArg(value);

			this.key = key;
			this.value = value;
			this.next = null;
			this.prev = null;
		}

		public boolean keyEquals(Object key) {
			return this.key.equals(key);
		}

		public Object getValue() {
			return value;
		}

		public Entry getNext() {
			return next;
		}

		public void setNext(Entry next) {
			this.next = next;
		}

		public Entry getPrev() {
			return prev;
		}

		public void setPrev(Entry prev) {
			this.prev = prev;
		}
	}

	private Entry getEntryWithKey(Object key) {
		for (Entry p = head; p != null; p = p.next) {
			if (p.keyEquals(key)) {
				return p;
			}
		}

		return null;
	}

	public void put(Object key, Object value) {
		Util.checkNullArg(key);
		Util.checkNullArg(value);

		Entry x = getEntryWithKey(key);
		Entry n = new Entry(key, value);

		if (x == null) {
			n.setPrev(tail);
			
			if (tail == null) {
				head = n;
			} else {
				tail.setNext(n);
			}

			tail = n;
			size++;

			return;
		}

		Entry prev = x.getPrev();
		Entry next = x.getNext();

		n.setPrev(prev);
		n.setNext(next);

		if (prev == null) {
			head = n;
		} else {
			prev.setNext(n);
		}

		if (next == null) {
			tail = n;
		} else {
			next.setPrev(n);
		}
	}
	
	public Object get(Object key) {
		Util.checkNullArg(key);

		Entry x = getEntryWithKey(key);

		if (x == null) {
			return null;
		}

		return x.getValue();
	}
	
	public void remove(Object key) {
		Util.checkNullArg(key);

		Entry x = getEntryWithKey(key);

		if (x == null) {
			return;
		}

		Entry prev = x.getPrev();
		Entry next = x.getNext();

		if (prev == null) {
			head = next;
		} else {
			prev.setNext(next);
		}

		if (next == null) {
			tail = prev;
		} else {
			next.setPrev(prev);
		}

		size--;
	}
	
	public boolean containsKey(Object key) {
		Util.checkNullArg(key);

		return (getEntryWithKey(key) != null);
	}
	
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public Iter values() {
		return new DictIter();
	}
}

/* vim: set noet ts=4 sw=4: */
