package tracker.course;

import java.util.HashSet;

public class CourseRepository {
    private final static HashSet<Course> courseRepository = new HashSet<>();

    public static void addCourse(Course course){
        courseRepository.add(course);
    }

    public static Course getCourseByName(String courseName){
        return courseRepository.stream().filter(course -> course.getCourseName().equals(courseName)).findAny().get();
    }
}
