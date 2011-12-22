import java.util.HashMap;

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
	 * 
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) throws Exception {

		ReplaceableTree<String> reptreeS = new ReplaceableTree<String>();
		InorderTree<Integer> intree = new InorderTree<Integer>();
		PreorderTree<Student> pretree = new PreorderTree<Student>();
		PostorderTree<Professor> posttree = new PostorderTree<Professor>();
		ReplaceableTree<Person> reptreeP = new ReplaceableTree<Person>();

		testInorderTreeWithInteger(intree);
		testPreorderTreeWithStudent(pretree);
		testPostorderTreeWithProfessor(posttree);
		testReplaceableTreeWithString(reptreeS);
		testReplaceableTreeWithPerson(reptreeP, pretree, posttree);
		testVarious();
		testTreeWithPerson(new PreorderTree<Person>());
		testTreeWithPerson(new InorderTree<Person>());
		testTreeWithPerson(new PostorderTree<Person>());
		testTreeWithPerson(new ReplaceableTree<Person>());
		printSummary();
	}

	private static boolean compareIterator(Iter<Boolean> it, Boolean[] steps) {
		if (it == null) {
			return false;
		}

		try {
			for (int i = 0; i < steps.length; i++) {
				if (!steps[i].equals(it.next())) {
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	private static <A> boolean compareTreeIterator(TreeIter<A> it,
			String steps, A[] results) {
		if (it == null) {
			return false;
		}

		boolean error = false;
		String[] fsteps = steps.split(" ");
		try {
			for (int i = 0; i < results.length; i++) {
				A o = null;
				if (fsteps[i].equals("next")) {
					o = it.next();
				} else if (fsteps[i].equals("prev")) {
					o = it.previous();
				} else if (fsteps[i].equals("down")) {
					it = it.down();
				}
				if (results[i] != null) {
					if (!results[i].equals(o)) {
						error = true;
					}
				} else {
					if (o != null) {
						error = true;
					}
				}
			}
		} catch (Exception e) {
			return false;
		}
		
		return !error;
	}

	private static void testTreeWithPerson(Tree<Person> tree) {
		String prefix = "Generic one node tree test with " + tree.getClass().getCanonicalName() + ": ";
		Student s = new Student("Test",1);
		tree.add(s);
		
		boolean success = false;
		if (tree.contains(s) != null && tree.contains(s).hasNext()){
			success = true;
		}
		
		test(prefix + "Adding Person to tree reference, then contains", true, success);
		
		success = false;
		if (tree.search(s) != null && !tree.search(s).hasNext()) {
			success = true;
		}
		test(prefix + "Adding Person to tree reference, then search", true, success);
		
		test(prefix + "Iterator of tree reference, checking first element",
				true, compareTreeIterator(tree.iterator(),
						"next", new Person[] {s}));
	}
	
	private static void testVarious() {
		String prefix = "Various tests: ";
		ReplaceableTree<Integer> reptree = new ReplaceableTree<Integer>();
		InorderTree<String> intree = new InorderTree<String>();
		PreorderTree<Integer> pretree = new PreorderTree<Integer>();
		PostorderTree<String> posttree = new PostorderTree<String>();

		reptree.add(1);
		reptree.add(2);
		reptree.add(3);
		reptree.add(4);
		reptree.add(5);

		intree.add("1");
		intree.add("2");
		intree.add("3");
		intree.add("4");
		intree.add("5");

		pretree.add(6);
		pretree.add(5);
		pretree.add(4);
		pretree.add(3);
		pretree.add(2);
		pretree.add(1);
		
		test(prefix + "ReplaceableTree<Integer>, uneven tree height, iterator",
				true, compareTreeIterator(reptree.iterator(),
						"next next next next next", new Integer[] { 4, 2, 5, 1, 3 }));
		test(prefix + "ReplaceableTree<Integer>, uneven tree height, search",
				true, compareIterator(reptree.search(5), new Boolean[] { false, true }));
		test(prefix + "InorderTree<String>, elements added in ascending list order, iterator",
				true, compareTreeIterator(intree.iterator(),
						"next next next next next", new String[] { "1", "2",
								"3", "4", "5" }));
		test(prefix + "InorderTree<String>, elements added in ascending list order, search",
				true, compareIterator(intree.search("5"), new Boolean[] { true, true, true, true }));
		test(prefix + "PreorderTree<Integer>, elements added in descending list order, iterator",
				true, compareTreeIterator(pretree.iterator(),
						"next next next next next next", new Integer[] { 6, 5, 4, 3, 2, 1 }));
		test(prefix + "PreorderTree<Integer>, elements added in descending list order, search",
				true, compareIterator(pretree.search(1), new Boolean[] { false, false, false, false }));

		
		InorderTree<Integer> subTree = new InorderTree<Integer>();
		Iter<Boolean> path = reptree.search(2);
		reptree.replace(path, subTree);

		test(prefix + "ReplaceableTree<Integer> replacing node with empty tree, iterator",
				true, compareTreeIterator(reptree.iterator(),
						"prev next next next",
						new Integer[] { null, 1, 3, null }));

		boolean result = false;
		try {
			reptree.replace(path, null);
		} catch (Exception e) {
			result = true;
		}
		test(prefix + "ReplaceableTree<Integer>, replacing with null", true, result);
		
		result = false;
		try {
			reptree.replace(null, subTree);
		} catch (Exception e) {
			result = true;
		}
		test(prefix + "ReplaceableTree<Integer>, replacing with nullpath", true, result);
		
		result = false;
		try {
			reptree.add(null);
		} catch (Exception e) {
			result = true;
		}
		test(prefix + "ReplaceableTree<Integer>, adding nullvalue", true, result);
		
		result = false;
		try {
			reptree.search(null);
		} catch (Exception e) {
			result = true;
		}
		test(prefix + "ReplaceableTree<Integer>, searching nullvalue", true, result);
		
		result = false;
		try {
			reptree.contains(null);
		} catch (Exception e) {
			result = true;
		}
		test(prefix + "ReplaceableTree<Integer>, containing nullvalue", true, result);
		
		result = false;
		try {
			intree.add(null);
		} catch (Exception e) {
			result = true;
		}
		test(prefix + "InorderTree<String>, adding nullvalue", true, result);
		
		result = false;
		try {
			intree.search(null);
		} catch (Exception e) {
			result = true;
		}
		test(prefix + "InorderTree<String>, searching nullvalue", true, result);
		
		result = false;
		try {
			intree.contains(null);
		} catch (Exception e) {
			result = true;
		}
		test(prefix + "InorderTree<String>, containing nullvalue", true, result);
		
		result = false;
		try {
			pretree.add(null);
		} catch (Exception e) {
			result = true;
		}
		test(prefix + "PreorderTree<Integer>, adding nullvalue", true, result);
		
		result = false;
		try {
			pretree.search(null);
		} catch (Exception e) {
			result = true;
		}
		test(prefix + "PreorderTree<Integer>, searching nullvalue", true, result);
		
		result = false;
		try {
			pretree.contains(null);
		} catch (Exception e) {
			result = true;
		}
		test(prefix + "PreorderTree<Integer>, containing nullvalue", true, result);
		
		result = false;
		try {
			posttree.add(null);
		} catch (Exception e) {
			result = true;
		}
		test(prefix + "PostorderTree<String>, adding nullvalue", true, result);
		
		result = false;
		try {
			posttree.search(null);
		} catch (Exception e) {
			result = true;
		}
		test(prefix + "PostorderTree<String>, searching nullvalue", true, result);
		
		result = false;
		try {
			posttree.contains(null);
		} catch (Exception e) {
			result = true;
		}
		test(prefix + "PostorderTree<String>, containing nullvalue", true, result);
	}

	private static void testReplaceableTreeWithPerson(ReplaceableTree<Person> reptree, PreorderTree<Student> pretree, PostorderTree<Professor> posttree) {
		String prefix = "ReplaceableTree<Person>: ";
		boolean test;
		Person[] samples = new Person[7];
		samples[0] = new Professor("Anna", "A");
		samples[1] = new Student("Bernhard", 1000001);
		samples[2] = new Professor("Corinna", "C");
		samples[3] = new Student("Dominik", 1);
		samples[4] = new Student("Eduard", 1);
		samples[5] = new Student("Franziska", 1);
		samples[6] = new Student("Gunter", 1);

		try {
			reptree.add(samples[1]);
			reptree.add(samples[0]);
			reptree.add(samples[2]);

			test = true;
		} catch (Exception e) {
			test = false;
		}

		test(prefix + "fill with valid values", true, test);

		test(prefix + "testing iterator of tree before inserting subtrees",
				true, compareTreeIterator(reptree.iterator(),
						"next next next next prev prev prev prev",
						new Person[] { samples[0], samples[1], samples[2], null, samples[1], samples[0] }));

		Iter<Boolean> leftrep = reptree.search(samples[0]);
		Iter<Boolean> rightrep = reptree.search(samples[2]);
		reptree.replace(leftrep, pretree);
		reptree.replace(rightrep, posttree);

		test(prefix + "testing iterator of tree after inserting subtrees", true, compareTreeIterator(reptree.iterator(),
						"next next next next next next next next next next next next next next next",
						new Person[] { samples[0], samples[1], samples[2],
								samples[3], samples[4], samples[5], samples[6],
								samples[1], samples[0], samples[1], samples[2],
								samples[3], samples[4], samples[5], samples[6] }));

		test(prefix + "contains with valid value after replace", true,
				compareTreeIterator(reptree.contains(samples[5]),
						"next next next", 
						new Person[] { samples[4], samples[5], samples[6] }));
		test(prefix + "contains with invalid value after replace", null,
				reptree.contains(new Student("blub", 0)));
		test(prefix + "search with valid value after replace", true,
				compareIterator(reptree.search(samples[6]), new Boolean[] { false, true, true }));
		test(prefix + "search with invalid value after replace", null, reptree.search(new Student("blub", 0)));
		test(prefix + "search root after replace", true, compareIterator(
				reptree.search(samples[1]), new Boolean[] {}));
	}

	private static void testPreorderTreeWithStudent(PreorderTree<Student> pretree) {
		String prefix = "PreorderTree<Student>: ";
		boolean test;
		Student[] samples = new Student[7];
		samples[0] = new Student("Anna", 1000001);
		samples[1] = new Student("Bernhard", 1000002);
		samples[2] = new Student("Corinna", 1000003);
		samples[3] = new Student("Dominik", 1000004);
		samples[4] = new Student("Eduard", 1000005);
		samples[5] = new Student("Franziska", 1000006);
		samples[6] = new Student("Gunter", 1000007);

		try {
			pretree.add(samples[3]);
			pretree.add(samples[1]);
			pretree.add(samples[5]);
			pretree.add(samples[0]);
			pretree.add(samples[2]);
			pretree.add(samples[4]);
			pretree.add(samples[6]);
			test = true;
		} catch (Exception e) {
			test = false;
		}

		test(prefix + "fill with valid values", true, test);

		test(prefix + "preorder iterator", true, compareTreeIterator(pretree.iterator(), 
				"next next next next next next next prev prev prev prev prev prev prev",
						new Student[] { samples[3], samples[1], samples[0],
								samples[2], samples[5], samples[4], samples[6],
								samples[4], samples[5], samples[2], samples[0],
								samples[1], samples[3] }));
		test(prefix + "bounds of iterator", true, compareTreeIterator(pretree.iterator(),
				"prev next next next next next next next next prev",
				new Student[] { null, samples[3], samples[1], samples[0],
						samples[2], samples[5], samples[4], samples[6], null,
						samples[4] }));
		test(prefix + "down of iterator", true, compareTreeIterator(pretree.iterator(), 
				"next next down next next next", 
				new Student[] {samples[3], samples[1], null, samples[1], samples[0], samples[2] }));
		test(prefix + "down of iterator at leaf", true, compareTreeIterator(
				pretree.iterator(), 
				"next next next down next prev",
				new Student[] { samples[3], samples[1], samples[0], null, samples[0], null }));
		test(prefix + "contains with valid value", true, compareTreeIterator(
				pretree.contains(samples[1]), "next next next", 
				new Student[] {samples[1], samples[0], samples[2] }));
		test(prefix + "contains with invalid value", null, pretree.contains(new Student("Unbekannt", 0)));
		test(prefix + "search with valid value, right tree", true,
				compareIterator(pretree.search(samples[4]), new Boolean[] {true, false }));
		test(prefix + "search with valid value, left tree", true,
				compareIterator(pretree.search(samples[2]), new Boolean[] {false, true }));
		test(prefix + "search with invalid value", null, pretree.search(new Student("Unbekannt", 0)));
		test(prefix + "search root", true, compareIterator(pretree.search(samples[3]), new Boolean[] {}));
	}

	private static void testPostorderTreeWithProfessor(PostorderTree<Professor> postree) {
		String prefix = "PostorderTree<Professor>: ";
		boolean test;
		Professor[] samples = new Professor[7];
		samples[0] = new Professor("Anna", "Institut A");
		samples[1] = new Professor("Bernhard", "Institut B");
		samples[2] = new Professor("Corinna", "Institut C");
		samples[3] = new Professor("Dominik", "Institut D");
		samples[4] = new Professor("Eduard", "Institut E");
		samples[5] = new Professor("Franziska", "Institut F");
		samples[6] = new Professor("Gunter", "Institut G");

		try {
			postree.add(samples[3]);
			postree.add(samples[1]);
			postree.add(samples[5]);
			postree.add(samples[0]);
			postree.add(samples[2]);
			postree.add(samples[4]);
			postree.add(samples[6]);
			test = true;
		} catch (Exception e) {
			test = false;
		}

		test(prefix + "fill with valid values", true, test);

		test(prefix + "postorder iterator", true, compareTreeIterator(postree.iterator(),
						"next next next next next next next prev prev prev prev prev prev prev",
						new Professor[] { samples[0], samples[2], samples[1],
								samples[4], samples[6], samples[5], samples[3],
								samples[5], samples[6], samples[4], samples[1],
								samples[2], samples[0] }));
		test(prefix + "bounds of iterator", true, compareTreeIterator(postree.iterator(),
				"prev next next next next next next next next prev",
				new Professor[] { null, samples[0], samples[2], samples[1],
						samples[4], samples[6], samples[5], samples[3], null, samples[5] }));
		test(prefix + "down of iterator", true, compareTreeIterator(postree
				.iterator(), "next next next down next next next",
				new Professor[] { samples[0], samples[2], samples[1], null, samples[0], samples[2], samples[1] }));
		test(prefix + "down of iterator at leaf", true, compareTreeIterator(
				postree.iterator(), "down next prev", new Professor[] { null, samples[0], null }));
		test(prefix + "contains with valid value", true, compareTreeIterator(
				postree.contains(samples[1]), "next next next",
				new Professor[] { samples[0], samples[2], samples[1] }));
		test(prefix + "contains with invalid value", null, postree.contains(new Professor("Unbekannt", "Z")));
		test(prefix + "search with valid value, right tree", true,
				compareIterator(postree.search(samples[4]), new Boolean[] {true, false }));
		test(prefix + "search with valid value, left tree", true,
				compareIterator(postree.search(samples[2]), new Boolean[] {false, true }));
		test(prefix + "search with invalid value", null, postree.search(new Professor("Unbekannt", "Z")));
		test(prefix + "search root", true, compareIterator(postree.search(samples[3]), new Boolean[] {}));
	}

	private static void testInorderTreeWithInteger(InorderTree<Integer> intree) {
		String prefix = "InorderTree<Integer>: ";
		boolean test;

		try {
			intree.add(4);
			intree.add(2);
			intree.add(1);
			intree.add(3);
			intree.add(6);
			intree.add(5);
			intree.add(7);
			test = true;
		} catch (Exception e) {
			test = false;
		}

		test(prefix + "fill with valid values", true, test);

		test(prefix + "inorder iterator", true, compareTreeIterator(intree.iterator(),
						"next next next next next next next prev prev prev prev prev prev prev",
						new Integer[] { 1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 1 }));
		test(prefix + "bounds of iterator", true, compareTreeIterator(intree.iterator(),
				"prev next next next next next next next next prev",
				new Integer[] { null, 1, 2, 3, 4, 5, 6, 7, null, 6 }));
		test(prefix + "down of iterator", true, compareTreeIterator(intree.iterator(), "next next down next next next", 
				new Integer[] {1, 2, null, 1, 2, 3 }));
		test(prefix + "down of iterator at leaf", true, compareTreeIterator(
				intree.iterator(), "down next prev", new Integer[] { null, 1, null }));
		test(prefix + "contains with valid value", true,
				compareTreeIterator(intree.contains(2), "next next next",
						new Integer[] { 1, 2, 3 }));
		test(prefix + "contains with invalid value", null, intree.contains(9));
		test(prefix + "search with valid value, right tree", true,
				compareIterator(intree.search(5), new Boolean[] { true, false }));
		test(prefix + "search with valid value, left tree", true,
				compareIterator(intree.search(3), new Boolean[] { false, true }));
		test(prefix + "search with invalid value", null, intree.search(9));
		test(prefix + "search root", true, compareIterator(intree.search(4),
				new Boolean[] {}));
	}

	private static void testReplaceableTreeWithString(ReplaceableTree<String> rtree) {
		String prefix = "ReplaceableTree<String>: ";
		boolean test;
		ReplaceableTree<String> subTree = null;
		try {
			rtree.add("replT_d");
			rtree.add("replT_b");
			rtree.add("replT_f");
			rtree.add("replT_a");
			rtree.add("replT_c");
			rtree.add("replT_e");
			rtree.add("replT_g");

			subTree = new ReplaceableTree<String>();
			subTree.add("subT_b");
			subTree.add("subT_a");
			subTree.add("subT_c");
			test = true;
		} catch (Exception e) {
			test = false;
		}

		test(prefix + "fill with valid values", true, test);

		test(prefix + "iterator (inorder)", true, compareTreeIterator(rtree.iterator(),
						"next next next next next next next prev prev prev prev prev prev prev",
						new String[] { "replT_a", "replT_b", "replT_c",
								"replT_d", "replT_e", "replT_f", "replT_g",
								"replT_f", "replT_e", "replT_d", "replT_c",
								"replT_b", "replT_a" }));
		test(prefix + "bounds of iterator", true, compareTreeIterator(rtree.iterator(),
				"prev next next next next next next next next prev",
				new String[] { null, "replT_a", "replT_b", "replT_c",
						"replT_d", "replT_e", "replT_f", "replT_g", null, "replT_f" }));
		test(prefix + "down of iterator", true, compareTreeIterator(rtree.iterator(), "next next down next next next", 
				new String[] {"replT_a", "replT_b", null, "replT_a", "replT_b", "replT_c" }));
		test(prefix + "down of iterator at leaf", true, compareTreeIterator(
				rtree.iterator(), "down next prev", new String[] { null, "replT_a", null }));
		test(prefix + "contains with valid value", true, compareTreeIterator(
				rtree.contains("replT_b"), "next next next", new String[] {"replT_a", "replT_b", "replT_c" }));
		test(prefix + "contains with invalid value", null, rtree.contains("blub"));
		test(prefix + "search with valid value, right tree", true,
				compareIterator(rtree.search("replT_e"), new Boolean[] { true, false }));
		test(prefix + "search with valid value, left tree", true,
				compareIterator(rtree.search("replT_c"), new Boolean[] { false,
						true }));
		test(prefix + "search with invalid value", null, rtree.search("blub"));
		test(prefix + "search root", true, compareIterator(rtree.search("replT_d"), new Boolean[] {}));

		if (subTree != null) {
			Iter<Boolean> path = rtree.search("replT_f");
			rtree.replace(path, subTree);
			test(prefix + "iterator after replacing subtree", true, compareTreeIterator(rtree.iterator(),
							"next next next next next next next prev prev prev prev prev prev prev",
							new String[] { "replT_a", "replT_b", "replT_c",
									"replT_d", "subT_a", "subT_b", "subT_c",
									"subT_b", "subT_a", "replT_d", "replT_c",
									"replT_b", "replT_a" }));
		}

	}

	/**
	 * Prints a single test result.
	 * 
	 * @param test
	 *            Test description
	 * @param expected
	 *            Expected test outcome
	 * @param got
	 *            Actual test outcome
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
				testCount++, test, labels.get(succeeded), abbreviate(expected),
				abbreviate(got));
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
