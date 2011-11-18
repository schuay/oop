import java.util.HashMap;
public class Test {
	private static boolean tests = true;
	private static HashMap<Boolean, String> labels =
		new HashMap<Boolean, String>();
	private static int testCount = 0;
	private static int failedCount = 0;

	static {
		labels.put(true, "pass");
		labels.put(false, "FAIL");
	}

	private static final String SEARCH_NOT_FOUND_MSG = "Knoten wurde nicht gefunden";

	/**
	 * Main entry point of the testing suite.
	 * @param args command line arguments
	 */
	public static void main(String[] args) throws Exception {
		ReplaceableTree replT;
		SortedTree sortT;
		IntTree intT;

		replT = new ReplaceableTree();
		testStringTree(replT, "(Empty) ReplaceableTree");
		replT = new ReplaceableTree();
		testReplaceableTree(replT);
		testStringTree(replT, "ReplaceableTree");
		
		sortT = new PreorderTree();
		testStringTree(sortT, "(Empty) PreorderTree");
		sortT = new PreorderTree();
		testSortedTree(sortT, "PreorderTree");
		test("Traverse PreorderTree", "sortT_d sortT_b sortT_a sortT_c sortT_d sortT_e sortT_d", sortT.traverse());
		testStringTree(sortT, "PreorderTree");

		sortT = new InorderTree();
		testStringTree(sortT, "(Empty) InorderTree");
		sortT = new InorderTree();
		testSortedTree(sortT, "InorderTree");
		test("Traverse InorderTree", "sortT_a sortT_b sortT_c sortT_d sortT_d sortT_d sortT_e", sortT.traverse());
		testStringTree(sortT, "InorderTree");

		sortT = new PostorderTree();
		testStringTree(sortT, "(Empty) PostorderTree");
		sortT = new PostorderTree();
		testSortedTree(sortT, "PostorderTree");
		test("Traverse PostorderTree", "sortT_a sortT_c sortT_b sortT_d sortT_e sortT_d sortT_d", sortT.traverse());
		testStringTree(sortT, "PostorderTree");

		intT = new IntTree();
		testIntTree(intT);

		printSummary();
	}

	/**
	 * Tests an IntTree.
	 * @param t The tree.
	 */
	private static void testIntTree(IntTree t) {
		String prefix = "IntTree: ";
		boolean test;

		test(prefix+"contains nonexistent value", false, t.contains(1));
		test(prefix+"search for nonexistent value", SEARCH_NOT_FOUND_MSG, t.search(1));

		/*  1
		 *  - 2
		 *   - 4
		 *   - 5
		 *  - 3
		 *   - 6
		 *   - 7
		 */
		try {
			t.add(1);
			t.add(2);
			t.add(3);
			t.add(4);
			t.add(5);
			t.add(6);
			t.add(7);
			test = true;
		} catch (Exception e) {
			test = false;
		}

		test(prefix+"fill with valid values", true, test);
		test(prefix+"contains existent value", true, t.contains(1));
		test(prefix+"search 1", "", t.search(1));
		test(prefix+"search 2", "left", t.search(2));
		test(prefix+"search 3", "right", t.search(3));
		test(prefix+"search 4", "left left", t.search(4));
		test(prefix+"search 5", "left right", t.search(5));
		test(prefix+"search 6", "right left", t.search(6));
		test(prefix+"search 7", "right right", t.search(7));
		test(prefix+"toString", " 1\n"+
				                " - 2\n"+
							    "  - 4\n"+
							    "  - 5\n"+
							    " - 3\n"+
								"  - 6\n"+
								"  - 7\n", t.toString());

		try {
			t.replace(null, " 10");
			test = true;
		} catch (NullPointerException e) {
			test = false;
		}
		test(prefix+"replace with NULL as 1st arg", false, test);

		try {
			t.replace("left", null);
			test = true;
		} catch (NullPointerException e) {
			test = false;
		}
		test(prefix+"replace with NULL as 2nd arg", false, test);

		try {
			t.replace("left left left", " 10");
			test = true;
		} catch (IllegalArgumentException e) {
			test = false;
		}
		test(prefix+"replace with invalid position 1", false, test);

		try {
			t.replace("x y", " 10");
			test = true;
		} catch (IllegalArgumentException e) {
			test = false;
		}
		test(prefix+"replace with invalid position 2", false, test);

		try {
			t.replace("left left", " 10\n"+
					               " 20\n");
			test = true;
		} catch (IllegalArgumentException e) {
			test = false;
		}
		test(prefix+"replace with invalid subtree 1", false, test);

		try {
			t.replace("left left", " 10\n"+
					               " - 20\n"+
								   " - 30\n"+
								   " - 40\n");
			test = true;
		} catch (IllegalArgumentException e) {
			test = false;
		}
		test(prefix+"replace with invalid subtree 2", false, test);

		try {
			t.replace("left left", " 10\n"+
					               " - 20\n"+
								   " - intT_z\n");
			test = true;
		} catch (IllegalArgumentException e) {
			test = false;
		}
		test(prefix+"replace with invalid subtree 3", false, test);

		try {
			t.replace("left left", " 10\n"+
					               " - 20\n"+
								   " - 30\n");
		} catch (Exception e) {}
		test(prefix+"replace with correct args", " 1\n"+
				                                 " - 2\n"+
							                     "  - 10\n"+
												 "   - 20\n"+
												 "   - 30\n"+
							                     "  - 5\n"+
							                     " - 3\n"+
									     "  - 6\n"+
										 "  - 7\n", t.toString());
	}

	/**
	 * Tests a ReplaceableTree.
	 * @param t The tree.
	 */
	private static void testReplaceableTree(ReplaceableTree t) {
		String prefix = "ReplaceableTree: ";
		boolean test;

		/*  replT_a
		 *  - replT_b
		 *   - replT_d
		 *   - replT_e
		 *  - replT_c
		 *   - replT_f
		 *   - replT_g
		 */
		try {
			t.add("replT_a");
			t.add("replT_b");
			t.add("replT_c");
			t.add("replT_d");
			t.add("replT_e");
			t.add("replT_f");
			t.add("replT_g");
			test = true;
		} catch (Exception e) {
			test = false;
		}

		test(prefix+"fill with valid values", true, test);
		test(prefix+"search 1", "", t.search("replT_a"));
		test(prefix+"search 2", "left", t.search("replT_b"));
		test(prefix+"search 3", "right", t.search("replT_c"));
		test(prefix+"search 4", "left left", t.search("replT_d"));
		test(prefix+"search 5", "left right", t.search("replT_e"));
		test(prefix+"search 6", "right left", t.search("replT_f"));
		test(prefix+"search 7", "right right", t.search("replT_g"));
		test(prefix+"toString", " replT_a\n"+
				                " - replT_b\n"+
							    "  - replT_d\n"+
							    "  - replT_e\n"+
							    " - replT_c\n"+
								"  - replT_f\n"+
								"  - replT_g\n", t.toString());

		try {
			t.replace(null, " replT_x");
			test = true;
		} catch (NullPointerException e) {
			test = false;
		}
		test(prefix+"replace with NULL as 1st arg", false, test);

		try {
			t.replace("left", null);
			test = true;
		} catch (NullPointerException e) {
			test = false;
		}
		test(prefix+"replace with NULL as 2nd arg", false, test);

		try {
			t.replace("left left left", " replT_x");
			test = true;
		} catch (IllegalArgumentException e) {
			test = false;
		}
		test(prefix+"replace with invalid position 1", false, test);

		try {
			t.replace("x y", "replT_x");
			test = true;
		} catch (IllegalArgumentException e) {
			test = false;
		}
		test(prefix+"replace with invalid position 2", false, test);

		try {
			t.replace("left left", " replT_x\n"+
					               " replT_y\n");
			test = true;
		} catch (IllegalArgumentException e) {
			test = false;
		}
		test(prefix+"replace with invalid subtree 1", false, test);

		try {
			t.replace("left left", " replT_w\n"+
					               " - replT_x\n"+
								   " - replT_y\n"+
								   " - replT_z\n");
			test = true;
		} catch (IllegalArgumentException e) {
			test = false;
		}
		test(prefix+"replace with invalid subtree 2", false, test);

		try {
			t.replace("left left", " replT_x\n"+
					               " - replT_y\n"+
								   " - replT_z\n");
		} catch (Exception e) {}
		test(prefix+"replace with correct args", " replT_a\n"+
				                                 " - replT_b\n"+
							                     "  - replT_x\n"+
												 "   - replT_y\n"+
												 "   - replT_z\n"+
							                     "  - replT_e\n"+
							                     " - replT_c\n"+
									     "  - replT_f\n"+
										 "  - replT_g\n", t.toString());
	}

	/**
	 * Tests a SortedTree.
	 * @param t The tree.
	 * @param type A String specifing the actual type of the tree. It is prepended to all
	 * test messages.
	 */
	private static void testSortedTree(SortedTree t, String type) {
		String prefix = type+" as SortedTree: ";
		boolean test;

		/*  sortT_d
		 *  - sortT_b
		 *   - sortT_a
		 *   - sortT_c
		 *  - sortT_d
		 *   - sortT_e
		 *    - sortT_d
		 */
		try {
			t.add("sortT_d");
			t.add("sortT_b");
			t.add("sortT_c");
			t.add("sortT_d");
			t.add("sortT_e");
			t.add("sortT_a");
			t.add("sortT_d");
			test = true;
		} catch (Exception e) {
			test = false;
		}

		test(prefix+"fill with valid values", true, test);
		test(prefix+"search 1", "", t.search("sortT_d"));
		test(prefix+"search 2", "left", t.search("sortT_b"));
		test(prefix+"search 3", "left right", t.search("sortT_c"));
		test(prefix+"search 4", "right right", t.search("sortT_e"));
		test(prefix+"search 5", "left left", t.search("sortT_a"));
		test(prefix+"toString", " sortT_d\n"+
				                " - sortT_b\n"+
							    "  - sortT_a\n"+
							    "  - sortT_c\n"+
							    " - sortT_d\n"+
								"  - sortT_e\n"+
								"   - sortT_d\n", t.toString());
	}

	/**
	 * Tests a StringTree.
	 * @param t The tree.
	 * @param type A String specifing the actual type of the tree. It is prepended to all
	 * test messages.
	 */
	private static void testStringTree(StringTree t, String type) {
		String prefix = type+" as StringTree: ";
		boolean test;

		try {
			t.contains(null);
			test = true;
		} catch (NullPointerException e) {
			test = false;
		}
		test(prefix+"contains with NULL argument", false, test);

		try {
			t.search(null);
			test = true;
		} catch (NullPointerException e) {
			test = false;
		}
		test(prefix+"search with NULL argument", false, test);

		try {
			t.add(null);
			test = true;
		} catch (NullPointerException e) {
			test = false;
		}
		test(prefix+"add with NULL argument", false, test);

		test(prefix+"contains nonexistent value", false, t.contains("strT_a"));
		test(prefix+"search for nonexistent value", SEARCH_NOT_FOUND_MSG, t.search("strT_a"));

		t.add("strT_a");
		test(prefix+"contains existent value", true, t.contains("strT_a"));

		String searchPath[] = {""};
		try {
			searchPath = t.search("strT_a").split(" ");
			test = true;
		} catch (Exception e) {
			test = false;
		}
		for (int i = 0; i < searchPath.length; i++) {
			if (searchPath[i] != "left" && searchPath[i] != "right") {
				test = false;
				break;
			}
		}
		test(prefix+"search for existent value", true, test);
	}

	/**
	 * Prints a single test result.
	 * @param test Test description
	 * @param expected Expected test outcome
	 * @param got Actual test outcome
	 */
	private static void test(String test, Object expected, Object got) {
		boolean succeeded;

		if (expected == null) {
			succeeded = (expected == got);
		} else {
			succeeded = expected.equals(got);
		}

		if (!succeeded) {
			failedCount++;
		}
		System.out.printf("%04d: %s%n%s: expected: %s, got: %s%n%n",
				testCount++, test, labels.get(succeeded),
				abbreviate(expected), abbreviate(got));
		tests = tests && succeeded;
	}
	
	private static void printSummary() {
		System.out.printf("%nFinal result: %s (%d passed, %d failed)%n%n",
			labels.get(tests), testCount - failedCount, failedCount);
	}

	private static String abbreviate(Object o) {

		if (o == null) {
			return null;
		}

		int cutoff = 50;
		String str = o.toString();

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
