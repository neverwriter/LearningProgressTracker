package tracker.student;

import tracker.student.Student;

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

    public static void printStudentList() {

        if (studentRepository.isEmpty()) {

            System.out.println("No students found.");

        } else {

            System.out.println("Students:");

            studentRepository.forEach(student -> System.out.println(student.getId()));
        }
    }

}
