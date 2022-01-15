package tracker.course;

import tracker.CoursesNames;
import tracker.StatisticService;
import tracker.student.Student;
import tracker.student.StudentRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseService {

    final static int JAVA_POINTS = 600;
    final static int DSA_POINTS = 400;
    final static int DATABASES_POINTS = 480;
    final static int SPRING_POINTS = 550;

    public static void updatePoints(int studentId, int[] points) {

        if (points[0] != 0) {
            updatePointsForCourse(CoursesNames.JAVA.getCourseName(), studentId, points[0]);
        }

        if (points[1] != 0) {
            updatePointsForCourse(CoursesNames.DSA.getCourseName(), studentId, points[1]);
        }

        if (points[2] != 0) {
            updatePointsForCourse(CoursesNames.DATABASES.getCourseName(), studentId, points[2]);
        }

        if (points[3] != 0) {
            updatePointsForCourse(CoursesNames.SPRING.getCourseName(), studentId, points[3]);
        }

    }

    private static void updatePointsForCourse(String courseName, int studentId, int score) {
        if (CourseRepository.getCourseByName(courseName).isStudentEnrolled(studentId)) {
            CourseRepository.getCourseByName(courseName).addStudentScore(studentId, score);
        } else {
            CourseRepository.getCourseByName(courseName).addNewStudentToCourse(studentId, score);
        }
    }

    public static Map<Integer, List<String>> defineCompletedCourses() {

        Map<Integer, List<String>> completedCourses = new HashMap<>();
        List<Student> studentList = new ArrayList<>();

        studentList = StudentRepository.getStudentRepository();

        studentList.forEach(student -> {
            int[] points = new int[4];
            points = StatisticService.getPoints(student.getId());

            List<String> completedCoursesList = new ArrayList<>();

            if (points[0] >= JAVA_POINTS) {
                completedCoursesList.add(CoursesNames.JAVA.getCourseName());
            }

            if (points[1] >= DSA_POINTS) {
                completedCoursesList.add(CoursesNames.DSA.getCourseName());
            }

            if (points[2] >= DATABASES_POINTS) {
                completedCoursesList.add(CoursesNames.DATABASES.getCourseName());
            }

            if (points[3] >= SPRING_POINTS) {
                completedCoursesList.add(CoursesNames.SPRING.getCourseName());
            }

            if (!completedCoursesList.isEmpty()) {
                completedCourses.put(student.getId(), completedCoursesList);
            }
        });

        return completedCourses;


    }
}
