package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.interactions.Actions;
import java.util.List;

public class FormPage {
    @FindBy(id = "name-input")
    private WebElement nameField;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;

    @FindBy(css = "input[value='Milk']")
    private WebElement milkCheckbox;

    @FindBy(css = "input[value='Coffee']")
    private WebElement coffeeCheckbox;

    @FindBy(xpath = "//input[@value='Yellow']")
    private WebElement yellowRadio;

    @FindBy(xpath = "//select[@data-testid='automation']")
    private WebElement automationSelect;

    @FindBy(xpath = "//input[@data-testid='email']")
    private WebElement emailField;

    @FindBy(css = "[data-testid='message']")
    private WebElement messageField;

    @FindBy(css = "[data-testid='submit-btn']")
    private WebElement submitButton;

    private WebDriver driver;
    private WebDriverWait wait;

    public FormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public FormPage enterName(String name) {
        new Actions(driver).moveToElement(nameField).perform();
        wait.until(ExpectedConditions.visibilityOf(nameField)).sendKeys(name);
        return this;
    }

    public FormPage enterPassword(String password) {
        new Actions(driver).moveToElement(passwordField).perform();
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
        return this;
    }

    public FormPage selectMilk() {
        new Actions(driver).moveToElement(milkCheckbox).perform();
        if (!milkCheckbox.isSelected()) {
            milkCheckbox.click();
        }
        return this;
    }

    public FormPage selectCoffee() {
        new Actions(driver).moveToElement(coffeeCheckbox).perform();
        if (!coffeeCheckbox.isSelected()) {
            coffeeCheckbox.click();
        }
        return this;
    }

    public FormPage selectYellow() {
        new Actions(driver).moveToElement(yellowRadio).click().perform();
        return this;
    }

    public FormPage selectAutomationOption(String value) {
        new Actions(driver).moveToElement(automationSelect).perform();
        Select select = new Select(automationSelect);
        select.selectByValue(value);
        return this;
    }

    public FormPage enterEmail(String email) {
        new Actions(driver).moveToElement(emailField).perform();
        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(email);
        return this;
    }

    public FormPage fillMessageWithToolsInfo() {
        new Actions(driver).moveToElement(messageField).perform();
        List<WebElement> toolElements = driver.findElements(By.xpath("//ul[li[text()='Selenium']]/li"));
        int count = toolElements.size();
        String longest = "";

        for (WebElement element : toolElements) {
            String name = element.getText();
            if (name.length() > longest.length()) {
                longest = name;
            }
        }

        String message = count + " tools, longest name: " + longest;
        messageField.sendKeys(message);
        return this;
    }

    public FormPage clickSubmit() {
        new Actions(driver).moveToElement(submitButton).perform();
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        return this;
    }

    public String getAlertText() {
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }
}
