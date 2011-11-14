public interface Scaleable extends Polygon {

	/* factor > 0
	 * scales each side by factor (proportion between sides stays constant) */
	public void scale(double factor) throws IllegalArgumentException;
}
/* vim: set noet ts=4 sw=4: */
