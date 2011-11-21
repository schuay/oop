public interface Replaceable {

	/* replaces tree at position (!= null, path description as formatted
	 * by search()) with subTree (!= null, subtree description as formatted by
	 * toString()).
	 * tree is not modified if an error occurs. */
	void replace(String position, String subTree);
}
/* vim: set noet ts=4 sw=4: */
