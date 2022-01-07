package tracker;

import tracker.course.CourseRepository;


import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;


public class StatisticService {
    final static String NOT_APPLICABLE = "n/a";
    final static int JAVA_POINTS = 600;
    final static int DSA_POINTS = 400;
    final static int DATABASES_POINTS = 480;
    final static int SPRING_POINTS = 550;

    public static void basicDetailsPrinter() {

        System.out.printf("Most popular: %s\n", defineMostPopular());
        System.out.printf("Least popular: %s\n", defineLeastPopular());
        System.out.printf("Highest activity: %s\n", NOT_APPLICABLE);
        System.out.printf("Lowest activity: %s\n", NOT_APPLICABLE);
        System.out.printf("Easiest course: %s\n", NOT_APPLICABLE);
        System.out.printf("Hardest course: %s\n", NOT_APPLICABLE);

        printInfoAboutTopLearners(CoursesNames.JAVA.getCourseName(), JAVA_POINTS);
        printInfoAboutTopLearners(CoursesNames.DSA.getCourseName(), DSA_POINTS);
        printInfoAboutTopLearners(CoursesNames.DATABASES.getCourseName(), DATABASES_POINTS);
        printInfoAboutTopLearners(CoursesNames.SPRING.getCourseName(), SPRING_POINTS);

    }

    private static String defineMostPopular() {

        Map<String, Integer> courseMap = new LinkedHashMap<>();

        fillCourseMap(courseMap);

        int value = courseMap
                .entrySet()
                .stream()
                .max((entry1, entry2) ->
                        entry1.getValue() > entry2.getValue() ? 1 : -1)
                .get().getValue();

        return buildResultString(value, courseMap);

    }

    private static String defineLeastPopular() {

        Map<String, Integer> courseMap = new LinkedHashMap<>();

        fillCourseMap(courseMap);

        int value = courseMap
                .entrySet()
                .stream()
                .max((entry1, entry2) ->
                        entry1.getValue() > entry2.getValue() ? -1 : 1)
                .get().getValue();

        return buildResultString(value, courseMap);
    }

    private static void fillCourseMap(Map<String, Integer> map) {
        map.put(CoursesNames.JAVA.getCourseName(),
                CourseRepository.getCourseByName(CoursesNames.JAVA.getCourseName())
                        .getStudentsWithScore().size());
        map.put(CoursesNames.DSA.getCourseName(),
                CourseRepository.getCourseByName(CoursesNames.DSA.getCourseName())
                        .getStudentsWithScore().size());
        map.put(CoursesNames.DATABASES.getCourseName(),
                CourseRepository.getCourseByName(CoursesNames.DATABASES.getCourseName())
                        .getStudentsWithScore().size());
        map.put(CoursesNames.SPRING.getCourseName(),
                CourseRepository.getCourseByName(CoursesNames.SPRING.getCourseName())
                        .getStudentsWithScore().size());
    }

    private static String buildResultString(int value, Map<String, Integer> map) {

        StringBuilder stringBuilder = new StringBuilder();

        map
                .entrySet()
                .stream()
                .filter(entry1 -> entry1.getValue() == value)
                .forEach((s1 -> stringBuilder.append(s1.getKey()).append(" ")));

        return stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString().replace(" ", ", ");
    }

    public static void printInfoAboutTopLearners(String courseName, int baseCoursePoints) {

        System.out.println(courseName);
        System.out.println("id     points  completed");

        HashMap<Integer, Integer> studentsWithFullScore = new HashMap<>();

        CourseRepository
                .getCourseByName(courseName)
                .getStudentsWithScore()
                .forEach((integer, integers) -> studentsWithFullScore
                        .put(integer, integers
                        .stream()
                        .mapToInt( s -> s)
                        .sum()));

//        HashMap<Integer, Integer> students = new HashMap<>();
//
//        students = studentsWithFullScore.entrySet().stream().sorted().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));
//
//        students.forEach((key, value) -> System.out.printf(Locale.US,
//                "%d  %-8d %.1f%%\n",
//                key,
//                value,
//                (double) value * 100 / baseCoursePoints));

        studentsWithFullScore.forEach((key, value) -> System.out.printf(Locale.US,
                "%d  %-8d %.1f%%\n",
                key,
                value,
                (double) value * 100 / baseCoursePoints));

 studentsWithFullScore.clear();
    }

    public static int[] getPoints(int studentId){
        int[] points = new int[4];

        points[0] = countStudentPoints(CoursesNames.JAVA.getCourseName(), studentId);
        points[1] = countStudentPoints(CoursesNames.DSA.getCourseName(), studentId);
        points[2] = countStudentPoints(CoursesNames.DATABASES.getCourseName(), studentId);
        points[3] = countStudentPoints(CoursesNames.SPRING.getCourseName(), studentId);

        return points;
    }

    public static int countStudentPoints(String courseName, int studentId){

        if(CourseRepository.getCourseByName(courseName).isStudentEnrolled(studentId)) {
            int points = CourseRepository
                    .getCourseByName(courseName)
                    .getStudentsWithScore()
                    .get(studentId)
                    .stream().mapToInt(s -> s).sum();

            return points;
        } else {
            return 0;
        }

    }
}
