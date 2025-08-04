package tests;

import pages.LoginPage;

public class LoginTest extends BaseTest {

    public static void main(String[] args) {
        LoginTest test = new LoginTest();
        test.setUp();

        LoginPage loginPage = new LoginPage(test.driver);

        System.out.println("Test 1: Button disabled with empty fields: " +
            !loginPage.isLoginButtonEnabled());

        System.out.println("Test 2: Password initially masked: " +
            loginPage.isPasswordMasked());
        loginPage.togglePasswordVisibility();
        System.out.println("Password visible after toggle: " +
            !loginPage.isPasswordMasked());

        loginPage.enterCredentials("random@invalid.com", "wrongpass123");
        loginPage.clickLogin();
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        String error = loginPage.getLoginErrorMessage();
        System.out.println("Test 3: Error Message on Invalid Login: " + !error.isEmpty());

        test.tearDown();
    }
}
