package uitest.m8;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v116.network.Network;
import org.openqa.selenium.devtools.v116.network.model.Request;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Optional;

public class PracticeDevTools {
    protected WebDriver driver;
    protected DevTools tools;

    /*
    @Test
    public void practiceCapturingRequest() {
        //Crear una sección devtools en la pestaña principal
        driver = new ChromeDriver();

        tools = ((ChromeDriver) driver).getDevTools();
        driver.get("https://www.deepl.com/");
        driver.manage().window().maximize();
        tools.createSession();
        //habilitar red
        tools.send(org.openqa.selenium.devtools.v116.network.Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        //El oyente
        tools.addListener(Network.requestWillBeSent(),
                requestEvent -> {
                    Request r = requestEvent.getRequest();
                    System.out.printf("Url : %S , Method: %s \n", r.getUrl(), r.getMethod());
                }
        );

        tools.addListener(Network.responseReceived(),
                responseReceived -> {
                    Response r = responseReceived.getResponse();
                    System.out.printf("Response status :% \n", r.getStatus());
                    Assert.assertFalse(r.getStatus()<=400);
                }
        );
        driver.get("");
    }*/
}

