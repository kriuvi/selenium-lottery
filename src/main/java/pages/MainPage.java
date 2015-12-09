package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class MainPage extends AbstractPage {
    @FindBy(css = "a.login")
    private WebElement loginLink;

    @FindBy(id = "nr_kasy_1")
    private WebElement cashDeskNoField;

    @FindBy(id = "nip_1")
    private WebElement nip_1_field;
    @FindBy(id = "nip_2")
    private WebElement nip_2_field;
    @FindBy(id = "nip_3")
    private WebElement nip_3_field;
    @FindBy(id = "nip_4")
    private WebElement nip_4_field;
    @FindBy(id = "nip_5")
    private WebElement nip_5_field;
    @FindBy(id = "nip_6")
    private WebElement nip_6_field;
    @FindBy(id = "nip_7")
    private WebElement nip_7_field;
    @FindBy(id = "nip_8")
    private WebElement nip_8_field;
    @FindBy(id = "nip_9")
    private WebElement nip_9_field;
    @FindBy(id = "nip_10")
    private WebElement nip_10_field;

    @FindBy(id = "rok")
    private WebElement yearField;
    @FindBy(id = "miesiac")
    private WebElement monthField;
    @FindBy(id = "dzien")
    private WebElement dayField;

    @FindBy(id = "nr_wydruku")
    private WebElement printNoField;

    @FindBy(id = "kwota_zl")
    private WebElement amountZlotyField;
    @FindBy(id = "kwota_gr")
    private WebElement amountGroszyField;

    @FindBy(id = "email")
    private WebElement emailField;
    @FindBy(id = "email_2")
    private WebElement repeatEmailField;

    @FindBy(id = "nr_tel_1")
    private WebElement telNo_1_field;
    @FindBy(id = "nr_tel_2")
    private WebElement telNo_2_field;
    @FindBy(id = "nr_tel_3")
    private WebElement telNo_3_field;
    @FindBy(id = "nr_tel_4")
    private WebElement telNo_4_field;
    @FindBy(id = "nr_tel_5")
    private WebElement telNo_5_field;
    @FindBy(id = "nr_tel_6")
    private WebElement telNo_6_field;
    @FindBy(id = "nr_tel_7")
    private WebElement telNo_7_field;
    @FindBy(id = "nr_tel_8")
    private WebElement telNo_8_field;
    @FindBy(id = "nr_tel_9")
    private WebElement telNo_9_field;

    @FindBy(id = "captcha-operation")
    private WebElement captchaField;
    @FindBy(id = "captcha-input")
    private WebElement captchaInputField;

    @FindBy(css = "label[for=zgoda_dane]")
    private WebElement dataAgreementFieldCheckBox;
    @FindBy(css = "label[for=zgoda_wizerunek]")
    private WebElement imageAgreementFieldCheckBox;
    @FindBy(css = "label[for=sprawdzone]")
    private WebElement dataCheckedFieldCheckBox;

    @FindBy(css = "button[type=submit]")
    private WebElement sendButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private Void common(String cashDeskNo, String[] nip, String[] date, String printNo, String[] amount, Boolean paidBranch, Boolean[] agreements, Boolean send) {
        this.typeInto(cashDeskNoField, cashDeskNo);

        this.typeInto(nip_1_field, nip[0]);
        this.typeInto(nip_2_field, nip[1]);
        this.typeInto(nip_3_field, nip[2]);
        this.typeInto(nip_4_field, nip[3]);
        this.typeInto(nip_5_field, nip[4]);
        this.typeInto(nip_6_field, nip[5]);
        this.typeInto(nip_7_field, nip[6]);
        this.typeInto(nip_8_field, nip[7]);
        this.typeInto(nip_9_field, nip[8]);
        this.typeInto(nip_10_field, nip[9]);

        this.typeInto(yearField, date[0]);
        this.typeInto(monthField, date[1]);
        this.typeInto(dayField, date[2]);

        this.typeInto(printNoField, printNo);

        this.typeInto(amountZlotyField, amount[0]);
        this.typeInto(amountGroszyField, amount[1]);

        Select branch = new Select(driver.findElement(By.id("branza")));
        if (paidBranch) branch.selectByValue("5602c71013287705788b4567");
        else branch.selectByVisibleText("inne");

        String captcha = this.captchaField.getText();
        Integer result = Integer.parseInt(captcha.substring(0,1)) + Integer.parseInt(captcha.substring(2));
        this.typeInto(captchaInputField, Integer.toString(result));

        if (agreements[0]) click(dataAgreementFieldCheckBox);
        if (agreements[1]) click(imageAgreementFieldCheckBox);
        if (agreements[2]) click(dataCheckedFieldCheckBox);

        if (send) sendButton.click();

        return null;
    }

    public MainPage CreateNewReceiptWithLogin(String cashDeskNo, String[] nip, String[] date, String printNo, String[] amount, Boolean paidBranch, Boolean[] agreements, Boolean send) {
        common(cashDeskNo, nip, date, printNo, amount, paidBranch, agreements, send);

        return new MainPage(getDriver());
    }

    public MainPage CreateNewReceiptNoLogin(String cashDeskNo, String[] nip, String[] date, String printNo, String[] amount, Boolean paidBranch, String email, String repeatEmail, String[] telNo, Boolean[] agreements, Boolean send) {
        common(cashDeskNo, nip, date, printNo, amount, paidBranch, agreements, send);

        this.typeInto(emailField, email);
        this.typeInto(repeatEmailField, repeatEmail);

        this.typeInto(telNo_1_field, telNo[0]);
        this.typeInto(telNo_2_field, telNo[1]);
        this.typeInto(telNo_3_field, telNo[2]);
        this.typeInto(telNo_4_field, telNo[3]);
        this.typeInto(telNo_5_field, telNo[4]);
        this.typeInto(telNo_6_field, telNo[5]);
        this.typeInto(telNo_7_field, telNo[6]);
        this.typeInto(telNo_8_field, telNo[7]);
        this.typeInto(telNo_9_field, telNo[8]);

        return new MainPage(getDriver());
    }

    public LoginPage goToLogin() {
        this.click(loginLink);
        return new LoginPage(getDriver());
    }
}