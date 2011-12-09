public class GiantTortoiseTerrarium extends Terrarium {
	
	/*
	 * Loads the passed GiantTortoise into this Loadable
	 * (o != null)
	 */
	public boolean loadLoadable(GiantTortoise o) {
		if (getLoad() != null) {
			return false;
		}
		setLoad(o);
		return true;
	}
	
	/*
	 * Loads the passed DwarfTortoise into this Loadable
	 * DwarfTortoise can be loaded into this Loadable as well
	 * (o != null)
	 */
	public boolean loadLoadable(DwarfTortoise o) {
		if (getLoad() != null) {
			return false;
		}
		setLoad(o);
		return true;
	}
}

/* vim: set noet ts=4 sw=4: */
