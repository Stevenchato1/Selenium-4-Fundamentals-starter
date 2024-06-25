package uitest.m6;

import helper.DriverFactory;
import helper.Pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class Waits {
    @Test
    public void practiceImplicitlyWait() {
        WebDriver driver = DriverFactory.newDriver();
        driver.get(Pages.LOANS);
        driver.findElement(By.id("borrow")).sendKeys("500");
        driver.findElement(By.id("result")).click();
        driver.quit();
    }

    @Test
    public void practiceExplicitlyWait(){
        WebDriver driver = DriverFactory.newDriver();
        driver.get(Pages.LOANS);
        driver.findElement(By.id("borrow")).sendKeys("500");

        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("period")));
        Assert.assertTrue(result.isDisplayed());
        result.click();
        driver.quit();
    }

    public static WebElement waitUntilClickable(WebDriver driver, By locator){
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(elementToBeClickable(locator));
    }

    @Test
    public void explicitWaitTestRefactored(){
        WebDriver driver = DriverFactory.newDriver();
        driver.get(Pages.LOANS);
        driver.findElement(By.id("borrow")).sendKeys("500");

        WebElement result = waitUntilClickable(driver, By.id("result"));
        Assert.assertTrue(result.isDisplayed());
    }

    @Test
    public void fluentWaitTest(){
        WebDriver driver  = DriverFactory.newDriver();
        driver.get(Pages.LOANS);
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(6))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);
        driver.findElement(By.id("borrow")).sendKeys("500");
        WebElement elemnt =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result")));
        elemnt.click();

    }

    @Test
    public void practiceNewWindowTab(){
        WebDriver driver = DriverFactory.newDriver();
        driver.get(Pages.INDEX);
        String firstTab = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(Pages.SAVINGS);
        Assert.assertEquals(driver.getWindowHandles().size(), 2);
    }
}
