public class Dictionary {

	/* Always points to the first entry or null if the Dictionary is empty. */
	private Entry head = null;

	/* Always points to the last entry or null if the Dictionary is empty. */
	private Entry tail = null;
	private int size = 0;

	private class DictIter implements Iter {
		/* An iterator over the Dictionary. */

		/* The current entry.
		 * It's value is returned by a call to next. */
		private Entry cur;

		/* Creates a new DictIter with the Dictionary's head as current entry. */
		public DictIter() {
			cur = head;
		}

		/* Returns false if cur is null, true otherwise. */
		public boolean hasNext() {
			return (cur != null);
		}

		/* Returns the value of the current entry and move to the next one.
		 * Returns null if the current entry is null. */
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
		/* An Entry for the Dictionary. */

		/* The key is unique among all entries in a dictionary.
		 * It is never null. */
		private final Object key;

		/* The value of the Entry is never null. */
		private final Object value;
		private Entry next;
		private Entry prev;

		/* Create a new Entry. 
		 * (key != null, value != null) */
		public Entry(Object key, Object value) {
			Util.checkNullArg(key);
			Util.checkNullArg(value);

			this.key = key;
			this.value = value;
			this.next = null;
			this.prev = null;
		}

		/* Returns true if the given key is equal to the key in the entry,
		 * false otherwise. */
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

	/* Searches the Dictionary for the entry with the key key.
	 * If such an entry exists it is returned. Otherwise null is returned.
	 * (key != null) */
	private Entry getEntryWithKey(Object key) {
		for (Entry p = head; p != null; p = p.next) {
			if (p.keyEquals(key)) {
				return p;
			}
		}

		return null;
	}

	/* Replaces the entry with the key key if it already exists, with this one,
	 * otherwise add the new entry to the end of the dictionary.
	 * (key != null, value != null) */
	public void put(Object key, Object value) {
		Util.checkNullArg(key);
		Util.checkNullArg(value);

		Entry x = getEntryWithKey(key);
		Entry n = new Entry(key, value);

		/* The entry doesn't exist yet, add it to the end of the Dictionary and
		 * increase size. */
		if (x == null) {
			n.setPrev(tail);
			
			/* Dictionary is empty, point head to new entry. */
			if (tail == null) {
				head = n;
			} else {
				tail.setNext(n);
			}

			tail = n;
			size++;

			return;
		}

		/* An entry with the same key already exists, replace it with the new
		 * one. */
		Entry prev = x.getPrev();
		Entry next = x.getNext();

		n.setPrev(prev);
		n.setNext(next);

		/* Replace entry at head of dictionary. */
		if (prev == null) {
			head = n;
		} else {
			prev.setNext(n);
		}

		/* Replace entry at tail of dictionary. */
		if (next == null) {
			tail = n;
		} else {
			next.setPrev(n);
		}
	}
	
	/* Returns the value of the entry with the key key or null if such an entry
	 * doesn't exist.
	 * (key != null) */
	public Object get(Object key) {
		Util.checkNullArg(key);

		Entry x = getEntryWithKey(key);

		if (x == null) {
			return null;
		}

		return x.getValue();
	}
	
	/* Removes the entry with the key key and decrease size by one if such an
	 * entry exists.
	 * (key != null) */
	public void remove(Object key) {
		Util.checkNullArg(key);

		Entry x = getEntryWithKey(key);

		/* No such entry. */
		if (x == null) {
			return;
		}

		Entry prev = x.getPrev();
		Entry next = x.getNext();

		/* Remove entry at head. */
		if (prev == null) {
			head = next;
		} else {
			prev.setNext(next);
		}

		/* Remove entry at tail. */
		if (next == null) {
			tail = prev;
		} else {
			next.setPrev(prev);
		}

		size--;
	}
	
	/* Returns true if the dictionary contains an entry with the key key, false
	 * otherwise.
	 * (key != null) */
	public boolean containsKey(Object key) {
		Util.checkNullArg(key);

		return (getEntryWithKey(key) != null);
	}
	
	/* Delete all entries and set size to 0. */
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	/* Returns an iterator over the directory.
	 * If the directory is empty the iterators next() function always returns
	 * null. */
	public Iter values() {
		return new DictIter();
	}
}

/* vim: set noet ts=4 sw=4: */
