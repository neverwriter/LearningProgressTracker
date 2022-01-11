package tracker;

public enum CoursesNames {
    JAVA ("Java"),
    DSA ("DSA"),
    DATABASES ("Databases"),
    SPRING("Spring");

    private String courseName;

    private CoursesNames(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }
}
