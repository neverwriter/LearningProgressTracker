package tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tracker.student.Student;
import tracker.student.StudentRepository;

import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryTest {

    @BeforeEach
    void createRepository() {

    }

    @Test
    void itShouldIsIdPresent() {
        //Given
        Student student = new Student("John", "Melvin", "jmelvin@gmail.com");
        StudentRepository.addStudent(student);
        //When

        System.out.println(StudentRepository.getSize());

        System.out.println(student.toString());

        //Then
        assertTrue(StudentRepository.isIdPresent(student.getId()));
    }

    @Test
    void itShouldAddStudent() {
        //Given
        //When
        //Then

    }

    @Test
    void itShouldGetSize() {
        //Given
        //When
        //Then

    }
}