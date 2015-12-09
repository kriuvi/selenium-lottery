import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.*;
import utils.dataFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;

import static org.junit.Assert.assertFalse;

@RunWith(value = Parameterized.class)
public class CheckLotteryResultsTest extends dataFactory {

    private WebDriver driver;
    private static final String BASE_URL = "https://loteriaparagonowa.gov.pl/wyniki";
    private static String pathToData = "src/main/java/data/codes.csv";

    private String resultsCode;

    public CheckLotteryResultsTest(String resultsCode) {
        this.resultsCode = resultsCode;
    }

    @Parameterized.Parameters
    public static Collection testData() throws IOException {
        return getTestData(pathToData);
    }

    @Before
    public void startBrowser() throws MalformedURLException {
        driver = new FirefoxDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

    //TODO: write numbers from "Add" to file and use those data for look-up
    @Test
    public void resultCodeNotOnTheList(){
        ResultsPage page = new ResultsPage(driver);
        assertFalse(page.findResult(resultsCode).isResultsFound());
    }

    @After
    public void closeBrowser() throws IOException {
        driver.quit();
    }
}