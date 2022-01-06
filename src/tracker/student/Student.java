package tracker.student;


public class Student {

    private final int ID_BASE = 10000;

    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public Student(String firstName, String lastName, String email) {
        this.id = idGenerator();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }

    public int idGenerator(){
        return ID_BASE + StudentRepository.getSize();
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
