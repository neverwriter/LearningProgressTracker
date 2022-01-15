package tracker.notification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationRepository {
    private final static Map<Integer, List<String>> notificationRepository = new HashMap<>();

    public static void addNotification(Integer studentId, String courseName){

        if(notificationRepository.containsKey(studentId)){

            notificationRepository.get(studentId).add(courseName);

        } else {

            List<String> list = new ArrayList<>();

            list.add(courseName);

            notificationRepository.put(studentId, list);
        }

    }

    public static Map<Integer, List<String>> getNotificationRepository() {
        return notificationRepository;
    }
}
