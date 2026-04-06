package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoAlertPresentException;
import static org.junit.jupiter.api.Assertions.*;

public class FormPageTest extends BaseTest {

    @Test
    void testPositiveScenario() {
        formPage.enterName("Vova")
                .enterPassword("VovaBubnec")
                .selectMilk()
                .selectCoffee()
                .selectYellow()
                .selectAutomationOption("yes")
                .enterEmail("VovaBubnec@simbirsoft.com")
                .fillMessageWithToolsInfo()
                .clickSubmit();

        String alertText = formPage.getAlertText();
        assertEquals("Message received!", alertText);
        formPage.acceptAlert();
    }

    @Test
    void testNegativeScenario() {
        formPage.enterPassword("VovaBubnec")
                .selectMilk()
                .selectCoffee()
                .selectYellow()
                .selectAutomationOption("yes")
                .enterEmail("VovaBubnec@simbirsoft.com")
                .fillMessageWithToolsInfo()
                .clickSubmit();

        boolean alertPresent = false;
        try {
            driver.switchTo().alert();
            alertPresent = true;
        } catch (NoAlertPresentException e) {
        }
        assertFalse(alertPresent, "Алёрт не появится, так как поле Name пустое");
    }
}