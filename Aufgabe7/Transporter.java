import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Transporter implements Loadable {
	
	private final Coupling coupling;
	
	/* TODO move all this stuff to one collection of Loadable */
	private final GiantTortoiseTerrarium gtt = new GiantTortoiseTerrarium();
	private final PythonTerrarium pt = new PythonTerrarium();
	private final WaterLandTank wlt = new WaterLandTank();
	private final WaterTank wt = new WaterTank();
	
	public Transporter(Set<LargeGame> tiedBigGame) {
		coupling = new Coupling(tiedBigGame);
	}
	
	/* Loads the TransportObject onto the transporter.
	 * Returns true if loading was successful, and false otherwise. */
	public boolean load(TransportObject to) {
		return to.loadObject(this);
	}
	
	/* Unloads the specified Loadable object and returns
	 * the object previously contained by it. */
	public TransportObject unload(Loadable l) {
		return l.unloadObject(this);
	}
	
	public List<String> list() {
		List<String> l = new LinkedList<String>();
		l.addAll(coupling.list());
		l.addAll(gtt.list());
		l.addAll(pt.list());
		l.addAll(wlt.list());
		l.addAll(wt.list());
		return l;
	}
	
	public boolean loadDwarfTortoise(DwarfTortoise o) {
		return gtt.loadDwarfTortoise(o);
		
		/* concept:
		 * 
		 * i = loadables.first;
		 * while (!i.load(o)) {
		 * 		i = loadables.next;
		 * }
		 * return (i != null);
		 */
	}
	
	public boolean loadGiantTortoise(GiantTortoise o) {
		return gtt.loadGiantTortoise(o);
	}
	
	public boolean loadMamba(Mamba o) {
		return pt.loadMamba(o);
	}
	
	public boolean loadPython(Python o) {
		return pt.loadPython(o);
	}
	
	public boolean loadDolphin(Dolphin o) {
		/* TODO check both WaterLand and WaterTanks */
		return wt.loadDolphin(o);
	}
	
	public boolean loadSeal(Seal o) {
		return wlt.loadSeal(o);		
	}
	
	public boolean loadSwordfish(Swordfish o) {
		/* TODO check both WaterLand and WaterTanks */
		return wt.loadSwordfish(o);		
	}
	
	public boolean loadTrailer(Trailer o) {
		return coupling.connectTrailer(o);
	}

	public TransportObject unloadObject(Transporter t) {
		return coupling.unloadObject(t);
	}
}

/* vim: set noet ts=4 sw=4: */