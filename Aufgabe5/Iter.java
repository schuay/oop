public interface Iter<A> {
	/* An iterator over a collection. */

	/* Returns the next element in the iteration. */
	A next();

	/* Returns the previous element in the iteration. */
	A previous();

	/* Returns true if the iteration has a next element. */
	boolean hasNext();

	/* Returns true if the iteration has a previous element. */
	boolean hasPrevious();
}
/* vim: set noet ts=4 sw=4: */
