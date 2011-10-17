import java.util.HashMap;
import java.lang.StringBuilder;
import java.lang.NullPointerException;

public class LVAManager {
	private final HashMap<String, LVA> lvaList = new HashMap<String, LVA>();

	/**
	 * @return A string describing all LVAs.
	 */
	public String getLVAList() {
		StringBuilder sb = new StringBuilder();
		for (LVA lva : lvaList.values()) {
			sb.append(lva.toString());
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * Adds a new LVA record.
	 * @param lva The new LVA
	 * @return false if the LVA already exists, true on success
	 */
	public boolean addLVA(LVA lva) {
		if (lvaList.containsKey(lva.getNr())) {
			return false;
		}

		lvaList.put(lva.getNr(), lva);

		return true;
	}

	/**
	 * Gets a list of all students enrolled in an LVA
	 * @param lvaNr The LVA number
	 * @return null if the LVA doesn't exist; the description on success
	 */
	public String getStudents(String lvaNr) {

		if (lvaNr == null || lvaNr.trim().equals("")) {
			throw new NullPointerException();
		}

		if (!lvaList.containsKey(lvaNr)) {
			return null;
		}
		LVA lva = lvaList.get(lvaNr);
		StringBuilder sb = new StringBuilder();
		for (Student student : lva.getStudents()) {
			sb.append(student.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
}
