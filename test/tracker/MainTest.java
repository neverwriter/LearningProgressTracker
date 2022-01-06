package tracker;

import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.exception.outcomes.WrongAnswer;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.TestedProgram;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hyperskill.hstest.testing.expect.Expectation.expect;
import static org.junit.jupiter.api.Assertions.*;

class MainTest extends StageTest<String> {

    private static final Random rnd = new Random();

    @DynamicTest (order = 1)
    CheckResult testFindByID() {
        TestedProgram main = new TestedProgram();
        main.start();
        main.execute("add students");

        List<String> credentials = getRandomCredentials(5);
        for (String arg : credentials) {
            main.execute(arg);
        }
        main.execute("back");

        String output = main.execute("list");
        List<String> lines = expect(output).toContain().lines();
        List<String> ids = parseIds(lines);

        main.execute("add points");
        for (int i = 0; i < ids.size(); i++) {
            main.execute(String.format("%s %d %d %d %d", ids.get(i), i, i, i, i));
            main.execute(String.format("%s %d %d %d %d", ids.get(i), i, i, i, i));
        }

        main.execute("back");
        output = main.execute("find");
        expect(output).toContain(1).lines();
        if (anyMissingKeywords(output, "enter", "id", "back", "return")) {
            return CheckResult.wrong("When 'find' command is entered, you program should " +
                    "print \"Enter an id or 'back' to return:\", but your output was: " + output);
        }

        for (int i = 0; i < ids.size(); i++) {
            output = main.execute(ids.get(i));
            expect(output).toContain(1).lines();
            String expected = String.format(
                    "%s points: Java=%d; DSA=%d; Databases=%d; Spring=%d",
                    ids.get(i), i * 2, i * 2, i * 2, i * 2
            );
            if (incorrectString(output, expected)) {
                return CheckResult.wrong("Expected output: " + expected +
                        ", but your output was: " + output);
            }
        }

        return CheckResult.correct();
    }

    private boolean anyMissingKeywords(String output, String... keywords) {
        List<String> tokens = Arrays.asList(
                output.trim().toLowerCase().split("\\W+")
        );

        return !tokens.containsAll(Arrays.stream(keywords)
                .map(String::toLowerCase)
                .collect(Collectors.toList()));
    }

    private boolean incorrectString(String output, String model) {
        String normalizedOutput = output.replaceAll("\\W+", "").toLowerCase();
        String normalizedModel = model.replaceAll("\\W+", "").toLowerCase();

        return !normalizedOutput.contains(normalizedModel);
    }

    private String[] getBlankInput() {
        return new String[]{"", "  ", "\t", " \t"};
    }

    private String[] getUnknownCommands() {
        return new String[]{"abc", "quit", "  brexit ", "exi  t", "?", "break",
                "-exit", "Ctrl+C", "exit please", ":q", "java", "spring", "dsa", "databases"};
    }

    private String[] getCorrectCredentials() {
        return new String[]{"John Smith jsmith@hotmail.com", "Anny Doolittle anny.md@mail.edu",
                "Jean-Claude O'Connor jcda123@google.net", "Mary Emelianenko 125367at@zzz90.z9",
                "Al Owen u15da125@a1s2f4f7.a1c2c5s4", "Robert Jemison Van de Graaff robertvdgraaff@mit.edu",
                "Ed Eden a1@a1.a1", "na'me s-u ii@ii.ii", "n'a me su aa-b'b ab@ab.ab", "nA me 1@1.1"};
    }

    private String[][] getIncorrectCredentials() {
        return new String[][]{
                {"", "Incorrect credentials"}, {" \t", "Incorrect credentials."},
                {"name surname", "Incorrect credentials."},
                {"n surname email@email.xyz", "Incorrect first name."},
                {"'name surname email@email.xyz", "Incorrect first name."},
                {"-name surname email@email.xyz", "Incorrect first name."},
                {"name- surname email@email.xyz", "Incorrect first name."},
                {"name' surname email@email.xyz", "Incorrect first name."},
                {"nam-'e surname email@email.xyz", "Incorrect first name."},
                {"na'-me surname email@email.xyz", "Incorrect first name."},
                {"na--me surname email@email.xyz", "Incorrect first name."},
                {"na''me surname email@email.xyz", "Incorrect first name."},
                {"námé surname email@email.xyz", "Incorrect first name."},
                {"name s email@email.xyz", "Incorrect last name."},
                {"name -surname email@email.xyz", "Incorrect last name."},
                {"name 'surname email@email.xyz", "Incorrect last name."},
                {"name surnam''e email@email.xyz", "Incorrect last name."},
                {"name surn--ame email@email.xyz", "Incorrect last name."},
                {"name s'-urname email@email.xyz", "Incorrect last name."},
                {"name su-'rname email@email.xyz", "Incorrect last name."},
                {"name surname- email@email.xyz", "Incorrect last name."},
                {"name surname' email@email.xyz", "Incorrect last name."},
                {"name surnámé email@email.xyz", "Incorrect last name."},
                {"name surname emailemail.xyz", "Incorrect email."},
                {"name surname email@emailxyz", "Incorrect email."},
                {"name surname email@e@mail.xyz", "Incorrect email."},
        };
    }

    private String[] getIncorrectPoints() {
        return new String[]{"", "-1 1 1 1", "1 1 2 A", "1 1 1", "1 1 1 1 1"};
    }

    private String[] getCorrectPoints(int n) {
        return Stream.generate(String::new)
                .limit(n)
                .map(it -> String.format("%d %d %d %d", nextPoint(), nextPoint(), nextPoint(), nextPoint()))
                .toArray(String[]::new);
    }

    private int nextPoint() {
        return rnd.nextInt(10) + 1;
    }

    private List<String> parseIds(List<String> lines) {
        try {
            return lines.stream()
                    .skip(1)
                    .map(String::trim)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new WrongAnswer("Error occurred while parsing your output " + e.getMessage());
        }
    }

    private List<String> generateNames(int n) {
        List<String> names = List.of("Shoshana Utica", "Marisa Firman", "Gwenette Anagnos", "Charlena Girardo",
                "Alexina Belcher", "Karee Antoinetta", "Dolley Panther", "Elysha Quinlan", "Trixie Winer",
                "Ricki Trovillion", "Amye Uriisa", "Hedwig Wally", "Gwenette Kironde", "Jermaine Naaman",
                "Olga Rosanne", "Annecorinne Ause", "Aurie Dorisa", "Van Fawnia", "Carmella Campman",
                "Francesca Francis", "Elwira Florrie", "Nonna Miko", "Natka Herculie", "Roxi Hett", "Brandise Hardan",
                "Toby Bleier", "Dalia Gleeson", "Emelia Annnora", "Beatrisa Jegar", "Barbara-Anne Chicky",
                "Ann Agnella", "Lebbie Alabaster", "Leola Whelan", "Starlin Griz", "Anjanette Uis", "Tasha Chem");

        List<String> selectedNames = new ArrayList<>(names);
        Collections.shuffle(selectedNames);
        return selectedNames.stream().limit(n).collect(Collectors.toList());
    }

    private List<String> generateEmails(int n) {
        return IntStream.rangeClosed(1, n).mapToObj(it -> "address" + it + "@mail.com").collect(Collectors.toList());
    }

    private List<String> getRandomCredentials(int n) {
        List<String> names = generateNames(n);
        List<String> emails = generateEmails(n);
        return IntStream.range(0, n)
                .mapToObj(it -> String.format("%s %s", names.get(it), emails.get(it)))
                .collect(Collectors.toList());
    }
}