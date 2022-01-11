package tracker.student;



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

}


