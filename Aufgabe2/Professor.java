/**
 * A Professor is a Person and can be employed.
 */
public class Professor extends Person implements Employable {
	
	private String accountNr;
	
	public Professor(String name, String accountNr) {
		super(name);
		setAccountNr(accountNr);
	}
	
	public void setAccountNr(String accountNr) {
		this.accountNr = Util.validateString(accountNr);
	}

	public String getAccountNr() {
		return accountNr;		
	}
}
/* vim: set noet ts=4 sw=4: */
