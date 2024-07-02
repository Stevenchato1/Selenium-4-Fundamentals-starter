package uitest.m6;

import helper.DemoHelper;
import helper.DriverFactory;
import helper.Pages;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.logging.Level;

public class ConfiguringDriver {
    @Test
    public void practiceWindowSize(){
        WebDriver driver = DriverFactory.newDriver();
        WebDriver.Window window = driver.manage().window();
        window.maximize();
        window.minimize();
        window.setSize(new Dimension(500, 500));
        window.setPosition(new Point(200, 200));
        driver.get(Pages.INDEX);
        DemoHelper.pause();
        driver.quit();
    }

    @Test
    public void practiceHeadlessMode(){
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        WebDriver driver = new FirefoxDriver(options);
        driver.get(Pages.INDEX);
        DemoHelper.pause();
        WebElement button = driver.findElement(By.id("register"));
        System.out.println(button.getText());
        driver.quit();
    }

    @Test
    public void handleAlertsByDefault(){
        FirefoxOptions options = new FirefoxOptions();
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS);
        //options.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
        WebDriver driver = new FirefoxDriver(options);
        driver.get(Pages.INDEX);
        driver.findElement(By.id("clear")).click();
        driver.findElement(By.id("register")).click();
        DemoHelper.pause();
    }

    /* Esto es una prueba de selenium para emular un mobile
    @Test
    public void deviceEmulation(){
        Map<String, String> mobileEmulation= Map.of("deviceName", "iPhone X");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("mobileEmulation="+mobileEmulation);
        WebDriver driver = new FirefoxDriver(options);
        driver.get(Pages.INDEX);
        DemoHelper.pause();
    }*/

    @Test
    public void practiceConsoleLogs(){
        //Se instancia el objeto LoginPreferences
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.BROWSER, Level.ALL);

        //Instanciar el driver
        EdgeOptions options = new EdgeOptions();
        options.setCapability(EdgeOptions.LOGGING_PREFS,logs);
        EdgeDriver driver =new  EdgeDriver(options);
        driver.get(Pages.INDEX);
        driver.findElement(By.id("register")).click();

        //Mostrar los logs
        LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
        Assert.assertFalse(browserLogs.getAll().isEmpty());
        browserLogs.forEach(System.out::println); //Este imprime la marca del tiempo del log
        browserLogs.forEach(logEntry -> System.out.println(logEntry.getLevel() + "" + logEntry.getMessage()));

        //Fallara la prueba, si encuentra error de estatus SEVERO
        browserLogs.forEach(logEntry -> checkNoError(logEntry));
    }

    private void checkNoError(LogEntry logEntry) {
        Assert.assertNotEquals(logEntry.getLevel().getName(),"SEVERE");
    }
}
