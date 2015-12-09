package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.NoSuchElementException;

public class ResultsPage extends AbstractPage {

    @FindBy(className = "show-one-result")
    private WebElement findResultButton;
    @FindBy(css = "input[name=code]")
    private WebElement resultInputField;

    public ResultsPage(WebDriver driver) {
        super(driver);
    }

    public ResultsPage findResult(String resultCode) {
        this.typeInto(resultInputField, resultCode);
        this.click(findResultButton);
        return this;
    }
    
    public Boolean isResultsFound() {
        Boolean found;
        try {
            found = driver.findElement(By.cssSelector("tbody tr")).isDisplayed();
        }
        catch (NoSuchElementException e){
            found = false;
        }
        return found;
    }
}
