package tests;

import pages.LoginPage;

public class LoginTest extends BaseTest {

    public static void main(String[] args) {
        LoginTest test = new LoginTest();
        test.setUp();

        LoginPage loginPage = new LoginPage(test.driver);

        // Test 1: Button should be disabled with empty fields
        System.out.println("Test 1: Button disabled with empty fields: " +
            !loginPage.isLoginButtonEnabled());

        // Test 2: Password should be initially masked
        System.out.println("Test 2: Password initially masked: " +
            loginPage.isPasswordMasked());

        // Test 3: Password visibility toggle
        loginPage.togglePasswordVisibility();
        System.out.println("Test 3: Password visible after toggle: " +
            !loginPage.isPasswordMasked());

        // Test 4: Toggle back to masked
        loginPage.togglePasswordVisibility();
        System.out.println("Test 4: Password masked again: " +
            loginPage.isPasswordMasked());

        // Test 5: Invalid login
        loginPage.enterCredentials("random@invalid.com", "wrongpass123");
        loginPage.clickLogin();
        sleep(2000);
        System.out.println("Test 5: Error message on invalid login: " +
            !loginPage.getLoginErrorMessage().isEmpty());

        // Test 6: Only email entered
        loginPage.enterCredentials("user@janitri.in", "");
        System.out.println("Test 6: Button disabled with only email: " +
            !loginPage.isLoginButtonEnabled());

        // Test 7: Only password entered
        loginPage.enterCredentials("", "somepassword");
        System.out.println("Test 7: Button disabled with only password: " +
            !loginPage.isLoginButtonEnabled());

        // Test 8: Invalid email format
        loginPage.enterCredentials("invalidemail", "password");
        loginPage.clickLogin();
        sleep(2000);
        System.out.println("Test 8: Error on invalid email format: " +
            !loginPage.getLoginErrorMessage().isEmpty());

        // Test 9: Whitespace-only fields
        loginPage.enterCredentials("   ", "   ");
        System.out.println("Test 9: Button disabled with whitespace-only: " +
            !loginPage.isLoginButtonEnabled());

        // Test 10: Very short password
        loginPage.enterCredentials("user@janitri.in", "1");
        loginPage.clickLogin();
        sleep(2000);
        System.out.println("Test 10: Error on short password: " +
            !loginPage.getLoginErrorMessage().isEmpty());

        // Test 11: Valid credentials (adjust with real working creds if needed)
        loginPage.enterCredentials("valid@janitri.in", "ValidPassword123");
        loginPage.clickLogin();
        sleep(3000);
        System.out.println("Test 11: Check if logged in with valid credentials");

        test.tearDown();
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
