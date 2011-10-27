import java.util.HashMap;
import java.util.Map;

public class CourseManager {
	private final HashMap<String, Course> courseList = new HashMap<String, Course>();

	/**
	 * @return A string describing all Courses.
	 */
	public String getCourseList() {
		StringBuilder sb = new StringBuilder();
		for (Course lva : getVisibleCourses().values()) {
			sb.append(lva.toString());
			sb.append("\n");
		}
		return sb.toString();
	}

	/* TODO: define some way to retrieve invisible courses */
	private HashMap<String, Course> getVisibleCourses() {
		HashMap<String, Course> result = new HashMap<String, Course>();
		for (Map.Entry<String, Course> e : courseList.entrySet()) {
			if (!e.getValue().getVisible()) {
				continue;
			}
			result.put(e.getKey(), e.getValue());
		}
		return result;
	}

	/**
	 * Adds a new Course record.
	 * @param lva The new Course
	 * @return false if the Course already exists, true on success
	 */
	public boolean addCourse(Course lva) {
		if (courseList.containsKey(lva.getNr())) {
			return false;
		}

		courseList.put(lva.getNr(), lva);

		return true;
	}
	
	public boolean cancelCourse(Course lva) {
		if (!courseList.containsKey(lva.getNr())) {
			return false;
		}
		
		courseList.remove(lva.getNr());
		lva.cancel();
		
		return true;
	}

	/**
	 * Gets a list of all students enrolled in an Course
	 * @param lvaNr The Course number
	 * @return null if the Course doesn't exist; the description on success
	 */
	public String getStudents(String lvaNr) {

		Util.validateString(lvaNr);

		if (!getVisibleCourses().containsKey(lvaNr)) {
			return null;
		}
		Course lva = courseList.get(lvaNr);
		StringBuilder sb = new StringBuilder();
		for (Student student : lva.getStudents()) {
			sb.append(student.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
}
