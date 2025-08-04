package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;

    private By userIdField = By.id("mui-1");
    private By passwordField = By.id("mui-2");
    private By loginButton = By.xpath("//button[contains(text(), 'Login')]");
    private By passwordToggle = By.xpath("//button[contains(@aria-label, 'toggle password visibility')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoginButtonEnabled() {
        return driver.findElement(loginButton).isEnabled();
    }

    public boolean isPasswordMasked() {
        WebElement password = driver.findElement(passwordField);
        return password.getAttribute("type").equals("password");
    }

    public void togglePasswordVisibility() {
        driver.findElement(passwordToggle).click();
    }

    public void enterCredentials(String username, String password) {
        driver.findElement(userIdField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public String getLoginErrorMessage() {
        try {
            return driver.findElement(By.cssSelector(".MuiFormHelperText-root")).getText();
        } catch (Exception e) {
            return "";
        }
    }
}
