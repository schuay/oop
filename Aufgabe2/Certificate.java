/**
 * The final grade for a Gradeable object.
 */

import java.util.Date;

public class Certificate extends Grade {
	private boolean passed;
	
	public Certificate(Gradeable forGradeable, String performance, Date dateofissue, boolean passed)
	{
		super(forGradeable, performance, dateofissue);
		setPassed(passed);
	}

	public boolean hasPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}
}
