package tracker.course;

import tracker.CoursesNames;

public class CourseService {

    public static void updatePoints(int studentId, int[] points){

        if(points[0] != 0){
            updatePointsForCourse(CoursesNames.JAVA.getCourseName(), studentId, points[0]);
        }

        if(points[1] != 0){
            updatePointsForCourse(CoursesNames.DSA.getCourseName(), studentId, points[1]);
        }

        if(points[2] != 0){
            updatePointsForCourse(CoursesNames.DATABASES.getCourseName(), studentId, points[2]);
        }

        if(points[3] != 0){
            updatePointsForCourse(CoursesNames.SPRING.getCourseName(), studentId, points[3]);
        }

    }

    private static void updatePointsForCourse(String courseName, int studentId, int score){
        if(CourseRepository.getCourseByName(courseName).isStudentEnrolled(studentId)){
            CourseRepository.getCourseByName(courseName).addStudentScore(studentId, score);
        } else {
            CourseRepository.getCourseByName(courseName).addNewStudentToCourse(studentId, score);
        }
    }
}
