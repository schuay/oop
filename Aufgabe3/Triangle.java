public class Triangle implements Polygon {
	private double a;
	private double b;
	private double c;
	private final int EDGES = 3;

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

	public int edges() {
		return EDGES;
	}

	public double area() {
		double s = perimeter() / 2;
		return Math.sqrt(s*(s-a)*(s-b)*(s-c));
	}

	public double perimeter() {
		return a + b + c;
	}

	public void setA(double a) throws IllegalArgumentException {
		checkTriangle(a, this.b, this.c);
		this.a = a;
	}

	public void setB(double b) throws IllegalArgumentException {
		checkTriangle(this.a, b, this.c);
		this.b = b;
	}

	public void setC(double c) throws IllegalArgumentException {
		checkTriangle(this.a, this.b, c);
		this.c = c;
	}
}
/* vim: set noet ts=4 sw=4: */
