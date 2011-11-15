import java.util.HashMap;
import java.math.BigDecimal;

public class Test {
	private static boolean tests = true;
	private static HashMap<Boolean, String> labels = new HashMap<Boolean, String>();
	private static int testCount = 0;
	private static int failedCount = 0;

	static {
		labels.put(true, "pass");
		labels.put(false, "FAIL");
	}

	private static void testTriangleSetters() {
		boolean result;
		Triangle t = new Triangle(1, 1, 1);

		try {
			t.setA(2);
			result = true;
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test(String.format("a = b + c - Triangle(1, 1, 1).setA(2)"),
			false, result);

		try {
			t.setB(2);
			result = true;
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test(String.format("a = b + c - Triangle(1, 1, 1).setB(2)"),
			false, result);

		try {
			t.setC(2);
			result = true;
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test(String.format("a = b + c - Triangle(1, 1, 1).setC(2)"),
			false, result);

		try {
			t.setA(3);
			result = true;
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test(String.format("a > b + c - Triangle(1, 1, 1).setA(3)"),
			false, result);

		try {
			t.setB(3);
			result = true;
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test(String.format("a > b + c - Triangle(1, 1, 1).setB(3)"),
			false, result);

		try {
			t.setC(3);
			result = true;
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test(String.format("a > b + c - Triangle(1, 1, 1).setC(3)"),
			false, result);

		try {
			t.setA(0);
			result = true;
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test(String.format("a = 0 - Triangle(1, 1, 1).setA(0)"),
			false, result);

		try {
			t.setB(0);
			result = true;
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test(String.format("a = 0 - Triangle(1, 1, 1).setB(0)"),
			false, result);

		try {
			t.setC(0);
			result = true;
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test(String.format("a = 0 - Triangle(1, 1, 1).setC(0)"),
			false, result);

		try {
			t.setA(-1);
			result = true;
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test(String.format("a = -1 - Triangle(1, 1, 1).setA(-1)"),
			false, result);

		try {
			t.setB(-1);
			result = true;
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test(String.format("a = -1 - Triangle(1, 1, 1).setB(-1)"),
			false, result);

		try {
			t.setC(-1);
			result = true;
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test(String.format("a = -1 - Triangle(1, 1, 1).setC(-1)"),
			false, result);

		/* setter, 0 < a < b + c */
		t = new Triangle(1, 1, 1);
		try {
			t.setA(1.5);
			result = true;
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test(String.format("a = 1.5 - Triangle(1, 1, 1).setA(1.5)"),
			true, result);

		t = new Triangle(1, 1, 1);
		try {
			t.setB(1.5);
			result = true;
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test(String.format("a = 1.5 - Triangle(1, 1, 1).setB(1.5)"),
			true, result);

		t = new Triangle(1, 1, 1);
		try {
			t.setC(1.5);
			result = true;
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test(String.format("a = 1.5 - Triangle(1, 1, 1).setC(1.5)"),
			true, result);
	}
	private static void testTriangleCtor(double a, double b, double c,
		boolean expectCtorFailure, String description) {

		boolean result;

		try {
			new Triangle(a, b, c);
			result = true;
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test(String.format("%s - Triangle(%f, %f, %f)", description, a, b, c),
			!expectCtorFailure, result);
	}

	private static void testSquareCtor(double a, boolean expectCtorFailure,
			String description) {

		boolean result;

		try {
			new Square(a);
			result = true;
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test(String.format("%s - Square(%f)", description, a),
			!expectCtorFailure, result);
	}

	private static void testEquilateralTriangleCtor(double a,
			boolean expectCtorFailure, String description) {

		boolean result;

		try {
			new EquilateralTriangle(a);
			result = true;
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test(String.format("%s - EquilateralTriangle(%f)", description, a),
			!expectCtorFailure, result);		
	}

	private static void testRectangleCtor(double a, double b,
			boolean expectCtorFailure, String description) {

		boolean result;

		try {
			new Rectangle(a, b);
			result = true;
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test(String.format("%s - Rectangle(%f)", description, a),
			!expectCtorFailure, result);
	}
	
	private static void testScaleable(Scaleable s, String description) {

		boolean result;

		try {
			s.scale(-1);
			result = true;
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test(String.format("%s - Scaleable.scale(-1)", description),
				false, result);

		try {
			s.scale(0);
			result = true;
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test(String.format("%s - Scaleable.scale(0)", description),
				false, result);

		try {
			s.scale(1.5);
			result = true;
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test(String.format("%s - Scaleable.scale(1.5)", description),
				true, result);
	}
	
	private static void testRegularPolygon(RegularPolygon rp,
			String description) {

		boolean result;

		try {
			rp.set(-1);
			result = true;
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test(String.format("%s - RegularPolygon.set(-1)", description),
				false, result);
		
		try {
			rp.set(0);
			result = true;
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test(String.format("%s - RegularPolygon.set(0)", description),
				false, result);

		try {
			rp.set(1);
			result = true;
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test(String.format("%s - RegularPolygon.set(1)", description),
				true, result);
	}
	
	private static BigDecimal roundTo5(double d) {
		return new BigDecimal(d).setScale(5, BigDecimal.ROUND_HALF_UP);
	}

	/* area and perimeter need to be provided with a precision of at least
	 * 5 decimal places.
	 */
	private static void testPolygon(Polygon p, int edges,
			double area, double perimeter, String description) {
		
		final int actEdges = p.edges();
		final double actArea = p.area();
		final double actPerimeter = p.perimeter();
		
		/* round doubles to a precision of 5 for equals() comparison */ 
		final BigDecimal bdArea = roundTo5(area);
		final BigDecimal bdPerimeter = roundTo5(perimeter);
		final BigDecimal bdActArea = roundTo5(actArea);
		final BigDecimal bdActPerimeter = roundTo5(actPerimeter);

		test(String.format("%s - Polygon edges > 2", description),
				true, actEdges > 2);
		test(String.format("%s - Polygon area > 0", description), 
				true, actArea > 0);
		test(String.format("%s - Polygon perimeter > 0", description),
				true, actPerimeter > 0);
		
		test(String.format("%s - edges() == %d", description, actEdges),
				edges, actEdges);
		test(String.format("%s - area() == %f", description, actArea),
				bdArea, bdActArea);
		test(String.format("%s - perimeter() == %f", description, actPerimeter),
				bdPerimeter, bdActPerimeter);
	}

	/**
	 * Main entry point of the testing suite.
	 * @param args command line arguments
	 */
	public static void main(String[] args) throws Exception {

		testTriangleCtor(0, 1, 1, true, "one side == 0");
		testTriangleCtor(1, 0, 1, true, "one side == 0");
		testTriangleCtor(1, 1, 0, true, "one side == 0");

		testTriangleCtor(-1, 1, 1, true, "one side < 0");
		testTriangleCtor(1, -1, 1, true, "one side < 0");
		testTriangleCtor(1, 1, -1, true, "one side < 0");

		testTriangleCtor(1, 1, 2, true, "side len a = b + c");
		testTriangleCtor(1, 2, 1, true, "side len a = b + c");
		testTriangleCtor(2, 1, 1, true, "side len a = b + c");

		testTriangleCtor(1, 1, 3, true, "side len a > b + c");
		testTriangleCtor(1, 3, 1, true, "side len a > b + c");
		testTriangleCtor(3, 1, 1, true, "side len a > b + c");

		testTriangleCtor(1, 1, 1, false, "equilateral");

		testTriangleCtor(3, 4, 5, false, "right angled");
		testTriangleCtor(3, 5, 4, false, "right angled");
		testTriangleCtor(4, 3, 5, false, "right angled");
		testTriangleCtor(4, 5, 3, false, "right angled");
		testTriangleCtor(5, 3, 4, false, "right angled");
		testTriangleCtor(5, 4, 3, false, "right angled");

		testTriangleCtor(2, 3, 3, false, "triangle");
		testTriangleCtor(3, 2, 3, false, "triangle");
		testTriangleCtor(3, 3, 2, false, "triangle");

		testTriangleSetters();

		testSquareCtor(0, true, "a == 0");
		testSquareCtor(-1, true, "a == -1");
		testSquareCtor(1, false, "a == 1");

		testEquilateralTriangleCtor(0, true, "a == 0");
		testEquilateralTriangleCtor(-1, true, "a == -1");
		testEquilateralTriangleCtor(1, false, "a == 1");

		testRectangleCtor(0, 1, true, "a == 0");
		testRectangleCtor(1, 0, true, "a == 0");

		testRectangleCtor(-1, 1, true, "a == -1");
		testRectangleCtor(1, -1, true, "a == -1");

		testRectangleCtor(1, 2, false, "rectangle");
		testRectangleCtor(2, 1, false, "rectangle");

		testScaleable(new Rectangle(1, 2), "Rectangle(1, 2)");
		testScaleable(new EquilateralTriangle(1), "EquilateralTriangle(1)");
		testScaleable(new Square(1), "Square(1)");

		testRegularPolygon(new EquilateralTriangle(1), "EquilateralTriangle(1)");
		testRegularPolygon(new Square(1), "Square(1)");

		testPolygon(new Rectangle(1, 2), 4, 2, 6, "Rectangle(1, 2");
		testPolygon(new EquilateralTriangle(1), 3, 0.43301, 3, "EquilateralTriangle(1)");
		testPolygon(new Square(1), 4, 1, 4, "Square(1)");
		testPolygon(new Triangle(3, 4, 5), 3, 6, 12, "Triangle(3, 4, 5)");
		
		Scaleable s = new Rectangle(1, 2);
		s.scale(2.0);
		testPolygon(s, 4, 8, 12, "Rectangle(1, 2) scaled x2");
		s = new EquilateralTriangle(1);
		s.scale(2.0);
		testPolygon(s, 3, 1.73205, 6, "EquilateralTriangle(1) scaled x2");
		s = new Square(1);
		s.scale(2.0);
		testPolygon(s, 4, 4, 8, "Square(1) scaled x2");

		RegularPolygon rp = new EquilateralTriangle(1);
		rp.set(2);
		testPolygon(rp, 3, 1.73205, 6, "EquilateralTriangle(1) set to 2");
		rp = new Square(1);
		rp.set(2);
		testPolygon(rp, 4, 4, 8, "Square(1) set to 2");

		Triangle t = new Triangle(2, 2, 2);
		t.setA(3);
		testPolygon(t, 3, 1.98431, 7, "Triangle(2, 2, 2) setA to 3");
		t = new Triangle(2, 2, 2);
		t.setB(3);
		testPolygon(t, 3, 1.98431, 7, "Triangle(2, 2, 2) setB to 3");
		t = new Triangle(2, 2, 2);
		t.setC(3);
		testPolygon(t, 3, 1.98431, 7, "Triangle(2, 2, 2) setC to 3");

		/* Ersetzbarkeit Rectangle <-> Square bereits durch
		 * Square <-> Scaleable und Square <-> Polygon abgedeckt
		 * nachdem Rectangle sonst keine Methoden hat.
		 */

		/* Ersetzbarkeit Triangle <-> EquilateralTriangle ist nicht gegeben: 
		 * EquilateralTriangle hat keine set{A,B,C} Methoden.
		 * 
		 * Triangle t = new EquilateralTriangle(1);
		 * t.setA(2); <- FAIL
		 */
		
		printSummary();
	}

	/**
	 * Prints a single test result.
	 * @param test Test description
	 * @param expected Expected test outcome
	 * @param got Actual test outcome
	 */
	private static void test(String test, Object expected, Object got) {
		boolean succeeded = expected.equals(got);
		if (!succeeded) {
			failedCount++;
		}
		System.out.printf("%04d: %s%n%s: expected: %s, got: %s%n%n", testCount++, test,
				labels.get(succeeded), abbreviate(expected.toString()),
				abbreviate(got.toString()));
		tests = tests && succeeded;
	}
	
	private static void printSummary() {
		System.out.printf("%nFinal result: %s (%d passed, %d failed)%n%n", labels.get(tests),
				testCount - failedCount, failedCount);
	}

	private static String abbreviate(String str) {
		int cutoff = 50;

		if (!str.contains("\n") && str.length() < cutoff) {
			return str;
		}

		if (str.contains("\n")) {
			cutoff = Math.min(cutoff, str.indexOf('\n'));
		}
		return str.substring(0, cutoff) + " [...]";
	}
}
/* vim: set noet ts=4 sw=4: */
