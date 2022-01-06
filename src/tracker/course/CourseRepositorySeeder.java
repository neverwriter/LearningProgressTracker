package tracker.course;

public class CourseRepositorySeeder {
    public static void fillWithPoints() {

        for (int i = 0; i < 10; i++) {
            CourseService.updatePoints(10000, new int[]{5, 0, 6, 2});

            CourseService.updatePoints(10001, new int[]{0, 3, 2, 0});

            CourseService.updatePoints(10002, new int[]{20, 0, 7, 0});

            CourseService.updatePoints(10004, new int[]{0, 1, 0, 0});

            CourseService.updatePoints(10001, new int[]{0, 3, 2, 0});

        }
    }
}
