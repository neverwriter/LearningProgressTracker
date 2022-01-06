package tracker.course;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Course {
    private final int DEFAULT_SCORE = 0;
    private String courseName;
    private Map<Integer, LinkedList<Integer>> studentsWithScore;

    public Course(String courseName) {
        this.courseName = courseName;
        this.studentsWithScore = new HashMap<>();
    }

    public void addNewStudentToCourse(Integer studentId, int score){
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.push(score);
        this.studentsWithScore.put(studentId, linkedList);
    }

    public void addStudentScore(Integer studentID, int score){
        studentsWithScore.get(studentID).push(score);
    }

    public Map<Integer, LinkedList<Integer>> getStudentsWithScore(){
        return studentsWithScore;
    }

    public String getCourseName() {
        return courseName;
    }

    public boolean isStudentEnrolled(int studentId){
        return studentsWithScore.containsKey(studentId);
    }
}
