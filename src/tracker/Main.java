package tracker;

public class Main {
    public static void main(String[] args) {

        ProgramController programController = new ProgramController();

        printStartMessage();

        while (programController.isRunProgram()) {

            programController.defineStep(CommandReader.readCommand());

        }
    }

    private static void printStartMessage(){
        System.out.println("Learning Progress Tracker");
    }
}
