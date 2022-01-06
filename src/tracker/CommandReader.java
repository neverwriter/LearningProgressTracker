package tracker;

import java.util.Scanner;

public class CommandReader {

    public static String readCommand() {
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }

}
