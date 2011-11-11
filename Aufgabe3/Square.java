public class Square extends Rectangle implements RegularPolygon {
	public Square(double a) throws IllegalArgumentException {
		super(a, a);
	}

	public void set(double a) throws IllegalArgumentException {
		if (a <= 0) {
			throw new IllegalArgumentException();
		}

		/* TODO: ... */
		super.scale(a/(perimeter()/4));
	}
}
/* vim: set noet ts=4 sw=4: */
