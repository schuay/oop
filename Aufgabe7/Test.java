import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

public class Test {
	private static boolean result = true;
	private final static HashMap<Boolean, String> labels =
		new HashMap<Boolean, String>();
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
		
		try {
			Dolphin dolphin1 = new Dolphin("dolphin1");
			Seal seal1 = new Seal("seal1");
			Swordfish swordfish1 = new Swordfish("swordfish1");

			DwarfTortoise dwarfTortoise1 = new DwarfTortoise("dwarfTortoise1");
			GiantTortoise giantTortoise1 = new GiantTortoise("giantTortoise1");
			Mamba mamba1 = new Mamba("mamba1");
			Python python1 = new Python("python1");

			Elephant elephant1 = new Elephant("elephant1");
			Giraffe giraffe1 = new Giraffe("giraffe1");

			Trailer trailer1 = new Trailer(new LinkedHashSet<Loadable>());

			DwarfTortoiseTerrarium dwarfTortoiseTer1 = new DwarfTortoiseTerrarium();
			GiantTortoiseTerrarium giantTortoiseTer1 = new GiantTortoiseTerrarium();
			MambaTerrarium mambaTer1 = new MambaTerrarium();
			PythonTerrarium pythonTer1 = new PythonTerrarium();
			WaterLandTank waterLandTank1 = new WaterLandTank();
			WaterTank waterTank1 = new WaterTank();
			Coupling coupling1 = new Coupling(new LinkedHashSet<LargeGame>());

			loadUnloadTest(dolphin1, dwarfTortoiseTer1, false);
			loadUnloadTest(dolphin1, giantTortoiseTer1, false);
			loadUnloadTest(dolphin1, mambaTer1, false);
			loadUnloadTest(dolphin1, pythonTer1, false);
			loadUnloadTest(dolphin1, waterLandTank1, true);
			loadUnloadTest(dolphin1, waterTank1, true);
			loadUnloadTest(dolphin1, coupling1, false);

			loadUnloadTest(seal1, dwarfTortoiseTer1, false);
			loadUnloadTest(seal1, giantTortoiseTer1, false);
			loadUnloadTest(seal1, mambaTer1, false);
			loadUnloadTest(seal1, pythonTer1, false);
			loadUnloadTest(seal1, waterLandTank1, true);
			loadUnloadTest(seal1, waterTank1, false);
			loadUnloadTest(seal1, coupling1, false);

			loadUnloadTest(swordfish1, dwarfTortoiseTer1, false);
			loadUnloadTest(swordfish1, giantTortoiseTer1, false);
			loadUnloadTest(swordfish1, mambaTer1, false);
			loadUnloadTest(swordfish1, pythonTer1, false);
			loadUnloadTest(swordfish1, waterLandTank1, true);
			loadUnloadTest(swordfish1, waterTank1, true);
			loadUnloadTest(swordfish1, coupling1, false);

			loadUnloadTest(dwarfTortoise1, dwarfTortoiseTer1, true);
			loadUnloadTest(dwarfTortoise1, giantTortoiseTer1, true);
			loadUnloadTest(dwarfTortoise1, mambaTer1, false);
			loadUnloadTest(dwarfTortoise1, pythonTer1, false);
			loadUnloadTest(dwarfTortoise1, waterLandTank1, false);
			loadUnloadTest(dwarfTortoise1, waterTank1, false);
			loadUnloadTest(dwarfTortoise1, coupling1, false);

			loadUnloadTest(giantTortoise1, dwarfTortoiseTer1, false);
			loadUnloadTest(giantTortoise1, giantTortoiseTer1, true);
			loadUnloadTest(giantTortoise1, mambaTer1, false);
			loadUnloadTest(giantTortoise1, pythonTer1, false);
			loadUnloadTest(giantTortoise1, waterLandTank1, false);
			loadUnloadTest(giantTortoise1, waterTank1, false);
			loadUnloadTest(giantTortoise1, coupling1, false);

			loadUnloadTest(mamba1, dwarfTortoiseTer1, false);
			loadUnloadTest(mamba1, giantTortoiseTer1, false);
			loadUnloadTest(mamba1, mambaTer1, true);
			loadUnloadTest(mamba1, pythonTer1, true);
			loadUnloadTest(mamba1, waterLandTank1, false);
			loadUnloadTest(mamba1, waterTank1, false);
			loadUnloadTest(mamba1, coupling1, false);

			loadUnloadTest(python1, dwarfTortoiseTer1, false);
			loadUnloadTest(python1, giantTortoiseTer1, false);
			loadUnloadTest(python1, mambaTer1, false);
			loadUnloadTest(python1, pythonTer1, true);
			loadUnloadTest(python1, waterLandTank1, false);
			loadUnloadTest(python1, waterTank1, false);
			loadUnloadTest(python1, coupling1, false);
	
			loadUnloadTest(elephant1, dwarfTortoiseTer1, false);
			loadUnloadTest(elephant1, giantTortoiseTer1, false);
			loadUnloadTest(elephant1, mambaTer1, false);
			loadUnloadTest(elephant1, pythonTer1, false);
			loadUnloadTest(elephant1, waterLandTank1, false);
			loadUnloadTest(elephant1, waterTank1, false);
			loadUnloadTest(elephant1, coupling1, false);

			loadUnloadTest(giraffe1, dwarfTortoiseTer1, false);
			loadUnloadTest(giraffe1, giantTortoiseTer1, false);
			loadUnloadTest(giraffe1, mambaTer1, false);
			loadUnloadTest(giraffe1, pythonTer1, false);
			loadUnloadTest(giraffe1, waterLandTank1, false);
			loadUnloadTest(giraffe1, waterTank1, false);
			loadUnloadTest(giraffe1, coupling1, false);

			loadUnloadTest(trailer1, dwarfTortoiseTer1, false);
			loadUnloadTest(trailer1, giantTortoiseTer1, false);
			loadUnloadTest(trailer1, mambaTer1, false);
			loadUnloadTest(trailer1, pythonTer1, false);
			loadUnloadTest(trailer1, waterLandTank1, false);
			loadUnloadTest(trailer1, waterTank1, false);
			loadUnloadTest(trailer1, coupling1, true);

			Transporter transporter1 = new Transporter(new LinkedHashSet<Loadable>());
			test("Loading null into Transporter", false, transporter1.load(null));
			test("Unloading null from Transporter", null, transporter1.unload(null));

			test("Loading null into Trailer", false, trailer1.load(null));
			test("Unloading null from Trailer", null, trailer1.unload(null));

			Trailer trailer2 = new Trailer(new LinkedHashSet<Loadable>());
			test("Connecting Trailer to Transporter with no coupling", false,
					transporter1.load(trailer2));

			Animal group1a[] = new Animal[] {
				new Elephant("g1elephant1"),
				new Elephant("g1elephant2"),
				new Elephant("g1elephant3"),
				new Giraffe("g1giraffe1"),
				new Giraffe("g1giraffe2"),
				new Giraffe("g1giraffe3")
			};

			Animal group1b[] = new Animal[] {
				new Dolphin("g1dolphin1"),
				new Seal("g1seal1"),
				new Seal("g1seal2"),
				new Swordfish("g1swordfish1")
			};

			Animal group1[] = combine(group1a, group1b);
			/*Animal group1[] = new Animal[] {
				new Elephant("g1elephant1"),
				new Elephant("g1elephant2"),
				new Elephant("g1elephant3"),
				new Giraffe("g1giraffe1"),
				new Giraffe("g1giraffe2"),
				new Giraffe("g1giraffe3"),
				new Dolphin("g1dolphin1"),
				new Seal("g1seal1"),
				new Seal("g1seal2"),
				new Swordfish("g1swordfish1")
			};*/

			Animal group2[] = new Animal[] {
				new Elephant("g2elephant1"),
				new Giraffe("g2giraffe1")
			};

			Animal group3[] = new Animal[] {
				new DwarfTortoise("g3dwarfTortoise1"),
				new DwarfTortoise("g3dwarfTortoise2"),
				new GiantTortoise("g3giantTortoise1"),
				new Mamba("g3mamba1"),
				new Mamba("g3mamba2"),
				new Python("g3python1")
			};

			LinkedHashSet<LargeGame> trailer3LargeGame = new LinkedHashSet<LargeGame>();
			trailer3LargeGame.add((LargeGame) group1[0]);
			trailer3LargeGame.add((LargeGame) group1[1]);
			trailer3LargeGame.add((LargeGame) group1[2]);
			trailer3LargeGame.add((LargeGame) group1[3]);
			trailer3LargeGame.add((LargeGame) group1[4]);
			trailer3LargeGame.add((LargeGame) group1[5]);

			LinkedHashSet<LargeGame> trailer4LargeGame = new LinkedHashSet<LargeGame>();
			trailer4LargeGame.add((LargeGame) group2[0]);
			trailer4LargeGame.add((LargeGame) group2[1]);

			Coupling trailer3Coupling1 = new Coupling(trailer3LargeGame);
			Coupling trailer3Coupling2 = new Coupling(new LinkedHashSet<LargeGame>());

			Coupling transporter2Coupling1 = new Coupling(new LinkedHashSet<LargeGame>());

			WaterTank trailer3waterTank1 = new WaterTank();
			WaterTank trailer3waterTank2 = new WaterTank();
			WaterLandTank trailer3waterLandTank1 = new WaterLandTank();
			WaterLandTank trailer3waterLandTank2 = new WaterLandTank();

			LinkedHashSet<Loadable> trailer3Loadable = new LinkedHashSet<Loadable>();
			trailer3Loadable.add(trailer3waterTank1);
			trailer3Loadable.add(trailer3waterTank2);
			trailer3Loadable.add(trailer3waterLandTank1);
			trailer3Loadable.add(trailer3waterLandTank2);
			trailer3Loadable.add(trailer3Coupling1);
			trailer3Loadable.add(trailer3Coupling2);

			LinkedHashSet<Loadable> trailer4Loadable = new LinkedHashSet<Loadable>();
			trailer4Loadable.add(new Coupling(trailer4LargeGame));

			LinkedHashSet<Loadable> transporter2Loadable = new LinkedHashSet<Loadable>();
			transporter2Loadable.add(transporter2Coupling1);
			transporter2Loadable.add(new DwarfTortoiseTerrarium());
			transporter2Loadable.add(new GiantTortoiseTerrarium());
			transporter2Loadable.add(new GiantTortoiseTerrarium());
			transporter2Loadable.add(new MambaTerrarium());
			transporter2Loadable.add(new PythonTerrarium());
			transporter2Loadable.add(new PythonTerrarium());

			Trailer trailer3 = new Trailer(trailer3Loadable);
			test("Loading Seal into Trailer3", true, trailer3.load(group1[7]));
			test("Loading Seal into Trailer3", true, trailer3.load(group1[8]));
			test("Loading Dolphin into Trailer3", true, trailer3.load(group1[6]));
			test("Loading Swordfish into Trailer3", true, trailer3.load(group1[9]));
			test("Checking output of list on Trailer3", true, checkList(group1,
					trailer3.list()));

			Trailer trailer4 = new Trailer(trailer4Loadable);
			test("Coupling Trailer4 to Trailer3", true, trailer3.load(trailer4));
			test("Checking output of list on Trailer3", true,
					checkList(combine(group1,group2), trailer3.list()));

			Transporter transporter2 = new Transporter(transporter2Loadable);
			test("Loading GiantTortoise into Transporter2", true,
					transporter2.load(group3[2]));
			test("Loading DwarfTortoise into Transporter2", true,
					transporter2.load(group3[0]));
			test("Loading DwarfTortoise into Transporter2", true,
					transporter2.load(group3[1]));
			test("Loading Python into Transporter2", true, transporter2.load(group3[5]));
			test("Loading Mamba into Transporter2", true, transporter2.load(group3[3]));
			test("Loading Mamba into Transporter2", true, transporter2.load(group3[4]));
			test("Checking output of list on Transporter2", true, checkList(group3,
					transporter2.list()));

			test("Coupling Trailer3 to Transporter2", true, transporter2.load(trailer3));
			test("Checking output of list on Transporter2", true,
					checkList(combine(group1,combine(group2,group3)), transporter2.list()));

			test("Adding another Trailer to Trailer3", true, trailer3.load(new Trailer(
					new LinkedHashSet<Loadable>())));
			test("Checking output of list on Trailer3", true,
					checkList(combine(group1,group2), trailer3.list()));

			test("Adding another Trailer to Trailer3 (no free Coupling)", false,
					trailer3.load(new Trailer(new LinkedHashSet<Loadable>())));

			test("Removing Trailer4 from Trailer3", trailer4,
					trailer3.unload(trailer3Coupling1));
			test("Checking output of list on Trailer3", true, checkList(group1,
					trailer3.list()));
			test("Checking output of list on Transporter2", true,
					checkList(combine(group1,group3), transporter2.list()));

			test("Removing Trailer (from empty Coupling) from Trailer3", null,
					trailer3.unload(trailer3Coupling1));

			test("Adding another Trailer (to now free Coupling) on Trailer3", true,
					trailer3.load(new Trailer(new LinkedHashSet<Loadable>())));

			test("Removing Trailer3 from Transporter2", trailer3,
					transporter2.unload(transporter2Coupling1));
			test("Checking output of list on Transporter2", true, checkList(group3,
					transporter2.list()));
			test("Checking output of list on Trailer3", true, checkList(group1,
					trailer3.list()));

			test("Removing Trailer (from empty Coupling) from Transporter2", null,
					transporter2.unload(transporter2Coupling1));

			test("Coupling Trailer4 to Transporter2", true, transporter2.load(trailer4));
			test("Checking output of list on Transporter2", true,
					checkList(combine(group2, group3),transporter2.list()));
			test("Coupling Trailer3 to Trailer4", true, trailer4.load(trailer3));
			test("Checking output of list on Transporter2", true,
					checkList(combine(combine(group1,group2), group3),transporter2.list()));

			test("Unloading Seal from Trailer3", group1[7],
					trailer3.unload(trailer3waterLandTank1));
			test("Unloading Seal from Trailer3", group1[8],
					trailer3.unload(trailer3waterLandTank2));
			test("Unloading Dolphin from Trailer3", group1[6],
					trailer3.unload(trailer3waterTank1));
			test("Unloading Swordfish from Trailer3", group1[9],
					trailer3.unload(trailer3waterTank2));
			test("Checking output of list on Trailer3", true, checkList(group1a,
					trailer3.list()));
			test("Checking output of list on Transporter2", true,
					checkList(combine(combine(group1a,group2), group3),transporter2.list()));

			printSummary();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.printf(
				"%n%nUnexpected exception thrown after %d tests; testing aborted!%n",
				testCount);
		}
	}

	private static Animal[] combine(Animal[] a, Animal[] b) {
		Animal[] c = new Animal[a.length+b.length];

		int j = 0;
		for (int i=0; i < a.length; i++) {
			c[j++] = a[i];
		}
		for (int i=0; i < b.length; i++) {
			c[j++] = b[i];
		}

		return c;
	}

	private static boolean checkList(Animal[] a, List<String> l) {
		if (l.size() != a.length) {
			return false;
		}

		for (Animal i : a) {
			if (!l.contains(i.getName())) {
				return false;
			}
		}

		return true;
	}

	private static void loadUnloadTestGeneric(TransportObject o, Loadable l,
			boolean shouldWork, Class<? extends TransportObject> to) {
		String asWhat = to.getName();
		String transportObjectType = o.getClass().getName();
		String loadableType = l.getClass().getName();
		String expectedResult = (shouldWork ? "ok" : "not ok");

		LinkedHashSet<Loadable> x = new LinkedHashSet<Loadable>();
		x.add(l);

		Transporter t = new Transporter(x);

		test(String.format("Loading %s into %s as %s (%s).", transportObjectType,
					loadableType, asWhat, expectedResult), shouldWork, t.load(to.cast(o)));

		if (o instanceof Animal) {
			if (shouldWork) {
				test("Checking if animal is in list.", true, t.list().size()==1 &&
						t.list().contains(((Animal) o).getName()));
			} else {
				test("Checking if list of loaded animals is empty.", true,
						t.list().isEmpty());
			}
		}

		if (shouldWork) {
			test(String.format("Loading %s into %s as %s (again).", transportObjectType,
					loadableType, asWhat), !shouldWork, t.load(to.cast(o)));
		}

		test(String.format("Unloading %s from %s (%s).", transportObjectType,
				loadableType, expectedResult), (shouldWork?o:null), t.unload(l));
		test("Checking if list of loaded animals is empty (after unload).", true,
				t.list().isEmpty());

		if (shouldWork) {
			test(String.format("Loading %s into %s as %s (after unload).", transportObjectType,
						loadableType, asWhat), shouldWork, t.load(to.cast(o)));
			t.unload(l);
		}

	}

	private static void loadUnloadTest(TransportObject o, Loadable l, boolean shouldWork) {
		loadUnloadTestGeneric(o, l, shouldWork, TransportObject.class);

		if (o instanceof Animal) {
			loadUnloadTestAnimal((Animal) o, l, shouldWork);
		} else {
			loadUnloadTestTrailer((Trailer) o, l, shouldWork);
		}
	}

	private static void loadUnloadTestAnimal(Animal o, Loadable l, boolean shouldWork) {
		loadUnloadTestGeneric(o, l, shouldWork, Animal.class);

		if (o instanceof LargeGame) {
			loadUnloadTestLargeGame((LargeGame) o, l, shouldWork);
		} else if (o instanceof Reptile) {
			loadUnloadTestReptile((Reptile) o, l, shouldWork);
		} else {
			loadUnloadTestSeaAnimal((SeaAnimal) o, l, shouldWork);
		}
	}

	private static void loadUnloadTestLargeGame(LargeGame o, Loadable l, boolean shouldWork) {
		loadUnloadTestGeneric(o, l, shouldWork, LargeGame.class);

		loadUnloadTestGeneric(o, l, shouldWork, o.getClass());
	}

	private static void loadUnloadTestReptile(Reptile o, Loadable l, boolean shouldWork) {
		loadUnloadTestGeneric(o, l, shouldWork, Reptile.class);

		loadUnloadTestGeneric(o, l, shouldWork, o.getClass());
	}

	private static void loadUnloadTestSeaAnimal(SeaAnimal o, Loadable l, boolean shouldWork) {
		loadUnloadTestGeneric(o, l, shouldWork, SeaAnimal.class);

		loadUnloadTestGeneric(o, l, shouldWork, o.getClass());
	}

	private static void loadUnloadTestTrailer(Trailer o, Loadable l, boolean shouldWork) {
		loadUnloadTestGeneric(o, l, shouldWork, Trailer.class);
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
		result = result && succeeded;
	}

	private static void printSummary() {
		System.out.printf("%nFinal result: %s (%d passed, %d failed)%n%n",
				labels.get(result), testCount - failedCount, failedCount);
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
