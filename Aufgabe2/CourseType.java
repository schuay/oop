/**
 * CourseType is a general course while a Course itself takes place in
 * a specific semester. CourseTypes are used to resolve registration deps.
 * 
 * @author zaru
 *
 */
public class CourseType {
	private String courseName;
	
	public CourseType(String courseName)
	{
		setCourseName(courseName);
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
}
