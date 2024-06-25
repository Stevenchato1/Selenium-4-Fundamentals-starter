package uitest.m6;

import helper.DemoHelper;
import helper.DriverFactory;
import helper.Pages;
import org.openqa.selenium.*;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class ActionsAdvanced {

    @Test
    public void practiceWithAlerts(){
        WebDriver driver = DriverFactory.newDriver();
        driver.get(Pages.INDEX);
        DemoHelper.pause();

        WebElement firstname = driver.findElement(By.id("firstName"));
        firstname.sendKeys("John");
        DemoHelper.pause();

        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.sendKeys("Doe");

        WebElement buttonClear = driver.findElement(By.id("clear"));
        buttonClear.click();
        DemoHelper.pause();

        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        DemoHelper.pause();

        Assert.assertEquals(firstname.getAttribute("value"), "John");

    }

    @Test
    public void practiceUploadingFiles() throws IOException {
        WebDriver driver= DriverFactory.newDriver();
        driver.get(Pages.LOANS);
        //Se localiza el elemento
        WebElement fileUpload = driver.findElement(By.xpath("//input[@type='file']"));
        Path path = Files.createTempFile("test", ".txt");
        String fileName = path.toAbsolutePath().toString();
        System.out.println(fileName);

        fileUpload.sendKeys(fileName);
        DemoHelper.pause();
        driver.quit();
        path.toFile().deleteOnExit();
    }

    @Test
    public void storageTest(){
        WebDriver driver= DriverFactory.newDriver();
        driver.get(Pages.INDEX);
        var firstName = driver.findElement(By.id("firstName"));
        var lastName = driver.findElement(By.id("lastName"));
        var save = driver.findElement(By.id("save"));

        firstName.sendKeys("John");
        lastName.sendKeys("Doe");
        save.click();

        //Manage web storage
        WebStorage webStorage = (WebStorage) driver;
        SessionStorage storage = webStorage.getSessionStorage();
        storage.keySet().forEach(key -> System.out.println(key + " : " + storage.getItem(key)));
        //
        DemoHelper.pause();

        driver.get(Pages.SAVINGS);
        driver.navigate().back();
        DemoHelper.pause();

        var first_1 = driver.findElement(By.id("firstName"));
        var last_1 = driver.findElement(By.id("lastName"));
        DemoHelper.pause();

        Assert.assertEquals(first_1.getAttribute("value"), storage.getItem("firstName"));
        Assert.assertEquals(last_1.getAttribute("value"), storage.getItem("lastName"));

        storage.clear();
        driver.navigate().refresh();
        DemoHelper.pause();
        var first_2 = driver.findElement(By.id("firstName"));
        var last_2 = driver.findElement(By.id("lastName"));
        Assert.assertEquals(first_2.getAttribute("value"),"");
        Assert.assertEquals(last_2.getAttribute("value"),"");
        driver.quit();
    }

    @Test
    public void cookiesTest(){
        WebDriver driver = DriverFactory.newDriver();
        driver.get(Pages.INDEX);

        WebDriver.Options options= driver.manage();
        Set<Cookie> cookies = options.getCookies();
        Cookie thing= options.getCookieNamed("thing");
        options.deleteAllCookies();
    }

    @Test
    public void fullPageScreenshot() throws IOException {
        WebDriver driver = DriverFactory.newDriver();
        driver.get(Pages.INDEX);

        TakesScreenshot ts = (TakesScreenshot) driver;
        File screenshot = ts.getScreenshotAs(OutputType.FILE);
        Path destination = Paths.get("failure-screenshot.png");

        Files.move(screenshot.toPath(), destination,REPLACE_EXISTING);
    }
}
