public class DwarfTortoiseTerrarium extends Terrarium {
	public boolean loadLoadable(DwarfTortoise o) {
		if (getLoad() != null) {
			return false;
		}
		setLoad(o);
		return true;
	}
}

/* vim: set noet ts=4 sw=4: */
