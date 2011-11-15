public class Triangle implements Polygon {
	private double a;
	private double b;
	private double c;
	private static final int EDGES = 3;

	/* a, b, c > 0;
	 * sum of 2 sides is always > the remaining side */
	public Triangle(double a, double b, double c) throws IllegalArgumentException {
		checkTriangle(a, b, c);

		this.a = a;
		this.b = b;
		this.c = c;
	}

	private static void checkTriangle(double a, double b, double c)
		throws IllegalArgumentException {
		/* already covered by next check, but repeat it here
		 * to make it explicit */
		if (a <= 0 || b <= 0 || c <= 0) {
			throw new IllegalArgumentException();
		}
		if (a + b <= c || b + c <= a || a + c <= b) {
			throw new IllegalArgumentException();
		}
	}

	/* edges = 3 */
	public int edges() {
		return EDGES;
	}

	public double area() {
		final double s = perimeter() / 2;
		return Math.sqrt(s*(s-a)*(s-b)*(s-c));
	}

	public double perimeter() {
		return a + b + c;
	}

	/* triangle must still be valid (see ctor) with new value of a
	 * this.a == a, b and c remain the same */
	public void setA(double a) throws IllegalArgumentException {
		checkTriangle(a, this.b, this.c);
		this.a = a;
	}

	/* triangle must still be valid (see ctor) with new value of b
	 * this.b == b, a and c remain the same */
	public void setB(double b) throws IllegalArgumentException {
		checkTriangle(this.a, b, this.c);
		this.b = b;
	}

	/* triangle must still be valid (see ctor) with new value of c
	 * this.c == c, a and b remain the same */
	public void setC(double c) throws IllegalArgumentException {
		checkTriangle(this.a, this.b, c);
		this.c = c;
	}
}
/* vim: set noet ts=4 sw=4: */
