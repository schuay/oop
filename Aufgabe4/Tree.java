public abstract class Tree {
	protected static final String leftStep = "left";
	protected static final String rightStep = "right";
	protected static final String separator = " ";
	protected static final String emptyString = "";
	protected static final String elementNotFound = "Knoten wurde nicht gefunden";

	abstract protected Node getRoot();

	/* return a string representation of the tree */
	public String toString() {
		if (getRoot() == null) {
			return emptyString;
		}
		return getRoot().toStringTree();
	}
}
