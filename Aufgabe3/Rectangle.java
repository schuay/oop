public class Rectangle implements Scaleable {
	private double a;
	private double b;
	private final int EDGES = 4;

	public Rectangle(double a, double b) throws IllegalArgumentException {
		if (a <= 0 || b <= 0) {
			throw new IllegalArgumentException();
		}

		this.a = a;
		this.b = b;
	}

	public int edges() {
		return EDGES;
	}

	public double area() {
		return a*b;
	}

	public double perimeter() {
		return (a+b)*2;
	}

	public void scale(double factor) throws IllegalArgumentException {
		if (factor <= 0) {
			throw new IllegalArgumentException();
		}

		a *= factor;
		b *= factor;
	}

	protected double getA() {
		return a;
	}

	protected double getB() {
		return b;
	}
}
/* vim: set noet ts=4 sw=4: */
