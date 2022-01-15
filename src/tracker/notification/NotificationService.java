package tracker.notification;

import tracker.course.CourseService;
import tracker.student.StudentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class NotificationService {

    public static int notifyStudents() {
        AtomicInteger numberOfNotifiedStudents = new AtomicInteger();

        Map<Integer, List<String>> completedCourses = new HashMap<>();

        completedCourses = CourseService.defineCompletedCourses();

        completedCourses.entrySet().stream().forEach(integerListEntry -> {
            AtomicInteger counter = new AtomicInteger();
            integerListEntry.getValue().forEach(str -> {


                if (NotificationRepository.getNotificationRepository().get(integerListEntry.getKey()) == null) {

                    sendNotification(integerListEntry.getKey(), str);
                    counter.getAndIncrement();
                    NotificationRepository.addNotification(integerListEntry.getKey(), str);

                } else {
                    if (!NotificationRepository.getNotificationRepository().get(integerListEntry.getKey()).contains(str)) {

                        sendNotification(integerListEntry.getKey(), str);
                        counter.getAndIncrement();
                        NotificationRepository.addNotification(integerListEntry.getKey(), str);

                    }
                }


            });
            if (counter.get() > 0) {
                numberOfNotifiedStudents.getAndIncrement();
            }
        });

        return numberOfNotifiedStudents.get();
    }

    private static void sendNotification(int studentId, String courseName) {

        System.out.printf("To: %s%n", StudentRepository.getStudentEmailById(studentId));
        System.out.println("Re: Your Learning Progress");
        System.out.printf("Hello, %s %s! You have accomplished our %s course!%n",
                StudentRepository.getStudentFirstNameById(studentId),
                StudentRepository.getStudentLastNameById(studentId),
                courseName);

    }

}
