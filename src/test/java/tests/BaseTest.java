package tests;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pages.MainPage;

import webdriver.Driver;

public class BaseTest {
	private Logger log = Logger.getLogger(BaseTest.class);
	
	 @BeforeMethod
	    public void init() throws MalformedURLException {
	        Driver.init();
	        log.info("Driver is initializated");
	    }

	 @AfterMethod
	    public void cleanup() {
	        Driver.tearDown();
	        log.info("Driver tear down");
	    }

}
