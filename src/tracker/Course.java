package tracker;

import java.util.HashMap;
import java.util.Map;

public class Course {
    private final int DEFAULT_SCORE = 0;
    private String courseName;
    private Map<Student, LinkedList<>> studentsWithScore;

    public Course(String courseName) {
        this.courseName = courseName;
        this.studentsWithScore = new HashMap<>();
    }

    public void addNewStudentToCourse(Student student){
        this.studentsWithScore.put(student, DEFAULT_SCORE);
    }

    public void updateScore(Student student, int score){
        this.studentsWithScore.replace(student, score);
    }

    public Map<Student, Integer> getStudentsWithScore(){
        return studentsWithScore;
    }
}
