import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Date;

public class Test {
	private static boolean tests = true;
	private static HashMap<Boolean, String> labels = new HashMap<Boolean, String>();

	static {
		labels.put(true, "Success");
		labels.put(false, "FAILURE");
	}

	/**
	 * Main entry point of the testing suite.
	 * @param args command line arguments
	 */
	public static void main(String[] args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		LVAManager lm = new LVAManager();

		Date d110920 = sdf.parse("20.09.2011");
		Date d111020 = sdf.parse("20.10.2011");
		Date d111010 = sdf.parse("10.10.2011");
		Date d111030 = sdf.parse("30.10.2011");

		LVA l1 = new LVA("1", "LVA1", d110920, d111020, d111020);
		LVA l2 = new LVA("2", "LVA2", d110920, d111010, d111020);
		LVA l3 = new LVA("3", "LVA3", d110920, d111010, d111010);
		LVA l4 = new LVA("4", "LVA4", d111020, d111030, d111030);
		LVA l5 = new LVA("5", "LVA5", d110920, d111020, d111010);
		LVA l5dup = new LVA("5", "LVA6", d110920, d111020, d111010);

		Student stud1 = new Student("1", "Stud1");
		Student stud2 = new Student("2", "Stud2");
		Student stud3 = new Student("3", "Stud3");

		test("adding LVA1", true, lm.addLVA(l1));
		test("adding LVA2", true, lm.addLVA(l2));
		test("adding LVA3", true, lm.addLVA(l3));
		test("adding LVA4", true, lm.addLVA(l4));
		test("adding LVA5", true, lm.addLVA(l5));
		test("adding LVA6 (with nr=5)", false, lm.addLVA(l5dup));

		String lvaList = String.format("%s%n%s%n%s%n%s%n%s%n",
				l3, l2, l1, l5, l4);
		test("comparing lva list output", lvaList, lm.getLVAList());

		test("unregistering Stud1 from LVA1 (not registered yet)", false,
				l1.unenroll(stud1));
		test("registering Stud1 to LVA1", true,	l1.enroll(stud1));
		test("registering Stud1 to LVA1 (again)", false, l1.enroll(stud1));
		test("unregistering Stud1 from LVA1", true, l1.unenroll(stud1));
		test("unregistering Stud1 from LVA1 (again)", false, l1.unenroll(stud1));
		test("registering Stud2 to LVA2 (too late)", false, l2.enroll(stud2));
		test("registering Stud2 to LVA4 (too early)", false, l4.enroll(stud2));
		test("registering Stud3 to LVA5", true, l5.enroll(stud3));
		test("unregistering Stud3 from LVA5 (too late)", false, l5.unenroll(stud3));
		test("registering Stud1 to LVA5", true, l5.enroll(stud1));
		test("registering Stud1 to LVA1", true, l1.enroll(stud1));
		test("registering Stud2 to LVA1", true, l1.enroll(stud2));

		String studentList1 = String.format("%s%n%s%n", stud1, stud2);
		String studentList5 = String.format("%s%n%s%n", stud3, stud1);
		test("comparing student enrollment (LVA1)", studentList1, lm.getStudents("1"));
		test("comparing student enrollment (LVA2)", "", lm.getStudents("2"));
		test("comparing student enrollment (LVA3)", "", lm.getStudents("3"));
		test("comparing student enrollment (LVA4)", "", lm.getStudents("4"));
		test("comparing student enrollment (LVA5)", studentList5, lm.getStudents("5"));


		test("unregistering Stud1 from LVA1", true, l1.unenroll(stud1));
		studentList1 = String.format("%s%n", stud2);
		test("comparing student enrollment (LVA1)", studentList1, lm.getStudents("1"));

		test("unregistering Stud2 from LVA1", true, l1.unenroll(stud2));
		test("comparing student enrollment (LVA1)", "", lm.getStudents("1"));

		boolean result = true;
		try {
			new Student(null, null);
		} catch (NullPointerException e) {
			result = false;
		}
		test("passing null args to Student ctor", false, result);

		result = true;
		try {
			new Student("", "");
		} catch (NullPointerException e) {
			result = false;
		}
		test("passing empty string args to Student ctor", false, result);

		result = true;
		try {
			new LVA(null, null, null, null, null);
		} catch (NullPointerException e) {
			result = false;
		}
		test("passing null args to LVA ctor", false, result);

		result = true;
		try {
			new LVA("6", "6", null, null, null);
		} catch (NullPointerException e) {
			result = false;
		}
		test("passing null args to LVA ctor", false, result);

		result = true;
		try {
			new LVA("", "", d110920, d110920, d110920);
		} catch (NullPointerException e) {
			result = false;
		}
		test("passing empty string args to Student ctor", false, result);

		result = true;
		try {
			new LVA("5", "5", d111020, d110920, d110920);
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test("passing begin > end date args to Student ctor", false, result);

		result = true;
		try {
			lm.getStudents(null);
		} catch (NullPointerException e) {
			result = false;
		}
		test("passing null arg to LVAManager.getStudents()", false, result);

		result = true;
		try {
			lm.getStudents("");
		} catch (NullPointerException e) {
			result = false;
		}
		test("passing empty string arg to LVAManager.getStudents()", false, result);

		System.out.println("Result: " + labels.get(tests));
	}

	/**
	 * Prints a single test result.
	 * @param test Test description
	 * @param expected Expected test outcome
	 * @param got Actual test outcome
	 */
	private static void test(String test, Object expected, Object got) {
		boolean succeeded = expected.equals(got);
		System.out.printf("Test: %s, expected: %s, got: %s... %s%n", test, expected,
				got, labels.get(succeeded));
		tests = tests && succeeded;
	}
}
