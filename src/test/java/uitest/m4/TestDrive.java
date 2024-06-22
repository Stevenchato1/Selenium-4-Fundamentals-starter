package uitest.m4;

import helper.DemoHelper;
import helper.DriverFactory;
import helper.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class TestDrive {



    @Test
    public void NavigateFactory() {
        //Se instancia el driver de la clase DriverFactory
     WebDriver driver = DriverFactory.newDriver();
        driver.get(Pages.INDEX);
        DemoHelper.pause();

        driver.get(Pages.SAVINGS);
        driver.navigate().back(); //Regresa a la página anterior

        WebElement inputName = driver.findElement(By.id("firstName"));
        System.out.println("The input field is displayed: " + inputName.isDisplayed());

        WebElement button = driver.findElement(By.id("register"));
        System.out.println("The button contains the text: " + button.getText());
        driver.quit();

        boolean value = driver instanceof FirefoxDriver;
        System.out.println("The driver is an instance of " + value);

    }

    @Test
    public void insertarDatos(){
        WebDriver driver = DriverFactory.newDriver();
        driver.get(Pages.INDEX);
        WebElement inputName = driver.findElement(By.id("firstName"));
        WebElement inputLastName = driver.findElement(By.id("lastName"));
        WebElement inputEmail = driver.findElement(By.id("email"));
        WebElement inputCalendar = driver.findElement(By.xpath("//input[@id='dob']"));

        inputName.sendKeys("John");
        inputLastName.sendKeys("Doe");
        inputEmail.sendKeys("huasupoma@gmail.com");
        inputEmail.clear();
        inputEmail.sendKeys("huasupoma@gmail.com");

        inputCalendar.sendKeys("22/03/2022");
        DemoHelper.pause();
        WebElement checkbox = driver.findElement(By.id("heard-about"));

        Actions actions=new Actions(driver);
        actions.doubleClick(checkbox).perform();

        DemoHelper.pause();
        driver.quit();
    }

    @Test
    public void selectCombox(){
        WebDriver driver = DriverFactory.newDriver();
        driver.get(Pages.LOANS);
        WebElement select= driver.findElement(By.id("period"));
        Select period = new Select(select);
        //period.selectByIndex(2);
        period.selectByVisibleText("2 months");
        Assert.assertTrue(select.isDisplayed());
        DemoHelper.pause();
        driver.quit();
    }

    @Test
    public void selectMultiplesElements(){
        WebDriver driver = DriverFactory.newDriver();
        driver.get(Pages.INDEX);
        driver.findElement(By.id("register")).click();
        List<WebElement> listaElementos;
        listaElementos = driver.findElements(By.className("invalid-feedback"));
        for (WebElement elemento: listaElementos){
            System.out.println(elemento.getText());
        }
    }

    @Test
    public void usingLinkText(){
        WebDriver driver = DriverFactory.newDriver();
        driver.get(Pages.INDEX);
        driver.findElement(By.linkText("Savings")).click();
        DemoHelper.pause();
        driver.findElement(By.partialLinkText("Loans")).click();
        DemoHelper.pause();
    }

    @Test
    public  void usingRelativeElements(){
        WebDriver driver = DriverFactory.newDriver();
        driver.get(Pages.INDEX);
        WebElement email = driver.findElement(By.id("email"));
        //Se usa para tener un elemento relativo a otro
        RelativeLocator.RelativeBy relativeBy = RelativeLocator.with(By.tagName("input"));
        //Se obtiene el elemento que está arriba del email
        WebElement datePicker= driver.findElement(relativeBy.above(email));
        System.out.println(datePicker.getAttribute("id"));


    }

}
