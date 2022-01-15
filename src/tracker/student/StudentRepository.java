package tracker.student;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

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

    public static String getStudentEmailById(int studentId){
    AtomicReference<String> email = new AtomicReference<>();
        studentRepository.forEach(student -> {
            if(student.getId() == studentId){
                email.set(student.getEmail());
            }
        });

        return email.toString();
    }

    public static String getStudentFirstNameById(int studentId){
        AtomicReference<String> name = new AtomicReference<>();
        studentRepository.forEach(student -> {
            if(student.getId() == studentId){
                name.set(student.getFirstName());
            }
        });

        return name.toString();
    }

    public static String getStudentLastNameById(int studentId){
        AtomicReference<String> name = new AtomicReference<>();
        studentRepository.forEach(student -> {
            if(student.getId() == studentId){
                name.set(student.getLastName());
            }
        });

        return name.toString();
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
