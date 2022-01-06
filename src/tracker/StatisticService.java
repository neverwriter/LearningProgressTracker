package tracker;

import java.math.RoundingMode;
import java.util.*;


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

        printInfoAboutTopLearners("Java");
        printInfoAboutTopLearners("DSA");
        printInfoAboutTopLearners("Databases");
        printInfoAboutTopLearners("Spring");

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
        map.put("Java", StudentRepository.countEnrolledStudents("Java"));
        map.put("DSA", StudentRepository.countEnrolledStudents("DSA"));
        map.put("Databases", StudentRepository.countEnrolledStudents("Databases"));
        map.put("Spring", StudentRepository.countEnrolledStudents("Spring"));
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

    public static void printInfoAboutTopLearners(String courseName) {

        System.out.println(courseName);
        System.out.println("id     points  completed");

        ArrayList<Student> students = StudentRepository.getStudentRepository();

        if (courseName.equals("Java")) {
            students
                    .stream()
                    .sorted(Comparator.comparingInt(Student::getJavaPoints).reversed())
                    .filter(student -> student.getJavaPoints() != 0)
                    .forEach(student -> System.out.printf(Locale.US,
                            "%d  %-8d %.1f%%\n",
                            student.getId(),
                            student.getJavaPoints(),
                            (double) student.getJavaPoints() * 100 / JAVA_POINTS));
        }

        if (courseName.equals("DSA")) {
            students
                    .stream()
                    .sorted(Comparator.comparingInt(Student::getDsaPoints).reversed())
                    .filter(student -> student.getDsaPoints() != 0)
                    .forEach(student -> System.out.printf(Locale.US,
                            "%d  %-8d %.1f%%\n",
                            student.getId(),
                            student.getDsaPoints(),
                            (double) student.getDsaPoints() * 100 / JAVA_POINTS));
        }

        if (courseName.equals("Databases")) {
            students
                    .stream()
                    .sorted(Comparator.comparingInt(Student::getDbPoints).reversed())
                    .filter(student -> student.getDbPoints() != 0)
                    .forEach(student -> System.out.printf(Locale.US,
                            "%d  %-8d %.1f%%\n",
                            student.getId(),
                            student.getDbPoints(),
                            (double) student.getDbPoints() * 100 / JAVA_POINTS));
        }

        if (courseName.equals("Spring")) {
            students
                    .stream()
                    .sorted(Comparator.comparingInt(Student::getSpringPoints).reversed())
                    .filter(student -> student.getSpringPoints() != 0)
                    .forEach(student -> System.out.printf(Locale.US,
                            "%d  %-8d %.1f%%\n",
                            student.getId(),
                            student.getSpringPoints(),
                            (double) student.getSpringPoints() * 100 / JAVA_POINTS));
        }
    }
}
