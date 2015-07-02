package webdriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;

import pages.MainPage;

public class Driver {
	
	private static WebDriver driver;
	private static Logger log = Logger.getLogger(Driver.class);

	public static WebDriver get() {
		return driver;
	}
	
	public static void set(WebDriver driverInput) {
        driver = driverInput;
    }
	
	public static void init() throws MalformedURLException {
		Properties properties = new Properties();
        FileInputStream propFile;
        try {
            propFile = new FileInputStream("test.properties");
            properties.load(propFile);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        @SuppressWarnings("unchecked")
        Enumeration<String> e = (Enumeration<String>) properties.propertyNames();
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            System.setProperty(key, properties.getProperty(key));
            Reporter.log(key + " - " + properties.getProperty(key), 2, true);
        }
		WebDriver driverInput;
		switch(System.getProperty("test.browser")){
		
        case "firefox": 
        	driverInput = new FirefoxDriver();
        	log.info("Browser - "+System.getProperty("test.browser"));
         	break;
         	
        case "chrome" :
        	System.setProperty("webdriver.chrome.driver", "d:\\chromedriver.exe");
        	driverInput = new ChromeDriver();
        	log.info("Browser - "+System.getProperty("test.browser"));
         	break;
 
        default:
            log.error("Unsupported browser");
            throw new AssertionError("Unsupported browser ");
        }

        driverInput.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driverInput.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Driver.set(driverInput);
    }
	
    public static void tearDown() {
        Driver.get().quit();
        log.info("Quit from browser");
    }

}
