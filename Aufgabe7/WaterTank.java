public class WaterTank extends Tank {
	public boolean loadDolphin(Dolphin o) {
		if (getLoad() != null) {
			return false;
		}
		setLoad(o);
		return true;
	}	
	public boolean loadSwordfish(Swordfish o) {
		if (getLoad() != null) {
			return false;
		}
		setLoad(o);
		return true;
	}
}

/* vim: set noet ts=4 sw=4: */
