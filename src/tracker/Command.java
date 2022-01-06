package tracker;

public enum Command {
    EXIT("EXIT"),
    ADD_STUDENTS("ADD STUDENTS"),
    BACK("BACK"),
    LIST("LIST"),
    FIND("FIND"),
    ADD_POINTS("ADD POINTS"),
    SEED("SEED"),
    STATISTICS("STATISTICS");


    private String command;

    private Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
