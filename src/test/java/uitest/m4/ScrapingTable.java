package uitest.m4;

import helper.DriverFactory;
import helper.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.List;

public class ScrapingTable {

    @Test
    public void getTableData(){
        WebDriver driver =new DriverFactory().newDriver();
        driver.get(Pages.SAVINGS);

        WebElement table = driver.findElement(By.id("rates"));
        System.out.println(table.getText());
        System.out.println("Filas de las tablas");
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        System.out.println("row1: "+rows.get(0).getText());
        System.out.println("row2: "+rows.get(1).getText());

        List<WebElement> columns = rows.get(1).findElements(By.tagName("td"));
        System.out.println("Columna 1: "+columns.get(0).getText());

        driver.quit();

    }

}
