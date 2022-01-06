package tracker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentCredentialsCheckerTest {

    @Test
    void itShouldIsCredentialsValid() {
        //Given
        String studentCredential = "Jean-Clause Smith jsmith@gmail.com";
        //When
        boolean isCredentialValid = StudentCredentialsChecker.isCredentialsValid(studentCredential);
        //Then
        assertTrue(isCredentialValid);

    }

    @Test
    void whenNoThreeWordsItShouldNotBeValid() {
        //Given
        String studentCredential = "Jean-Clause jsmith@gmail.com";
        //When
        boolean isCredentialValid = StudentCredentialsChecker.isCredentialsValid(studentCredential);
        //Then
        assertFalse(isCredentialValid);

    }

    @Test
    void FirstNameShouldBeValid() {
        //Given
        String firstName = "John";
        //When
        boolean isFirstNameValid = StudentCredentialsChecker.isFirstNameValid(firstName);
        //Then
        assertTrue(isFirstNameValid);
    }

    @Test
    void FirstNameWhenContainsNumberShouldNotBeValid() {
        //Given
        String firstName = "John9";
        //When
        boolean isFirstNameValid = StudentCredentialsChecker.isFirstNameValid(firstName);
        //Then
        assertFalse(isFirstNameValid);
    }

    @Test
    void FirstNameWhenContainsSpecialCharShouldNotBeValid() {
        //Given
        String firstName = "'John";
        //When
        boolean isFirstNameValid = StudentCredentialsChecker.isFirstNameValid(firstName);
        //Then
        assertFalse(isFirstNameValid);
    }


    @Test
    void FirstNameWhenContainsApostropheShouldBeValid() {
        //Given
        String firstName = "O'Neill";
        //When
        boolean isFirstNameValid = StudentCredentialsChecker.isFirstNameValid(firstName);
        //Then
        assertTrue(isFirstNameValid);
    }

    @Test
    void FirstNameWhenContainsDashShouldBeValid() {
        //Given
        String firstName = "Jean-Clause";
        //When
        boolean isFirstNameValid = StudentCredentialsChecker.isFirstNameValid(firstName);
        //Then
        assertTrue(isFirstNameValid);
    }

    @Test
    void EmailWhenNotContainsAtShouldNotBeValid() {
        //Given
        String email = "jsmitgmail.com";
        //When
        boolean isEmailValid = StudentCredentialsChecker.isEmailValid(email);
        //Then
        assertFalse(isEmailValid);
    }

    @Test
    void EmailWhenNotContainsDotShouldNotBeValid() {
        //Given
        String email = "jsmit@gmailcom";
        //When
        boolean isEmailValid = StudentCredentialsChecker.isEmailValid(email);
        //Then
        assertFalse(isEmailValid);
    }
}