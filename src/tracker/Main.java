package tracker;

import tracker.course.Course;
import tracker.course.CourseRepository;

public class Main {
    public static void main(String[] args) {

        courseInitialization();

        ProgramController programController = new ProgramController();

        printStartMessage();

        while (programController.isRunProgram()) {

            programController.defineStep(CommandReader.readCommand());

        }
    }

    private static void printStartMessage(){
        System.out.println("Learning Progress Tracker");
    }

    private static void courseInitialization(){
        CourseRepository.addCourse(new Course(CoursesNames.JAVA.getCourseName()));
        CourseRepository.addCourse(new Course(CoursesNames.DSA.getCourseName()));
        CourseRepository.addCourse(new Course(CoursesNames.DATABASES.getCourseName()));
        CourseRepository.addCourse(new Course(CoursesNames.SPRING.getCourseName()));
    }
}
