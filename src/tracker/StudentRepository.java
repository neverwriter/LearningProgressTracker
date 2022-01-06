package tracker;

import java.util.ArrayList;

public class StudentRepository {

    private static ArrayList<Student> studentRepository = new ArrayList<>();

    public static ArrayList<Student> getStudentRepository() {
        return studentRepository;
    }

    public static boolean isIdPresent(int id) {

        return studentRepository.stream().anyMatch(student ->
                student.getId() == id);

    }

    public static boolean isEmailTaken(String email) {

        return studentRepository.stream().anyMatch(student ->
                student.getEmail().equals(email));
    }

    public static void addStudent(Student student) {

        studentRepository.add(student);

    }

    public static int getSize() {

        return studentRepository.size();

    }

    public static int[] getPoints(int id) {

        int[] points = new int[4];

        for (Student student : studentRepository) {

            if (student.getId() == id) {
                points[0] = student.getJavaPoints();
                points[1] = student.getDsaPoints();
                points[2] = student.getDbPoints();
                points[3] = student.getSpringPoints();
            }
        }
        return points;

    }

    public static void updatePoints(int id, int[] points) {

        for (Student student : studentRepository) {

            if (student.getId() == id) {
                student.setJavaPoints(student.getJavaPoints() + points[0]);
                student.setDsaPoints(student.getDsaPoints() + points[1]);
                student.setDbPoints(student.getDbPoints() + points[2]);
                student.setSpringPoints(student.getSpringPoints() + points[3]);
            }
        }

    }

    public static void printStudentList() {

        if (studentRepository.isEmpty()) {

            System.out.println("No students found.");

        } else {

            System.out.println("Students:");

            studentRepository.forEach(student -> System.out.println(student.getId()));
        }
    }

    public static int countEnrolledStudents(String curseName) {
        int counter = 0;

        if (curseName.equals("Java")) {
            counter = (int) studentRepository.stream().filter(student -> student.getJavaPoints() != 0).count();
        }
        if (curseName.equals("DSA")) {
            counter = (int) studentRepository.stream().filter(student -> student.getDsaPoints() != 0).count();
        }
        if (curseName.equals("Databases")) {
            counter = (int) studentRepository.stream().filter(student -> student.getDbPoints() != 0).count();
        }
        if (curseName.equals("Spring")) {
            counter = (int) studentRepository.stream().filter(student -> student.getSpringPoints() != 0).count();
        }

        return counter;
    }

}
