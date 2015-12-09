package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

    @FindBy(css = "form#loginForm div div div #email")
    private WebElement loginField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(css = "form#loginForm div button")
    private WebElement loginButton;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public MainPage login(String email, String password) {
        this.typeInto(loginField, email);
        this.typeInto(passwordField, password);
        this.click(loginButton);
        return new MainPage(driver);
    }
}