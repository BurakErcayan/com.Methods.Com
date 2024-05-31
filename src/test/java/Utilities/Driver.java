package Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {
    /*
        Driver class'inin sadece getDriver() ile kullanilmasini saglamak,
        bu class'dan obje olusturulmasina engel olmak icin
        POM dizayninda Singleton Pattern kullanilir

        Singleton Pattern bir class'dan obje olusturulmasini engellemek icin
        class icerisinde istenen sartlara sahip constructor olusturup
        erisimi kapatmamiza dayanir
     */

    private Driver(){

    }

    static WebDriver driver;

    public static WebDriver getDriver() {

        if (driver == null) {
            switch (ConfigReader.getProperty("browser")) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    driver = new SafariDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();

            }


            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }
        return driver;
    }


    public static void closeDriver(){

        if (driver != null) {
            driver.close();
            driver = null;
        }
    }

    public static void quitDriver(){

        if (driver != null){
            driver.quit();
            driver = null;
        }
    }
}
