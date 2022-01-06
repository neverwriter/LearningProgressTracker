package tracker;

public class StudentRepositorySeeder {


    public static void fillWithStudents(){
        StudentRepository.addStudent(new Student("Jan", "Kowalski", "j.kowalski@gmail.com"));
        StudentRepository.addStudent(new Student("John", "Smith", "jsmith@hotmail.com"));
        StudentRepository.addStudent(new Student("Anny", "Doolittle", "anny.md@mail.edu"));
        StudentRepository.addStudent(new Student("Jean-Claude", "O'Connor", "jcda123@google.net"));
        StudentRepository.addStudent(new Student("Mary", "Emelianenko", "125367at@zzz90.z9"));
        StudentRepository.addStudent(new Student("Al", "Owen", "u15da125@a1s2f4f7.a1c2c5s4"));
        StudentRepository.addStudent(new Student("Robert", "Jemison Van de Graaff", "robertvdgraaff@mit.edu"));
        StudentRepository.addStudent(new Student("Ed", "Eden", "a1@a1.a1"));
    }

    public static void fillWithPoints(){
        StudentRepository.updatePoints(10000, new int[] {34, 0, 8, 16});
        StudentRepository.updatePoints(10001, new int[] {0, 300, 65,0});
        StudentRepository.updatePoints(10002, new int[] {45, 0, 0, 0});
        StudentRepository.updatePoints(10003, new int[] {150, 100, 200, 300});
        StudentRepository.updatePoints(10004, new int[] {0, 0, 0, 100});
        StudentRepository.updatePoints(10005, new int[] {85, 95, 0, 0});
        StudentRepository.updatePoints(10006, new int[] {49, 58, 98, 32});
        StudentRepository.updatePoints(10007, new int[] {254, 0, 148, 0});
    }
}


