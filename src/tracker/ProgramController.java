package tracker;


import tracker.course.CourseRepository;
import tracker.course.CourseRepositorySeeder;
import tracker.course.CourseService;
import tracker.student.Student;
import tracker.student.StudentCredentialsChecker;
import tracker.student.StudentRepository;
import tracker.student.StudentRepositorySeeder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProgramController {

    private boolean runProgram;

    public void defineStep(String command) {

        if (command.equalsIgnoreCase(Command.EXIT.getCommand())) {

            setRunProgram(false);
            System.out.println("Bye!");

        } else if (command.isBlank()) {

            System.out.println("No input.");

        } else if (command.equalsIgnoreCase(Command.ADD_STUDENTS.getCommand())) {

            System.out.println("Enter student credentials or 'back' to return:");
            String studentAddCommand = CommandReader.readCommand();
            int counter = 0;

            while (!studentAddCommand.equalsIgnoreCase(Command.BACK.getCommand())) {
                if (addStudent(studentAddCommand)) {
                    counter++;
                }

                studentAddCommand = CommandReader.readCommand();

            }
            ;

            System.out.printf("Total %d students have been added\n", counter);

        } else if (command.equalsIgnoreCase(Command.BACK.getCommand())) {

            System.out.println("Enter 'exit' to exit the program.");

        } else if (command.equalsIgnoreCase(Command.LIST.getCommand())) {

            StudentRepository.printStudentList();

        } else if (command.equalsIgnoreCase(Command.ADD_POINTS.getCommand())) {

            updateStudentPoints();

        }else if (command.equalsIgnoreCase(Command.STATISTICS.getCommand())) {

            deliverStatistics();

        } else if (command.equalsIgnoreCase(Command.FIND.getCommand())) {

            System.out.println("Enter an id or 'back' to return:");

            String studentFindCommand = CommandReader.readCommand();

            int number = 0;
            while (!studentFindCommand.equalsIgnoreCase(Command.BACK.getCommand())) {
                try {
                    number = Integer.parseInt(studentFindCommand);

                    studentPrintPoints(number);

                } catch (NumberFormatException ex) {
                    System.out.println("Incorrect id format");
                }

                studentFindCommand = CommandReader.readCommand();

            }

        } else if (command.equalsIgnoreCase(Command.SEED.getCommand())) {

            seedStudentRepository();

        } else {

            System.out.println("Error: unknown command!");

        }

    }

    public boolean addStudent(String studentCredential) {

        if (StudentCredentialsChecker.isCredentialsValid(studentCredential)) {

            String[] studentArray = createStudentArray(studentCredential);

            Student student = new Student(studentArray[0], studentArray[1], studentArray[2]);

            if (!StudentRepository.isEmailTaken(student.getEmail())) {

                StudentRepository.addStudent(student);
                System.out.println("The student has been added.");
                return true;

            } else {
                System.out.println("This email is already taken.");
                return false;
            }

        } else return false;

    }

    public void studentPrintPoints(int id) {

        if (!StudentRepository.isIdPresent(id)) {

            System.out.printf("No student is found for id=%d.\n", id);

        } else {

            int[] points = StatisticService.getPoints(id);

            System.out.printf("%d points: Java=%d; DSA=%d; Databases=%d; Spring=%d\n", id, points[0], points[1], points[2], points[3]);

        }


    }

    private String[] createStudentArray(String studentCredential) {

        List<String> studentCredentialList = new ArrayList<>(Arrays.asList(studentCredential.split(" ")));

        String[] studentArray = new String[3];

        studentArray[0] = studentCredentialList.get(0);

        studentArray[1] = "";

        for (int i = 1; i < studentCredentialList.size() - 1; i++) {

            studentArray[1] = studentArray[1].concat(studentCredentialList.get(i).concat(" "));

        }

        studentArray[2] = studentCredentialList.get(studentCredentialList.size() - 1);

        return studentArray;
    }

    private void seedStudentRepository() {

        StudentRepositorySeeder.fillWithStudents();

        CourseRepositorySeeder.fillWithPoints();

    }

    private void deliverStatistics() {

        System.out.println("Type the name of a course to see details or 'back' to quit:");

        StatisticService.basicDetailsPrinter();

        String statisticSelectCommand = CommandReader.readCommand();

        while (!statisticSelectCommand.equalsIgnoreCase(Command.BACK.getCommand())) {



            statisticSelectCommand = CommandReader.readCommand();

        }

    }

    private void updateStudentPoints() {

        System.out.println("Enter an id and points or 'back' to return:");

        String studentUpdateCommand = CommandReader.readCommand();

        while (!studentUpdateCommand.equalsIgnoreCase(Command.BACK.getCommand())) {

            List<String> studentPoints = new ArrayList<>(Arrays.asList(studentUpdateCommand.split(" ")));
            try {

                int id = 0;

                try {
                    id = Integer.parseInt(studentPoints.get(0));
                } catch (NumberFormatException ex) {
                    throw new IllegalCallerException();
                }
                if (StudentRepository.isIdPresent(id)) {

                    int[] points = new int[4];

                    for (int i = 1; i < 5; i++) {
                        points[i - 1] = Integer.parseInt(studentPoints.get(i));
                    }

                    if (checkIfPointsAreGreaterThanOrEqualZero(points) && studentPoints.size() == 5) {

                        CourseService.updatePoints(id, points);
                        System.out.println("Points updated.");

                    } else throw new Exception();


                } else {
                    System.out.printf("No student is found for id=%d.\n", id);
                }

            } catch (IllegalCallerException ex) {
                System.out.printf("No student is found for id=%s.\n", studentPoints.get(0));
            } catch (Exception ex) {
                System.out.println("Incorrect points format");
            }

            studentUpdateCommand = CommandReader.readCommand();

        }

    }

    private boolean checkIfPointsAreGreaterThanOrEqualZero(int[] points) {

        return points[0] >= 0 && points[1] >= 0 && points[2] >= 0 && points[3] >= 0;

    }

    public ProgramController() {
        this.runProgram = true;
    }

    public boolean isRunProgram() {
        return runProgram;
    }

    public void setRunProgram(boolean runProgram) {
        this.runProgram = runProgram;
    }


}
