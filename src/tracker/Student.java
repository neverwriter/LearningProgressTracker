package tracker;


public class Student {

    private final int ID_BASE = 10000;

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int javaPoints;
    private int dsaPoints;
    private int dbPoints;
    private int springPoints;

    public Student(String firstName, String lastName, String email) {
        this.id = idGenerator();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.javaPoints = 0;
        this.dsaPoints = 0;
        this.dbPoints = 0;
        this.springPoints = 0;
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

    public int getJavaPoints() {
        return javaPoints;
    }

    public void setJavaPoints(int javaPoints) {
        this.javaPoints = javaPoints;
    }

    public int getDsaPoints() {
        return dsaPoints;
    }

    public void setDsaPoints(int dsaPoints) {
        this.dsaPoints = dsaPoints;
    }

    public int getDbPoints() {
        return dbPoints;
    }

    public void setDbPoints(int dbPoints) {
        this.dbPoints = dbPoints;
    }

    public int getSpringPoints() {
        return springPoints;
    }

    public void setSpringPoints(int springPoints) {
        this.springPoints = springPoints;
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
