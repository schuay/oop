public class EquilateralTriangle implements RegularPolygon {
	private double a;
	private static final int EDGES = 3;

	/* a is > 0
	 * constructs an equilateral triangle with all side lengths = a */
	public EquilateralTriangle(double a) throws IllegalArgumentException {
		set(a);
	}

	public void set(double a) throws IllegalArgumentException {
		if (a <= 0) {
			throw new IllegalArgumentException();
		}

		this.a = a;
	}

	/* edges = 3 */
	public int edges() {
		return EDGES;
	}

	public double area() {
		return a * a * Math.sqrt(3) / 4;
	}

	public double perimeter() {
		return EDGES*a;
	}

	public void scale(double factor) throws IllegalArgumentException {
		if (factor <= 0) {
			throw new IllegalArgumentException();
		}

		a *= factor;
	}
}
/* vim: set noet ts=4 sw=4: */
