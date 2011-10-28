import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;

public class Test {
	private static boolean tests = true;
	private static HashMap<Boolean, String> labels = new HashMap<Boolean, String>();
	private static int testCount = 0;
	private static int failedCount = 0;

	static {
		labels.put(true, "pass");
		labels.put(false, "FAIL");
	}

	/**
	 * Main entry point of the testing suite.
	 * @param args command line arguments
	 */
	public static void main(String[] args) throws Exception {
		CourseManager lm = new CourseManager();

		Calendar now = Calendar.getInstance();
		Calendar twentyDaysAgo = (Calendar)now.clone();
		Calendar tenDaysAgo = (Calendar)now.clone();
		Calendar inTenDays = (Calendar)now.clone();
		Calendar inTwentyDays = (Calendar)now.clone();

		twentyDaysAgo.add(Calendar.DATE, -20);
		tenDaysAgo.add(Calendar.DATE, -10);
		inTenDays.add(Calendar.DATE, 10);
		inTwentyDays.add(Calendar.DATE, 20);

		Course l1 = new Course("1", "Course1");
		Course l2 = new Course("2", "Course2");
		Course l3 = new Course("3", "Course3");
		Course l4 = new Course("4", "Course4");
		Course l5 = new Course("5", "Course5");
		Course l5dup = new Course("5", "Course6");
		
		l2.setEnrollFrom(twentyDaysAgo.getTime());
		l2.setEnrollTo(tenDaysAgo.getTime());
		l4.setEnrollFrom(inTenDays.getTime());
		l5.setUnenrollTo(tenDaysAgo.getTime());

		Student stud1 = new Student("1", "Stud1");
		Student stud2 = new Student("2", "Stud2");
		Student stud3 = new Student("3", "Stud3");
		
		/*
		 * 0
		 */

		test("adding Course1", true, lm.addCourse(l1));
		test("adding Course2", true, lm.addCourse(l2));
		test("adding Course3", true, lm.addCourse(l3));
		test("adding Course4", true, lm.addCourse(l4));
		test("adding Course5", true, lm.addCourse(l5));
		test("adding Course6 (with nr=5)", false, lm.addCourse(l5dup));

		String lvaList = String.format("%s%n%s%n%s%n%s%n%s%n",
				l3, l2, l1, l5, l4);
		test("comparing lva list output", lvaList, lm.getCourseList());

		lvaList = String.format("%s%n%s%n%s%n%s%n",
				l3, l2, l1, l5);
		l4.setVisible(false);
		test("comparing lva list output (after marking l4 invisible)", lvaList, lm.getCourseList());

		test("unregistering Stud1 from Course1 (not registered yet)", false,
				l1.unenroll(stud1));
		test("registering Stud1 to Course1", true,	l1.enroll(stud1));
		
		/*
		 * 10
		 */
		
		test("registering Stud1 to Course1 (again)", false, l1.enroll(stud1));
		test("unregistering Stud1 from Course1", true, l1.unenroll(stud1));
		test("unregistering Stud1 from Course1 (again)", false, l1.unenroll(stud1));
		test("registering Stud2 to Course2 (too late)", false, l2.enroll(stud2));
		test("registering Stud2 to Course4 (too early)", false, l4.enroll(stud2));
		test("registering Stud3 to Course5", true, l5.enroll(stud3));
		test("unregistering Stud3 from Course5 (too late)", false, l5.unenroll(stud3));
		test("registering Stud1 to Course5", true, l5.enroll(stud1));
		test("registering Stud1 to Course1", true, l1.enroll(stud1));
		test("registering Stud2 to Course1", true, l1.enroll(stud2));
		
		/*
		 * 20
		 */
		
		String studentList1 = String.format("%s%n%s%n", stud1, stud2);
		String studentList5 = String.format("%s%n%s%n", stud3, stud1);
		test("comparing student enrollment (Course1)", studentList1, lm.getStudents("1"));
		test("comparing student enrollment (Course2)", "", lm.getStudents("2"));
		test("comparing student enrollment (Course3)", "", lm.getStudents("3"));
		l4.setVisible(true);
		test("comparing student enrollment (Course4 after marking visible)", "", lm.getStudents("4"));
		test("comparing student enrollment (Course5)", studentList5, lm.getStudents("5"));


		test("unregistering Stud1 from Course1", true, l1.unenroll(stud1));
		studentList1 = String.format("%s%n", stud2);
		test("comparing student enrollment (Course1)", studentList1, lm.getStudents("1"));

		test("unregistering Stud2 from Course1", true, l1.unenroll(stud2));
		test("comparing student enrollment (Course1)", "", lm.getStudents("1"));

		boolean result = true;
		try {
			new Student(null, null);
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test("passing null args to Student ctor", false, result);
		
		/*
		 * 30
		 */
		
		result = true;
		try {
			new Student("", "");
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test("passing empty string args to Student ctor", false, result);

		result = true;
		try {
			new Course(null, null);
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test("passing null args to Course ctor", false, result);

		result = true;
		try {
			new Student("", "");
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test("passing empty string args to Course ctor", false, result);

		result = true;
		try {
			l1.setEnrollTo(tenDaysAgo.getTime());
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test("setting begin > end date in Course", false, result);

		result = true;
		try {
			lm.getStudents(null);
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test("passing null arg to CourseManager.getStudents()", false, result);

		result = true;
		try {
			lm.getStudents("");
		} catch (IllegalArgumentException e) {
			result = false;
		}
		test("passing empty string arg to CourseManager.getStudents()", false, result);

		l4.setEnrollFrom(tenDaysAgo.getTime());
		test("changing enrollment date", tenDaysAgo.getTime(), l4.getEnrollFrom());

		result = lm.cancelCourse(l4);
		test("cancelling course", true, result);

		Course l6 = new Course("6", "Course6");
		l6.setMaxParticipants(2);
		l6.enroll(stud1);
		l6.enroll(stud2);
		test("registering Stud3 to Course6 (exceeds maxParticipants)", false, l6.enroll(stud3));

		l6.unenroll(stud1);
		test("registering Stud3 to Course6 (after unregistering a student)", true, l6.enroll(stud3));
		
		/*
		 * 40
		 */
		
		Course l7 = new Course("7", "Course7");
		Requirements deps = new Requirements();
		deps.add(l1);
		deps.add(l2);
		l7.setDependencies(deps);
		test("registering Stud1 to Course7 (dependencies not met)", false, l7.enroll(stud1));

		Student stud4 = new Student("4", "Stud4");
		stud4.addCertificate(new Certificate(l1, now.getTime(), true, "A"));
		test("registering Stud4 to Course7 (dependencies partly met)", false, l7.enroll(stud4));
		stud4.addCertificate(new Certificate(l2, now.getTime(), false, "A"));
		test("registering Stud4 to Course7 (dependencies partly met (not passed))", false, l7.enroll(stud4));
		stud4.addCertificate(new Certificate(l2, inTenDays.getTime(), true, "A"));
		test("registering Stud4 to Course7 (dependencies met)", true, l7.enroll(stud4));

		Exam ex1 = new Exam("exam1", l7, now.getTime());
		l7.addEnrollable(ex1);
		l7.addEnrollable(new ExerciseInterview("interview", l7, now.getTime()));
		l7.addEnrollable(new Group("group"));
		Set<Enrollable> enrollables = l7.getEnrollables();
		test("list of enrollables is correct", 3, enrollables.size());

		Enrollable e1 = enrollables.toArray(new Enrollable[0])[0];
		test("enrolling to first enrollable in l7", true, e1.enroll(stud4));
		
		Grade g1 = new Grade(ex1, "supadupa", now.getTime());
		stud4.addGrade(g1);
		test("student stud4 has registered grades correctly", 1, stud4.getGrades().size());

		Tutor stud5 = new Tutor("5", "tut", "12345");
		Professor prof1 = new Professor("prof1", "12346");
		test("adding tutor to l7 staff", true, l7.addStaff(stud5));
		test("adding tutor to l7 staff (duplicate)", false, l7.addStaff(stud5));
		test("adding prof1 to l7 staff", true, l7.addStaff(prof1));
		
		/*
		 * 50
		 */
		
		test("staff has been correctly registered in l7", 2, l7.getStaff().size());


		System.out.printf("%nFinal result: %s (%d passed, %d failed)%n%n", labels.get(tests),
				testCount - failedCount, failedCount);
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

	private static String abbreviate(String str) {
		final int cutofflen = 50;

		if (!str.contains("\n") && str.length() < cutofflen) {
			return str;
		}

		int cutoff = Math.min(cutofflen, str.indexOf('\n'));
		return str.substring(0, cutoff) + " [...]";
	}
}
/* vim: set noet ts=4 sw=4: */
