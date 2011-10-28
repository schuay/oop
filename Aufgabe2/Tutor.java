public class Tutor extends Student implements Employable {
	
	private String accountNr;
	
	public Tutor(String matrNr, String name, String accountNr) {
		super(matrNr, name);
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
