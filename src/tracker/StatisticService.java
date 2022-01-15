package tracker;

import tracker.course.Course;
import tracker.course.CourseRepository;


import java.util.*;
import java.util.Map.Entry;


public class StatisticService {
    final static String NOT_APPLICABLE = "n/a";
    final static int JAVA_POINTS = 600;
    final static int DSA_POINTS = 400;
    final static int DATABASES_POINTS = 480;
    final static int SPRING_POINTS = 550;

    public static void basicDetailsPrinter() {

        System.out.printf("Most popular: %s\n", definePopular(true));
        System.out.printf("Least popular: %s\n", definePopular(false));
        System.out.printf("Highest activity: %s\n", defineActivity(true));
        System.out.printf("Lowest activity: %s\n", defineActivity(false));
        System.out.printf("Easiest course: %s\n", defineDifficulty(false));
        System.out.printf("Hardest course: %s\n", defineDifficulty(true));



    }

    private static String defineDifficulty(boolean isHardest) {

        Map<String, Integer> courseMap = new LinkedHashMap<>();
        Map<String, Integer> courseDifficultyMap = new LinkedHashMap<>();

        fillCourseMap(courseMap);

        if (checkIfCoursesNotEnrolled(courseMap)) {

            return NOT_APPLICABLE;

        } else {

            fillCourseDifficultyMap(courseDifficultyMap);

        }

        int value = 0;

        if (isHardest) {
            value = courseDifficultyMap
                    .entrySet()
                    .stream()
                    .min((entry1, entry2) ->
                            entry1.getValue() > entry2.getValue() ? 1 : -1)
                    .get().getValue();
        } else {
            value = courseDifficultyMap
                    .entrySet()
                    .stream()
                    .max((entry1, entry2) ->
                            entry1.getValue() > entry2.getValue() ? 1 : -1)
                    .get().getValue();
        }

        return buildResultString(value, courseDifficultyMap);
    }

    private static String defineActivity(boolean maxActivity) {

        Map<String, Integer> courseMap = new LinkedHashMap<>();
        Map<String, Integer> courseActivityMap = new LinkedHashMap<>();

        fillCourseMap(courseMap);

        if (checkIfCoursesNotEnrolled(courseMap)) {

            return NOT_APPLICABLE;

        } else {

            fillCourseActivityMap(courseActivityMap);

        }

        int value = 0;

        if (maxActivity) {
            value = courseActivityMap
                    .entrySet()
                    .stream()
                    .max((entry1, entry2) ->
                            entry1.getValue() > entry2.getValue() ? 1 : -1)
                    .get().getValue();
        } else {
            value = courseActivityMap
                    .entrySet()
                    .stream()
                    .min((entry1, entry2) ->
                            entry1.getValue() > entry2.getValue() ? 1 : -1)
                    .get().getValue();
        }
        return buildResultString(value, courseActivityMap);
    }

    private static String definePopular(boolean maxPopular) {

        Map<String, Integer> courseMap = new LinkedHashMap<>();

        fillCourseMap(courseMap);

        if (checkIfCoursesNotEnrolled(courseMap)) {

            return NOT_APPLICABLE;

        } else {

            int value = 0;

            if (maxPopular) {

                value = courseMap
                        .entrySet()
                        .stream()
                        .max((entry1, entry2) ->
                                entry1.getValue() > entry2.getValue() ? 1 : -1)
                        .get().getValue();
            } else {

                value = courseMap
                        .entrySet()
                        .stream()
                        .min((entry1, entry2) ->
                                entry1.getValue() > entry2.getValue() ? 1 : -1)
                        .get().getValue();
            }

            return buildResultString(value, courseMap);
        }
    }

    private static void fillCourseDifficultyMap(Map<String, Integer> map) {
        map.put(CoursesNames.JAVA.getCourseName(), countAverageGrade(CoursesNames.JAVA.getCourseName()));
        map.put(CoursesNames.DSA.getCourseName(), countAverageGrade(CoursesNames.DSA.getCourseName()));
        map.put(CoursesNames.DATABASES.getCourseName(), countAverageGrade(CoursesNames.DATABASES.getCourseName()));
        map.put(CoursesNames.SPRING.getCourseName(), countAverageGrade(CoursesNames.SPRING.getCourseName()));
    }

    private static int countAverageGrade(String courseName){
       Map<Integer, LinkedList<Integer>> list = new HashMap<>();

        list = CourseRepository.getCourseByName(courseName).getStudentsWithScore();

        ArrayList<LinkedList<Integer>> arrayList = new ArrayList<>();

        list.entrySet().stream().forEach(entry -> {
            arrayList.add(entry.getValue());
        });

        int gradesSum = arrayList.stream().flatMap(Collection::stream).mapToInt(grade -> grade).sum();

        int amountOfGrades = CourseRepository
                .getCourseByName(courseName)
                .getStudentsWithScore()
                .values()
                .stream()
                .mapToInt(LinkedList::size)
                .sum();

        return (gradesSum*1000)/amountOfGrades;

    }

    private static void fillCourseActivityMap(Map<String, Integer> map) {
        map.put(CoursesNames.JAVA.getCourseName(),
                CourseRepository
                        .getCourseByName(CoursesNames.JAVA.getCourseName())
                        .getStudentsWithScore()
                        .values()
                        .stream()
                        .mapToInt(LinkedList::size)
                        .sum());
        map.put(CoursesNames.DSA.getCourseName(),
                CourseRepository
                        .getCourseByName(CoursesNames.DSA.getCourseName())
                        .getStudentsWithScore()
                        .values()
                        .stream()
                        .mapToInt(LinkedList::size)
                        .sum());
        map.put(CoursesNames.DATABASES.getCourseName(),
                CourseRepository
                        .getCourseByName(CoursesNames.DATABASES.getCourseName())
                        .getStudentsWithScore()
                        .values()
                        .stream()
                        .mapToInt(LinkedList::size)
                        .sum());
        map.put(CoursesNames.SPRING.getCourseName(),
                CourseRepository
                        .getCourseByName(CoursesNames.SPRING.getCourseName())
                        .getStudentsWithScore()
                        .values()
                        .stream()
                        .mapToInt(LinkedList::size)
                        .sum());
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

    public static void printInfoAboutCourses(String command){

        if (command.equalsIgnoreCase(CoursesNames.JAVA.getCourseName())) {

            printInfoAboutTopLearners(CoursesNames.JAVA.getCourseName(), JAVA_POINTS);

        } else  if (command.equalsIgnoreCase(CoursesNames.DSA.getCourseName())) {

            printInfoAboutTopLearners(CoursesNames.DSA.getCourseName(), DSA_POINTS);

        } else  if (command.equalsIgnoreCase(CoursesNames.DATABASES.getCourseName())) {

            printInfoAboutTopLearners(CoursesNames.DATABASES.getCourseName(), DATABASES_POINTS);

        } else  if (command.equalsIgnoreCase(CoursesNames.SPRING.getCourseName())) {

            printInfoAboutTopLearners(CoursesNames.SPRING.getCourseName(), SPRING_POINTS);

        } else if (command.equalsIgnoreCase(Command.STATISTICS.getCommand())){

            printInfoAboutTopLearners(CoursesNames.JAVA.getCourseName(), JAVA_POINTS);

            printInfoAboutTopLearners(CoursesNames.DSA.getCourseName(), DSA_POINTS);

            printInfoAboutTopLearners(CoursesNames.DATABASES.getCourseName(), DATABASES_POINTS);

            printInfoAboutTopLearners(CoursesNames.SPRING.getCourseName(), SPRING_POINTS);

        }  else {
            System.out.println("Unknown course.");
        }


    }

    private static void printInfoAboutTopLearners(String courseName, int baseCoursePoints) {

        System.out.println(courseName);
        System.out.println("id     points  completed");

        HashMap<Integer, Integer> studentsWithFullScore = new HashMap<>();

        CourseRepository
                .getCourseByName(courseName)
                .getStudentsWithScore()
                .forEach((integer, integers) -> studentsWithFullScore
                        .put(integer, integers
                                .stream()
                                .mapToInt(s -> s)
                                .sum()));


        sortByComparator(studentsWithFullScore, false).forEach(
                (key, value) -> System.out.printf(Locale.US,
                        "%d  %-8d %.1f%%\n",
                        key,
                        value,
                        (double) value * 100 / baseCoursePoints));


        studentsWithFullScore.clear();
    }

    private static Map<Integer, Integer> sortByComparator(Map<Integer, Integer> unsortMap, final boolean order) {

        List<Entry<Integer, Integer>> list = new LinkedList<Entry<Integer, Integer>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Entry<Integer, Integer>>() {
            public int compare(Entry<Integer, Integer> o1,
                               Entry<Integer, Integer> o2) {
                if (order) {
                    return o1.getValue().compareTo(o2.getValue());
                } else {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
        for (Entry<Integer, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public static int[] getPoints(int studentId) {
        int[] points = new int[4];

        points[0] = countStudentPoints(CoursesNames.JAVA.getCourseName(), studentId);
        points[1] = countStudentPoints(CoursesNames.DSA.getCourseName(), studentId);
        points[2] = countStudentPoints(CoursesNames.DATABASES.getCourseName(), studentId);
        points[3] = countStudentPoints(CoursesNames.SPRING.getCourseName(), studentId);

        return points;
    }

    public static int countStudentPoints(String courseName, int studentId) {

        if (CourseRepository.getCourseByName(courseName).isStudentEnrolled(studentId)) {
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

    private static boolean checkIfCoursesNotEnrolled (Map<String, Integer> courseMap) {

        return courseMap.values().isEmpty();
    }
}
