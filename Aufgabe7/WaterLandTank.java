public class WaterLandTank extends Tank {
	
	/*
	 * Loads the passed Seal into this Loadable
	 * (o != null)
	 */
	public boolean loadLoadable(Seal o) {
		if (getLoad() != null) {
			return false;
		}
		setLoad(o);
		return true;
	}
	
	/*
	 * Loads the passed Dolphin into this Loadable
	 * (o != null)
	 */
	public boolean loadLoadable(Dolphin o) {
		if (getLoad() != null) {
			return false;
		}
		setLoad(o);
		return true;
	}	
	
	/*
	 * Loads the passed Swordfish into this Loadable
	 * (o != null)
	 */
	public boolean loadLoadable(Swordfish o) {
		if (getLoad() != null) {
			return false;
		}
		setLoad(o);
		return true;
	}
}

/* vim: set noet ts=4 sw=4: */
