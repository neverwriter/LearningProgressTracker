package tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class StudentCredentialsChecker {

    public static boolean isCredentialsValid(String studentCredentials) {

        List<String> studentCredential = new ArrayList<>(Arrays.asList(studentCredentials.split(" ")));

        if (studentCredential.size() < 3) {
            System.out.println("Incorrect credentials.");
            return false;
        } else {

            String firstName = studentCredential.get(0);

            String lastName = "";

            for (int i = 1; i < studentCredential.size() - 1; i++) {

                lastName = lastName.concat(studentCredential.get(i));

            }

            String email = studentCredential.get(studentCredential.size() - 1);

            if (!isFirstNameValid(firstName) || firstName.length() < 2) {
                System.out.println("Incorrect first name.");
                return false;
            } else if (!isLastNameValid(lastName) || lastName.length() < 2) {
                System.out.println("Incorrect last name.");
                return false;
            } else if (!isEmailValid(email)) {
                System.out.println("Incorrect email.");
                return false;
            } else {
                return true;
            }
        }
    }

    public static boolean isFirstNameValid(String firstName) {

            return Pattern.matches("\\b[a-zA-Z]+(?:['-]?[a-zA-Z]+)*\\b", firstName)  ;

    }

    public static boolean isLastNameValid(String lastName) {

            return Pattern.matches("\\b[a-zA-Z]+(?:['-]?[a-zA-Z]+)*\\b", lastName);

    }

    public static boolean isEmailValid(String email) {


            return Pattern.matches("\\b[a-zA-Z\\.0-9]+[@]+[a-zA-Z0-9]+[\\.]+[a-z0-9]+\\b",email);


    }

}
