public class GiantTortoiseTerrarium extends Terrarium {
	public boolean loadLoadable(GiantTortoise o) {
		if (getLoad() != null) {
			return false;
		}
		setLoad(o);
		return true;
	}
	public boolean loadLoadable(DwarfTortoise o) {
		if (getLoad() != null) {
			return false;
		}
		setLoad(o);
		return true;
	}
}

/* vim: set noet ts=4 sw=4: */
