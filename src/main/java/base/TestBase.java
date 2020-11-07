package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utilities.TestUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static Properties prop;
    public static String userdir = System.getProperty("user.dir");

    //initialization constructor and load the properties file
    public TestBase() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(userdir + "/src/main/"
                    + "/resources/config/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * start the driver instance based on the browser mentioned
     * in the config.properties file.
     * Drivers for each browser are stored in resources/Drivers/
     * directory.
     *
     * @return return the webdriver instance for usage in the
     * execution of project
     */

    public static WebDriver initialization() {
        String browserName = prop.getProperty("browser");
        System.out.println(browserName + "-----" + userdir);
        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", (System.getProperty("user.dir") + prop.getProperty("chromedriver.path")));
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", (System.getProperty("user.dir") + prop.getProperty("geckodriver.path")));
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
        return driver;

    }

}
