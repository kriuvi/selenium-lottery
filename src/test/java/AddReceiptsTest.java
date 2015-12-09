import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.*;
import pages.MainPage;
import utils.dataFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(value = Parameterized.class)
public class AddReceiptsTest extends dataFactory {
    private static WebDriver driver;

    private final String BASE_URL = "https://loteriaparagonowa.gov.pl/";
    private static String pathToData = "src/main/java/data/newReceipts.csv";

    private String cashDeskNo;
    private String nip;
    private String date;
    private String printNo;
    private String amount;
    private String place;

    public AddReceiptsTest(String cashDeskNo, String nip, String date, String printNo, String amount, String place) {
        this.cashDeskNo = cashDeskNo;
        this.nip = nip;
        this.date = date;
        this.printNo = printNo;
        this.amount = amount;
        this.place = place;
    }

    @Parameters
    public static Collection testData() throws IOException {
        return getTestData(pathToData);
    }

    @Before
    public void startBrowser() throws MalformedURLException {
        driver = new FirefoxDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

    @Test
    public void sendCorrectForm() throws InterruptedException {
        final String email = "vira.kriukova@gmail.com";
        final String password = "A5UMMVM8SYS";

        Boolean[] agreements = {true, true, true};
        Boolean paidBranch = false;
        Boolean send = true;

        MainPage mainPage = new MainPage(driver);
        MainPage nextPage = mainPage.goToLogin()
                .login(email, password);

        Thread.sleep(2000);

        nextPage.CreateNewReceiptWithLogin(cashDeskNo, nip.split("-"), date.split("-"), printNo, amount.split("-"), paidBranch, agreements, send);

        Thread.sleep(2000);

        String applicationIsAccepted = driver.findElement(By.className("thanks")).getText();
        assertEquals(applicationIsAccepted, "Dziękujemy za Twoje zgłoszenie!");
    }

    @After
    public void closeBrowser() throws IOException {
        driver.quit();
    }
}