public class GiantTortoiseTerrarium extends Terrarium {
	public boolean loadGiantTortoise(GiantTortoise o) {
		if (getLoad() != null) {
			return false;
		}
		setLoad(o);
		return true;
	}
	public boolean loadDwarfTortoise(DwarfTortoise o) {
		if (getLoad() != null) {
			return false;
		}
		setLoad(o);
		return true;
	}
}

/* vim: set noet ts=4 sw=4: */
