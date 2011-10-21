import java.util.Date;

public interface Enrollable {
	public String getTitle();
	public Date getEnrollFrom();
	public Date getEnrollTo();
	public Date getUnenrollTo();

	public boolean enroll(Student s);
	public boolean unenroll(Student s);
}
