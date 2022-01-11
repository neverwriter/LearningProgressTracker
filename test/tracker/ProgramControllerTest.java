package tracker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import tracker.student.StudentRepository;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ProgramControllerTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void itShouldPrintNoInput() {
        //Given
        String command = "";
        ProgramController programController = new ProgramController();

        //When
        programController.defineStep(command);

        //Then
        assertEquals("No input.", outputStreamCaptor.toString()
                .trim());

    }

    @Test
    public void itShouldUnknownCommand() {
        //Given
        String command = "quit";
        ProgramController programController = new ProgramController();

        //When
        programController.defineStep(command);

        //Then
        assertEquals("Error: unknown command!", outputStreamCaptor.toString()
                .trim());

    }

    @Test
    public void itShouldEndProgram() {
        //Given
        String command = "Exit";
        ProgramController programController = new ProgramController();

        //When
        programController.defineStep(command);

        //Then
        assertEquals("Bye!", outputStreamCaptor.toString()
                .trim());
        assertFalse(programController.isRunProgram());

    }

    @ParameterizedTest
    @CsvSource({"John Melvin van Graf jmelvin@gmail.com", "Anna Moore amoore@gmail.com"})
    public void itShouldAddStudent(String studentCredential){
        //Given
        ProgramController programController = new ProgramController();
        //When
        programController.addStudent(studentCredential);

        StudentRepository.printStudentList();
    }
}