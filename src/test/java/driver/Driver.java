package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Driver
{
    private static WebDriver driver;
    private static final String WEB_CHROME_DRIVER = "webdriver.chrome.driver";
    private static final String WEB_CHROME_DRIVER_EXE_PATH = "src/test/resources/drivers/chromedriver.exe";
    private static final String URL = "http://192.168.100.4:8080/login?from=%2F";

    private Driver()
    {

    }

    public static WebDriver getDriver()
    {
        if (driver == null)
        {
            System.setProperty(WEB_CHROME_DRIVER, WEB_CHROME_DRIVER_EXE_PATH);
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get(URL);
        }
        return driver;
    }

    public static void closeDriver()
    {
        driver.quit();
        driver = null;
    }

}
