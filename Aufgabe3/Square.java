
public class Square extends Rectangle implements RegularPolygon {

	/* a > 0
	 * constructs a square with all side lengths = a */
	public Square(double a) throws IllegalArgumentException {
		super(a, a);
	}

	public void set(double a) throws IllegalArgumentException {
		if (a <= 0) {
			throw new IllegalArgumentException();
		}

		/* a = b in super, so we can use getA() to calculate
		 * factor for scale() */
		scale(a / super.getA());
	}
}
/* vim: set noet ts=4 sw=4: */
