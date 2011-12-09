public class WaterLandTank extends Tank {
	public boolean loadLoadable(Seal o) {
		if (getLoad() != null) {
			return false;
		}
		setLoad(o);
		return true;
	}
	public boolean loadLoadable(Dolphin o) {
		if (getLoad() != null) {
			return false;
		}
		setLoad(o);
		return true;
	}	
	public boolean loadLoadable(Swordfish o) {
		if (getLoad() != null) {
			return false;
		}
		setLoad(o);
		return true;
	}
}

/* vim: set noet ts=4 sw=4: */
